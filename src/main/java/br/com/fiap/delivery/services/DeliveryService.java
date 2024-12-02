package br.com.fiap.delivery.services;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fiap.delivery.models.Delivery;
import br.com.fiap.delivery.models.TrackingStage;
import br.com.fiap.delivery.repositories.DeliveryRepository;

@Service
public class DeliveryService {

  @Autowired
  private final DeliveryRepository deliveryRepository;

  public DeliveryService(DeliveryRepository deliveryRepository) {
    this.deliveryRepository = deliveryRepository;
  }

  public List<Delivery> findAll() {
    return deliveryRepository.findAll();
  }

  public Delivery findById(UUID id) {
    return deliveryRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Delivery not found"));
  }

  public Delivery save(Delivery delivery) {
    return deliveryRepository.save(delivery);
  }

  public Delivery update(UUID id, Delivery updatedDelivery) {
    Delivery delivery = findById(id);
    
    delivery.setCarrier(updatedDelivery.getCarrier());
    delivery.setRetailerLatitude(updatedDelivery.getRetailerLatitude());
    delivery.setRetailerLongitude(updatedDelivery.getRetailerLongitude());
    delivery.setCustomerLatitude(updatedDelivery.getCustomerLatitude());
    delivery.setCustomerLongitude(updatedDelivery.getCustomerLongitude());
    delivery.setTrackingStage(updatedDelivery.getTrackingStage());

    return deliveryRepository.save(delivery);
  }

  public void delete(UUID id) {
    deliveryRepository.deleteById(id);
  }

  public Delivery updateTrackingStage(UUID deliveryId, TrackingStage stage) {
    Delivery delivery = deliveryRepository.findById(deliveryId)
        .orElseThrow(() -> new IllegalArgumentException("Entrega não encontrada"));
    delivery.setTrackingStage(stage);
    return deliveryRepository.save(delivery);
  }

  public TrackingStage showTrackingStage(UUID deliveryId) {
    Delivery delivery = deliveryRepository.findById(deliveryId)
        .orElseThrow(() -> new IllegalArgumentException("Entrega não encontrada"));
    return delivery.getTrackingStage();
  }

  public Integer estimateDeliveryTimeInDays(UUID id) {
    final double AVERAGE_TRUCK_SPEED_KMH = 60.0; // Velocidade média do caminhão
    final double EARTH_RADIUS_KM = 6371.0; // Raio médio da Terra em quilômetros

    Delivery delivery = findById(id);
    double retailerLat = Math.toRadians(Double.parseDouble(delivery.getRetailerLatitude()));
    double retailerLon = Math.toRadians(Double.parseDouble(delivery.getRetailerLongitude()));
    double customerLat = Math.toRadians(Double.parseDouble(delivery.getCustomerLatitude()));
    double customerLon = Math.toRadians(Double.parseDouble(delivery.getCustomerLongitude()));

    // Fórmula de Haversine
    double latDiff = customerLat - retailerLat;
    double lonDiff = customerLon - retailerLon;
    double a = Math.sin(latDiff / 2) * Math.sin(latDiff / 2)
        + Math.cos(retailerLat) * Math.cos(customerLat)
            * Math.sin(lonDiff / 2) * Math.sin(lonDiff / 2);
    double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
    double distanceInKm = EARTH_RADIUS_KM * c;

    // Calcular o tempo em horas e converter para dias
    double timeInHours = distanceInKm / AVERAGE_TRUCK_SPEED_KMH;
    int estimatedDays = (int) Math.ceil(timeInHours / 24.0); // Arredonda para cima
    return estimatedDays;
  }
}