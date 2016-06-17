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

public class UmpNTratador extends AbstractTratador {

    @Override
    public boolean accept(ModEntRel modEntRel) {
        Iterator<AbstractRelacionamento> relacionamentos = modEntRel.getTodosRelacionamentos();
        while (relacionamentos.hasNext()) {
            AbstractRelacionamento relacionamento = relacionamentos.next();

            if (relacionamento.getCardinalidadeDeRelacionamento()
                    == AbstractRelacionamento.CARDINALIDADE_DE_RELACIONAMENTO.UM_PRA_N) {
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
                    == AbstractRelacionamento.CARDINALIDADE_DE_RELACIONAMENTO.UM_PRA_N) {

                AbstractRelacionavel[] relacionaveis = relacionamento.getRelacionaveis();
                String nomeatrib = "_" + relacionamento.getNome_relacionamento() + "_";
                AbstractRelacionavel relN = relacionamento.getRelacionaveisCardinalidadeN()[0];

                switch (relacionaveis.length) {
                    ////unario
                    case 1: {
                        nomeatrib += relacionaveis[0].getNome();

                        Iterator<Atributo> it = relacionaveis[0].getAtributos().getAtributosIterator();

                        while (it.hasNext()) {
                            Atributo atributo = it.next();
                            if (atributo.isChave_primaria()) {
                                relacionaveis[0].addAtributo(
                                        new Atributo(atributo.getNome() + nomeatrib, atributo.getSource(), atributo.getTipo()));
                            }
                        }
                    }
                    break;
                    ///binario
                    case 2: {
                        ///quem tem n pega as chaves

                        AbstractRelacionavel rel1 = relacionamento.getRelacionaveisCardinalidade1()[0];

                        nomeatrib += rel1.getNome();

                        Iterator<Atributo> it = rel1.getAtributos().getAtributosIterator();

                        while (it.hasNext()) {
                            Atributo atributo = it.next();
                            if (atributo.isChave_primaria()) {
                                relN.addAtributo(
                                        new Atributo(atributo.getNome() + nomeatrib, atributo.getSource() + "#%", atributo.getTipo()));
                            }
                        }
                    }
                    break;
                }

                Iterator<Atributo> atributosIterator
                        = relacionamento.getAtributos().getAtributosIterator();

                if (relacionamento.hasAtributos()) {
                    while (atributosIterator.hasNext()) {
                        Atributo atributo = atributosIterator.next();
///nao clona
                        relN.addAtributo(atributo);

                    }
                }
            }
        }
    }
}
