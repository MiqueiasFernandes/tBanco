/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tbanco.model;

import tbanco.model.relacionamento.AbstractRelacionavel;
import java.util.Iterator;

/**
 *
 * @author mfernandes
 */
public class Entidade extends AbstractRelacionavel {

    private String nome, tipo = null;

    public Entidade(String nome) {
        this.nome = nome;
    }

    public Entidade(String nome, String tipo) {
        this.nome = nome;
        this.tipo = tipo;
    }

    public String getNome() {
        return nome;
    }

    public Entidade getSuperTipo(Iterator<Entidade> entidades) {
        if (tipo != null) {
            while (entidades.hasNext()) {
                Entidade next = entidades.next();
                if (next.getNome().equalsIgnoreCase(tipo)) {
                    return next;
                }
            }
        }
        return null;
    }

    @Override
    public boolean equals(AbstractRelacionavel other) {
        if (!other.isEntidade()) {
            return false;
        }

        return this == other;
    }

    @Override
    public boolean isEntidade() {
        return true;
    }

    @Override
    public boolean isAgregacao() {
        return false;
    }
}
