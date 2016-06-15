/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tbanco.model;

import java.util.HashSet;
import java.util.Iterator;
import java.util.TreeSet;
import tbanco.model.relacionamento.AbstractRelacionamento;

/**
 *
 * @author mfernandes
 */
public class ModEntRel {

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

    void addEntidade(Entidade entidade) {
        entidades.add(entidade);
    }

    public Iterator<Entidade> getEntidadesIterator() {
        return entidades.iterator();
    }

    public Iterator<Agregacao> getAgregacoesIterator() {
        return agregacoes.iterator();
    }

    public HashSet<Entidade> getTodasEntidades() {
        HashSet<Entidade> tEnt = new HashSet<>(entidades);

        Iterator<Agregacao> it = getAgregacoesIterator();

        while (it.hasNext()) {
            Agregacao next = it.next();
            next.addAllEntidades(tEnt);
        }

        return tEnt;
    }

    public TreeSet<AbstractRelacionamento> getTodosRelacionamento() {
        TreeSet<AbstractRelacionamento> array = new TreeSet<>();

        addEntidades(array, getEntidadesIterator());

        Iterator<Agregacao> it = getAgregacoesIterator();

        while (it.hasNext()) {
            Agregacao next = it.next();
            addEntidades(array, next.getEntidadesIterator());
        }

        return array;
    }

    void addEntidades(TreeSet<AbstractRelacionamento> array, Iterator<Entidade> it) {
        while (it.hasNext()) {
            Entidade next = it.next();
            next.addAllRelacionamentos(array);
        }
    }

}
