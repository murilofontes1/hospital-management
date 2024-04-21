package src.models;

import java.util.Date;
/*
 * Classe para representar uma internação
 * Internação é feita por um médico a um paciente, e pode ser atraves de uma consulta
 * Internação contém um código, data de internação, data de alta, id do paciente e id da enfermaria
 */
public class Internacao {
    private String codInternacao;
    private Date dataInternacao;
    private Date dataAlta;
    private String idPaciente;
    private String idEnfermaria;

    public Internacao(String codInternacao, Date dataInternacao, Date dataAlta, String idPaciente,
            String idEnfermaria) {
        this.codInternacao = codInternacao;
        this.dataInternacao = dataInternacao;
        this.dataAlta = dataAlta;
        this.idPaciente = idPaciente;
        this.idEnfermaria = idEnfermaria;
    }

    public Internacao() {

    }

    public String getCodInternacao() {
        return codInternacao;
    }

    public void setCodInternacao(String codInternacao) {
        this.codInternacao = codInternacao;
    }

    public Date getDataInternacao() {
        return dataInternacao;
    }

    public void setDataInternacao(Date dataInternacao) {
        this.dataInternacao = dataInternacao;
    }

    public Date getDataAlta() {
        return dataAlta;
    }

    public void setDataAlta(Date dataAlta) {
        this.dataAlta = dataAlta;
    }

    public String getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(String idPaciente) {
        this.idPaciente = idPaciente;
    }

    public String getIdEnfermaria() {
        return idEnfermaria;
    }

    public void setIdEnfermaria(String idEnfemaria) {
        this.idEnfermaria = idEnfemaria;
    }

    @Override
    public String toString() {
        String s = "";
        s += "Código da internação: " + getCodInternacao() + "\n";
        s += "Data de internação: " + getDataInternacao() + "\n";
        s += "Data de alta: " + (getDataAlta() == null ? "Internado(a)" : getDataAlta()) + "\n";
        s += "Paciente internado: " + getIdPaciente() + "\n";
        s += "Enfermaria da internação: " + getIdEnfermaria() + "\n";
        return s;
    }
}
