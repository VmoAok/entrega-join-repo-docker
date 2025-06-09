package com.projetojoin.jikicosmeticos.jikicosmeticos.repository;

import com.projetojoin.jikicosmeticos.jikicosmeticos.models.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class EstoqueProdutoRepositoryTest {

    @Autowired
    private EstoqueProdutoRepository repository;

    @Test
    void testSaveAndFindById() {
        Product product = new Product();
        product.setProduto("Shampoo");
        product.setTipo_produto("Cabelo");
        product.setCategoria_produto("Hidratação");
        product.setDescricao_produto("Shampoo para cabelos secos");
        product.setEstoque("15");

        Product saved = repository.save(product);
        Optional<Product> found = repository.findById(saved.getId());

        assertTrue(found.isPresent());
        assertEquals("Shampoo", found.get().getProduto());
        assertEquals("Cabelo", found.get().getTipo_produto());
        assertEquals("Hidratação", found.get().getCategoria_produto());
        assertEquals("Shampoo para cabelos secos", found.get().getDescricao_produto());
        assertEquals("15", found.get().getEstoque());
    }

    @Test
    void testDeleteById() {
        Product product = new Product();
        product.setProduto("Condicionador");
        product.setTipo_produto("Cabelo");
        product.setCategoria_produto("Nutrição");
        product.setDescricao_produto("Condicionador para cabelos secos");
        product.setEstoque("10");

        Product saved = repository.save(product);
        Long id = saved.getId();

        repository.deleteById(id);

        Optional<Product> found = repository.findById(id);
        assertFalse(found.isPresent());
    }

    @Test
    void testUpdateProduct() {
        Product product = new Product();
        product.setProduto("Máscara");
        product.setTipo_produto("Cabelo");
        product.setCategoria_produto("Reconstrução");
        product.setDescricao_produto("Máscara capilar");
        product.setEstoque("5");

        Product saved = repository.save(product);
        saved.setProduto("Máscara Atualizada");
        saved.setEstoque("8");

        Product updated = repository.save(saved);

        Optional<Product> found = repository.findById(updated.getId());
        assertTrue(found.isPresent());
        assertEquals("Máscara Atualizada", found.get().getProduto());
        assertEquals("8", found.get().getEstoque());
    }

    @Test
    void testFindAll() {
        Product product1 = new Product();
        product1.setProduto("Produto 1");
        product1.setTipo_produto("Tipo 1");
        product1.setCategoria_produto("Categoria 1");
        product1.setDescricao_produto("Descricao 1");
        product1.setEstoque("3");

        Product product2 = new Product();
        product2.setProduto("Produto 2");
        product2.setTipo_produto("Tipo 2");
        product2.setCategoria_produto("Categoria 2");
        product2.setDescricao_produto("Descricao 2");
        product2.setEstoque("7");

        repository.save(product1);
        repository.save(product2);

        Iterable<Product> allProducts = repository.findAll();
        assertTrue(allProducts.iterator().hasNext());
    }
}