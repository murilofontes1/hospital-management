package src.controllers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import src.data.Banco;
import src.models.Medicamento;

/*
 * Classe para manipular os dados de um medicamento
 * MedicamentoDao contém métodos para cadastrar, buscar, editar e excluir um medicamento
 */

public class MedicamentoDao {

    public static void cadastrarMedicamento(Medicamento medicamento, Banco db) {

            /* 
            * Método para cadastrar um medicamento
            * Recebe um objeto medicamento e um objeto de banco de dados
            * Insere os dados do medicamento no banco de dados
            */

        String query = String.format("INSERT INTO Medicamento (nome) VALUES ('%s');", medicamento.getNome());
        db.queryInsup(query);
    }

    public static Medicamento buscaMedicamento(String id, Banco db) throws SQLException {
            
                /*
                * Método para buscar um medicamento
                * Recebe um id de medicamento e um objeto de banco de dados
                * Retorna um objeto medicamento com os dados do medicamento encontrado
                */

        String query = String.format("SELECT * FROM Medicamento WHERE id_medicamento = '%s';", id);
        ResultSet rs = db.queryBusca(query);
        if (rs.next() && rs != null) {
            String nomeMed = rs.getString("nome");
            String codMedicamento = rs.getString("id_medicamento");
            Medicamento medicamento = new Medicamento(codMedicamento, nomeMed);
            return medicamento;
        }
        return null;
    }

    public static void editaMedicamento(Medicamento medicamento, Banco db) {

            /* 
            * Método para editar um medicamento
            * Recebe um objeto medicamento e um objeto de banco de dados
            * Edita os dados do medicamento no banco de dados
            */

        String query = String.format("UPDATE Medicamento SET nome = '%s' WHERE id_medicamento = '%s';",
                medicamento.getNome(), medicamento.getCodMedicamento());
        db.queryInsup(query);
    }

    public static void excluirMedicamento(Medicamento medicamento, Banco db) throws SQLException {
            
                /* 
                * Método para excluir um medicamento
                * Recebe um objeto medicamento e um objeto de banco de dados
                * Exclui os dados do medicamento no banco de dados
                */

        if (medicamento != null) {
            String querry = "DELETE FROM Medicamento WHERE id_medicamento = '" + medicamento.getCodMedicamento() + "';";
            db.queryInsup(querry);
        }
    }

    public static ArrayList<Medicamento> listarMedicamentos(Banco db) throws SQLException {
            
                /* 
                * Método para listar os medicamentos
                * Recebe um objeto de banco de dados
                * Retorna um ArrayList com os medicamentos encontrados
                */
                
        String query = "SELECT * FROM Medicamento;";
        ResultSet rs = db.queryBusca(query);
        ArrayList<Medicamento> medicamentos = new ArrayList<Medicamento>();

        while (rs.next()) {
            Medicamento medicamento = new Medicamento(rs.getString("id_medicamento"), rs.getString("nome"));
            medicamentos.add(medicamento);
        }
        return medicamentos;
    }
}
