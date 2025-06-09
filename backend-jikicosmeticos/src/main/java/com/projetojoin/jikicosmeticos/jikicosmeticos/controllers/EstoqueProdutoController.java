package com.projetojoin.jikicosmeticos.jikicosmeticos.controllers;
import com.projetojoin.jikicosmeticos.jikicosmeticos.models.Product;
import com.projetojoin.jikicosmeticos.jikicosmeticos.repository.EstoqueProdutoRepository;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;


@RestController
@RequestMapping("/estoque")
public class EstoqueProdutoController {

    private final EstoqueProdutoRepository repository;

    public EstoqueProdutoController(EstoqueProdutoRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Product> listarTodos() {
        return repository.findAll();
    }
}