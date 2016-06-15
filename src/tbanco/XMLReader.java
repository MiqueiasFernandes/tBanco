/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tbanco;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import javax.swing.JTextArea;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import tbanco.model.Entidade;
import tbanco.model.ModEntRel;

/**
 *
 * @author mfernandes
 */
public class XMLReader {

    public static ModEntRel importMER(File XMLFile, JTextArea jTextArea) {
        ModEntRel modEntRel = null;

        try {

            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(XMLFile);
            doc.getDocumentElement().normalize();

            modEntRel = new ModEntRel(doc.getDocumentElement().getNodeName());
            jTextArea.append("Nome do Modelo : " + doc.getDocumentElement().getNodeName() + "\n");

            
            ////importar entidades
            NodeList root = doc.getElementsByTagName("root");
            
                Node rootN = root.item(0);
                if (rootN.getNodeType() == Node.ELEMENT_NODE) {

                    Element eElement = (Element) rootN;
                    
                    importEntidades(entidades, eElement, jTextArea);

                    importRelacionamentos(relacionamentos, relacionaveis, eElement, jTextArea);

                }
            
            
            ////importar relacionamentos
            
            
            
            ////importar agregações
            
            

        } catch (ParserConfigurationException | SAXException | IOException ex) {
            jTextArea.append(ex + "\n");
        }

        return modEntRel;
    }
    
    
    
     public static void importEntidades(HashSet entidades, Element _eElement, JTextArea jTextArea) {
        NodeList entidadesNL = _eElement.getElementsByTagName("entidade");
        for (int temp = 0; temp < entidadesNL.getLength(); temp++) {

            Node nNode = entidadesNL.item(temp);

            System.out.println("\n------------->" + nNode.getNodeName());
            jTextArea.append("\n------------->" + nNode.getNodeName() + "\n");
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                Element eElement = (Element) nNode;

                String tipo = "";

                if (eElement.hasAttribute("tipo")) {
                    tipo = eElement.getAttribute("tipo");
                }

              //  JOptionPane.showMessageDialog(jTextArea, _eElement.getNodeName() + " > " + eElement.getAttribute("nome"));
                System.out.println("Entidade nome: " + eElement.getAttribute("nome") + " é um " + tipo);
                jTextArea.append("Entidade nome: " + eElement.getAttribute("nome") + " é um " + tipo + "\n");

                Entidade entidade = new Entidade(eElement.getAttribute("nome"), tipo);

                importAtributos(entidade.getAtributos(), eElement, jTextArea);

                entidades.add(entidade);

            }
        }
    }


}
