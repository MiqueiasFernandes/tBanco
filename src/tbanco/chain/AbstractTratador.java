/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tbanco.chain;

import java.util.Iterator;
import tbanco.model.Atributo;
import tbanco.model.IAtributavel;
import tbanco.model.ModEntRel;

/**
 *
 * @author mfernandes
 */
public abstract class AbstractTratador {

    AbstractTratador proximo;

    public void setProximo(AbstractTratador prox) {
        if (proximo == null) {
            proximo = prox;
        } else {
            proximo.setProximo(prox);
        }
    }

    public void verificar(ModEntRel modEntRel) {
        if (accept(modEntRel)) {
            tratar(modEntRel);
        }
        if (proximo != null) {
            proximo.verificar(modEntRel);
        }
    }

    void addChavesPrimarias(IAtributavel origem, IAtributavel destino) {
        Iterator<Atributo> atributos = origem.getAtributos().getAtributosIterator();
        while (atributos.hasNext()) {
            Atributo atributo = atributos.next();
            if (atributo.isChave_primaria()) {
                destino.getAtributos().addAtributo(atributo);
            }
        }
    }

    public abstract boolean accept(ModEntRel modEntRel);

    public abstract void tratar(ModEntRel modEntRel);
}
