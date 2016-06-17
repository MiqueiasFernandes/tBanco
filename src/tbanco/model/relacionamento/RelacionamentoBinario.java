/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tbanco.model.relacionamento;

/**
 *
 * @author mfernandes
 */
public class RelacionamentoBinario extends AbstractRelacionamento {

    char cardinalidade_origem, cardinalidade_destino; /// 1-1 1-N N-N
    AbstractRelacionavel origem, destino;

    public RelacionamentoBinario(String nome_relacionamento, String cardinalidade,
            AbstractRelacionavel origem, AbstractRelacionavel destino) {
        super(nome_relacionamento, cardinalidade = cardinalidade.toUpperCase());
        this.origem = origem;
        this.destino = destino;
        this.cardinalidade_origem = cardinalidade.split("-")[0].charAt(0);
        this.cardinalidade_destino = cardinalidade.split("-")[1].charAt(0);

        origem.addRelacionamento(this);
        destino.addRelacionamento(this);
    }

    @Override
    public AbstractRelacionamento.TIPO_DE_RELACIONAMENTO getTipoDeRelacionamento() {
        return AbstractRelacionamento.TIPO_DE_RELACIONAMENTO.BINARIO;
    }

    @Override
    public AbstractRelacionamento.CARDINALIDADE_DE_RELACIONAMENTO getCardinalidadeDeRelacionamento() {

        if (cardinalidade_origem == '1' && cardinalidade_destino == '1') {
            return AbstractRelacionamento.CARDINALIDADE_DE_RELACIONAMENTO.UM_PRA_UM;
        }

        if (cardinalidade_origem == 'N' && cardinalidade_destino == 'N') {
            return AbstractRelacionamento.CARDINALIDADE_DE_RELACIONAMENTO.N_PRA_N;
        }

        return AbstractRelacionamento.CARDINALIDADE_DE_RELACIONAMENTO.UM_PRA_N;

    }

    @Override
    public AbstractRelacionavel[] getRelacionaveis() {
        AbstractRelacionavel[] ar = {origem, destino};
        return ar;
    }

    @Override
    public boolean isEntidadeOrigem(AbstractRelacionavel relacionavel) {
        return origem.equals(relacionavel);
    }

    @Override
    public AbstractRelacionavel[] getRelacionaveisCardinalidadeN() {
        switch (getCardinalidadeDeRelacionamento()) {
            case UM_PRA_UM:
                return null;

            case UM_PRA_N: {
                AbstractRelacionavel[] r = {cardinalidade_origem == 'N' ? origem : destino};
                return r;
            }

            case N_PRA_N:
                return getRelacionaveis();
        }
        return null;
    }

    @Override
    public AbstractRelacionavel[] getRelacionaveisCardinalidade1() {
        switch (getCardinalidadeDeRelacionamento()) {
            case UM_PRA_UM:
                return getRelacionaveis();

            case UM_PRA_N: {
                AbstractRelacionavel[] r = {cardinalidade_origem == '1' ? origem : destino};
                return r;
            }

            case N_PRA_N:
                return null;
        }
        return null;
    }

}
