package br.com.fiap.delivery.models;

public enum TrackingStage {
  TRANSPORT_TO_RETAILER("A transportadora está indo em direção ao varejista"),
  RETAILER_COLLECTED("A transportadora coletou seu produto"),
  TRANSPORT_TO_CITY("A transportadora está indo em direção à sua cidade"),
  OUT_FOR_DELIVERY("Seu produto está em rota de entrega");

  private final String description;

  TrackingStage(String description) {
    this.description = description;
  }

  public String getDescription() {
    return description;
  }
}