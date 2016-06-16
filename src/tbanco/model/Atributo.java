/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tbanco.model;

/**
 *
 * @author mfernandes
 */
public class Atributo {

    private String nome, source = null, tipo = null;

    private boolean nao_nulo = false, chave_primaria = false, chave_estrangeira = false;

    public Atributo(String nome) {
        this.nome = nome;
    }

    public Atributo(String nome, String source) {
        this.nome = nome;

        if (source != null) {
            this.source = source = source.toUpperCase();

            if (source.contains("N")) {
                nao_nulo = true;
            }
            if (source.contains("#")) {
                chave_estrangeira = true;
            }
            if (source.contains("*")) {
                chave_primaria = true;
            }
        }
    }

    public Atributo(String nome, String source, String tipo) {
        this(nome, source);
        this.tipo = tipo;
    }

    public String getNome() {
        return nome;
    }

    public boolean isNao_nulo() {
        return nao_nulo;
    }

    public boolean isChave_primaria() {
        return chave_primaria;
    }

    public boolean isChave_estrangeira() {
        return chave_estrangeira;
    }

    public String getTipo() {
        return tipo;
    }

    public String getSource() {
        return source;
    }

    @Override
    public String toString() {
        return "Atributo{" + "nome=" + nome + ", source=" + source + ", tipo=" + tipo + '}';
    }

}
