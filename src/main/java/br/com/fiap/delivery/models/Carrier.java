package br.com.fiap.delivery.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Data
@Entity
@Table(name = "carriers")
public class Carrier {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  @Column(nullable = false, unique = true)
  private String name;

  @Column(nullable = false)
  private String phone;

  @Column(nullable = false, unique = true)
  private String email;

  @Column(nullable = false)
  private String address;
}
