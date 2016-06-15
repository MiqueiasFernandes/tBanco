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
public class RelacionamentoTernario extends AbstractRelacionamento {

    AbstractRelacionavel topo, esquerdo, direito;
    char cardinalidade_topo, cardinalidade_esquerdo, cardinalidade_direito;

    public RelacionamentoTernario(
            String nome_relacionamento,
            AbstractRelacionavel topo,
            AbstractRelacionavel esquerdo,
            AbstractRelacionavel direito,
            String cardinalidade) {
        super(nome_relacionamento, cardinalidade.toUpperCase());
        this.topo = topo;
        this.esquerdo = esquerdo;
        this.direito = direito;

        this.cardinalidade_topo = super.cardinalidade.split("-")[0].charAt(0);
        this.cardinalidade_esquerdo = super.cardinalidade.split("-")[1].charAt(0);
        this.cardinalidade_direito = super.cardinalidade.split("-")[2].charAt(0);
    }

    @Override
    public TIPO_DE_RELACIONAMENTO getTipoDeRelacionamento() {
        return TIPO_DE_RELACIONAMENTO.TERNARIO;
    }

    @Override
    public CARDINALIDADE_DE_RELACIONAMENTO getCardinalidadeDeRelacionamento() {
        int um = 0, ene = 0;

        um += getTipo(cardinalidade_topo, '1');
        um += getTipo(cardinalidade_direito, '1');
        um += getTipo(cardinalidade_esquerdo, '1');
        ene += getTipo(cardinalidade_topo, 'N');
        ene += getTipo(cardinalidade_direito, 'N');
        ene += getTipo(cardinalidade_esquerdo, 'N');

        switch (um) {
            case 3:
                return CARDINALIDADE_DE_RELACIONAMENTO.UM_PRA_UM;
            case 2:
                return CARDINALIDADE_DE_RELACIONAMENTO.UM_PRA_UM_PRA_N;
        }

        switch (ene) {
            case 3:
                return CARDINALIDADE_DE_RELACIONAMENTO.N_PRA_N;
            case 2:
                return CARDINALIDADE_DE_RELACIONAMENTO.N_PRA_N_PRA_UM;
        }

        return null;
    }

    int getTipo(char cardinalidade, char tipo) {

        if (cardinalidade == tipo) {
            return 1;
        } else {
            return 0;
        }

    }
    
    @Override
    public AbstractRelacionavel[] getRelacionaveis() {
        AbstractRelacionavel[] ar = {topo, esquerdo, direito};
        return ar;
    }

     @Override
    public boolean isEntidadeOrigem(AbstractRelacionavel relacionavel) {
        return topo.equals(relacionavel);
    }
    
}
