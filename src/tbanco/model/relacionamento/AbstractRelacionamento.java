/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tbanco.model.relacionamento;

import tbanco.model.Atributos;

/**
 *
 * @author mfernandes
 */
public abstract class AbstractRelacionamento {

    public static enum TIPO_DE_RELACIONAMENTO {
        UNARIO,
        BINARIO,
        TERNARIO
    }

    public static enum CARDINALIDADE_DE_RELACIONAMENTO {
        UM_PRA_UM,
        UM_PRA_N,
        N_PRA_N,
        UM_PRA_UM_PRA_N,
        N_PRA_N_PRA_UM
    }

    Atributos atributos;
    String nome_relacionamento, cardinalidade;

    public AbstractRelacionamento(String nome_relacionamento, String cardinalidade) {
        this.atributos = new Atributos();
        this.nome_relacionamento = nome_relacionamento;
        this.cardinalidade = cardinalidade;
    }

    public abstract TIPO_DE_RELACIONAMENTO getTipoDeRelacionamento();

    public abstract CARDINALIDADE_DE_RELACIONAMENTO getCardinalidadeDeRelacionamento();

    public String getCardinalidade() {
        return cardinalidade;
    }

    public String getNome_relacionamento() {
        return nome_relacionamento;
    }

    public Atributos getAtributos() {
        return atributos;
    }

    public abstract AbstractRelacionavel[] getRelacionaveis();

}
