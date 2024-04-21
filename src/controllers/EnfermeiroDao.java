package src.controllers;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;

import src.data.Banco;
import src.models.Enfermeiro;

/*
 *Classe para manipular os dados de um enfermeiro
*EnfermeiroDao contém métodos para cadastrar, buscar, editar e excluir um enfermeiro 
 */
public class EnfermeiroDao {

    public static void cadastrarEnfermeiro(Enfermeiro enfermeiro, Banco db) {
            /* 
            * Método para cadastrar um enfermeiro
            * Recebe um objeto enfermeiro e um objeto de banco de dados
            * Insere os dados do enfermeiro no banco de dados
            */
        String query = String.format(
                "INSERT INTO Enfermeiro (nome, cpf, telefone, data_nascimento, sexo, salario, data_admissao, horario_trabalho_inicio, horario_trabalho_final, coren )VALUES ('%s', '%s', '%s', '%tF', %b, '%.2f', '%tF', '%tT', '%tT', '%s');",
                enfermeiro.getNome(), enfermeiro.getCpf(), enfermeiro.getTelefone(), enfermeiro.getDataNasc(),
                enfermeiro.isSexo(), enfermeiro.getSalario(), enfermeiro.getDataDeAdmissao(),
                enfermeiro.getHorarioDeTrabalhoInicio(), enfermeiro.getHorarioDeTrabalhoFinal(),
                enfermeiro.getCoren());
        db.queryInsup(query);
    }

    public static Enfermeiro buscaEnfermeiro(String coren, Banco db) throws SQLException {
            /*
            * Método para buscar um enfermeiro
            * Recebe um coren e um objeto de banco de dados
            * Retorna um objeto enfermeiro com os dados do enfermeiro encontrado
            */
        String query = "SELECT * FROM Enfermeiro WHERE coren = '" + coren + "';";
        ResultSet rs = db.queryBusca(query);
        Enfermeiro enfermeiro = new Enfermeiro();

        if (rs.next() && rs != null) {
            enfermeiro.setNome(rs.getString("nome"));
            enfermeiro.setCpf(rs.getString("cpf"));
            enfermeiro.setTelefone(rs.getString("telefone"));
            enfermeiro.setSexo(rs.getBoolean("sexo"));
            String dataNascStr = rs.getString("data_nascimento");
            Date dataNasc = Date.valueOf(dataNascStr);
            enfermeiro.setDataNasc(dataNasc);

            enfermeiro.setSalario(rs.getDouble("salario"));

            String dataAdmissaoStr = rs.getString("data_admissao");
            Date dataAdmissao = Date.valueOf(dataAdmissaoStr);
            enfermeiro.setDataDeAdmissao(dataAdmissao);

            String horarioInicioStr = rs.getString("horario_trabalho_inicio");
            Time horarioInicio = Time.valueOf(horarioInicioStr);
            enfermeiro.setHorarioDeTrabalhoInicio(horarioInicio);

            String horarioFinalStr = rs.getString("horario_trabalho_final");
            Time horarioFinal = Time.valueOf(horarioFinalStr);
            enfermeiro.setHorarioDeTrabalhoFinal(horarioFinal);

            enfermeiro.setCoren(rs.getString("coren"));
            enfermeiro.setCodEnfermaria(rs.getString("id_enfermaria"));
        } else {
            return null;
        }

        return enfermeiro;
    }

    public static void editarEnfermeiro(Enfermeiro enfermeiro, Banco db) throws SQLException {

            /*
            * Método para editar um enfermeiro
            * Recebe um objeto enfermeiro e um objeto de banco de dados
            * Atualiza os dados do enfermeiro no banco de dados
            */

        String query = String.format(
                "UPDATE Enfermeiro SET id_enfermaria = %d, nome = '%s', cpf = '%s', telefone = '%s', data_nascimento = '%tF', sexo = %b, salario = '%.2f', data_admissao = '%tF', horario_trabalho_inicio = '%tT', horario_trabalho_final = '%tT', coren = '%s' WHERE coren = '%s';",
                Integer.parseInt(enfermeiro.getCodEnfermaria()),
                enfermeiro.getNome(), enfermeiro.getCpf(), enfermeiro.getTelefone(), enfermeiro.getDataNasc(),
                enfermeiro.isSexo(), enfermeiro.getSalario(), enfermeiro.getDataDeAdmissao(),
                enfermeiro.getHorarioDeTrabalhoInicio(), enfermeiro.getHorarioDeTrabalhoFinal(),
                enfermeiro.getCoren(), enfermeiro.getCoren());
        db.queryInsup(query);
    }

    public static void excluirEnfermeiro(Enfermeiro enfermeiro, Banco db) throws SQLException {
            /*
            * Método para excluir um enfermeiro
            * Recebe um objeto enfermeiro e um objeto de banco de dados
            * Exclui os dados do enfermeiro no banco de dados
            */
        if (enfermeiro != null) {
            String querry = "DELETE FROM Enfermeiro WHERE coren = '" + enfermeiro.getCoren() + "';";
            db.queryInsup(querry);
        }
    }

    public static ArrayList<Enfermeiro> listarEnfermeirosSemEnfermaria(Banco db) throws SQLException {

            /*
            * Método para listar os enfermeiros sem enfermaria
            * Recebe um objeto de banco de dados
            * Retorna um ArrayList com os enfermeiros encontrados
            */

        String query = "SELECT * FROM Enfermeiro WHERE id_enfermaria IS NULL;";
        ResultSet rs = db.queryBusca(query);
        ArrayList<Enfermeiro> enfermeiros = new ArrayList<Enfermeiro>();

        while (rs.next()) {
            Enfermeiro enfermeiro = new Enfermeiro();
            enfermeiro.setNome(rs.getString("nome"));
            enfermeiro.setCpf(rs.getString("cpf"));
            enfermeiro.setTelefone(rs.getString("telefone"));
            enfermeiro.setSexo(rs.getBoolean("sexo"));

            String dataNascStr = rs.getString("data_nascimento");
            Date dataNasc = Date.valueOf(dataNascStr);
            enfermeiro.setDataNasc(dataNasc);

            enfermeiro.setSalario(rs.getDouble("salario"));

            String dataAdmissaoStr = rs.getString("data_admissao");
            Date dataAdmissao = Date.valueOf(dataAdmissaoStr);
            enfermeiro.setDataDeAdmissao(dataAdmissao);

            String horarioInicioStr = rs.getString("horario_trabalho_inicio");
            Time horarioInicio = Time.valueOf(horarioInicioStr);
            enfermeiro.setHorarioDeTrabalhoInicio(horarioInicio);

            String horarioFinalStr = rs.getString("horario_trabalho_final");
            Time horarioFinal = Time.valueOf(horarioFinalStr);
            enfermeiro.setHorarioDeTrabalhoFinal(horarioFinal);

            enfermeiro.setCoren(rs.getString("coren"));
            enfermeiro.setCodEnfermaria(rs.getString("id_enfermaria"));
            enfermeiros.add(enfermeiro);

        }
        return enfermeiros;
    }

    public static ArrayList<Enfermeiro> listarEnfermeiros(Banco db) throws SQLException {
            
                /*
                * Método para listar os enfermeiros
                * Recebe um objeto de banco de dados
                * Retorna um ArrayList com os enfermeiros encontrados
                */
                
        String query = "SELECT * FROM Enfermeiro;";
        ResultSet rs = db.queryBusca(query);
        ArrayList<Enfermeiro> enfermeiros = new ArrayList<Enfermeiro>();

        while (rs.next()) {
            Enfermeiro enfermeiro = new Enfermeiro();
            enfermeiro.setNome(rs.getString("nome"));
            enfermeiro.setCpf(rs.getString("cpf"));
            enfermeiro.setTelefone(rs.getString("telefone"));
            enfermeiro.setSexo(rs.getBoolean("sexo"));

            String dataNascStr = rs.getString("data_nascimento");
            Date dataNasc = Date.valueOf(dataNascStr);
            enfermeiro.setDataNasc(dataNasc);

            enfermeiro.setSalario(rs.getDouble("salario"));

            String dataAdmissaoStr = rs.getString("data_admissao");
            Date dataAdmissao = Date.valueOf(dataAdmissaoStr);
            enfermeiro.setDataDeAdmissao(dataAdmissao);

            String horarioInicioStr = rs.getString("horario_trabalho_inicio");
            Time horarioInicio = Time.valueOf(horarioInicioStr);
            enfermeiro.setHorarioDeTrabalhoInicio(horarioInicio);

            String horarioFinalStr = rs.getString("horario_trabalho_final");
            Time horarioFinal = Time.valueOf(horarioFinalStr);
            enfermeiro.setHorarioDeTrabalhoFinal(horarioFinal);

            enfermeiro.setCoren(rs.getString("coren"));
            enfermeiro.setCodEnfermaria(rs.getString("id_enfermaria"));
            enfermeiros.add(enfermeiro);
        }
        return enfermeiros;
    }
}
