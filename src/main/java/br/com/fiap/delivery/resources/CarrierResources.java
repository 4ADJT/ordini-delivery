package br.com.fiap.delivery.resources;

import br.com.fiap.delivery.models.Carrier;
import br.com.fiap.delivery.services.CarrierService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/carriers")
public class CarrierResources {

  @Autowired
  private final CarrierService carrierService;

  public CarrierResources(CarrierService carrierService) {
    this.carrierService = carrierService;
  }

  @GetMapping
  public ResponseEntity<List<Carrier>> getAllCarriers() {
    return ResponseEntity.ok(carrierService.findAll());
  }

  @GetMapping("/{id}")
  public ResponseEntity<Carrier> getCarrierById(@PathVariable UUID id) {
    return ResponseEntity.ok(carrierService.findById(id));
  }

  @PostMapping
  public ResponseEntity<Carrier> createCarrier(@RequestBody Carrier carrier) {
    return ResponseEntity.ok(carrierService.save(carrier));
  }

  @PutMapping("/{id}")
  public ResponseEntity<Carrier> updateCarrier(@PathVariable UUID id, @RequestBody Carrier carrier) {
    return ResponseEntity.ok(carrierService.update(id, carrier));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteCarrier(@PathVariable UUID id) {
    carrierService.delete(id);
    return ResponseEntity.noContent().build();
  }
}
