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
import tbanco.model.relacionamento.AbstractRelacionavel;

public class NpNTratador extends AbstractTratador {

    @Override
    public boolean accept(ModEntRel modEntRel) {
        Iterator<AbstractRelacionamento> relacionamentos = modEntRel.getTodosRelacionamentos();
        while (relacionamentos.hasNext()) {
            AbstractRelacionamento relacionamento = relacionamentos.next();
            if (relacionamento.getCardinalidadeDeRelacionamento()
                    == AbstractRelacionamento.CARDINALIDADE_DE_RELACIONAMENTO.N_PRA_N) {
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
                    == AbstractRelacionamento.CARDINALIDADE_DE_RELACIONAMENTO.N_PRA_N) {

                ////cria uma novaentidade
                ///copia todas chaves primarias
                ///copia os atributos do relacionamento
                Entidade entidade = new Entidade(relacionamento.getNome());
                AbstractRelacionavel[] relacionaveis = relacionamento.getRelacionaveis();
                for (AbstractRelacionavel relacionavel : relacionaveis) {
                    addChavesPrimarias(relacionavel, entidade, true, relacionamento.getNome());
                }
                if (relacionamento.hasAtributos()) {
                    Iterator<Atributo> atributosIterator
                            = relacionamento.getAtributos().getAtributosIterator();
                    while (atributosIterator.hasNext()) {
                        Atributo atributo = atributosIterator.next();
                        entidade.addAtributoAlterado(atributo, relacionamento.getNome(),
                                null, atributo.isChave_primaria());
                    }
                }
                modEntRel.addTabela(entidade);
            }
        }
    }

}
