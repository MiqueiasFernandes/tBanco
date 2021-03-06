/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tbanco;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import tbanco.chain.MERProcessor;
import tbanco.model.Agregacao;
import tbanco.model.Atributo;
import tbanco.model.Entidade;
import tbanco.model.IAtributavel;
import tbanco.model.ModEntRel;
import tbanco.model.relacionamento.AbstractRelacionamento;
import tbanco.model.relacionamento.AbstractRelacionavel;

/**
 *
 * @author mfernandes
 */
public class MainView extends javax.swing.JFrame {

    /**
     * Creates new form MainView
     */
    public MainView() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTree1 = new javax.swing.JTree();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jLabel1 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.tree.DefaultMutableTreeNode treeNode1 = new javax.swing.tree.DefaultMutableTreeNode("Empresa");
        javax.swing.tree.DefaultMutableTreeNode treeNode2 = new javax.swing.tree.DefaultMutableTreeNode("Pessoa");
        javax.swing.tree.DefaultMutableTreeNode treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("nome (cen)");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("sexo");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("relacionamento");
        javax.swing.tree.DefaultMutableTreeNode treeNode4 = new javax.swing.tree.DefaultMutableTreeNode("casa");
        treeNode3.add(treeNode4);
        treeNode2.add(treeNode3);
        treeNode1.add(treeNode2);
        jTree1.setModel(new javax.swing.tree.DefaultTreeModel(treeNode1));
        jScrollPane1.setViewportView(jTree1);

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane3.setViewportView(jTextArea1);

        jButton1.setText("MAPEAR!");
        jButton1.setToolTipText("");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Exportar");
        jButton2.setAutoscrolls(true);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel1.setBackground(new java.awt.Color(254, 254, 254));
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("...");
        jLabel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jLabel1.setOpaque(true);
        jScrollPane2.setViewportView(jLabel1);

        jMenu1.setText("Arquivo");

        jMenuItem1.setText("Importar XML");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Editar");
        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(109, Short.MAX_VALUE))
                            .addComponent(jScrollPane2)))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 404, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 83, Short.MAX_VALUE)
                            .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane2)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        JFileChooser jf = new JFileChooser("./");
        jf.showOpenDialog(this);
        modeloEntidadeRelacionamento = XMLReader.importMER(jf.getSelectedFile(), jTextArea1);
        JOptionPane.showMessageDialog(this, "Modelo importado com sucesso!");
        popularTree();
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    ModEntRel modeloEntidadeRelacionamento = null;

    void popularTree() {

        if (modeloEntidadeRelacionamento != null) {

            DefaultMutableTreeNode root = new DefaultMutableTreeNode(modeloEntidadeRelacionamento.getNome());

            addEntidades(root, modeloEntidadeRelacionamento.getEntidadesIterator());

            if (modeloEntidadeRelacionamento.hasAgregacoes()) {
                DefaultMutableTreeNode agregacoes = new DefaultMutableTreeNode("agregacoes");

                Iterator<Agregacao> agregacoesIT = modeloEntidadeRelacionamento.getAgregacoesIterator();

                while (agregacoesIT.hasNext()) {
                    Agregacao agregacao = agregacoesIT.next();

                    DefaultMutableTreeNode agreg = new DefaultMutableTreeNode(agregacao.getNome());

                    addAtributos(agregacao, agreg);
                    addRelacionamentos(agregacao, agreg);
                    addEntidades(agreg, agregacao.getEntidadesIterator());

                    agregacoes.add(agreg);
                }
                root.add(agregacoes);
            }
            jTree1.setModel(new DefaultTreeModel(root));
            jTree1.invalidate();
        }
    }

    void addEntidades(DefaultMutableTreeNode root, Iterator<Entidade> entidades) {

        while (entidades.hasNext()) {
            Entidade entidade = entidades.next();

            DefaultMutableTreeNode ent = new DefaultMutableTreeNode(entidade.getNome()
                    + (entidade.isSubtipo() ? " (" + entidade.getTipo() + ")" : ""));

            addAtributos(entidade, ent);
            if (entidade.hasRelacionamentos()) {
                addRelacionamentos(entidade, ent);
            }
            root.add(ent);
        }
    }

    void addAtributos(IAtributavel atributavel, DefaultMutableTreeNode ent) {
        Iterator<Atributo> atributos = atributavel.getAtributos().getAtributosIterator();
        while (atributos.hasNext()) {
            Atributo atributo = atributos.next();
            ent.add(new DefaultMutableTreeNode(atributo.toString()));
        }
    }

    void addRelacionamentos(AbstractRelacionavel relacionavel, DefaultMutableTreeNode root) {
        DefaultMutableTreeNode rel = new DefaultMutableTreeNode("Relacionamentos");
        Iterator<AbstractRelacionamento> relacionamentos = relacionavel.getRelacionamentosIterator();
        while (relacionamentos.hasNext()) {
            AbstractRelacionamento relacionamento = relacionamentos.next();
            DefaultMutableTreeNode relac = new DefaultMutableTreeNode(relacionamento.toString());
            addAtributos(relacionamento, relac);
            rel.add(relac);
        }
        root.add(rel);
    }


    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        MERProcessor processor = new MERProcessor();

        if (modeloEntidadeRelacionamento != null) {
            processor.tratar(modeloEntidadeRelacionamento);

            Iterator<Entidade> entidades = modeloEntidadeRelacionamento.getTabelaIterator();

            String modelo = "<html><body>\n";

            while (entidades.hasNext()) {
                Entidade tabela = entidades.next();
                String tbl = "<br>\n" + tabela.getNome() + " [ ";

                Iterator<Atributo> atributos = tabela.getAtributos().getAtributosIterator();

                while (atributos.hasNext()) {
                    Atributo atributo = atributos.next();
                    if (atributo.isNao_nulo()) {
                        tbl += "<u>";
                    }

                    if (atributo.isChave_estrangeira()) {
                        tbl += "#";
                    }

                    if (atributo.isChave_primaria()) {
                        tbl += "*";
                    }

                    tbl += atributo.getNome();

                    if (atributo.isNao_nulo()) {
                        tbl += "</u>";
                    }

                    if (atributos.hasNext()) {
                        tbl += ", ";
                    }

                }
                tbl += " ]";
                modelo += tbl;
            }
            modelo += "</body></html>";

            jLabel1.setText(modelo);
            jLabel1.repaint();
            System.out.println(modelo);
        } else {
            JOptionPane.showMessageDialog(this, "importe primeiro");
        }

    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed

        if (!jLabel1.getText().equals("...")) {
            JFileChooser jf = new JFileChooser("./");
            jf.showSaveDialog(this);

            File arq = jf.getSelectedFile().getAbsoluteFile();
            try {
                arq.createNewFile();

                FileWriter fw = new FileWriter(arq);

                if (arq.getName().contains(".html")) {
                    fw.write(jLabel1.getText());
                } else {

                    String str = jLabel1.getText();
                    str = str.replace("<html>", "");
                    str = str.replace("</html>", "");
                    str = str.replace("<body>", "");
                    str = str.replace("</body>", "");
                    str = str.replace("<br>", "");
                    str = str.replace("</u>", "");
                    str = str.replace("<u>", "ÑN");
                    fw.write(str);
                }

                fw.flush();
                fw.close();
                JOptionPane.showMessageDialog(this, "Arquivo salvo com sucesso");
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, ex);
            }
        } else {
            JOptionPane.showMessageDialog(this, "mapeie primeiro");
        }


    }//GEN-LAST:event_jButton2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTree jTree1;
    // End of variables declaration//GEN-END:variables
}
