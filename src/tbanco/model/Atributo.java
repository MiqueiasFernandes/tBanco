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

    private String nome, source;

    private boolean nao_nulo, chave_primaria, chave_estrangeira;

    public Atributo(String nome, String source) {
        this.nome = nome;
        this.source = source;

        if (source.contains("n") || source.contains("N")) {
            nao_nulo = true;
        }
        if (source.contains("#")) {
            chave_estrangeira = true;
        }
        if (source.contains("*")) {
            chave_primaria = true;
        }
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
}
