package src.models;

import java.sql.Time;
import java.util.Date;

public class Administrador extends Funcionario {
    private String login;
    private String senha;

    public Administrador(String nome, String cpf, String telefone, Date dataNasc, boolean sexo, String codFunc,
            double salario, Date dataDeAdmissao, Time horarioDeTrabalhoInicio, Time horarioDeTrabalhoFinal,
            String login, String senha) {
        super(nome, cpf, telefone, dataNasc, sexo, codFunc, salario, dataDeAdmissao, horarioDeTrabalhoInicio,
                horarioDeTrabalhoFinal);
        this.login = login;
        this.senha = senha;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

}