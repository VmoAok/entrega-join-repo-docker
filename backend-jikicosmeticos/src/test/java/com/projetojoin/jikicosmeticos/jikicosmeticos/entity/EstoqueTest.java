package com.projetojoin.jikicosmeticos.jikicosmeticos.entity;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class EstoqueTest {

    @Test
    void testSetAndGetIdCosmetico() {
        Estoque estoque = new Estoque();
        estoque.setIdCosmetico (1L);
        assertEquals(1L, estoque.getIdCosmetico());
    }

    @Test
    void testSetAndGetProduto() {
        Estoque estoque = new Estoque();
        estoque.setProduto("Shampoo");
        assertEquals("Shampoo", estoque.getProduto());
    }

    @Test
    void testSetAndGetTipoProduto() {
        Estoque estoque = new Estoque();
        estoque.setTipoProduto("Cabelo");
        assertEquals("Cabelo", estoque.getTipoProduto());
    }

    @Test
    void testSetAndGetCategoriaProduto() {
        Estoque estoque = new Estoque();
        estoque.setCategoriaProduto("Hidratação");
        // O método get está nomeado errado na classe original: deveria ser getCategoriaProduto()
        // Está como setCategoriaProduto(), então o teste chama setCategoriaProduto() para obter o valor
        assertEquals("Hidratação", estoque.setCategoriaProduto());
    }

    @Test
    void testSetAndGetDescricaoProduto() {
        Estoque estoque = new Estoque();
        estoque.setDescricaoProduto("Shampoo para cabelos secos");
        assertEquals("Shampoo para cabelos secos", estoque.getDescricaoProduto());
    }

    @Test
    void testSetAndGetQuantidade() {
        Estoque estoque = new Estoque();
        estoque.setQuantidade("10");
        assertEquals("10", estoque.getQuantidade());
    }
}