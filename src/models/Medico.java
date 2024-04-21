package src.models;

import java.sql.Time;
import java.sql.Date;
/*
 * Classe para representar um médico
 * Médico é um funcionário que possui um CRM e uma especialidade
 * Médico contem crm, especialidade e se está em plantão
 */
public class Medico extends Funcionario {
    private String crm;
    private String especialidade;
    private boolean plantao;

    public Medico() {
        super();
    }

    public Medico(String nome, String cpf, String telefone, Date dataNasc, boolean sexo, double salario,
            Date dataDeAdmissao, Time horarioDeTrabalhoInicio, Time horarioDeTrabalhoFinal, String crm,
            String especialidade, boolean plantao) {
        super(nome, cpf, telefone, dataNasc, sexo, salario, dataDeAdmissao, horarioDeTrabalhoInicio,
                horarioDeTrabalhoFinal);
        this.crm = crm;
        this.especialidade = especialidade;
        this.plantao = plantao;
    }

    public String getCrm() {
        return crm;
    }

    public void setCrm(String crm) {
        this.crm = crm;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }

    public boolean isPlantao() {
        return plantao;
    }

    public String getPlantao() {
        if (plantao)
            return "Sim";
        return "Não";
    }

    public void setPlantao(boolean plantao) {
        this.plantao = plantao;
    }

    @Override
    public String toString() {
        String s = "";
        s += "Médico: " + getNome() + "\n";
        s += "CPF: " + getCpf() + "\n";
        s += "Telefone: " + getTelefone() + "\n";
        s += "Salário: " + getSalario() + "\n";
        s += "Data de nascimento: " + getDataNasc() + "\n";
        s += "Data de admissão: " + getDataDeAdmissao() + "\n";
        s += "Horário de trabalho: " + getHorarioDeTrabalhoInicio() + " às " + getHorarioDeTrabalhoFinal() + "\n";
        s += "CRM: " + getCrm() + "\n";
        s += "Especialidade: " + getEspecialidade() + "\n";
        s += "Plantão: " + getPlantao() + "\n";
        return s;
    }

}
