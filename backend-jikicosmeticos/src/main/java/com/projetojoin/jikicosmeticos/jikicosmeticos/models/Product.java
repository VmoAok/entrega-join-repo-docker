package com.projetojoin.jikicosmeticos.jikicosmeticos.models;

import jakarta.persistence.*;

@Entity
@Table(name = "tb_jiki_estoque_produtos")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String produto;
    private String tipo_produto;
    private String categoria_produto;
    private String descricao_produto;
    private String estoque;

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getProduto() { return produto; }
    public void setProduto(String produto) { this.produto = produto; }

    public String getTipo_produto() { return tipo_produto; }
    public void setTipo_produto(String tipo_produto) { this.tipo_produto = tipo_produto; }

    public String getCategoria_produto() { return categoria_produto; }
    public void setCategoria_produto(String categoria_produto) { this.categoria_produto = categoria_produto; }

    public String getDescricao_produto() { return descricao_produto; }
    public void setDescricao_produto(String descricao_produto) { this.descricao_produto = descricao_produto; }

    public String getEstoque() { return estoque; }
    public void setEstoque(String estoque) { this.estoque = estoque; }
}