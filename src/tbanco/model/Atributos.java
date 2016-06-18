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

    public void addAtributoSimples(Atributo atributo) {
        atributos.add(atributo);
    }

    public void addAtributoAlterado(Atributo atributo, String prefixo, String sufixo, boolean isKey) {
        String nome = atributo.getNome();

        if (prefixo != null && !prefixo.isEmpty()) {
            nome = prefixo + "_" + nome;
        }
        if (sufixo != null && !sufixo.isEmpty()) {
            nome += "_" + sufixo;
        }
        String source = "";
        if (isKey) {
            source += "*";
        }
        if (atributo.isChave_estrangeira()) {
            source += "#";
        }
        if (atributo.isNao_nulo()) {
            source += "N";
        }
        addAtributoSimples(new Atributo(nome, source));
    }

    public int atributosCount() {
        return atributos.size();
    }

    public Atributo[] getAtributosArray() {
        return atributos.toArray(new Atributo[atributos.size()]);
    }
}
