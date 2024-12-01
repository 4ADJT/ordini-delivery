package br.com.fiap.delivery.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Data
@Entity
@Table(name = "deliveries")
public class Delivery {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  private String retailerLatitude;
  private String retailerLongitude;

  private String customerLatitude;
  private String customerLongitude;

  @Enumerated(EnumType.STRING)
  private TrackingStage trackingStage;

  @ManyToOne
  @JoinColumn(name = "carrier_id", nullable = false)
  private Carrier carrier; // Associação com a transportadora
}