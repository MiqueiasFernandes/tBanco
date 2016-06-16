/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tbanco.model;

import java.util.Iterator;
import tbanco.model.relacionamento.AbstractRelacionavel;

/**
 *
 * @author mfernandes
 */
public interface IRelacionavel {

    Iterator<AbstractRelacionavel> getRelacionaveis();

}
