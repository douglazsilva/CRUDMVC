/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import model.Produto;
import model.ProdutoDAO;
import view.FormProduto;

/**
 *
 * @author Douglaz
 */
public class ProdutoCtrl implements ActionListener {
    
    private Produto prod;
    private ProdutoDAO pDAO;
    private FormProduto form;

    public ProdutoCtrl(Produto prod, ProdutoDAO pDAO, FormProduto form) {
        this.prod = prod;
        this.pDAO = pDAO;
        this.form = form;
        
        this.form.btnSalvar.addActionListener(this);
        this.form.btnEditar.addActionListener(this);
        this.form.btnExcluir.addActionListener(this);
        this.form.btnLimpar.addActionListener(this);
        this.form.btnBuscar.addActionListener(this);
    }

    public void iniciar(){
        form.setTitle("Produtos");
        form.setLocationRelativeTo(null);
        form.txtId.setVisible(false);
    }
    
    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == form.btnSalvar){
            prod.setCodigo(form.txtCodigo.getText());
            prod.setNome(form.txtNome.getText());
            prod.setPreco(Double.parseDouble(form.txtPreco.getText()));
            prod.setQuantidade(Integer.parseInt(form.txtQuantidade.getText()));
            
            if(pDAO.registrar(prod)){
                JOptionPane.showMessageDialog(null, "Registro Salvo!");
                limparCampos();
            } else {
                JOptionPane.showMessageDialog(null, "Erro ao Salvar!");
                limparCampos();
            }
        }
        
        if(e.getSource() == form.btnEditar){
            prod.setId(Integer.parseInt(form.txtId.getText()));
            prod.setCodigo(form.txtCodigo.getText());
            prod.setNome(form.txtNome.getText());
            prod.setPreco(Double.parseDouble(form.txtPreco.getText()));
            prod.setQuantidade(Integer.parseInt(form.txtQuantidade.getText()));
            
            if(pDAO.alterar(prod)){
                JOptionPane.showMessageDialog(null, "Registro Alterado!");
                limparCampos();
            } else {
                JOptionPane.showMessageDialog(null, "Erro ao Alterar!");
                limparCampos();
            }
        }
        
        if(e.getSource() == form.btnExcluir){
            prod.setId(Integer.parseInt(form.txtId.getText()));            
            
            if(pDAO.excluir(prod)){
                JOptionPane.showMessageDialog(null, "Registro Excluído!");
                limparCampos();
            } else {
                JOptionPane.showMessageDialog(null, "Erro ao Excluir!");
                limparCampos();
            }
        }
        
        if(e.getSource() == form.btnBuscar){
            prod.setCodigo(form.txtCodigo.getText());            
            
            if(pDAO.buscar(prod)){
                
                form.txtId.setText(String.valueOf(prod.getId()));
                form.txtCodigo.setText(prod.getCodigo());
                form.txtNome.setText(prod.getNome());
                form.txtPreco.setText(String.valueOf(prod.getPreco()));
                form.txtQuantidade.setText(String.valueOf(prod.getQuantidade()));
                
            } else {
                JOptionPane.showMessageDialog(null, "Nenhum Registro!");
                limparCampos();
            }
        }
        
        if(e.getSource() == form.btnLimpar){
            limparCampos();
        }
    }
    
    public void limparCampos(){
        form.txtId.setText(null);
        form.txtCodigo.setText(null);
        form.txtNome.setText(null);
        form.txtPreco.setText(null);
        form.txtQuantidade.setText(null);
    }
    
}
