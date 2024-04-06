package src.models;

import java.util.Date;

public class Internacao {
    private String codInternacao;
    private String dataInternacao;
    private Date dataAlta;
    private Paciente pacienteInternado;
    private Enfermaria enfermariaInternacao;

    public Internacao(String codInternacao, String dataInternacao, Date dataAlta, Paciente pacienteInternado,
            Enfermaria enfermariaInternacao) {
        this.codInternacao = codInternacao;
        this.dataInternacao = dataInternacao;
        this.dataAlta = dataAlta;
        this.pacienteInternado = pacienteInternado;
        this.enfermariaInternacao = enfermariaInternacao;
    }

    public String getCodInternacao() {
        return codInternacao;
    }

    public void setCodInternacao(String codInternacao) {
        this.codInternacao = codInternacao;
    }

    public String getDataInternacao() {
        return dataInternacao;
    }

    public void setDataInternacao(String dataInternacao) {
        this.dataInternacao = dataInternacao;
    }

    public Date getDataAlta() {
        return dataAlta;
    }

    public void setDataAlta(Date dataAlta) {
        this.dataAlta = dataAlta;
    }

    public Paciente getPacienteInternado() {
        return pacienteInternado;
    }

    public void setPacienteInternado(Paciente pacienteInternado) {
        this.pacienteInternado = pacienteInternado;
    }

    public Enfermaria getEnfermariaInternacao() {
        return enfermariaInternacao;
    }

    public void setEnfermariaInternacao(Enfermaria enfermariaInternacao) {
        this.enfermariaInternacao = enfermariaInternacao;
    }

    @Override
    public String toString() {
        return "Internacao [codInternacao=" + codInternacao + ", dataAlta=" + dataAlta + ", dataInternacao="
                + dataInternacao + ", enfermariaInternacao=" + enfermariaInternacao.getCodEnfermaria() + ", pacienteInternado="
                + pacienteInternado.getNome() + "]\n";
    }
}
