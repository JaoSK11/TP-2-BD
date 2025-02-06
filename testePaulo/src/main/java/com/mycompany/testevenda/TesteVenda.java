package com.mycompany.testevenda;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import javax.swing.JOptionPane;
import modelo.Venda;

public class TesteVenda {
    public static void main(String[] args) throws SQLException {
        // Criando uma nova venda
        Venda venda = new Venda();
        String horario = JOptionPane.showInputDialog("Digite o horário da venda (hh:mm:ss):");
        int valorTotal = Integer.parseInt(JOptionPane.showInputDialog("Digite o valor total da venda:"));
        int codigoFuncionario = Integer.parseInt(JOptionPane.showInputDialog("Digite o código do funcionário:"));

        venda.setHorario(Time.valueOf(horario));
        venda.setValorTotal(valorTotal);
        venda.setCodigoFun(codigoFuncionario);

        venda.inserirVenda();

        // Buscando uma venda
        Venda vendaBusca = new Venda();
        ResultSet rs = null;

        String tipoBusca = JOptionPane.showInputDialog("Digite o tipo de busca (id ou funcionario):");
        int idBusca = 0;
        int codFuncionarioBusca = 0;

        if (tipoBusca.equalsIgnoreCase("id")) {
            idBusca = Integer.parseInt(JOptionPane.showInputDialog("Digite o ID da venda:"));
        } else if (tipoBusca.equalsIgnoreCase("funcionario")) {
            codFuncionarioBusca = Integer.parseInt(JOptionPane.showInputDialog("Digite o código do funcionário:"));
        } else {
            System.out.println("Tipo de busca inválido. Encerrando o programa.");
            return;
        }

        try {
            rs = vendaBusca.buscarVenda(tipoBusca, idBusca, codFuncionarioBusca);

            if (!rs.isBeforeFirst()) {
                System.out.println("Nenhuma venda encontrada.");
            } else {
                while (rs.next()) {
                    int codVenda = rs.getInt(1);
                    Time hora = rs.getTime(2);
                    int valor = rs.getInt(3);
                    int funcionario = rs.getInt(4);

                    System.out.println("\nVenda Encontrada:");
                    System.out.println("Código: " + codVenda);
                    System.out.println("Horário: " + hora);
                    System.out.println("Valor Total: " + valor);
                    System.out.println("Código do Funcionário: " + funcionario);
                }
            }
        } catch (SQLException ex) {
            System.out.println("Erro ao buscar venda: " + ex.getMessage());
        } finally {
            if (rs != null) rs.close();
        }
    }
}
