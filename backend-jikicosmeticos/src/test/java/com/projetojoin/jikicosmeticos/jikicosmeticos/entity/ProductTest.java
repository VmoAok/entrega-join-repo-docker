package com.projetojoin.jikicosmeticos.jikicosmeticos.entity;

import org.junit.jupiter.api.Test;

import com.projetojoin.jikicosmeticos.jikicosmeticos.entity.Product;

import static org.junit.jupiter.api.Assertions.*;

class ProductTest {

    @Test
    void testSetAndGetId() {
        Product product = new Product();
        product.setId(10L);
        assertEquals(10L, product.getId());
    }

    @Test
    void testSetAndGetProduto() {
        Product product = new Product();
        product.setProduto("Shampoo");
        assertEquals("Shampoo", product.getProduto());
    }

    @Test
    void testSetAndGetTipoProduto() {
        Product product = new Product();
        product.setTipo_produto("Cabelo");
        assertEquals("Cabelo", product.getTipo_produto());
    }

    @Test
    void testSetAndGetCategoriaProduto() {
        Product product = new Product();
        product.setCategoria_produto("Hidratação");
        assertEquals("Hidratação", product.getCategoria_produto());
    }

    @Test
    void testSetAndGetDescricaoProduto() {
        Product product = new Product();
        product.setDescricao_produto("Shampoo para cabelos secos");
        assertEquals("Shampoo para cabelos secos", product.getDescricao_produto());
    }

    @Test
    void testSetAndGetEstoque() {
        Product product = new Product();
        product.setEstoque("15");
        assertEquals("15", product.getEstoque());
    }
}