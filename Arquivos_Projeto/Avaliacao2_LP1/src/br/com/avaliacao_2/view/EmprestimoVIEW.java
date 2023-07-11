package br.com.avaliacao_2.view;

import java.awt.Dimension;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.sql.ResultSet;
import br.com.avaliacao_2.dto.EmprestimoDTO;
import br.com.avaliacao_2.ctr.EmprestimoCTR;
import br.com.avaliacao_2.dto.ExemplarDTO;
import br.com.avaliacao_2.ctr.ExemplarCTR;
import br.com.avaliacao_2.dto.ClienteDTO;
import br.com.avaliacao_2.ctr.ClienteCTR;
import java.text.SimpleDateFormat;
import java.util.Date;

public class EmprestimoVIEW extends javax.swing.JInternalFrame {
    EmprestimoCTR emprestimoCTR = new EmprestimoCTR();
    EmprestimoDTO emprestimoDTO = new EmprestimoDTO();
    ExemplarCTR exemplarCTR = new ExemplarCTR();
    ExemplarDTO exemplarDTO = new ExemplarDTO();
    ClienteDTO clienteDTO = new ClienteDTO();
    ClienteCTR clienteCTR = new ClienteCTR();
    
    ResultSet rs;
    DefaultTableModel modelo_jtl_consultar_cli;
    DefaultTableModel modelo_jtl_consultar_exemplar;
    DefaultTableModel modelo_jtl_consultar_emprestimo;
    
    public void setPosicao() {
        Dimension d = this.getDesktopPane().getSize();
        this.setLocation((d.width - this.getSize().width) / 2, (d.height - this.getSize().height) / 2); 
    }
    
    public void gravar(){        
        emprestimoDTO.setData_emprest(new Date());
        emprestimoDTO.setQtd_exemplar_emprest(Integer.parseInt(String.valueOf(calculaQtdExemplar())));            
        clienteDTO.setId_cli(Integer.parseInt(String.valueOf(jtl_consultar_cli.getValueAt(jtl_consultar_cli.getSelectedRow(), 0))));
        
        JOptionPane.showMessageDialog(null, emprestimoCTR.inserirEmprestimo(emprestimoDTO, clienteDTO, jtl_consultar_emprestimo));
    }
    
    private void preencheTabelaCliente(String nome_cli) {
        try {
            modelo_jtl_consultar_cli.setNumRows(0);
            clienteDTO.setNome_cli(nome_cli);
            rs = clienteCTR.consultarCliente(clienteDTO, 1);
            while(rs.next()) {
                modelo_jtl_consultar_cli.addRow(new Object[] {
                    rs.getString("id_cli"),
                    rs.getString("nome_cli")
                });
            }
        }
        catch (Exception erTab) {
            System.out.println("Erro SQL: " +erTab);
        }
    }
    
    private void preencheTabelaExemplar(String edicao_exemplar) {
        try{
            modelo_jtl_consultar_exemplar.setNumRows(0);
            exemplarDTO.setEdicao_exemplar(edicao_exemplar);
            rs = exemplarCTR.consultarExemplar(exemplarDTO, 3);
            while(rs.next()) {
                modelo_jtl_consultar_exemplar.addRow(new Object[] {
                    rs.getString("id_exemplar"),
                    rs.getString("titulo_livro"),
                    rs.getString("edicao_exemplar")
                });
            }
        }
        catch (Exception erTab) {
            System.out.println("Erro SQL: " +erTab);
        }
    }
    
    private void adicionaExemplarSelecionado(int id_cli, int id_exemplar) {
        try{            
            modelo_jtl_consultar_emprestimo.addRow(new Object[] {
                id_cli, 
                id_exemplar,
                mostraDataEmprest()
            });
        }
        catch (Exception erTab) {
            System.out.println("Erro SQL: " +erTab);
        }
    }
    
    private void removeEmprestimoSelecionado(int linha_selecionada) {
        try {
            if(linha_selecionada >=0) {
                modelo_jtl_consultar_emprestimo.removeRow(linha_selecionada);
            }
        }
        catch (Exception erTab) {
            System.out.println("Erro SQL: " +erTab);
        }
    }
    
    private int calculaQtdExemplar(){
        try{
            int cont = 0;
            double total = 0;
            for (cont = 0; cont < jtl_consultar_emprestimo.getRowCount(); cont++) {
                total += cont;
            }
            return cont;
        }
        catch (Exception erTab) {
            System.out.println("Erro SQL: " +erTab);
        }
        return 0;
    }
    
    private void calculaMostraDataDevolucao() {
        Date dataDev = new Date();
        
        SimpleDateFormat formataData = new SimpleDateFormat("dd/MM/yyyy");  
            
        dataDev.setDate(dataDev.getDate() + Integer.parseInt(String.valueOf(jtl_consultar_emprestimo.getValueAt(jtl_consultar_emprestimo.getSelectedRow(), 3))));
             
        String dataDevFormatada = formataData.format(dataDev);
                
        modelo_jtl_consultar_emprestimo.setValueAt(dataDevFormatada, jtl_consultar_emprestimo.getSelectedRow(), 4);
    }
    
    private String mostraDataEmprest(){
        Date dataEmprest = new Date();
        
        SimpleDateFormat formataData = new SimpleDateFormat("dd/MM/yyyy");
        
        String dataEmprestFormatada = formataData.format(dataEmprest);
        
        return dataEmprestFormatada;
    }
    
    private void liberaCampos (boolean a) {
        pesquisa_nome_cli.setEnabled(a);
        btnPesquisarCli.setEnabled(a);
        jtl_consultar_cli.setEnabled(a);
        pesquisa_edicao_exemplar.setEnabled(a);
        btnPesquisarExemplar.setEnabled(a);
        jtl_consultar_exemplar.setEnabled(a);
        btnAdd.setEnabled(a);
        btnRem.setEnabled(a);
        jtl_consultar_emprestimo.setEnabled(a);
    }
    
    private void limpaCampos() {
        pesquisa_nome_cli.setText("");
        modelo_jtl_consultar_cli.setNumRows(0);
        pesquisa_edicao_exemplar.setText("");
        modelo_jtl_consultar_exemplar.setNumRows(0);
        modelo_jtl_consultar_emprestimo.setNumRows(0);
    }
    
    public void liberaBotoes (boolean a, boolean b, boolean c, boolean d) {
        btnNovo.setEnabled(a);
        btnSalvar.setEnabled(b);
        btnCancelar.setEnabled(c);
        btnSair.setEnabled(d);
    }
    
    private boolean verificaPreenchimentoClienteExemplar() {
        if(jtl_consultar_cli.getSelectedRowCount() <=0) {
            JOptionPane.showMessageDialog(null, "Deve ser selecionado um cliente!");
            jtl_consultar_cli.requestFocus();
            return false;
        }
        else{
            if(jtl_consultar_exemplar.getSelectedRowCount() <= 0){
                JOptionPane.showMessageDialog(null, "Deve ser selecionado um exemplar!");
                jtl_consultar_exemplar.requestFocus();
                return false;
            }
            else{
                return true;
            }
        }
    }
    
    private boolean verificaPreenchimentoEmprestimoDias(){
        if(jtl_consultar_emprestimo.getSelectedRowCount() <= 0){
            JOptionPane.showMessageDialog(null, "Deve ser selecionado um empréstimo!");
            jtl_consultar_emprestimo.requestFocus();
            return false;
        }
        else {
            int verifica =0;
            for (int cont = 0; cont < jtl_consultar_emprestimo.getRowCount(); cont++) {
                if(String.valueOf(jtl_consultar_emprestimo.getValueAt(cont, 3)).equalsIgnoreCase("null")) {
                    verifica++;
                }
            }
            if(verifica >0) {
                JOptionPane.showMessageDialog(null, 
                        "Deve ser informada a quantidade de dias do empréstimo!");
                jtl_consultar_emprestimo.requestFocus();
                return false;
            }
            else{
                return true;
            }
        }
    }
    
    private boolean verificaClientes(){
        
        int x = Integer.parseInt(String.valueOf(jtl_consultar_emprestimo.getValueAt(0, 0)));
        
        for(int cont = 0; cont < jtl_consultar_emprestimo.getRowCount(); cont++) {
            if(Integer.parseInt(String.valueOf(jtl_consultar_emprestimo.getValueAt(cont, 0))) != x){
                JOptionPane.showMessageDialog(null, "É permitido um cliente por empréstimo!");
                return false;
            }
        }
        return true;
    }
    
    private boolean verificaPreenchimentoEmprestimo(){
        if(jtl_consultar_emprestimo.getSelectedRowCount() <= 0){
            JOptionPane.showMessageDialog(null, "Deve ser selecionado o empréstimo a ser removido!");
            jtl_consultar_emprestimo.requestFocus();
            return false;
        }
        else{
            return true;
        }
    }
    
    public EmprestimoVIEW() {
        initComponents();
        liberaCampos(false);
        liberaBotoes(true, false, false, true);
        modelo_jtl_consultar_cli = (DefaultTableModel) jtl_consultar_cli.getModel();
        modelo_jtl_consultar_exemplar = (DefaultTableModel) jtl_consultar_exemplar.getModel();
        modelo_jtl_consultar_emprestimo = (DefaultTableModel) jtl_consultar_emprestimo.getModel();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        pesquisa_nome_cli = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtl_consultar_cli = new javax.swing.JTable();
        btnPesquisarCli = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        pesquisa_edicao_exemplar = new javax.swing.JTextField();
        btnPesquisarExemplar = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jtl_consultar_exemplar = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jtl_consultar_emprestimo = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        btnNovo = new javax.swing.JButton();
        btnSalvar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        btnSair = new javax.swing.JButton();
        btnAdd = new javax.swing.JButton();
        btnRem = new javax.swing.JButton();

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14))); // NOI18N

        jLabel2.setText("Cliente:");

        jtl_consultar_cli.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID Cliente", "Nome"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jtl_consultar_cli.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane1.setViewportView(jtl_consultar_cli);
        if (jtl_consultar_cli.getColumnModel().getColumnCount() > 0) {
            jtl_consultar_cli.getColumnModel().getColumn(0).setResizable(false);
            jtl_consultar_cli.getColumnModel().getColumn(0).setPreferredWidth(10);
            jtl_consultar_cli.getColumnModel().getColumn(1).setResizable(false);
            jtl_consultar_cli.getColumnModel().getColumn(1).setPreferredWidth(85);
        }

        btnPesquisarCli.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/avaliacao_2/view/imagens/pesquisar.png"))); // NOI18N
        btnPesquisarCli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPesquisarCliActionPerformed(evt);
            }
        });

        jLabel5.setText("Exemplar:");

        btnPesquisarExemplar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/avaliacao_2/view/imagens/pesquisar.png"))); // NOI18N
        btnPesquisarExemplar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPesquisarExemplarActionPerformed(evt);
            }
        });

        jtl_consultar_exemplar.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID Exemplar", "Título", "Edição"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jtl_consultar_exemplar.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane2.setViewportView(jtl_consultar_exemplar);
        if (jtl_consultar_exemplar.getColumnModel().getColumnCount() > 0) {
            jtl_consultar_exemplar.getColumnModel().getColumn(0).setResizable(false);
            jtl_consultar_exemplar.getColumnModel().getColumn(1).setResizable(false);
            jtl_consultar_exemplar.getColumnModel().getColumn(2).setResizable(false);
        }

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pesquisa_nome_cli, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnPesquisarCli))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pesquisa_edicao_exemplar, javax.swing.GroupLayout.PREFERRED_SIZE, 306, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnPesquisarExemplar))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(pesquisa_nome_cli, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(btnPesquisarCli))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel5)
                        .addComponent(pesquisa_edicao_exemplar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnPesquisarExemplar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14))); // NOI18N

        jtl_consultar_emprestimo.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID Cliente", "ID Exemplar", "Data Empréstimo", "Qtd Dias", "Data Devolução"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, true, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jtl_consultar_emprestimo.setSelectionMode(javax.swing.ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        jtl_consultar_emprestimo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jtl_consultar_emprestimoKeyReleased(evt);
            }
        });
        jScrollPane3.setViewportView(jtl_consultar_emprestimo);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(21, Short.MAX_VALUE)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 545, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 265, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(23, Short.MAX_VALUE))
        );

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 17)); // NOI18N
        jLabel1.setText("Empréstimo");

        btnNovo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/avaliacao_2/view/imagens/novo.png"))); // NOI18N
        btnNovo.setText("Novo");
        btnNovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNovoActionPerformed(evt);
            }
        });

        btnSalvar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/avaliacao_2/view/imagens/salvar.png"))); // NOI18N
        btnSalvar.setText("Salvar");
        btnSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarActionPerformed(evt);
            }
        });

        btnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/avaliacao_2/view/imagens/cancelar.png"))); // NOI18N
        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        btnSair.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/avaliacao_2/view/imagens/sair.png"))); // NOI18N
        btnSair.setText("Sair");
        btnSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSairActionPerformed(evt);
            }
        });

        btnAdd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/avaliacao_2/view/imagens/add.png"))); // NOI18N
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        btnRem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/avaliacao_2/view/imagens/rem.png"))); // NOI18N
        btnRem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(371, 371, 371)
                        .addComponent(btnNovo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSalvar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCancelar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSair))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnRem, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(489, 489, 489)
                        .addComponent(jLabel1)))
                .addContainerGap(37, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel1)
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(106, 106, 106)
                            .addComponent(btnAdd)
                            .addGap(39, 39, 39)
                            .addComponent(btnRem))
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnNovo)
                    .addComponent(btnSalvar)
                    .addComponent(btnCancelar)
                    .addComponent(btnSair))
                .addGap(21, 21, 21))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNovoActionPerformed
        liberaCampos(true);
        liberaBotoes(false, true, true, true);
    }//GEN-LAST:event_btnNovoActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        limpaCampos();
        liberaCampos(false);
        modelo_jtl_consultar_cli.setNumRows(0);
        modelo_jtl_consultar_exemplar.setNumRows(0);
        liberaBotoes(true, false, false, true);
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSairActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnSairActionPerformed

    private void btnPesquisarCliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPesquisarCliActionPerformed
        preencheTabelaCliente(pesquisa_nome_cli.getText());
    }//GEN-LAST:event_btnPesquisarCliActionPerformed

    private void btnPesquisarExemplarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPesquisarExemplarActionPerformed
        preencheTabelaExemplar(pesquisa_edicao_exemplar.getText());
    }//GEN-LAST:event_btnPesquisarExemplarActionPerformed

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        if(verificaPreenchimentoClienteExemplar()){
            adicionaExemplarSelecionado(
                Integer.parseInt(String.valueOf(jtl_consultar_cli.getValueAt(
                        jtl_consultar_cli.getSelectedRow(), 0))),
                Integer.parseInt(String.valueOf(jtl_consultar_exemplar.getValueAt(
                        jtl_consultar_exemplar.getSelectedRow(), 0)))
            ); 
        }
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnRemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemActionPerformed
        if(verificaPreenchimentoEmprestimo()){
            removeEmprestimoSelecionado(jtl_consultar_emprestimo.getSelectedRow());
        }
    }//GEN-LAST:event_btnRemActionPerformed

    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarActionPerformed
        if(verificaPreenchimentoEmprestimoDias() && verificaClientes()) {
            gravar();
            limpaCampos();
            liberaCampos(false);
            liberaBotoes(true, false, false, true);
        }
    }//GEN-LAST:event_btnSalvarActionPerformed

    private void jtl_consultar_emprestimoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtl_consultar_emprestimoKeyReleased
        if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {
            calculaMostraDataDevolucao();
        }
    }//GEN-LAST:event_jtl_consultar_emprestimoKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnNovo;
    private javax.swing.JButton btnPesquisarCli;
    private javax.swing.JButton btnPesquisarExemplar;
    private javax.swing.JButton btnRem;
    private javax.swing.JButton btnSair;
    private javax.swing.JButton btnSalvar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jtl_consultar_cli;
    private javax.swing.JTable jtl_consultar_emprestimo;
    private javax.swing.JTable jtl_consultar_exemplar;
    private javax.swing.JTextField pesquisa_edicao_exemplar;
    private javax.swing.JTextField pesquisa_nome_cli;
    // End of variables declaration//GEN-END:variables
}
