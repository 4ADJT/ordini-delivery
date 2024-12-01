package br.com.fiap.delivery.services;

import br.com.fiap.delivery.models.Carrier;
import br.com.fiap.delivery.repositories.CarrierRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CarrierService {

  @Autowired
  private final CarrierRepository carrierRepository;

  public CarrierService(CarrierRepository carrierRepository) {
    this.carrierRepository = carrierRepository;
  }

  public List<Carrier> findAll() {
    return carrierRepository.findAll();
  }

  public Carrier findById(UUID id) {
    return carrierRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Carrier not found"));
  }

  public Carrier save(Carrier carrier) {
    return carrierRepository.save(carrier);
  }

  public Carrier update(UUID id, Carrier updatedCarrier) {
    Carrier carrier = findById(id);
    carrier.setName(updatedCarrier.getName());
    carrier.setPhone(updatedCarrier.getPhone());
    carrier.setEmail(updatedCarrier.getEmail());
    carrier.setAddress(updatedCarrier.getAddress());
    return carrierRepository.save(carrier);
  }

  public void delete(UUID id) {
    carrierRepository.deleteById(id);
  }
}
