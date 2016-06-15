/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tbanco.model;

import java.util.ArrayList;
import java.util.Iterator;
import tbanco.model.relacionamento.AbstractRelacionamento;

/**
 *
 * @author mfernandes
 */
public class ModeloEntidadeRelacionamento {

    private ArrayList<Agregacao> agregacoes;
    private ArrayList<Entidade> entidades;

    String nome;

    public ModeloEntidadeRelacionamento(String nome) {
        this.nome = nome;
        agregacoes = new ArrayList<>();
        entidades = new ArrayList<>();
    }

    void addAgregacao(Agregacao agregacao) {
        agregacoes.add(agregacao);
    }

    void addEntidade(Entidade entidade) {
        entidades.add(entidade);
    }

    public Iterator<Entidade> getEntidadesIterator() {
        return entidades.iterator();
    }

    public Iterator<Agregacao> getAgregacoesIterator() {
        return agregacoes.iterator();
    }

    ArrayList<Entidade> getTodasEntidades() {
        ArrayList<Entidade> tEnt = new ArrayList<>(entidades);

        Iterator<Agregacao> it = getAgregacoesIterator();

        while (it.hasNext()) {
            Agregacao next = it.next();
            next.addAllEntidades(tEnt);
        }

        return tEnt;
    }

    ArrayList<AbstractRelacionamento> getTodosRelacionamento() {
        ArrayList<AbstractRelacionamento> tEnt = new ArrayList<>();

        Iterator<Entidade> it = getEntidadesIterator();

        while (it.hasNext()) {
            Entidade next = it.next();
            tEnt.addAll(next.getRelacionamentosIterator());
        }

        return tEnt;
    }

}
