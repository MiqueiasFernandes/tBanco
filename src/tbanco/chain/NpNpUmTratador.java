/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tbanco.chain;

import java.util.Iterator;
import tbanco.model.Atributo;
import tbanco.model.Entidade;
import tbanco.model.ModEntRel;
import tbanco.model.relacionamento.AbstractRelacionamento;

public class NpNpUmTratador extends AbstractTratador {

    @Override
    public boolean accept(ModEntRel modEntRel) {
        Iterator<AbstractRelacionamento> relacionamentos = modEntRel.getTodosRelacionamentos();
        while (relacionamentos.hasNext()) {
            AbstractRelacionamento relacionamento = relacionamentos.next();
            if (relacionamento.getCardinalidadeDeRelacionamento()
                    == AbstractRelacionamento.CARDINALIDADE_DE_RELACIONAMENTO.N_PRA_N_PRA_UM) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void tratar(ModEntRel modEntRel) {
        Iterator<AbstractRelacionamento> relacionamentos = modEntRel.getTodosRelacionamentos();
        while (relacionamentos.hasNext()) {
            AbstractRelacionamento relacionamento = relacionamentos.next();
            if (relacionamento.getCardinalidadeDeRelacionamento()
                    == AbstractRelacionamento.CARDINALIDADE_DE_RELACIONAMENTO.N_PRA_N_PRA_UM) {
//x = 1; y = m; z = n
//ABC (chaves-A, *chaves-B, *chaves-C)

                Entidade entidade = new Entidade(relacionamento.getNome());

                addChavesPrimarias(relacionamento.getRelacionaveisCardinalidadeN()[0], entidade, true, null);
                addChavesPrimarias(relacionamento.getRelacionaveisCardinalidadeN()[1], entidade, true, null);

                Iterator<Atributo> atributosIterator
                        = relacionamento.getRelacionaveisCardinalidade1()[0].getAtributos().getAtributosIterator();

                while (atributosIterator.hasNext()) {
                    Atributo atributo = atributosIterator.next();
                    entidade.addAtributoAlterado(atributo, null,
                            relacionamento.getRelacionaveisCardinalidade1()[0].getNome(), false);
                }

                if (relacionamento.hasAtributos()) {
                    atributosIterator = relacionamento.getAtributos().getAtributosIterator();

                    while (atributosIterator.hasNext()) {
                        Atributo atributo = atributosIterator.next();
                        entidade.addAtributoAlterado(atributo, relacionamento.getNome(), null, atributo.isChave_primaria());
                    }
                }
                modEntRel.addTabela(entidade);

            }
        }
    }

}
