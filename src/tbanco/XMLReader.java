/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tbanco;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import javax.swing.JTextArea;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import tbanco.model.Agregacao;
import tbanco.model.Atributo;
import tbanco.model.Entidade;
import tbanco.model.IAtributavel;
import tbanco.model.IEntidavel;
import tbanco.model.IRelacionavel;
import tbanco.model.ModEntRel;
import tbanco.model.relacionamento.AbstractRelacionamento;
import tbanco.model.relacionamento.AbstractRelacionavel;
import tbanco.model.relacionamento.RelacionamentoBinario;
import tbanco.model.relacionamento.RelacionamentoTernario;
import tbanco.model.relacionamento.RelacionamentoUnario;

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

                importEntidades(modEntRel, eElement, jTextArea);

                importAgregacoes(doc.getElementsByTagName("agregacao"), modEntRel, jTextArea);

                importRelacionamentos(modEntRel, eElement, jTextArea);
            }

        } catch (ParserConfigurationException | SAXException | IOException ex) {
            jTextArea.append(ex + "\n");
        }

        return modEntRel;
    }

    public static void importEntidades(IEntidavel entidavel, Element _eElement, JTextArea jTextArea) {
        NodeList entidadesNL = _eElement.getElementsByTagName("entidade");
        for (int temp = 0; temp < entidadesNL.getLength(); temp++) {

            Node nNode = entidadesNL.item(temp);

            System.out.println("\n------------->" + nNode.getNodeName());
            jTextArea.append("\n------------->" + nNode.getNodeName() + "\n");
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                Element eElement = (Element) nNode;

                String tipo = null;

                if (eElement.hasAttribute("tipo")) {
                    tipo = eElement.getAttribute("tipo");
                }

                System.out.println("Entidade nome: " + eElement.getAttribute("nome") + " é um " + tipo);
                jTextArea.append("Entidade nome: " + eElement.getAttribute("nome") + " é um " + tipo + "\n");

                Entidade entidade = new Entidade(eElement.getAttribute("nome"), tipo);

                importAtributos(entidade, eElement, jTextArea);

                entidavel.addEntidade(entidade);

            }
        }
    }

    public static void importAtributos(IAtributavel atributavel, Element eElement, JTextArea jTextArea) {
        NodeList atributos = eElement.getElementsByTagName("atributo");

        for (int tmp = 0; tmp < atributos.getLength(); tmp++) {
            Node item = atributos.item(tmp);
            if (item.hasAttributes()) {
                String source = null;
                String tipo = null;

                for (int i = 0; i < item.getAttributes().getLength(); i++) {

                    Node atributo = item.getAttributes().item(i);

                    if ("restricao".equals(atributo.getNodeName())) {
                        source = atributo.getNodeValue();
                    }

                    if ("tipo".equals(atributo.getNodeName())) {
                        tipo = atributo.getNodeValue();
                    }

                }
                System.out.println("Atributo : " + item.getTextContent() + " restrições: " + source + " tipo " + tipo);
                jTextArea.append("Atributo : " + item.getTextContent() + " restrições: " + source + " tipo " + tipo + "\n");
                atributavel.addAtributo(new Atributo(item.getTextContent(), source, tipo));
            } else {
                System.out.println("Atributo : " + item.getTextContent());
                jTextArea.append("Atributo : " + item.getTextContent() + "\n");
                atributavel.addAtributo(new Atributo(item.getTextContent()));
            }
        }
    }

    ///relacionavel somente para pesquisa
    public static void importRelacionamentos(IRelacionavel relacionaveis,
            Element _eElement, JTextArea jTextArea) {
        NodeList relacionamentosNL = _eElement.getElementsByTagName("relacionamento");
        for (int temp = 0; temp < relacionamentosNL.getLength(); temp++) {

            Node nNode = relacionamentosNL.item(temp);
            System.out.println("\n-------------->" + nNode.getNodeName());
            jTextArea.append("\n-------------->" + nNode.getNodeName() + "\n");
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                Element eElement = (Element) nNode;
                String nome = eElement.getAttribute("nome");
                System.out.println("Relacionamento nome: " + nome);
                jTextArea.append("Relacionamento nome: " + nome + "\n");

                NodeList cardinal = eElement.getElementsByTagName("cardinalidade");
                String cardinalidade = cardinal.item(0).getTextContent();

                AbstractRelacionamento relacionamento = null;

                NodeList relac = eElement.getElementsByTagName("relacionavel");

                switch (relac.getLength()) {
                    case 1: {
                        System.out.println("RELACIONAMENTO UNARIO");
                        NodeList papeis = eElement.getElementsByTagName("papel");
                        String papelOr = null, papelDt = null;
                        if (papeis.getLength() > 0) {
                            papelOr = papeis.item(0).getTextContent();
                        }
                        if (papeis.getLength() > 1) {
                            papelDt = papeis.item(1).getTextContent();
                        }
                        AbstractRelacionavel relacionavel = pesquisaRelacionavel(relacionaveis, relac.item(0).getTextContent());
                        relacionamento = new RelacionamentoUnario(nome, papelOr, papelDt, cardinalidade, relacionavel);
                    }
                    break;
                    case 2: {
                        System.out.println("RELACIONAMENTO BINARIO");
                        AbstractRelacionavel origem, destino;
                        origem = pesquisaRelacionavel(relacionaveis, relac.item(0).getTextContent());
                        destino = pesquisaRelacionavel(relacionaveis, relac.item(1).getTextContent());
                        relacionamento = new RelacionamentoBinario(nome, cardinalidade, origem, destino);
                    }
                    break;
                    case 3: {
                        System.out.println("RELACIONAMENTO TERNARIO");
                        AbstractRelacionavel topo, esquerdo, direito;
                        topo = pesquisaRelacionavel(relacionaveis, relac.item(0).getTextContent());
                        esquerdo = pesquisaRelacionavel(relacionaveis, relac.item(1).getTextContent());
                        direito = pesquisaRelacionavel(relacionaveis, relac.item(2).getTextContent());
                        relacionamento = new RelacionamentoTernario(nome, topo, esquerdo, direito, cardinalidade);
                    }
                    break;
                    default:
                        System.out.println("numero invalido de relacionaveis, abortado. " + relac.getLength());
                        jTextArea.append("numero invalido de relacionaveis, abortado. " + +relac.getLength() + "\n");
                        continue;
                }

                if (relacionamento != null) {
                    importAtributos(relacionamento, eElement, jTextArea);
                } else {
                    System.out.println("houve uma falha ao importar, abortado. " + relac.getLength());
                    jTextArea.append("houve uma falha ao importar, abortado. " + +relac.getLength() + "\n");
                }
            }
        }
    }

    static AbstractRelacionavel pesquisaRelacionavel(IRelacionavel relacionavel, String nome) {
        Iterator<AbstractRelacionavel> relacionaveis = relacionavel.getRelacionaveis();
        while (relacionaveis.hasNext()) {
            AbstractRelacionavel relac = relacionaveis.next();
            if (relac.getNome().equalsIgnoreCase(nome)) {
                return relac;
            }
        }
        System.err.println("ERRO: entidade não encontrada no grupo: " + nome);
        return null;
    }

    static void importAgregacoes(NodeList agsNodeList, ModEntRel modEntRel, JTextArea jTextArea) {

        for (int item = 0; item < agsNodeList.getLength(); item++) {

            Node agregNode = agsNodeList.item(item);

            if (agregNode.getNodeType() == Node.ELEMENT_NODE) {

                Element eElement = (Element) agregNode;

                Agregacao agregacao = new Agregacao(eElement.getAttribute("nome"));

                importEntidades(agregacao, eElement, jTextArea);

                importRelacionamentos(agregacao, eElement, jTextArea);

                importAtributos(agregacao, eElement, jTextArea);
            }

        }

    }
}
