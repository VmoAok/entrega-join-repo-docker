/*  tabela de estoque: tb_jiki_estoque_produtos
    id_cosmetico
    produto
    tipo_produto
    categoria_produto
    descricao_produto
    quantidade */

package com.projetojoin.jikicosmeticos.jikicosmeticos.entity;
import jakarta.persistence.*;

@Entity
@Table(name = "tb_jiki_estoque_produtos")
public class Estoque {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private String idCosmetico;

    private String produto;

    @Column(name = "tipo_produto")
    private String tipoProduto;

    @Column(name = "categoria_produto_estoque")
    private String categoriaProduto;

    @Column(name = "descricao_produto")
    private String descricaoProduto;

    private String quantidade;

    public String getIdCosmetico() {
        return idCosmetico;
    }
    public void setIdCosmetico(String idCosmetico) {
        this.idCosmetico = idCosmetico;
    }
    public String getProduto() {
        return produto;
    }
    public void setProduto(String produto) {
        this.produto = produto;
    }
    public String getTipoProduto() {
        return tipoProduto;
    }
    public void setTipoProduto(String tipoProduto) {
        this.tipoProduto = tipoProduto;
    }
    public String setCategoriaProduto() {
        return categoriaProduto;
    }
    public void setCategoriaProduto(String categoriaProduto) {
        this.categoriaProduto = categoriaProduto;
    }
    public String getDescricaoProduto() {
        return descricaoProduto;
    }
    public void setDescricaoProduto(String descricaoProduto) {
        this.descricaoProduto = descricaoProduto;
    }
    public String getQuantidade() {
        return quantidade;
    }
    public void setQuantidade(String quantidade) {
        this.quantidade = quantidade;
    }
}