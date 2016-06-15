/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tbanco.model;

import java.util.HashSet;
import java.util.Iterator;
import tbanco.model.relacionamento.AbstractRelacionamento;
import tbanco.model.relacionamento.AbstractRelacionavel;

/**
 *
 * @author mfernandes
 */
public class ModEntRel implements IEntidavel {

    private HashSet<Agregacao> agregacoes;
    private HashSet<Entidade> entidades;

    String nome;

    public ModEntRel(String nome) {
        this.nome = nome;
        agregacoes = new HashSet<>();
        entidades = new HashSet<>();
    }

    void addAgregacao(Agregacao agregacao) {
        agregacoes.add(agregacao);
    }

    @Override
    public void addEntidade(Entidade entidade) {
        entidades.add(entidade);
    }

    public Iterator<Entidade> getEntidadesIterator() {
        return entidades.iterator();
    }

    public Iterator<Agregacao> getAgregacoesIterator() {
        return agregacoes.iterator();
    }

    public Iterator<Entidade> getTodasEntidades() {
        HashSet<Entidade> tEnt = new HashSet<>(entidades);

        Iterator<Agregacao> it = getAgregacoesIterator();

        while (it.hasNext()) {
            Agregacao next = it.next();
            next.addAllEntidades(tEnt);
        }

        return tEnt.iterator();
    }

    public Iterator<AbstractRelacionamento> getTodosRelacionamentos() {
        HashSet<AbstractRelacionamento> array = new HashSet<>();

        addRelacionamentoDeEntidades(array, getEntidadesIterator());

        Iterator<Agregacao> it = getAgregacoesIterator();

        while (it.hasNext()) {
            Agregacao next = it.next();
            addRelacionamentoDeEntidades(array, next.getEntidadesIterator());
        }

        return array.iterator();
    }

    void addRelacionamentoDeEntidades(HashSet<AbstractRelacionamento> array, Iterator<Entidade> it) {
        while (it.hasNext()) {
            Entidade next = it.next();
            next.addAllRelacionamentos(array);
        }
    }

    public Iterator<AbstractRelacionavel> getRelacionaveis() {
        HashSet<AbstractRelacionavel> relacionaveis = new HashSet<>();
        relacionaveis.addAll(entidades);
        relacionaveis.addAll(agregacoes);
        return relacionaveis.iterator();
    }

}
