package br.com.fiap.delivery.resources;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.fiap.delivery.models.Delivery;
import br.com.fiap.delivery.models.TrackingStage;
import br.com.fiap.delivery.services.DeliveryService;

@RestController
@RequestMapping("/deliveries")
public class DeliveryResource {

  @Autowired
  private final DeliveryService deliveryService;

  public DeliveryResource(DeliveryService deliveryService) {
    this.deliveryService = deliveryService;
  }

  @PutMapping("/{id}/tracking-stage")
  public ResponseEntity<Delivery> updateTrackingStage(
      @PathVariable UUID id,
      @RequestBody TrackingStage stage) {
    Delivery updated = deliveryService.updateTrackingStage(id, stage);
    return ResponseEntity.ok(updated);
  }

  @GetMapping("/{id}/tracking-stage")
  public ResponseEntity<TrackingStage> showTrackingStage(
      @PathVariable UUID id) {
    TrackingStage trackingStage = deliveryService.showTrackingStage(id);
    return ResponseEntity.ok(trackingStage);
  }

  @GetMapping("/estimate-time")
  public ResponseEntity<Double> estimateDeliveryTime(
      @RequestParam String retailerLat,
      @RequestParam String retailerLon,
      @RequestParam String customerLat,
      @RequestParam String customerLon) {
    double estimate = deliveryService.estimateDeliveryTime(retailerLat, retailerLon, customerLat, customerLon);
    return ResponseEntity.ok(estimate);
  }
}