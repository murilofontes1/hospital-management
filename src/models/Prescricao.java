package src.models;

public class Prescricao {
    private String codPrescricao;
    private String medicamento;
    private String dosagem; // pode ser em ml, mg e etc
    private String posologia;

    public Prescricao(String codPrescricao, String medicamento, String dosagem, String posologia) {
        this.codPrescricao = codPrescricao;
        this.medicamento = medicamento;
        this.dosagem = dosagem;
        this.posologia = posologia;
    }

    public String getCodPrescricao() {
        return codPrescricao;
    }

    public void setCodPrescricao(String codPrescricao) {
        this.codPrescricao = codPrescricao;
    }

    public String getMedicamento() {
        return medicamento;
    }

    public void setMedicamento(String medicamento) {
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
        return "Prescricao [codPrescricao=" + codPrescricao + ", dosagem=" + dosagem + ", medicamento=" + medicamento
                + ", posologia=" + posologia + "]\n";
    }
}