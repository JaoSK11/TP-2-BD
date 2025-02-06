package conexaobd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoPostgreSQL {
    private static final String URL = "jdbc:postgresql://127.0.0.1:5432/BD2 TP2";
    private static final String USUARIO = "root";
    private static final String SENHA = "123";

    public static Connection conectar() {
        try {
            return DriverManager.getConnection(URL, USUARIO, SENHA);
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao conectar ao banco de dados!", e);
        }
    }

    public static void main(String[] args) {
        Connection conexao = conectar();
        if (conexao != null) {
            System.out.println("Conectado ao PostgreSQL!");
        }
    }

    public Connection getConexao() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getConexao'");
    }

    public void disconnect() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'disconnect'");
    }
}
