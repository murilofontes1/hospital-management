package src.models;

import java.sql.Time;
import java.sql.Date;
/*
 * Classe para representar um enfermeiro
 * Enfermeiro é um tipo de funcionário
 * Enfermeiro contém um COREN e os atributos de funcionário
 * Enfermeiro é responsável por uma enfermaria
 */
public class Enfermeiro extends Funcionario {
    private String coren;
    private String codEnfermaria;

    public Enfermeiro(String nome, String cpf, String telefone, Date dataNasc, boolean sexo,
            double salario, Date dataDeAdmissao, Time horarioDeTrabalhoInicio, Time horarioDeTrabalhoFinal,
            String coren) {
        super(nome, cpf, telefone, dataNasc, sexo, salario, dataDeAdmissao, horarioDeTrabalhoInicio,
                horarioDeTrabalhoFinal);
        this.coren = coren;
    }

    public Enfermeiro(String nome, String cpf, String telefone, Date dataNasc, boolean sexo,
            double salario, Date dataDeAdmissao, Time horarioDeTrabalhoInicio, Time horarioDeTrabalhoFinal,
            String coren, String codEnfermaria) {
        super(nome, cpf, telefone, dataNasc, sexo, salario, dataDeAdmissao, horarioDeTrabalhoInicio,
                horarioDeTrabalhoFinal);
        this.coren = coren;
        this.codEnfermaria = codEnfermaria;
    }

    public Enfermeiro() {
        super();
    }

    public String getCodEnfermaria() {
        return codEnfermaria;
    }

    public void setCodEnfermaria(String codEnfermaria) {
        this.codEnfermaria = codEnfermaria;
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
        s += "Data de nascimento: " + getDataNasc() + "\n";
        s += "Data de admissão: " + getDataDeAdmissao() + "\n";
        s += "Horário de trabalho: " + getHorarioDeTrabalhoInicio() + " às " + getHorarioDeTrabalhoFinal() + "\n";
        s += "COREN: " + coren + "\n";
        if (codEnfermaria != null) {
            s += "Código da enfermaria em que trabalha: " + codEnfermaria + "\n";
        } else {
            s += "Código da enfermaria em que trabalha: Sem enfermaria\n";
        }
        return s;
    }
}
