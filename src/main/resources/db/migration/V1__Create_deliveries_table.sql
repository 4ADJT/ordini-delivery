CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE deliveries (
  id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
  retailer_latitude VARCHAR(50) NOT NULL,
  retailer_longitude VARCHAR(50) NOT NULL,
  customer_latitude VARCHAR(50) NOT NULL,
  customer_longitude VARCHAR(50) NOT NULL,
  tracking_stage VARCHAR(50) NOT NULL,
  -- Enum armazenado como string
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
  updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL
);