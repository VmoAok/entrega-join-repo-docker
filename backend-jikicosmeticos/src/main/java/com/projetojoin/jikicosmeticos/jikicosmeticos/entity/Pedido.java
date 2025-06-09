package com.projetojoin.jikicosmeticos.jikicosmeticos.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "tb_jiki_pedidos")
public class Pedido {

    @Id
    @Column(name = "id_pedido")
    private String idPedido;

    @ManyToOne
    @JoinColumn(name = "id_user")
    private Usuario usuario;

    @Column(name = "id_cosmetico")
    private String idCosmetico;

    @Column(name = "produto")
    private String produto;

    @Column(name = "tipo_produto")
    private String tipoProduto;

    @Column(name = "categoria_produto")
    private String categoriaProduto;

    @Column(name = "quantidade")
    private Integer quantidade;

    @Column(name = "status_pedido")
    private String statusPedido;

    @Column(name = "data_realizacao_pedido")
    private LocalDate dataRealizacaoPedido;

    @Column(name = "data_previsao_entrega")
    private LocalDate dataPrevisaoEntrega;

    // Getters e Setters

    public String getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(String idPedido) {
        this.idPedido = idPedido;
    }

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

    public String getCategoriaProduto() {
        return categoriaProduto;
    }

    public void setCategoriaProduto(String categoriaProduto) {
        this.categoriaProduto = categoriaProduto;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public String getStatusPedido() {
        return statusPedido;
    }

    public void setStatusPedido(String statusPedido) {
        this.statusPedido = statusPedido;
    }

    public LocalDate getDataRealizacaoPedido() {
        return dataRealizacaoPedido;
    }

    public void setDataRealizacaoPedido(LocalDate dataRealizacaoPedido) {
        this.dataRealizacaoPedido = dataRealizacaoPedido;
    }

    public LocalDate getDataPrevisaoEntrega() {
        return dataPrevisaoEntrega;
    }

    public void setDataPrevisaoEntrega(LocalDate dataPrevisaoEntrega) {
        this.dataPrevisaoEntrega = dataPrevisaoEntrega;
    }

    @Override
    public String toString() {
        return "Pedido{" +
                "idPedido='" + idPedido + '\'' +
                ", idCosmetico='" + idCosmetico + '\'' +
                ", produto='" + produto + '\'' +
                ", tipoProduto='" + tipoProduto + '\'' +
                ", categoriaProduto='" + categoriaProduto + '\'' +
                ", quantidade=" + quantidade +
                ", statusPedido='" + statusPedido + '\'' +
                ", dataRealizacaoPedido=" + dataRealizacaoPedido +
                ", dataPrevisaoEntrega=" + dataPrevisaoEntrega +
                '}';
    }

    public Usuario getIdUser() {
        return getIdUser();

    }
    public void setIdUser(Usuario idUser) {
        this.setIdUser(idUser);
    }

    public void setStatus(String status) {
        this.statusPedido = status;
        
    }
}