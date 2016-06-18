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
public class MERProcessor {

    AbstractTratador primeiro;

    public MERProcessor() {
        addTratador(new SubTipoTratador());
        addTratador(new EntForteTratador());
        addTratador(new NpNTratador());
        addTratador(new NpNpUmTratador());
        addTratador(new UmpNTratador());
        addTratador(new UmpUmTratador());
        addTratador(new UmpUmpNTratador());
    }

    public void addTratador(AbstractTratador tratador) {
        if (primeiro == null) {
            primeiro = tratador;
        } else {
            primeiro.setProximo(tratador);
        }
    }

    public void tratar(ModEntRel modEntRel) {
        if (primeiro != null) {
            primeiro.verificar(modEntRel);
        }
    }

}
