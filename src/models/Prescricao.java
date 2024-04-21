package src.models;
/*
 * Classe para representar uma prescrição
 * Prescrição é feita por um médico a um paciente atraves de uma consulta
 * Prescrição contém um código, código da consulta, medicamento, dosagem e posologia
 */
public class Prescricao {
    private String codPrescricao;
    private String codConsulta;
    private Medicamento medicamento;
    private String dosagem; // pode ser em ml, mg e etc
    private String posologia;

    public Prescricao(String codPrescricao, String codConsulta, Medicamento medicamento, String dosagem,
            String posologia) {
        this.codPrescricao = codPrescricao;
        this.medicamento = medicamento;
        this.dosagem = dosagem;
        this.posologia = posologia;
        this.codConsulta = codConsulta;
    }

    public Prescricao(Medicamento medicamento, String codConsulta, String dosagem, String posologia) {
        this.medicamento = medicamento;
        this.dosagem = dosagem;
        this.posologia = posologia;
        this.codConsulta = codConsulta;
    }

    public Prescricao() {
    }

    public String getCodConsulta() {
        return codConsulta;
    }

    public void setCodConsulta(String codConsulta) {
        this.codConsulta = codConsulta;
    }

    public String getCodPrescricao() {
        return codPrescricao;
    }

    public void setCodPrescricao(String codPrescricao) {
        this.codPrescricao = codPrescricao;
    }

    public Medicamento getMedicamento() {
        return medicamento;
    }

    public void setMedicamento(Medicamento medicamento) {
        this.medicamento = medicamento;
    }

    public String getDosagem() {
        return dosagem;
    }

    public void setDosagem(String dosagem) {
        this.dosagem = dosagem;
    }

    public String getPosologia() {
        return posologia;
    }

    public void setPosologia(String posologia) {
        this.posologia = posologia;
    }

    @Override
    public String toString() {
        String s = "";
        s += "Código da prescrição: " + getCodPrescricao() + "\n";
        s += "Código da consulta: " + getCodConsulta() + "\n";
        s += "Medicamento: " + getMedicamento().getNome() + "\n";
        s += "Dosagem: " + getDosagem() + "\n";
        s += "Posologia: " + getPosologia() + "\n";
        return s;
    }
}