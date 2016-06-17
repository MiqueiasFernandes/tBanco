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

        topo.addRelacionamento(this);
        esquerdo.addRelacionamento(this);
        direito.addRelacionamento(this);
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

    @Override
    public AbstractRelacionavel[] getRelacionaveisCardinalidadeN() {
        switch (getCardinalidadeDeRelacionamento()) {
            case UM_PRA_UM:
                return null;

            case UM_PRA_UM_PRA_N: {
                AbstractRelacionavel[] r = {
                    (cardinalidade_topo == 'N') ? topo
                    : (cardinalidade_esquerdo == 'N') ? esquerdo
                    : (cardinalidade_direito == 'N') ? direito
                    : null
                };
                return r;
            }

            case N_PRA_N_PRA_UM: {
                AbstractRelacionavel p = (cardinalidade_topo == 'N') ? topo
                        : (cardinalidade_esquerdo == 'N') ? esquerdo
                                : (cardinalidade_direito == 'N') ? direito
                                        : null;

                AbstractRelacionavel s = (p != topo && cardinalidade_topo == 'N') ? topo
                        : (p != esquerdo && cardinalidade_esquerdo == 'N') ? esquerdo
                                : (p != direito && cardinalidade_direito == 'N') ? direito
                                        : null;

                AbstractRelacionavel[] r = {p, s};
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
            case N_PRA_N:
                return null;

            case N_PRA_N_PRA_UM: {
                AbstractRelacionavel[] r = {
                    (cardinalidade_topo == '1') ? topo
                    : (cardinalidade_esquerdo == '1') ? esquerdo
                    : (cardinalidade_direito == '1') ? direito
                    : null
                };
                return r;
            }

            case UM_PRA_UM_PRA_N: {
                AbstractRelacionavel p = (cardinalidade_topo == '1') ? topo
                        : (cardinalidade_esquerdo == '1') ? esquerdo
                                : (cardinalidade_direito == '1') ? direito
                                        : null;

                AbstractRelacionavel s = (p != topo && cardinalidade_topo == '1') ? topo
                        : (p != esquerdo && cardinalidade_esquerdo == '1') ? esquerdo
                                : (p != direito && cardinalidade_direito == '1') ? direito
                                        : null;

                AbstractRelacionavel[] r = {p, s};
                return r;

            }

            case UM_PRA_UM:
                return getRelacionaveis();
        }
        return null;
    }

}
