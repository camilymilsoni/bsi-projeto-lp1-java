package br.com.avaliacao_2.view;

import javax.swing.JOptionPane;
import java.awt.Image;
import java.awt.Graphics;
import javax.swing.ImageIcon;
import br.com.avaliacao_2.dto.FuncionarioDTO;
import java.awt.Toolkit;

public class PrincipalVIEW extends javax.swing.JFrame {

    public PrincipalVIEW(FuncionarioDTO funcionarioDTO) {
        initComponents();
        setIcon();
        this.setLocationRelativeTo(null);
        if (funcionarioDTO.getTipo_fun().equalsIgnoreCase("COMUM")) {
            itemMenuFuncionario.setVisible(false);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenu1 = new javax.swing.JMenu();
        ImageIcon imageicon = new ImageIcon(getClass().getResource("imagens/livros.jpg"));
        Image image = imageicon.getImage();
        desktopPane = new javax.swing.JDesktopPane(){
            public void paintComponent(Graphics graphics){
                graphics.drawImage(image, 0, 0, getWidth(), getHeight(), this);
            }
        };
        menuBar = new javax.swing.JMenuBar();
        menuCadastro = new javax.swing.JMenu();
        itemMenuCliente = new javax.swing.JMenuItem();
        itemMenuLivro = new javax.swing.JMenuItem();
        itemMenuExemplar = new javax.swing.JMenuItem();
        itemMenuFuncionario = new javax.swing.JMenuItem();
        menuEmprestimo = new javax.swing.JMenu();
        itemMenuEmprestimo = new javax.swing.JMenuItem();
        menuSair = new javax.swing.JMenu();

        jMenuItem1.setText("jMenuItem1");

        jMenuItem3.setText("jMenuItem3");

        jMenu1.setText("jMenu1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        menuCadastro.setMnemonic('c');
        menuCadastro.setText("Cadastro");

        itemMenuCliente.setText("Cliente");
        itemMenuCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemMenuClienteActionPerformed(evt);
            }
        });
        menuCadastro.add(itemMenuCliente);

        itemMenuLivro.setMnemonic('f');
        itemMenuLivro.setText("Livro");
        itemMenuLivro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemMenuLivroActionPerformed(evt);
            }
        });
        menuCadastro.add(itemMenuLivro);

        itemMenuExemplar.setMnemonic('p');
        itemMenuExemplar.setText("Exemplar");
        itemMenuExemplar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemMenuExemplarActionPerformed(evt);
            }
        });
        menuCadastro.add(itemMenuExemplar);

        itemMenuFuncionario.setText("Funcionário");
        itemMenuFuncionario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemMenuFuncionarioActionPerformed(evt);
            }
        });
        menuCadastro.add(itemMenuFuncionario);

        menuBar.add(menuCadastro);

        menuEmprestimo.setText("Empréstimo");

        itemMenuEmprestimo.setText("Realizar Empréstimo");
        itemMenuEmprestimo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemMenuEmprestimoActionPerformed(evt);
            }
        });
        menuEmprestimo.add(itemMenuEmprestimo);

        menuBar.add(menuEmprestimo);

        menuSair.setMnemonic('s');
        menuSair.setText("Sair");
        menuSair.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menuSairMouseClicked(evt);
            }
        });
        menuBar.add(menuSair);

        setJMenuBar(menuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(desktopPane, javax.swing.GroupLayout.DEFAULT_SIZE, 1255, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(desktopPane, javax.swing.GroupLayout.DEFAULT_SIZE, 742, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void menuSairMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuSairMouseClicked
        sair();
    }//GEN-LAST:event_menuSairMouseClicked

    private void itemMenuLivroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemMenuLivroActionPerformed
        abreLivroVIEW();
    }//GEN-LAST:event_itemMenuLivroActionPerformed

    private void itemMenuExemplarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemMenuExemplarActionPerformed
        abreExemplarVIEW();
    }//GEN-LAST:event_itemMenuExemplarActionPerformed

    private void itemMenuClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemMenuClienteActionPerformed
        abreClienteVIEW();
    }//GEN-LAST:event_itemMenuClienteActionPerformed

    private void itemMenuEmprestimoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemMenuEmprestimoActionPerformed
        abreEmprestimoVIEW();
    }//GEN-LAST:event_itemMenuEmprestimoActionPerformed

    private void itemMenuFuncionarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemMenuFuncionarioActionPerformed
        abreFuncionarioVIEW();
    }//GEN-LAST:event_itemMenuFuncionarioActionPerformed

    private void sair(){
        Object[] options = { "Sair", "Cancelar" };
        if(JOptionPane.showOptionDialog(null, "Deseja sair do sistema?", "Informação", 
                JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[0]) == 0){
            System.exit(0);
        } 
    } 
 
    private void abreClienteVIEW(){
        ClienteVIEW clienteVIEW = new ClienteVIEW();
        this.desktopPane.add(clienteVIEW);
        clienteVIEW.setVisible(true); 
        clienteVIEW.setPosicao();
    }

    private void abreLivroVIEW(){
        LivroVIEW livroVIEW = new LivroVIEW();
        this.desktopPane.add(livroVIEW);
        livroVIEW.setVisible(true); 
        livroVIEW.setPosicao();
    }

    private void abreExemplarVIEW(){
        ExemplarVIEW exemplarVIEW = new ExemplarVIEW();
        this.desktopPane.add(exemplarVIEW);
        exemplarVIEW.setVisible(true); 
        exemplarVIEW.setPosicao();
    }
    
    private void abreEmprestimoVIEW() {
        EmprestimoVIEW emprestimoVIEW = new EmprestimoVIEW();
        this.desktopPane.add(emprestimoVIEW);
        emprestimoVIEW.setVisible(true);
        emprestimoVIEW.setPosicao();
    }
    
    private void abreFuncionarioVIEW() {
        FuncionarioVIEW funcionarioVIEW = new FuncionarioVIEW();
        this.desktopPane.add(funcionarioVIEW);
        funcionarioVIEW.setVisible(true);
        funcionarioVIEW.setPosicao();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDesktopPane desktopPane;
    private javax.swing.JMenuItem itemMenuCliente;
    private javax.swing.JMenuItem itemMenuEmprestimo;
    private javax.swing.JMenuItem itemMenuExemplar;
    private javax.swing.JMenuItem itemMenuFuncionario;
    private javax.swing.JMenuItem itemMenuLivro;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JMenu menuCadastro;
    private javax.swing.JMenu menuEmprestimo;
    private javax.swing.JMenu menuSair;
    // End of variables declaration//GEN-END:variables

    private void setIcon() {
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("imagens/iconeLivro.png")));
    }

}
