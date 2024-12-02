package br.com.fiap.delivery.resources;

import br.com.fiap.delivery.models.Carrier;
import br.com.fiap.delivery.services.CarrierService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Tag(name = "Carriers")
@RestController
@RequestMapping("/carriers")
public class CarrierResources {

  @Autowired
  private final CarrierService carrierService;

  public CarrierResources(CarrierService carrierService) {
    this.carrierService = carrierService;
  }

  @GetMapping
  @Operation(summary = "List carriers", description = "List carriers.")
  public ResponseEntity<List<Carrier>> getAllCarriers() {
    return ResponseEntity.ok(carrierService.findAll());
  }

  @GetMapping("/{id}")
  @Operation(summary = "Find carrier by id", description = "Find carrier by id.")
  public ResponseEntity<Carrier> getCarrierById(@PathVariable UUID id) {
    return ResponseEntity.ok(carrierService.findById(id));
  }

  @PostMapping
  @Operation(summary = "Create carrier", description = "Create carrier.")
  public ResponseEntity<Carrier> createCarrier(@RequestBody Carrier carrier) {
    return ResponseEntity.ok(carrierService.save(carrier));
  }

  @PutMapping("/{id}")
  @Operation(summary = "Update carrier", description = "Update carrier.")
  public ResponseEntity<Carrier> updateCarrier(@PathVariable UUID id, @RequestBody Carrier carrier) {
    return ResponseEntity.ok(carrierService.update(id, carrier));
  }

  @DeleteMapping("/{id}")
  @Operation(summary = "Delete carrier", description = "Delete carrier.")
  public ResponseEntity<Void> deleteCarrier(@PathVariable UUID id) {
    carrierService.delete(id);
    return ResponseEntity.noContent().build();
  }
}
