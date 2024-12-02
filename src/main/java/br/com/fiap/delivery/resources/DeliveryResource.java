package br.com.fiap.delivery.resources;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.fiap.delivery.models.Delivery;
import br.com.fiap.delivery.models.TrackingStage;
import br.com.fiap.delivery.services.DeliveryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Deliveries")
@RestController
@RequestMapping("/deliveries")
public class DeliveryResource {

  @Autowired
  private final DeliveryService deliveryService;

  public DeliveryResource(DeliveryService deliveryService) {
    this.deliveryService = deliveryService;
  }

  @GetMapping
  @Operation(summary = "List deliveries", description = "List deliveries.")
  public ResponseEntity<List<Delivery>> getAllDeliveries() {
    return ResponseEntity.ok(deliveryService.findAll());
  }

  @GetMapping("/{id}")
  @Operation(summary = "Find delivery by id", description = "Find delivery by id.")
  public ResponseEntity<Delivery> getDeliveryById(@PathVariable UUID id) {
    return ResponseEntity.ok(deliveryService.findById(id));
  }

  @PostMapping
  @Operation(summary = "Create delivery", description = "Create delivery.")
  public ResponseEntity<Delivery> createDelivery(@RequestBody Delivery delivery) {
    return ResponseEntity.ok(deliveryService.save(delivery));
  }

  @PutMapping("/{id}")
  @Operation(summary = "Update delivery", description = "Update delivery.")
  public ResponseEntity<Delivery> updateDelivery(@PathVariable UUID id, @RequestBody Delivery delivery) {
    return ResponseEntity.ok(deliveryService.update(id, delivery));
  }

  @DeleteMapping("/{id}")
  @Operation(summary = "Delete delivery", description = "Delete delivery.")
  public ResponseEntity<Void> deleteDelivery(@PathVariable UUID id) {
    deliveryService.delete(id);
    return ResponseEntity.noContent().build();
  }

  @PutMapping("/{id}/tracking-stage")
  @Operation(summary = "Update tracking stage", description = "Update tracking stage.")
  public ResponseEntity<Delivery> updateTrackingStage(
      @PathVariable UUID id,
      @RequestBody TrackingStage stage) {
    Delivery updated = deliveryService.updateTrackingStage(id, stage);
    return ResponseEntity.ok(updated);
  }

  @GetMapping("/{id}/tracking-stage")
  @Operation(summary = "Show tracking stage", description = "Show tracking stage.")
  public ResponseEntity<TrackingStage> showTrackingStage(
      @PathVariable UUID id) {
    TrackingStage trackingStage = deliveryService.showTrackingStage(id);
    return ResponseEntity.ok(trackingStage);
  }

  @GetMapping("/{id}/estimate-time")
  @Operation(summary = "Displays estimated delivery time (in days)", description = "Displays estimated delivery time (in days).")
  public ResponseEntity<Integer> estimateDeliveryTime(
      @PathVariable UUID id) {
    return ResponseEntity.ok(deliveryService.estimateDeliveryTimeInDays(id));
  }
}