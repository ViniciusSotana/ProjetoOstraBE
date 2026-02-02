package dev.projetoostra.projetoostra.product.jewelryType;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface JewelryTypeRepository extends JpaRepository<JewelryType, UUID> {
}
