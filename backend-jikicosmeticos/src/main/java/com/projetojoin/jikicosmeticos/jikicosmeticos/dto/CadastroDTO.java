package com.projetojoin.jikicosmeticos.jikicosmeticos.dto;

public class CadastroDTO {
    private String email;
    private String nome;
    private String cpf;
    private String cep;
    private String cidade;
    private String estado;
    private String bairro;
    private String endereco;
    private String password;
    private String telefone;


    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getCpf() {
        return cpf;
    }
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
    public String getCep() {
        return cep;
    }
    public void setCep(String cep) {
        this.cep = cep;
    }
    public String getCidade() {
        return cidade;
    }
    public void setCidade(String cidade) {
        this.cidade = cidade;
    }
    public String getEstado() {
        return estado;
    }
    public void setEstado(String estado) {
        this.estado = estado;
    }
    public String getBairro() {
        return bairro;
    }
    public void setBairro(String bairro) {
        this.bairro = bairro;
    }
    public String getEndereco() {
        return endereco;
    }
    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getTelefone() {
        return telefone;
    }
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public CadastroDTO(String email, String nome, String cpf, String cep, String cidade, String estado, String bairro, String endereco, String password, String telefone) {
        this.email = email;
        this.nome = nome;
        this.cpf = cpf;
        this.cep = cep;
        this.cidade = cidade;
        this.estado = estado;
        this.bairro = bairro;
        this.endereco = endereco;
        this.password = password;
        this.telefone = telefone;
    }

    public CadastroDTO() {
        // lembrar de ajustar construtor para a serialização/deserialização do Kafka
    }
    @Override
    public String toString() {
        return "CadastroDTO{" +
                "email='" + email + '\'' +
                ", nome='" + nome + '\'' +
                ", cpf='" + cpf + '\'' +
                ", cep='" + cep + '\'' +
                ", cidade='" + cidade + '\'' +
                ", estado='" + estado + '\'' +
                ", bairro='" + bairro + '\'' +
                ", endereco='" + endereco + '\'' +
                ", password='" + password + '\'' +
                ", telefone='" + telefone + '\'' +
                '}';
    }
} 
