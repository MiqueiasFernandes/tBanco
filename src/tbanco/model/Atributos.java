/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tbanco.model;

import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author mfernandes
 */
public class Atributos {

    private ArrayList<Atributo> atributos;

    public Atributos() {
        atributos = new ArrayList<>();
    }

    public Iterator<Atributo> getAtributosIterator() {
        return atributos.iterator();
    }

    public void addAtributo(Atributo atributo) {
        atributos.add(atributo);
    }

}
