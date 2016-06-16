/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tbanco.chain;

import java.util.Iterator;
import tbanco.model.Entidade;
import tbanco.model.ModEntRel;

public class EntForteTratador extends AbstractTratador {

    @Override
    public boolean accept(ModEntRel modEntRel) {
        Iterator<Entidade> entidades = modEntRel.getTodasEntidades();
        while (entidades.hasNext()) {
            Entidade entidade = entidades.next();
            if (!entidade.hasRelacionamentos()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void tratar(ModEntRel modEntRel) {
        Iterator<Entidade> entidades = modEntRel.getTodasEntidades();
        while (entidades.hasNext()) {
            Entidade entidade = entidades.next();
            if (!entidade.hasRelacionamentos()) {
                modEntRel.addTabela(entidade);
            }
        }
    }

}
