package modelo;

import conexaobd.ConexaoPostgreSQL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;

import javax.swing.JOptionPane;

public class Venda {
    private Integer codigo_ven;
    private Time horario;
    private Integer valortotal;
    private Integer codigo_fun;

    public Venda() {
    }

    public Venda(Integer codigo_ven, Time horario, Integer valortotal, Integer codigo_fun) {
        this.codigo_ven = codigo_ven;
        this.horario = horario;
        this.valortotal = valortotal;
        this.codigo_fun = codigo_fun;
    }

    // Getters e Setters
    public Integer getCodigoVen() {
        return codigo_ven;
    }

    public void setCodigoVen(Integer codigo_ven) {
        this.codigo_ven = codigo_ven;
    }

    public Time getHorario() {
        return horario;
    }

    public void setHorario(Time horario) {
        this.horario = horario;
    }

    public Integer getValorTotal() {
        return valortotal;
    }

    public void setValorTotal(Integer valortotal) {
        this.valortotal = valortotal;
    }

    public Integer getCodigoFun() {
        return codigo_fun;
    }

    public void setCodigoFun(Integer codigo_fun) {
        this.codigo_fun = codigo_fun;
    }

    // Método para inserir uma venda no banco de dados
    public void inserirVenda() {
        ConexaoPostgreSQL conexao = new ConexaoPostgreSQL();
        Connection conn = conexao.getConexao();
        PreparedStatement ps = null;
        String sql = "INSERT INTO venda (horario, valortotal, codigo_fun) VALUES (?, ?, ?)";

        try {
            ps = conn.prepareStatement(sql);
            ps.setTime(1, this.horario);
            ps.setInt(2, this.valortotal);
            ps.setInt(3, this.codigo_fun);
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Venda cadastrada com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar venda: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                if (ps != null) ps.close();
                conexao.disconnect();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // Método para buscar uma venda pelo código
    public ResultSet buscarVendaPorCodigo(int codigo) {
        ConexaoPostgreSQL conexao = new ConexaoPostgreSQL();
        Connection conn = conexao.getConexao();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT codigo_ven, horario, valortotal, codigo_fun FROM venda WHERE codigo_ven = ?";

        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, codigo);
            rs = ps.executeQuery();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao buscar venda: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }

        return rs;
    }

    public ResultSet buscarVenda(String tipoBusca, int idBusca, int codFuncionarioBusca) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'buscarVenda'");
    }
}
