package br.com.fiap.delivery.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.delivery.models.Delivery;

public interface DeliveryRepository extends JpaRepository<Delivery, UUID> {
}