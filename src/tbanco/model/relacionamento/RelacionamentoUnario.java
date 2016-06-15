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
public class RelacionamentoUnario extends AbstractRelacionamento {

    String papelOrigem, papelDestino;
    char cardinalidade_origem, cardinalidade_destino; /// 1-1 1-N N-N
    AbstractRelacionavel relacionavel;

    public RelacionamentoUnario(String nome, String papelOrigem, String papelDestino,
            String cardinalidade, AbstractRelacionavel relacionavel) {
        super(nome, cardinalidade.toUpperCase());
        this.papelOrigem = papelOrigem;
        this.papelDestino = papelDestino;
        this.cardinalidade_origem = cardinalidade.split("-")[0].charAt(0);
        this.cardinalidade_destino = cardinalidade.split("-")[1].charAt(0);
        this.relacionavel = relacionavel;
    }

    @Override
    public TIPO_DE_RELACIONAMENTO getTipoDeRelacionamento() {
        return AbstractRelacionamento.TIPO_DE_RELACIONAMENTO.UNARIO;
    }

    @Override
    public CARDINALIDADE_DE_RELACIONAMENTO getCardinalidadeDeRelacionamento() {

        if (cardinalidade_origem == '1' && cardinalidade_destino == '1') {
            return CARDINALIDADE_DE_RELACIONAMENTO.UM_PRA_UM;
        }

        if (cardinalidade_origem == 'N' && cardinalidade_destino == 'N') {
            return CARDINALIDADE_DE_RELACIONAMENTO.N_PRA_N;
        }

        return CARDINALIDADE_DE_RELACIONAMENTO.UM_PRA_N;

    }

    @Override
    public AbstractRelacionavel[] getRelacionaveis() {
        AbstractRelacionavel[] ar = {relacionavel};
        return ar;
    }

}
