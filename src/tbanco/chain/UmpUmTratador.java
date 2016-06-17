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

public class UmpUmTratador extends AbstractTratador {

    @Override
    public boolean accept(ModEntRel modEntRel) {
        Iterator<AbstractRelacionamento> relacionamentos = modEntRel.getTodosRelacionamentos();
        while (relacionamentos.hasNext()) {
            AbstractRelacionamento relacionamento = relacionamentos.next();

            if (relacionamento.getCardinalidadeDeRelacionamento() == AbstractRelacionamento.CARDINALIDADE_DE_RELACIONAMENTO.UM_PRA_UM) {
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
            if (relacionamento.getCardinalidadeDeRelacionamento() == AbstractRelacionamento.CARDINALIDADE_DE_RELACIONAMENTO.UM_PRA_UM) {

                String nomeatrib = "_" + relacionamento.getNome_relacionamento() + "_";

                ///caso 
                AbstractRelacionavel[] relacionaveis = relacionamento.getRelacionaveis();
                switch (relacionaveis.length) {
                    ////unario
                    case 1: {
                        ///pessoa (código_pessoa, nome_pessoa, casamento_pessoa)
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
                        nomeatrib += relacionaveis[0].getNome();

                        Iterator<Atributo> it = relacionaveis[0].getAtributos().getAtributosIterator();

                        while (it.hasNext()) {
                            Atributo atributo = it.next();
                            if (atributo.isChave_primaria()) {
                                relacionaveis[1].addAtributo(
                                        new Atributo(atributo.getNome() + nomeatrib, atributo.getSource() + "%", atributo.getTipo()));
                            }
                        }
                        nomeatrib = "_" + relacionamento.getNome_relacionamento() + "_" + relacionaveis[1].getNome();

                        it = relacionaveis[1].getAtributos().getAtributosIterator();

                        while (it.hasNext()) {
                            Atributo atributo = it.next();
                            if (atributo.isChave_primaria() && !atributo.getSource().contains("%")) {
                                relacionaveis[0].addAtributo(
                                        new Atributo(atributo.getNome() + nomeatrib, atributo.getSource(), atributo.getTipo()));
                            }
                        }
                    }
                    break;
                    ///ternario
                    case 3: {

                        for (AbstractRelacionavel relacionavel : relacionamento.getRelacionaveis()) {
                            for (AbstractRelacionavel relacionavei : relacionamento.getRelacionaveis()) {

                                nomeatrib = "_" + relacionamento.getNome_relacionamento() + "_" + relacionavel.getNome();

                                Iterator<Atributo> it = relacionavel.getAtributos().getAtributosIterator();

                                while (it.hasNext()) {
                                    Atributo atributo = it.next();
                                    if (atributo.isChave_primaria() && !atributo.getSource().contains("%")) {
                                        relacionavei.addAtributo(
                                                new Atributo(atributo.getNome() + nomeatrib, atributo.getSource() + "%", atributo.getTipo()));
                                    }
                                }

                            }
                        }
                    }
                    break;
                }

                if (relacionamento.hasAtributos()) {

                    Iterator<Atributo> atributosIterator = relacionamento.getAtributos().getAtributosIterator();

                    while (atributosIterator.hasNext()) {
                        Atributo atributo = atributosIterator.next();
                        relacionaveis[0].addAtributo(atributo);
                    }

                }

            }
        }
    }

}
