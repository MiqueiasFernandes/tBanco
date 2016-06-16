/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tbanco.model;

import tbanco.model.relacionamento.AbstractRelacionavel;
import tbanco.model.relacionamento.AbstractRelacionamento;
import java.util.HashSet;
import java.util.Iterator;

/**
 *
 * @author mfernandes
 */
public class Agregacao extends AbstractRelacionavel implements IEntidavel, IRelacionavel {

    private String nome;
    private HashSet<Entidade> entidades;

    public Agregacao(String nome) {
        this.nome = nome;
        this.entidades = new HashSet<>();
    }

    @Override
    public String getNome() {
        return nome;
    }

    public Iterator<Entidade> getEntidadesIterator() {
        return entidades.iterator();
    }

    @Override
    public void addEntidade(Entidade entidade) {
        entidades.add(entidade);
    }

    public AbstractRelacionamento getRelacionamentoPrincipal() {

        Iterator<AbstractRelacionamento> it = getRelacionamentosIterator();

        while (it.hasNext()) {
            AbstractRelacionamento next = it.next();

            if (entidades.isEmpty()) {
                return next;
            }

            if (relacionaComTodasEntidades(next)) {
                return next;
            }
        }
        return null;
    }

    boolean relacionaComTodasEntidades(AbstractRelacionamento relacionamento) {
        ///pega todas entidades deste relacionamento
        AbstractRelacionavel[] relacionaveis = relacionamento.getRelacionaveis();
        ///pega todas entidades desta agregacao
        Iterator<Entidade> it_entidades = getEntidadesIterator();
        ///roda todas entidades da agregacao
        while (it_entidades.hasNext()) {
            Entidade next = it_entidades.next();

            boolean faz_parte_do_conjunto = false;
            ////para cada entidade da agregacao
            ////procura ela nas entidades do relacionamento
            for (AbstractRelacionavel abstractRelacionavel : relacionaveis) {
                ///se pertence a este relacionamento
                if (next.equals(abstractRelacionavel)) {
                    faz_parte_do_conjunto = true;
                }
            }

            if (!faz_parte_do_conjunto) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean equals(AbstractRelacionavel other) {

        if (!other.isAgregacao()) {
            return false;
        }

        return this == other;

    }

    @Override
    public boolean isEntidade() {
        return false;
    }

    @Override
    public boolean isAgregacao() {
        return true;
    }

    public void addAllEntidades(HashSet<Entidade> array) {
        array.addAll(entidades);
    }

    @Override
    public Iterator<AbstractRelacionavel> getRelacionaveis() {
        HashSet<AbstractRelacionavel> relacionaveis = new HashSet<>();
        relacionaveis.addAll(entidades);
        return relacionaveis.iterator();
    }

}
