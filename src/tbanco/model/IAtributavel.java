/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tbanco.model;

/**
 *
 * @author mfernandes
 */
public interface IAtributavel {

    public void addAtributoSimples(Atributo atributo);

    public void addAtributoAlterado(Atributo atributo, String prefixo, String Sufixo, boolean isKey);

    public Atributos getAtributos();

    public boolean hasAtributos();

    public String getNome();

    public Atributo[] getAtributosArray();

}
