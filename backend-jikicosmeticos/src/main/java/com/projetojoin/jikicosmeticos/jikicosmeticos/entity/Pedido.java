package com.projetojoin.jikicosmeticos.jikicosmeticos.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_jiki_pedidos")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pedido")
    private Long idPedido;

    @ManyToOne
    @JoinColumn(name = "id_user")
    private Usuario usuario;

    @Column(name = "id_cosmetico")
    private Long idCosmetico;

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

    public void setIdUser(Usuario usuario) {
        this.usuario = usuario;
    }

    public Usuario getIdUser() {
        return this.usuario;
    }
}
