package src.controllers;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;

import src.data.Banco;
import src.models.Administrador;
import src.utils.FuncUtils;

/*
 * Classe para manipular os dados de um administrador
 * AdministradorDao contém métodos para cadastrar, buscar, editar e excluir um administrador
 */

public class AdministradorDao {

    public static void cadastrarAdministrador(Administrador administrador, Banco db)
            throws NoSuchAlgorithmException, UnsupportedEncodingException {

                /* 
                 * Método para cadastrar um administrador
                 * Recebe um objeto administrador e um objeto de banco de dados
                 * Insere os dados do administrador no banco de dados
                 */

        String senha = administrador.getSenha();
        senha = FuncUtils.encryptMD5(senha);
        String query = String.format(
                "INSERT INTO Administrador (nome, cpf, telefone, data_nascimento, sexo, salario, data_admissao, horario_trabalho_inicio, horario_trabalho_final,login,senha )VALUES ('%s', '%s', '%s', '%tF', %b, '%.2f', '%tF', '%tT', '%tT', '%s', '%s');",
                administrador.getNome(), administrador.getCpf(), administrador.getTelefone(),
                administrador.getDataNasc(),
                administrador.isSexo(), administrador.getSalario(), administrador.getDataDeAdmissao(),
                administrador.getHorarioDeTrabalhoInicio(), administrador.getHorarioDeTrabalhoFinal(),
                administrador.getLogin(), senha);
        db.queryInsup(query);
    }

    public static Administrador buscaAdministrador(String login, Banco db) throws SQLException {

                /*
                 * Método para buscar um administrador
                 * Recebe um login e um objeto de banco de dados
                 * Retorna um objeto administrador com os dados do administrador encontrado
                 */

        String query = "SELECT * FROM Administrador WHERE login = '" + login + "';";
        ResultSet rs = db.queryBusca(query);
        Administrador administrador = new Administrador();

        if (rs.next() && rs != null) {
            administrador.setNome(rs.getString("nome"));
            administrador.setCpf(rs.getString("cpf"));
            administrador.setTelefone(rs.getString("telefone"));
            administrador.setSexo(rs.getBoolean("sexo"));
            String dataNascStr = rs.getString("data_nascimento");
            Date dataNasc = Date.valueOf(dataNascStr);
            administrador.setDataNasc(dataNasc);

            administrador.setSalario(rs.getDouble("salario"));

            String dataAdmissaoStr = rs.getString("data_admissao");
            Date dataAdmissao = Date.valueOf(dataAdmissaoStr);
            administrador.setDataDeAdmissao(dataAdmissao);

            String horarioInicioStr = rs.getString("horario_trabalho_inicio");
            Time horarioInicio = Time.valueOf(horarioInicioStr);
            administrador.setHorarioDeTrabalhoInicio(horarioInicio);

            String horarioFinalStr = rs.getString("horario_trabalho_final");
            Time horarioFinal = Time.valueOf(horarioFinalStr);
            administrador.setHorarioDeTrabalhoFinal(horarioFinal);
            administrador.setLogin(rs.getString("login"));

            String senha = rs.getString("senha");
            administrador.setSenha(senha);
        } else {
            return null;
        }
        return administrador;
    }

    public static void editarAdministrador(Administrador administrador, Banco db)
            throws SQLException, NoSuchAlgorithmException, UnsupportedEncodingException {

                /*
                 * Método para editar um administrador
                * Recebe um objeto administrador e um objeto de banco de dados
                * Atualiza os dados do administrador no banco de dados
                 */

        String senha = administrador.getSenha();
        senha = FuncUtils.encryptMD5(senha);
        String query = String.format(
                "UPDATE Administrador SET nome = '%s', cpf = '%s', telefone = '%s', data_nascimento = '%tF', sexo = %b, salario = '%.2f', data_admissao = '%tF', horario_trabalho_inicio = '%tT', horario_trabalho_final = '%tT', login = '%s', senha = '%s' WHERE cpf = '%s';",
                administrador.getNome(), administrador.getCpf(), administrador.getTelefone(),
                administrador.getDataNasc(),
                administrador.isSexo(), administrador.getSalario(), administrador.getDataDeAdmissao(),
                administrador.getHorarioDeTrabalhoInicio(), administrador.getHorarioDeTrabalhoFinal(),
                administrador.getLogin(), senha, administrador.getCpf());
        db.queryInsup(query);
    }

    public static void excluirAdministrador(Administrador administrador, Banco db) throws SQLException {
                
                /*
                * Método para excluir um administrador
                * Recebe um objeto administrador e um objeto de banco de dados
                * Remove os dados do administrador do banco de dados
                */

        if (administrador != null) {
            String querry = "DELETE FROM Administrador WHERE cpf = '" + administrador.getCpf() + "';";
            db.queryInsup(querry);
        }
    }

    public static ArrayList<Administrador> listarAdministradores(Banco db) throws SQLException {
                
                /*
                 * Método para listar os administradores
                 * Recebe um objeto de banco de dados
                 * Retorna um ArrayList com os administradores encontrados
                 */

        String query = "SELECT * FROM Administrador;";
        ResultSet rs = db.queryBusca(query);
        ArrayList<Administrador> administradores = new ArrayList<Administrador>();

        while (rs.next()) {
            Administrador administrador = new Administrador();
            administrador.setNome(rs.getString("nome"));
            administrador.setCpf(rs.getString("cpf"));
            administrador.setTelefone(rs.getString("telefone"));
            administrador.setLogin(rs.getString("login"));
            administrador.setSenha(rs.getString("senha"));
            administrador.setSexo(rs.getBoolean("sexo"));
            administrador.setSalario(rs.getDouble("salario"));

            String dataNascStr = rs.getString("data_nascimento");
            Date dataNasc = Date.valueOf(dataNascStr);
            administrador.setDataNasc(dataNasc);

            String dataAdmissaoStr = rs.getString("data_admissao");
            Date dataAdmissao = Date.valueOf(dataAdmissaoStr);
            administrador.setDataDeAdmissao(dataAdmissao);

            String horarioInicioStr = rs.getString("horario_trabalho_inicio");
            Time horarioInicio = Time.valueOf(horarioInicioStr);
            administrador.setHorarioDeTrabalhoInicio(horarioInicio);

            String horarioFinalStr = rs.getString("horario_trabalho_final");
            Time horarioFinal = Time.valueOf(horarioFinalStr);
            administrador.setHorarioDeTrabalhoFinal(horarioFinal);

            administradores.add(administrador);
        }
        return administradores;
    }
}