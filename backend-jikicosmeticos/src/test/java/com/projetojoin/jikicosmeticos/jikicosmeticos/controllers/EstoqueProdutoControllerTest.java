package com.projetojoin.jikicosmeticos.jikicosmeticos.controllers;

import com.projetojoin.jikicosmeticos.jikicosmeticos.models.Product;
import com.projetojoin.jikicosmeticos.jikicosmeticos.repository.EstoqueProdutoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class EstoqueProdutoControllerTest {

    @InjectMocks
    private EstoqueProdutoController estoqueProdutoController;

    @Mock
    private EstoqueProdutoRepository estoqueProdutoRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void listarTodos_ShouldReturnListOfProducts() {
        // Arrange
        Product product1 = new Product();
        product1.setId(1L);
        product1.setProduto("Product 1");

        Product product2 = new Product();
        product2.setId(2L);
        product2.setProduto("Product 2");

        List<Product> productList = Arrays.asList(product1, product2);

        when(estoqueProdutoRepository.findAll()).thenReturn(productList);

        // Act
        List<Product> result = estoqueProdutoController.listarTodos();

        // Assert
        assertEquals(2, result.size(), "Should return a list of 2 products");
        assertEquals("Product 1", result.get(0).getProduto(), "First product name should match");
        assertEquals("Product 2", result.get(1).getProduto(), "Second product name should match");
    }
}