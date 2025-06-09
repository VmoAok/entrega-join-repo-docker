package com.projetojoin.jikicosmeticos.jikicosmeticos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projetojoin.jikicosmeticos.jikicosmeticos.models.Product;

public interface EstoqueProdutoRepository extends JpaRepository<Product, Long> {
}