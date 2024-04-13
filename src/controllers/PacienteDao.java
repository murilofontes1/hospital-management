package src.controllers;

import java.sql.ResultSet;
import java.sql.SQLException;
import src.data.Banco;
import src.models.Paciente;
import src.utils.FuncUtils;

import java.sql.Date;

public class PacienteDao {
    Banco db;

    public PacienteDao() {
        db = new Banco();
    }

    public void cadastrarPaciente(Paciente paciente) {
        String querry = String.format(
                "INSERT INTO Paciente (nome, cpf, telefone, data_nascimento, sexo, internado, plano_saude)VALUES ('%s', '%s', '%s', '%tF', %b, %b, %b);",
                paciente.getNome(), paciente.getCpf(), paciente.getTelefone(), paciente.getDataNasc(),
                paciente.isSexo(),
                paciente.isInternado(), paciente.isPlanoDeSaude());
        db.querry_insup(querry);
    }

    public Paciente buscaPaciente(String codPaciente) throws SQLException {
        String query = "SELECT * FROM Paciente WHERE id_paciente = '" + codPaciente + "';";
        ResultSet rs = db.querry_busca(query);
        Paciente paciente = new Paciente();

        if (rs.next() && rs != null) {
            paciente.setNome(rs.getString("nome"));
            paciente.setCpf(rs.getString("cpf"));
            paciente.setTelefone(rs.getString("telefone"));

            String dataNascStr = rs.getString("data_nascimento");
            Date dataNasc = Date.valueOf(dataNascStr);
            paciente.setDataNasc(dataNasc);

            paciente.setCodPaciente(rs.getString("id_paciente"));
            paciente.setSexo(rs.getBoolean("sexo"));
            paciente.setPlanoDeSaude(rs.getBoolean("plano_saude"));
            paciente.setInternado(rs.getBoolean("internado"));
        } else {
            System.out.println("Paciente não encontrado.");
            return null;
        }
        return paciente;
    }

    public void editarPaciente(Paciente paciente) throws SQLException {
        String querry = String.format(
                "UPDATE Paciente SET nome = '%s', cpf = '%s', telefone = '%s', data_nascimento = '%tF', sexo = %b, internado = %b, plano_saude = %b WHERE id_paciente = '%s';",
                paciente.getNome(), paciente.getCpf(), paciente.getTelefone(), paciente.getDataNasc(),
                paciente.isSexo(),
                paciente.isInternado(), paciente.isPlanoDeSaude(), paciente.getCodPaciente());
        db.querry_insup(querry);
    }

    public void excluirPaciente(Paciente paciente) throws SQLException {
        if (paciente != null) {
            String querry = "DELETE FROM Paciente WHERE id_paciente = '" + paciente.getCodPaciente() + "';";
            db.querry_insup(querry);
        }
    }

    public void listarPacientes() throws SQLException {
        String query = "SELECT * FROM Paciente;";
        ResultSet rs = db.querry_busca(query);

        System.out.printf("|Cod%s|Nome%s|CPF%s|Telefone%s|Nascimento%s|Sexo%s|Internado%s|Plano de Saúde\n",
                FuncUtils.spacesGenerator(4), FuncUtils.spacesGenerator(26), FuncUtils.spacesGenerator(9),
                FuncUtils.spacesGenerator(4),
                FuncUtils.spacesGenerator(2), FuncUtils.spacesGenerator(8), FuncUtils.spacesGenerator(1));

        while (rs.next()) {
            String cod = rs.getString("id_paciente");
            String nome = rs.getString("nome");
            String cpf = rs.getString("cpf");
            String telefone = rs.getString("telefone");
            String dataNascimento = rs.getString("data_nascimento");
            boolean sexo = rs.getBoolean("sexo");
            boolean internado = rs.getBoolean("internado");
            boolean planoSaude = rs.getBoolean("plano_saude");

            String sexoStr = sexo ? "Masculino" : "Feminino";
            String internadoStr = internado ? "Sim" : "Não";
            String planoSaudeStr = planoSaude ? "Possui" : "Não possui";

            System.out.printf("|%-7s|%-30s|%-12s|%-12s|%-12s|%-12s|%-10s|%s\n", cod, nome, cpf, telefone, dataNascimento,
                    sexoStr,
                    internadoStr, planoSaudeStr);
        }
    }

    public void internar(Paciente aux) throws SQLException {
        if (aux != null) {
            String querry = "UPDATE Paciente SET internado = 1 WHERE id_paciente = '" + aux.getCodPaciente() + "';";
            db.querry_insup(querry);
            System.out.println("\n" + aux.getNome() + " foi internado.");
        }

    }

    public void alta(Paciente aux) throws SQLException {
        if (aux != null) {
            String querry = "UPDATE Paciente SET internado = 0 WHERE id_paciente = '" + aux.getCodPaciente() + "';";
            db.querry_insup(querry);
            System.out.println("\n" + aux.getNome() + " recebeu alta.");
        }
    }

    public void fech() {
        db.desconect();
    }
}
