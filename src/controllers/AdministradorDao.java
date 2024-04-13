package src.controllers;


import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;

import src.data.Banco;
import src.models.Administrador;
import src.utils.FuncUtils;

public class AdministradorDao {
    Banco db;

    public AdministradorDao(){
        db = new Banco();
    }

    public void cadastrarAdministrador(Administrador administrador) throws NoSuchAlgorithmException, UnsupportedEncodingException {

        String senha = administrador.getSenha();
        senha = FuncUtils.encryptMD5(senha);
        System.out.println(senha);
        String query = String.format(
        "INSERT INTO Administrador (nome, cpf, telefone, data_nascimento, sexo, salario, data_admissao, horario_trabalho_inicio, horario_trabalho_final,login,senha )VALUES ('%s', '%s', '%s', '%tF', %b, '%.2f', '%tF', '%tT', '%tT', '%s', '%s');",
        administrador.getNome(), administrador.getCpf(), administrador.getTelefone(), administrador.getDataNasc(),
        administrador.isSexo(), administrador.getSalario(), administrador.getDataDeAdmissao(),
        administrador.getHorarioDeTrabalhoInicio(), administrador.getHorarioDeTrabalhoFinal(),administrador.getLogin(),senha);
    System.out.println(query);
        db.querry_insup(query);
    }

    public Administrador buscaAdministrador(String login) throws SQLException {
        String query = "SELECT * FROM Administrador WHERE login = '" + login + "';";
        ResultSet rs = db.querry_busca(query);
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
            senha = FuncUtils.decryptMD5(senha);
            administrador.setSenha(senha);
        } else {
            System.out.println("Administrador não encontrado.");
            return null;
        }
        rs.close();
        return administrador;
    }

    public void editarAdministrador(Administrador administrador) throws SQLException, NoSuchAlgorithmException, UnsupportedEncodingException {
        
        String senha = administrador.getSenha();
        senha = FuncUtils.encryptMD5(senha);
        
        String query = String.format(
                "UPDATE Administrador SET nome = '%s', cpf = '%s', telefone = '%s', data_nascimento = '%tF', sexo = %b, salario = '%.2f', data_admissao = '%tF', horario_trabalho_inicio = '%tT', horario_trabalho_final = '%tT', login = '%s', senha = '%s' WHERE cpf = '%s';",
                administrador.getNome(), administrador.getCpf(), administrador.getTelefone(), administrador.getDataNasc(),
                administrador.isSexo(), administrador.getSalario(), administrador.getDataDeAdmissao(),
                administrador.getHorarioDeTrabalhoInicio(), administrador.getHorarioDeTrabalhoFinal(),
                administrador.getLogin(), senha, administrador.getCpf());
        db.querry_insup(query);
    }

    public void excluirAdministrador(Administrador administrador) throws SQLException {
        if (administrador != null) {
            String querry = "DELETE FROM Administrador WHERE cpf = '" + administrador.getCpf() + "';";
            db.querry_insup(querry);
        }
    }

    public void listarAdministradores() throws SQLException {
        String query = "SELECT * FROM Administrador;";
        ResultSet rs = db.querry_busca(query);

        System.out.printf("|Cod%s|Nome%s|Sexo%s|Salário%s|CPF%s|\n",
                FuncUtils.spacesGenerator(4), FuncUtils.spacesGenerator(26), FuncUtils.spacesGenerator(9),
                FuncUtils.spacesGenerator(3),
                FuncUtils.spacesGenerator(12), FuncUtils.spacesGenerator(8));

        while (rs.next()) {
            String cod = rs.getString("id_adm");
            String nome = rs.getString("nome");
            boolean sexo = rs.getBoolean("sexo");
            double salario = rs.getDouble("salario");
            String cpf = rs.getString("cpf");
            String sexoStr = sexo ? "Masculino" : "Feminino";

            System.out.printf("|%-7s|%-30s|%-13s|%-10.2f|%s\n", cod, nome, sexoStr, salario, cpf);
        }
        rs.close();
    }
    
    public void administradorFech(){
        db.desconect();
    }
}

