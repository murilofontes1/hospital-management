package src.models;

import java.sql.Time;
import java.sql.Date;

public class Enfermeiro extends Funcionario {
    private String coren;

    public Enfermeiro(String nome, String cpf, String telefone, Date dataNasc, boolean sexo,
            double salario, Date dataDeAdmissao, Time horarioDeTrabalhoInicio, Time horarioDeTrabalhoFinal,
            String coren) {
        super(nome, cpf, telefone, dataNasc, sexo, salario, dataDeAdmissao, horarioDeTrabalhoInicio,
                horarioDeTrabalhoFinal);
        this.coren = coren;
    }

    public Enfermeiro(){
        super();
    }

    public String getCoren() {
        return coren;
    }

    public void setCoren(String coren) {
        this.coren = coren;
    }

    @Override
    public String toString() {
        String s = "";
        s += "Enfermeiro: " + getNome() + "\n";
        s += "CPF: " + getCpf() + "\n";
        s += "Telefone: " + getTelefone() + "\n";
        s += "Salário: " + getSalario() + "\n";
        s += "Data de admissão: " + getDataDeAdmissao() + "\n";
        s += "Horário de trabalho: " + getHorarioDeTrabalhoInicio() + " às " + getHorarioDeTrabalhoFinal() + "\n";
        s += "COREN: " + coren + "\n";
        return s;
    }
}