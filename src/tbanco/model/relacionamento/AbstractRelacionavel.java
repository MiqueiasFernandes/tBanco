/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tbanco.model.relacionamento;

import java.util.ArrayList;
import java.util.Iterator;
import tbanco.model.Atributos;

/**
 *
 * @author mfernandes
 */
public abstract class AbstractRelacionavel {

    private Atributos atributos;
    private ArrayList<AbstractRelacionamento> relacionamentos;

    public AbstractRelacionavel() {
        atributos = new Atributos();
        relacionamentos = new ArrayList<>();
    }

    public Atributos getAtributos() {
        return atributos;
    }

    public Iterator<AbstractRelacionamento> getRelacionamentosIterator() {
        return relacionamentos.iterator();
    }

    public void addRelacionamento(AbstractRelacionamento relacionamento) {
        relacionamentos.add(relacionamento);
    }

    public void addAllRelacionamentos(ArrayList<AbstractRelacionamento> array) {
        array.addAll(relacionamentos);
    }

    public abstract boolean equals(AbstractRelacionavel other);

    public abstract boolean isEntidade();

    public abstract boolean isAgregacao();

}
