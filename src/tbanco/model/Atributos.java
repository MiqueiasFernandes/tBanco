/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tbanco.model;

import java.util.HashSet;
import java.util.Iterator;

/**
 *
 * @author mfernandes
 */
public class Atributos {

    private HashSet<Atributo> atributos;

    public Atributos() {
        atributos = new HashSet<>();
    }

    public Iterator<Atributo> getAtributosIterator() {
        return atributos.iterator();
    }

    public void addAtributo(Atributo atributo) {
        atributos.add(atributo);
    }

    public int atributosCount() {
        return atributos.size();
    }

}
