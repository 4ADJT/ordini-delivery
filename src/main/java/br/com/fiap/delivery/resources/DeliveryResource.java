package br.com.fiap.delivery.resources;

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

  @GetMapping("/estimate-time")
  @Operation(summary = "Displays estimated delivery time (in days)", description = "Displays estimated delivery time (in days).")
  public ResponseEntity<Double> estimateDeliveryTime(
      @RequestParam String retailerLat,
      @RequestParam String retailerLon,
      @RequestParam String customerLat,
      @RequestParam String customerLon) {
    double estimate = deliveryService.estimateDeliveryTime(retailerLat, retailerLon, customerLat, customerLon);
    return ResponseEntity.ok(estimate);
  }
}