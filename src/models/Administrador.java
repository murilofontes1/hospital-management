package src.models;

import java.sql.Time;
import java.sql.Date;
/*
    *Classe para reprensentar um administrador
    *Administrador é um tipo de funcionário
    *Administrador contém um login e senha e os atributos de funcionário
 */
public class Administrador extends Funcionario {
    private String login;
    private String senha;

    public Administrador(String nome, String cpf, String telefone, Date dataNasc, boolean sexo,
            double salario, Date dataDeAdmissao, Time horarioDeTrabalhoInicio, Time horarioDeTrabalhoFinal,
            String login, String senha) {
        super(nome, cpf, telefone, dataNasc, sexo, salario, dataDeAdmissao, horarioDeTrabalhoInicio,
                horarioDeTrabalhoFinal);
        this.login = login;
        this.senha = senha;
    }

    public Administrador() {

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

    @Override
    public String toString() {
        String s = "";
        s += "Administrador: " + getNome() + "\n";
        s += "Login: " + getLogin() + "\n";
        s += "CPF: " + getCpf() + "\n";
        s += "Telefone: " + getTelefone() + "\n";
        s += "Salário: " + getSalario() + "\n";
        s += "Data de nascimento: " + getDataNasc() + "\n";
        s += "Data de admissão: " + getDataDeAdmissao() + "\n";
        s += "Horário de trabalho: " + getHorarioDeTrabalhoInicio() + " às " + getHorarioDeTrabalhoFinal() + "\n";
        return s;
    }
}
