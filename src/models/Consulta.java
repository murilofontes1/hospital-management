package src.models;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
/*
 * Classe para representar uma consulta
 * Consulta é feita por um médico a um paciente, pode gerar prescrições e internações
 * Consulta contém um código, data, horário, diagnóstico, sintomas, prescrições, se precisa internar, id do médico e id do paciente
 */
public class Consulta {
    private String codConsulta;
    private String idMedico;
    private String idPaciente;
    private Date dataConsulta;
    private Time horarioConsulta;
    private String diagnostico;
    private ArrayList<String> sintomas;
    private ArrayList<Prescricao> prescricoes;
    private boolean precisaInternar;

    public Consulta(String codConsulta, Date dataConsulta, Time horarioConsulta, String diagnostico,
            ArrayList<String> sintomas, boolean precisaInternar, String idMedico, String idPaciente) {
        this.codConsulta = codConsulta;
        this.dataConsulta = dataConsulta;
        this.horarioConsulta = horarioConsulta;
        this.diagnostico = diagnostico;
        this.sintomas = sintomas;
        this.precisaInternar = precisaInternar;
        this.idMedico = idMedico;
        this.idPaciente = idPaciente;
    }

    public Consulta(Date dataConsulta, Time horarioConsulta, String diagnostico,
            ArrayList<String> sintomas, boolean precisaInternar, String idMedico, String idPaciente) {
        this.dataConsulta = dataConsulta;
        this.horarioConsulta = horarioConsulta;
        this.diagnostico = diagnostico;
        this.sintomas = sintomas;
        this.precisaInternar = precisaInternar;
        this.idMedico = idMedico;
        this.idPaciente = idPaciente;
    }

    public Consulta() {

    }

    public String getCodConsulta() {
        return codConsulta;
    }

    public void setCodConsulta(String codConsulta) {
        this.codConsulta = codConsulta;
    }

    public String getIdMedico() {
        return idMedico;
    }

    public void setIdMedico(String idMedico) {
        this.idMedico = idMedico;
    }

    public String getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(String idPaciente) {
        this.idPaciente = idPaciente;
    }

    public Date getDataConsulta() {
        return dataConsulta;
    }

    public void setDataConsulta(Date dataConsulta) {
        this.dataConsulta = dataConsulta;
    }

    public Time getHorarioConsulta() {
        return horarioConsulta;
    }

    public void setHorarioConsulta(Time horarioConsulta) {
        this.horarioConsulta = horarioConsulta;
    }

    public String getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
    }

    public ArrayList<String> getSintomas() {
        return sintomas;
    }

    public void setSintomas(ArrayList<String> sintomas) {
        this.sintomas = sintomas;
    }

    public String getSintomasStr() {
        String s = "";
        for (int i = 0; i < this.sintomas.size(); i++) {
            s += sintomas.get(i);
            if (i != sintomas.size() - 1) {
                s += ", ";
            }
        }
        return s;
    }

    public boolean isPrecisaInternar() {
        return precisaInternar;
    }

    public String getPrecisaInternar() {
        if (precisaInternar)
            return "Sim";
        return "Não";
    }

    public void setPrecisaInternar(boolean precisaInternar) {
        this.precisaInternar = precisaInternar;
    }

    public ArrayList<Prescricao> getPrescricoes() {
        return prescricoes;
    }

    public void setPrescricoes(ArrayList<Prescricao> prescricoes) {
        this.prescricoes = prescricoes;
    }

    public String getPrescricoesStr() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < this.prescricoes.size(); i++) {
            sb.append("Cod: ").append(String.format("%-4s", prescricoes.get(i).getCodPrescricao())).append("|");
            sb.append("Medicamento: ").append(String.format("%-20s", prescricoes.get(i).getMedicamento().getNome()))
                    .append("|");
            sb.append(String.format("%-40s", prescricoes.get(i).getDosagem())).append("|");
            sb.append(String.format("%-40s", prescricoes.get(i).getPosologia())).append("\n");
        }
        return sb.toString();
    }

    @Override
    public String toString() {
        String s = "";
        s += "Código da consulta: " + getCodConsulta() + "\n";
        s += "Data da consulta: " + getDataConsulta() + "\n";
        s += "Horário da consulta: " + getHorarioConsulta() + "\n";
        s += "Diagnóstico: " + getDiagnostico() + "\n";
        s += "Precisou internar: " + getPrecisaInternar() + "\n";
        s += "Sintomas: " + getSintomasStr() + "\n";
        s += "CRM do Médico: " + getIdMedico() + "\n";
        s += "Id do Paciente: " + getIdPaciente() + "\n";
        s += "Prescrições: \n";
        s += getPrescricoesStr();
        return s;
    }

}
