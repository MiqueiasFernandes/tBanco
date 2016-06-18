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

                String nomeatrib = "_" + relacionamento.getNome() + "_";

                ///caso 
                AbstractRelacionavel[] relacionaveis = relacionamento.getRelacionaveis();

                if (relacionaveis != null && relacionaveis.length > 0) {
                    switch (relacionaveis.length) {
                        ////unario
                        case 1: {
                            ///pessoa (código_pessoa, nome_pessoa, casamento_pessoa)
                            nomeatrib += relacionaveis[0].getNome();

                            for (Atributo atributo : relacionaveis[0].getAtributosArray()) {
                                if (atributo.isChave_primaria()) {
                                    relacionaveis[0].addAtributoSimples(
                                            new Atributo(atributo.getNome() + nomeatrib, atributo.getSource(), atributo.getTipo()));
                                }
                            }

                        }
                        break;
                        ///binario
                        case 2: {
                            nomeatrib += relacionaveis[0].getNome();

                            for (Atributo atributo : relacionaveis[0].getAtributosArray()) {
                                if (atributo.isChave_primaria()) {
                                    relacionaveis[1].addAtributoSimples(
                                            new Atributo(atributo.getNome() + nomeatrib, atributo.getSource() + "%", atributo.getTipo()));
                                }
                            }
                            nomeatrib = "_" + relacionamento.getNome() + "_" + relacionaveis[1].getNome();

                            for (Atributo atributo : relacionaveis[1].getAtributosArray()) {

                                if (atributo.isChave_primaria() && !atributo.getSource().contains("%")) {
                                    relacionaveis[0].addAtributoSimples(
                                            new Atributo(atributo.getNome() + nomeatrib, atributo.getSource(), atributo.getTipo()));
                                }
                            }
                        }
                        break;
                        ///ternario
                        case 3: {

                            ////cria uma nova tabela
                            ////chave A e B + atributos chaves de C
                            Entidade entidade = new Entidade(relacionamento.getNome());

                            addChavesPrimarias(entidade, relacionaveis[0], true, relacionamento.getNome());
                            addChavesPrimarias(entidade, relacionaveis[1], true, relacionamento.getNome());

                            AbstractRelacionavel relacionavel = relacionaveis[2];

                            for (Atributo atributo : relacionavel.getAtributosArray()) {
                                if (atributo.isChave_primaria() && !atributo.getSource().contains("%")) {
                                    entidade.addAtributoSimples(
                                            new Atributo(atributo.getNome(), atributo.getSource().replace("*", ""), atributo.getTipo()));
                                }
                            }

                            if (relacionamento.hasAtributos()) {
                                for (Atributo atributo : relacionamento.getAtributosArray()) {
                                    entidade.addAtributoAlterado(atributo, relacionamento.getNome(), null, atributo.isChave_primaria());
                                }
                            }
                            modEntRel.addTabela(entidade);
                        }
                        break;
                    }

                    if (relacionamento.hasAtributos() && relacionaveis.length < 3) {

                        for (Atributo atributo : relacionamento.getAtributosArray()) {
                            relacionaveis[0].addAtributoAlterado(atributo, relacionamento.getNome(), null, atributo.isChave_primaria());
                        }

                    }
                } else {
                    System.err.println("há um erro com o relacionamento " + relacionamento.getNome());
                }
            }
        }
    }

}
