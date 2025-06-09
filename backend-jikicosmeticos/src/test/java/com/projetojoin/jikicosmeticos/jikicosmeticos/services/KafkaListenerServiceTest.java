package com.projetojoin.jikicosmeticos.jikicosmeticos.services;

import com.projetojoin.jikicosmeticos.jikicosmeticos.controllers.CadastroUsuarioController;
import com.projetojoin.jikicosmeticos.jikicosmeticos.dto.CadastroDTO;
import com.projetojoin.jikicosmeticos.jikicosmeticos.entity.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaListenerService {

    @Autowired
    private CadastroUsuarioController cadastroUsuario;

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    @KafkaListener(topics = "cadastro-usuario", groupId = "grupo-cadastro")
    public void listen(CadastroDTO cadastroDTO) {
        // Converte o DTO para a entidade Usuario
        Usuario usuario = new Usuario();
        usuario.setNome(cadastroDTO.getNome());
        usuario.setEmail(cadastroDTO.getEmail());
        usuario.setPassword(cadastroDTO.getPassword());

        // Chama o serviço de cadastro
        cadastroUsuario.cadastrarUsuario(usuario);

        // Envia uma mensagem de confirmação para outro tópico, se necessário
        kafkaTemplate.send("confirmacao-cadastro", "Usuário cadastrado com sucesso: " + usuario.getEmail());
    }

    public void enviarCadastro(CadastroDTO cadastroDTO) {
        // Envia o DTO para o tópico Kafka
        kafkaTemplate.send("cadastro-usuario", cadastroDTO);
    }
    public void enviarConfirmacao(String mensagem) {
        kafkaTemplate.send("confirmacao-cadastro", mensagem);
    }
    public void enviarErro(String mensagem) {
        kafkaTemplate.send("erro-cadastro", mensagem);
    }
    public void enviarAtualizacao(String mensagem) {
        kafkaTemplate.send("atualizacao-cadastro", mensagem);
    }
    public void enviarExclusao(String mensagem) {
        kafkaTemplate.send("exclusao-cadastro", mensagem);
    }
}