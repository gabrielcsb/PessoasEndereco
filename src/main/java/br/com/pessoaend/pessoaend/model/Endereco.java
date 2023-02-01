package br.com.pessoaend.pessoaend.model;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotEmpty;

@Entity
public class Endereco implements Serializable {
   
    @Id 
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    @NotEmpty
    private String logradouro;
    
    private String cep;
    
    private Integer numero;
    
    private String cidade;

    
   
    @ManyToOne
    @JoinColumn(name = "pessoa_codigo")
    private Pessoa pessoa; 

    private boolean enderecoPrincipal;
  
    
    
  
    public Pessoa getPessoa() {
        return pessoa;
    }
    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getLogradouro() {
        return logradouro;
    }
    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }
    public String getCep() {
        return cep;
    }
    public void setCep(String cep) {
        this.cep = cep;
    }
    public Integer getNumero() {
        return numero;
    }
    public void setNumero(Integer numero) {
        this.numero = numero;
    }
    public String getCidade() {
        return cidade;
    }
    public void setCidade(String cidade) {
        this.cidade = cidade;
    }
    public boolean isEnderecoPrincipal() {
        return enderecoPrincipal;
    }
    public void setEnderecoPrincipal(boolean enderecoPrincipal) {
        this.enderecoPrincipal = enderecoPrincipal;
    }
   
}
