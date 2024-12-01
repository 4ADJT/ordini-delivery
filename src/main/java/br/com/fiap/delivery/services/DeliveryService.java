package br.com.fiap.delivery.services;

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

  public double estimateDeliveryTime(String retailerLat, String retailerLon, String customerLat, String customerLon) {
    double latDiff = Math.abs(Double.parseDouble(retailerLat) - Double.parseDouble(customerLat));
    double lonDiff = Math.abs(Double.parseDouble(retailerLon) - Double.parseDouble(customerLon));
    return (latDiff + lonDiff) * 50; // Mock: cada grau equivale a 50 minutos.
  }
}