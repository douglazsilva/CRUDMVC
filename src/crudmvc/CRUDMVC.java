/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crudmvc;

import controller.ProdutoCtrl;
import model.Produto;
import model.ProdutoDAO;
import view.FormProduto;

/**
 *
 * @author Douglaz
 */
public class CRUDMVC {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        Produto prod = new Produto();
        ProdutoDAO pDAO = new ProdutoDAO();
        FormProduto form = new FormProduto();
        
        ProdutoCtrl ctrl = new ProdutoCtrl(prod, pDAO, form);
        ctrl.iniciar();
        form.setVisible(true);
        
    }
    
}
