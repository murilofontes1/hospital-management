package src.controllers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import src.data.Banco;
import src.models.Enfermaria;
/*
 * Classe para manipular os dados de uma enfermaria
 * EnfermariaDao contém métodos para cadastrar, buscar, editar e excluir uma enfermaria
 */
public class EnfermariaDao {

    public static void cadastrarEnfermaria(Enfermaria enfermaria, Banco db) {

        /* 
         * Método para cadastrar uma enfermaria
         * Recebe um objeto enfermaria e um objeto de banco de dados
         * Insere os dados da enfermaria no banco de dados
         */    
        
        String query = String.format("INSERT INTO Enfermaria (qnt_leitos, leitos_disponiveis) VALUES ('%d', '%d');",
                enfermaria.getLeitosDisponiveis(), enfermaria.getLeitosDisponiveis());
        db.queryInsup(query);
    }

    public static Enfermaria buscaEnfermaria(String codEnfermaria, Banco db) throws SQLException {

            /* 
             * Método para buscar uma enfermaria
             * Recebe um código de enfermaria e um objeto de banco de dados
             * Retorna um objeto enfermaria com os dados da enfermaria encontrada
             */

        String query = String.format("SELECT * FROM Enfermaria WHERE id_enfermaria = '%s';", codEnfermaria);
        ResultSet rs = db.queryBusca(query);
        if (rs.next() && rs != null) {
            String cod = rs.getString("id_enfermaria");
            int qnt_leitos = rs.getInt("qnt_leitos");
            int leitos_disponiveis = rs.getInt("leitos_disponiveis");
            Enfermaria enfermaria = new Enfermaria(cod, qnt_leitos, leitos_disponiveis);
            return enfermaria;
        }
        System.out.println("Enfermaria não encontrada.");
        return null;
    }

    public static Enfermaria ultimaEnfermariaCadastrada(Banco db) throws SQLException {

            /* 
             * Método para buscar a última enfermaria cadastrada
             * Recebe um objeto de banco de dados
             * Retorna um objeto enfermaria com os dados da última enfermaria cadastrada
             */

        String query = "SELECT * FROM Enfermaria ORDER BY id_enfermaria DESC LIMIT 1;";
        ResultSet rs = db.queryBusca(query);
        if (rs.next() && rs != null) {
            String cod = rs.getString("id_enfermaria");
            int qnt_leitos = rs.getInt("qnt_leitos");
            int leitos_disponiveis = rs.getInt("leitos_disponiveis");
            Enfermaria enfermaria = new Enfermaria(cod, qnt_leitos, leitos_disponiveis);
            return enfermaria;
        }
        System.out.println("Enfermaria não encontrada.");
        return null;
    }

    public static void editaEnfermaria(Enfermaria enfermaria, Banco db) {

            /* 
             * Método para editar uma enfermaria
             * Recebe um objeto enfermaria e um objeto de banco de dados
             * Atualiza os dados da enfermaria no banco de dados
             */

        String query = String.format(
                "UPDATE Enfermaria SET qnt_leitos = '%d', leitos_disponiveis = '%d' WHERE id_enfermaria = '%s';",
                enfermaria.getQtdeLeitos(), enfermaria.getLeitosDisponiveis(), enfermaria.getCodEnfermaria());
        db.queryInsup(query);
    }

    public static void excluirEnfermaria(Enfermaria enfermaria, Banco db) throws SQLException {
        String query = String.format("DELETE FROM Enfermaria WHERE id_enfermaria = '%s';",
                enfermaria.getCodEnfermaria());
        db.queryInsup(query);
    }

    public static ArrayList<Enfermaria> listarEnfermarias(Banco db) throws SQLException {

            /* 
             * Método para listar as enfermarias
             * Recebe um objeto de banco de dados
             * Retorna um ArrayList com as enfermarias encontradas
             */

        String query = "SELECT * FROM Enfermaria;";
        ResultSet rs = db.queryBusca(query);
        ArrayList<Enfermaria> enfermarias = new ArrayList<Enfermaria>();
        while (rs.next()) {
            Enfermaria enfermaria = new Enfermaria();
            String codEnfermaria = rs.getString("id_enfermaria");
            int qnt_leitos = rs.getInt("qnt_leitos");
            int leitos_disponiveis = rs.getInt("leitos_disponiveis");
            enfermaria.setCodEnfermaria(codEnfermaria);
            enfermaria.setQtdeLeitos(qnt_leitos);
            enfermaria.setLeitosDisponiveis(leitos_disponiveis);
            enfermarias.add(enfermaria);
        }
        return enfermarias;
    }

    public static ArrayList<Enfermaria> listarEnfermariasDisponiveis(Banco db) throws SQLException {

            /* 
             * Método para listar as enfermarias disponíveis
             * Recebe um objeto de banco de dados
             * Retorna um ArrayList com as enfermarias disponíveis
             */
            
        String query = "SELECT * FROM Enfermaria WHERE leitos_disponiveis > 0;";
        ResultSet rs = db.queryBusca(query);
        ArrayList<Enfermaria> enfermarias = new ArrayList<Enfermaria>();
        while (rs.next()) {
            Enfermaria enfermaria = new Enfermaria();
            String codEnfermaria = rs.getString("id_enfermaria");
            int qnt_leitos = rs.getInt("qnt_leitos");
            int leitos_disponiveis = rs.getInt("leitos_disponiveis");
            enfermaria.setCodEnfermaria(codEnfermaria);
            enfermaria.setQtdeLeitos(qnt_leitos);
            enfermaria.setLeitosDisponiveis(leitos_disponiveis);
            enfermarias.add(enfermaria);
        }
        return enfermarias;
    }

}
