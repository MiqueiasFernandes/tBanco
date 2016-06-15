/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tbanco.model.relacionamento;

import java.util.HashSet;
import java.util.Iterator;
import tbanco.model.Atributo;
import tbanco.model.Atributos;
import tbanco.model.IAtributavel;

/**
 *
 * @author mfernandes
 */
public abstract class AbstractRelacionavel implements IAtributavel {

    private Atributos atributos;
    private HashSet<AbstractRelacionamento> relacionamentos;

    public AbstractRelacionavel() {
        atributos = new Atributos();
        relacionamentos = new HashSet<>();
    }

    @Override
    public Atributos getAtributos() {
        return atributos;
    }

    @Override
    public void addAtributo(Atributo atributo) {
        atributos.addAtributo(atributo);
    }

    public Iterator<AbstractRelacionamento> getRelacionamentosIterator() {
        return relacionamentos.iterator();
    }

    public void addRelacionamento(AbstractRelacionamento relacionamento) {
        relacionamentos.add(relacionamento);
    }

    public void addAllRelacionamentos(HashSet<AbstractRelacionamento> array) {
        array.addAll(relacionamentos);
    }

    public abstract boolean equals(AbstractRelacionavel other);

    public abstract boolean isEntidade();

    public abstract boolean isAgregacao();

    public abstract String getNome();

}
