/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tbanco.chain;

import java.util.Iterator;
import tbanco.model.Atributo;
import tbanco.model.Entidade;
import tbanco.model.IAtributavel;
import tbanco.model.ModEntRel;

public class SubTipoTratador extends AbstractTratador {

    @Override
    public boolean accept(ModEntRel modEntRel) {
        Iterator<Entidade> entidades = modEntRel.getTodasEntidades();
        while (entidades.hasNext()) {
            Entidade entidade = entidades.next();
            if (entidade.isSubtipo()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void tratar(ModEntRel modEntRel) {

//Modelo entidade relacionamento
//– E 1 : superclasse
//– E 2, ..., E n : subclasses de E 1
//
//Modelo relacional
//– a tabela de E 1 possuirá:
//N os atributos de E 1
// + N um atributo discriminador, caso necessário: tipo_<subtipo> (0 ou 1)
//– as tabelas de E 2 a E n possuirão:
//N os seus atributos específicos
// + N a chave primária de E 1
        Iterator<Entidade> entidades = modEntRel.getTodasEntidades();
        while (entidades.hasNext()) {
            Entidade subtipo = entidades.next();
            if (subtipo.isSubtipo()) {
                {
                    Entidade supertipo = getSuperTipo(subtipo.getTipo(), modEntRel.getTodasEntidades());
                    supertipo.getAtributos().addAtributo(new Atributo("tipo_" + subtipo.getNome()));
                    addChavesPrimarias(supertipo, subtipo);
                }
            }
        }

    }

    Entidade getSuperTipo(String nome, Iterator<Entidade> entidades) {
        while (entidades.hasNext()) {
            Entidade entidade = entidades.next();
            if (entidade.getNome().equalsIgnoreCase(nome)) {
                return entidade;
            }
        }

        System.err.println("ERRO: supertipo não encontrado" + nome);

        return null;
    }

   

}
