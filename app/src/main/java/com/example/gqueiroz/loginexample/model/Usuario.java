package com.example.gqueiroz.loginexample.model;

import java.sql.Date;

/**
 * Created by gabrielqueiroz on 4/28/16.
 */
public class Usuario {

    private String nome;
    private String sobreNome;
    private Date dataNasc;
    private String email;
    private String senha;

    public Usuario(){}

    public Usuario(String nome, String sobreNome, Date dataNasc, String email, String senha) {
        this.nome = nome;
        this.sobreNome = sobreNome;
        this.dataNasc = dataNasc;
        this.email = email;
        this.senha = senha;
    }

    public Usuario nome(String nome){
        this.nome = nome;
        return this;
    }

    public Usuario sobreNome(String sobreNome){
        this.sobreNome = sobreNome;
        return this;
    }

    public Usuario dataNasc(Date dataNasc){
        this.dataNasc = dataNasc;
        return this;
    }
    
    public Usuario email(String email){
        this.email = email;
        return this;
    }

    public Usuario senha(String senha){
        this.senha = senha;
        return this;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobreNome() {
        return sobreNome;
    }

    public void setSobreNome(String sobreNome) {
        this.sobreNome = sobreNome;
    }

    public Date getDataNasc() {
        return dataNasc;
    }

    public void setDataNasc(Date dataNasc) {
        this.dataNasc = dataNasc;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "nome='" + nome + '\'' +
                ", sobreNome='" + sobreNome + '\'' +
                ", dataNasc=" + dataNasc +
                ", email='" + email + '\'' +
                ", senha='" + senha + '\'' +
                '}';
    }
}
