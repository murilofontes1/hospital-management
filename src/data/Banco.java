package src.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Banco {
    private Connection db = null; // criando a conexao e o statemente
    private Statement statement = null;

    public Banco() {
        try {
            this.db = DriverManager.getConnection("jdbc:sqlite:src/data/data.db"); // carregando o driver e iniciando a
                                                                                   // conexao so quando inicia a classe
            this.statement = this.db.createStatement(); // iniciando o statement
            this.statement.setQueryTimeout(5); // Espera só por 5 segundos para conectar
        } catch (SQLException e) {
            System.out.println("Erro na conexão");
            System.out.println(e);
        }
    }

    public void disconnect() { // funcao para desconectar o banco
        try {
            if (db != null) {
                db.close();
                statement.close();
            }
        } catch (SQLException e) {
            System.out.println("Erro na hora de fechar conexão");
            System.out.println(e);
        }
    }

    public void queryInsup(String query) { // funcao para querys de insert e update
        try {
            statement.executeUpdate(query);
        } catch (SQLException e) {
            System.out.println("Erro na query");
            System.out.println(e);
        }
    }

    public ResultSet queryBusca(String query_busca) { // funcoes para query select
        ResultSet rs = null;
        try {
            rs = statement.executeQuery(query_busca);
            return rs;
        } catch (SQLException e) {
            System.out.println("Erro na query");
            System.out.println(e);
            return rs;
        }
    }

    public void testConnection(Statement statement) { // teste de conexao
        try {
            ResultSet rs = statement.executeQuery("SELECT 1");
            if (rs.next()) {
                System.out.println("Conexão bem sucedida!");
            } else {
                System.out.println("Conexão falhou!");
            }
        } catch (SQLException e) {
            System.out.println("Erro na query");
            System.out.println(e);
        }
    }
}