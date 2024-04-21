package src.controllers;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import src.data.Banco;
import src.models.Internacao;
/*
 * Classe para manipular os dados de uma internação
 * InternacaoDao contém métodos para cadastrar, buscar, editar e excluir uma internação
 */
public class InternacaoDao {

    public static void cadastrarInternacao(Internacao internacao, Banco db) {

            /* 
            * Método para cadastrar uma internação
            * Recebe um objeto internação e um objeto de banco de dados
            * Insere os dados da internação no banco de dados
            */

        String query = String.format(
                "INSERT INTO Internacao (data_internacao, id_paciente, id_enfermaria) VALUES ('%tF','%s','%s');",
                internacao.getDataInternacao(), Integer.parseInt(internacao.getIdPaciente()),
                Integer.parseInt(internacao.getIdEnfermaria()));
        db.queryInsup(query);
    }

    public static Internacao buscaInternacao(String codInternacao, Banco db) throws SQLException {
            
                /*
                * Método para buscar uma internação
                * Recebe um código de internação e um objeto de banco de dados
                * Retorna um objeto internação com os dados da internação encontrada
                */

        String query = "SELECT * FROM Internacao WHERE id_internacao = '" + codInternacao + "';";
        ResultSet rs = db.queryBusca(query);
        if (rs.next()) {
            Internacao internacao = new Internacao();
            internacao.setCodInternacao(rs.getString("id_internacao"));

            String dataInternacaoStr = rs.getString("data_internacao");
            Date dataInternacao = Date.valueOf(dataInternacaoStr);
            internacao.setDataInternacao(dataInternacao);

            if (rs.getString("data_alta") != null) {
                String dataAltaStr = rs.getString("data_alta");
                Date dataAlta = Date.valueOf(dataAltaStr);
                internacao.setDataAlta(dataAlta);
            }

            internacao.setIdPaciente(rs.getString("id_paciente"));
            internacao.setIdEnfermaria(rs.getString("id_enfermaria"));
            return internacao;
        }
        return null;
    }

    public static void editarInternacao(Internacao internacao, Banco db) throws SQLException {

            /*
            * Método para editar uma internação
            * Recebe um objeto internação e um objeto de banco de dados
            * Atualiza os dados da internação no banco de dados
            */

        String query = String.format(
                "UPDATE Internacao SET data_internacao = '%tF', data_alta = '%tF', id_paciente = '%s', id_enfermaria = '%s' WHERE id_internacao = '%s';",
                internacao.getDataInternacao(), internacao.getDataAlta(), internacao.getIdPaciente(),
                internacao.getIdEnfermaria(), internacao.getCodInternacao());
        db.queryInsup(query);
    }

    public static void excluirInternacao(Internacao internacao, Banco db) throws SQLException {
            
                /*
                * Método para excluir uma internação
                * Recebe um objeto internação e um objeto de banco de dados
                * Remove os dados da internação do banco de dados
                */

        String query = String.format("DELETE FROM Internacao WHERE id_Internacao = '%s';",
                internacao.getCodInternacao());
        db.queryInsup(query);
    }

    public static ArrayList<Internacao> listarInternacoes(Banco db) throws SQLException {
            
                /*
                * Método para listar as internações
                * Recebe um objeto de banco de dados
                * Retorna um ArrayList com as internações encontradas
                */

        String query = "SELECT * FROM Internacao;";
        ResultSet rs = db.queryBusca(query);
        ArrayList<Internacao> internacoes = new ArrayList<Internacao>();
        while (rs.next()) {
            Internacao internacao = new Internacao();
            internacao.setCodInternacao(rs.getString("id_internacao"));

            String dataInternacaoStr = rs.getString("data_internacao");
            Date dataInternacao = Date.valueOf(dataInternacaoStr);
            internacao.setDataInternacao(dataInternacao);

            if (rs.getString("data_alta") != null) {
                String dataAltaStr = rs.getString("data_alta");
                Date dataAlta = Date.valueOf(dataAltaStr);
                internacao.setDataAlta(dataAlta);
            }

            internacao.setIdPaciente(rs.getString("id_paciente"));
            internacao.setIdEnfermaria(rs.getString("id_enfermaria"));

            internacoes.add(internacao);
        }
        return internacoes;
    }

    public static ArrayList<Internacao> listarInternacoesAtivas(Banco db) throws SQLException {
                
                    /*
                    * Método para listar as internações ativas
                    * Recebe um objeto de banco de dados
                    * Retorna um ArrayList com as internações ativas encontradas
                    */
                    
        String query = "SELECT * FROM Internacao WHERE data_alta IS NULL;";
        ResultSet rs = db.queryBusca(query);
        ArrayList<Internacao> internacoes = new ArrayList<Internacao>();
        while (rs.next()) {
            Internacao internacao = new Internacao();
            internacao.setCodInternacao(rs.getString("id_internacao"));

            String dataInternacaoStr = rs.getString("data_internacao");
            Date dataInternacao = Date.valueOf(dataInternacaoStr);
            internacao.setDataInternacao(dataInternacao);

            if (rs.getString("data_alta") != null) {
                String dataAltaStr = rs.getString("data_alta");
                Date dataAlta = Date.valueOf(dataAltaStr);
                internacao.setDataAlta(dataAlta);
            }

            internacao.setIdPaciente(rs.getString("id_paciente"));
            internacao.setIdEnfermaria(rs.getString("id_enfermaria"));

            internacoes.add(internacao);
        }
        return internacoes;
    }

}
