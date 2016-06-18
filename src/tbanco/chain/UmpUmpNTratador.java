/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tbanco.chain;

import java.util.Iterator;
import tbanco.model.Atributo;
import tbanco.model.Atributos;
import tbanco.model.Entidade;
import tbanco.model.ModEntRel;
import tbanco.model.relacionamento.AbstractRelacionamento;
import tbanco.model.relacionamento.AbstractRelacionavel;

public class UmpUmpNTratador extends AbstractTratador {

    @Override
    public boolean accept(ModEntRel modEntRel) {
        Iterator<AbstractRelacionamento> relacionamentos = modEntRel.getTodosRelacionamentos();
        while (relacionamentos.hasNext()) {
            AbstractRelacionamento relacionamento = relacionamentos.next();
            if (relacionamento.getCardinalidadeDeRelacionamento()
                    == AbstractRelacionamento.CARDINALIDADE_DE_RELACIONAMENTO.UM_PRA_UM_PRA_N) {
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
                    == AbstractRelacionamento.CARDINALIDADE_DE_RELACIONAMENTO.UM_PRA_UM_PRA_N) {

                ////cria uma nova tabela
                ////chave A e B + atributos chaves de C
///x = 1; y = 1; z = m
///ABC (chaves-A, chaves-B, chaves-C)
                AbstractRelacionavel[] relac1 = relacionamento.getRelacionaveisCardinalidade1();
                AbstractRelacionavel[] relacn = relacionamento.getRelacionaveisCardinalidadeN();

                Entidade entidade = new Entidade(relacionamento.getNome());

                addChavesPrimarias(relacn[0], entidade, true, null);
                addChavesPrimarias(relac1[0], entidade, true, null);

                Atributos atributos = new Atributos();

                Iterator<Atributo> it = relac1[1].getAtributos().getAtributosIterator();
                while (it.hasNext()) {
                    Atributo atributo = it.next();
                    if (atributo.isChave_primaria()) {
                        atributos.addAtributoAlterado(atributo, null, relac1[1].getNome(), true);
                    }
                }

                it = atributos.getAtributosIterator();
                while (it.hasNext()) {
                    Atributo atributo = it.next();
                    entidade.addAtributoSimples(atributo);
                }

                if (relacionamento.hasAtributos()) {
                    Iterator<Atributo> atributosIterator = relacionamento.getAtributos().getAtributosIterator();

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
