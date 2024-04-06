package src.models;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;

public class Medico extends Funcionario {
    private String crm;
    private String especialidade;
    private boolean plantao;
    private ArrayList<Consulta> consultasRealizadas;

    public Medico(String nome, String cpf, String telefone, Date dataNasc, boolean sexo, String codFunc, double salario,
            Date dataDeAdmissao, Time horarioDeTrabalhoInicio, Time horarioDeTrabalhoFinal, String crm,
            String especialidade, boolean plantao, ArrayList<Consulta> consultasRealizadas) {
        super(nome, cpf, telefone, dataNasc, sexo, codFunc, salario, dataDeAdmissao, horarioDeTrabalhoInicio,
                horarioDeTrabalhoFinal);
        this.crm = crm;
        this.especialidade = especialidade;
        this.plantao = plantao;
        this.consultasRealizadas = consultasRealizadas;
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
            return "Está de plantão";
        return "Não está de plantão";
    }

    public void setPlantao(boolean plantao) {
        this.plantao = plantao;
    }

    public ArrayList<Consulta> getConsultasRealizadas() {
        return consultasRealizadas;
    }

    public void setConsultasRealizadas(ArrayList<Consulta> consultasRealizadas) {
        this.consultasRealizadas = consultasRealizadas;
    }

    @Override
    public String toString() {
        String s = "";
        s += "Médico: " + getNome() + "\n";
        s += "CRM: " + crm + "\n";
        s += "Especialidade: " + especialidade + "\n";
        s += "Plantão: " + getPlantao() + "\n";
        return s;
    }

}
