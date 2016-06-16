/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tbanco.chain;

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

    public abstract boolean accept(ModEntRel modEntRel);

    public abstract void tratar(ModEntRel modEntRel);
}
