/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.testepaulo;
//import conexaobd.ConexaoBDPostgres;

import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import modelo.Cidade;


/**
 *
 * @author Aluno
 */
public class TestePaulo {

    public static void main(String[] args) throws SQLException {
       
        //Cidade insert = new Cidade("Vila Velha", "RJ");
        Cidade insert = new Cidade();
        String nomeCidade = null;
        String nomeEstado = null;
        
        nomeCidade = JOptionPane.showInputDialog("Digite o nome da Cidade");
        //number = Integer.parseInt(numero);
        nomeEstado = JOptionPane.showInputDialog("Digite o nome do Estado");
        
        insert.setNomeCidade(nomeCidade);
        insert.setEstado(nomeEstado);
        
        insert.inserirCidade();
             
        

        
          Cidade cidade = new Cidade();
          ResultSet rs = null;
          String tipoBusca = "id";

        try {
            //rs = cliente.buscarCliente((String) jBuscaTipo.getModel().getSelectedItem(), jBuscaDigitado.getText(),(String)jBuscaTipo.getModel().getSelectedItem());
            rs = cidade.buscarCidade(tipoBusca, 3, "Guarulhos");

            while (rs.next())
            {
                int codcidade = rs.getInt(1);
                String nameCidade = rs.getString(2);
                String nomeUF = rs.getString(3);
                
                System.out.println("Codigo da Cidade: " + codcidade);
                System.out.println("Nome da Cidade: " + nameCidade);
                System.out.println("Nome do Estado: " + nomeUF);
            }
        }catch (SQLException ex) {
             System.out.println("Não foi possivel localizar a cidade informada");
        }
    }
}
