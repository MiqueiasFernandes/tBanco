/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tbanco.chain;

import java.util.Iterator;
import tbanco.model.Entidade;
import tbanco.model.ModEntRel;

public class TabelaTratador extends AbstractTratador {

    @Override
    public boolean accept(ModEntRel modEntRel) {

        Iterator<Entidade> entidadesIterator = modEntRel.getEntidadesIterator();

        while (entidadesIterator.hasNext()) {
            Entidade entidade = entidadesIterator.next();
            if (!modEntRel.tabelaContain(entidade)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void tratar(ModEntRel modEntRel) {
        Iterator<Entidade> entidadesIterator = modEntRel.getEntidadesIterator();
        while (entidadesIterator.hasNext()) {
            Entidade entidade = entidadesIterator.next();
            if (!modEntRel.tabelaContain(entidade)) {
                modEntRel.addTabela(entidade);
            }
        }
    }

}
