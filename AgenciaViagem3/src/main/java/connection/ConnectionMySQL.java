package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionMySQL {

    // URL de conexão com o banco de dados MySQL
    private static final String URL = "jdbc:mysql://localhost:3306/agenciadeviagem3";
    private static final String USUARIO = "root";
    private static final String SENHA = "123456";

    // Método para criar uma conexão com o banco de dados MySQL
    public static Connection createConnectionMySQL() throws SQLException {
        Connection connection = null;
        try {
            // Registre o driver JDBC do MySQL
           Class.forName("com.mysql.cj.jdbc.Driver");
            // Crie a conexão com o banco de dados
            connection = DriverManager.getConnection(URL, USUARIO, SENHA);
        } catch (ClassNotFoundException e) {
            throw new SQLException("Driver JDBC MySQL não encontrado: " + e.getMessage(), e);
        }
        return connection;
    }

    public static void main(String[] args) {
        Connection con = null;
        try {
            // Recupera uma conexão com o banco de dados
            con = createConnectionMySQL();

            if (con != null) {
                System.out.println("Conexão obtida com sucesso: " + con);
            } else {
                System.out.println("Falha ao obter conexão.");
            }
        } catch (SQLException e) {
            System.err.println("Erro ao criar a conexão: " + e.getMessage());
        } finally {
            // Certifique-se de fechar a conexão, independentemente do resultado
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    System.err.println("Erro ao fechar a conexão: " + e.getMessage());
                }
            }
        }
    }
}
