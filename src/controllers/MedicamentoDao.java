package src.controllers;

import java.sql.ResultSet;
import java.sql.SQLException;

import src.data.Banco;
import src.models.Medicamento;
import src.utils.FuncUtils;

public class MedicamentoDao {
    Banco db;

    public MedicamentoDao() {
        db = new Banco();
    }

    public void cadastrarMedicamento(Medicamento medicamento) {
        String query = String.format("INSERT INTO Medicamento (nome) VALUES ('%s');", medicamento.getNome());
        db.querry_insup(query);
    }

    public Medicamento buscaMedicamento(String nome) throws SQLException {
        String query = String.format("SELECT * FROM Medicamento WHERE nome = '%s';", nome);
        ResultSet rs = db.querry_busca(query);
        if (rs.next() && rs != null) {
            String nomeMed = rs.getString("nome");
            String codMedicamento = rs.getString("id_medicamento");
            Medicamento medicamento = new Medicamento(codMedicamento, nomeMed);
            rs.close();
            return medicamento;
        }
        System.out.println("Medicamento não encontrado.");
        rs.close();
        return null;
    }

    public void editaMedicamento(Medicamento medicamento) {
        String query = String.format("UPDATE Medicamento SET nome = '%s' WHERE id_medicamento = '%s';",
                medicamento.getNome(), medicamento.getCodMedicamento());
        db.querry_insup(query);
    }

    public void excluirMedicamento(Medicamento medicamento) throws SQLException {
        if (medicamento != null) {
            String querry = "DELETE FROM Medicamento WHERE id_medicamento = '" + medicamento.getCodMedicamento() + "';";
            db.querry_insup(querry);
        }
    }

    public void listarMedicamentos() throws SQLException {
        String query = "SELECT * FROM Medicamento;";
        ResultSet rs = db.querry_busca(query);

        System.out.printf("|Cod%s|Nome\n", FuncUtils.spacesGenerator(4));

        while (rs.next()) {
            String codMedicamento = rs.getString("id_medicamento");
            String nome = rs.getString("nome");
            System.out.printf("|%-7s|%-30s\n", codMedicamento, nome);
        }
        rs.close();
    }
}
