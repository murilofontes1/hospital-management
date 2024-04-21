package src.models;

import java.sql.Date;
/*
 * Classe para representar um paciente
 * Paciente é uma pessoa que pode ser internada e ter plano de saúde
 * Paciente contém um código, se está internado e se tem plano de saúde
 */
public class Paciente extends Pessoa {
    private String codPaciente;
    private boolean internado;
    private boolean planoDeSaude;

    public Paciente() {
        super("", "", "", new Date(System.currentTimeMillis()), true);
        this.internado = false;
        this.planoDeSaude = false;
    }

    public Paciente(String nome, String cpf, String telefone, Date dataNasc, boolean sexo,
            boolean internado, boolean planoDeSaude) {
        super(nome, cpf, telefone, dataNasc, sexo);
        this.internado = internado;
        this.planoDeSaude = planoDeSaude;
    }

    public String getCodPaciente() {
        return codPaciente;
    }

    public void setCodPaciente(String codPaciente) {
        this.codPaciente = codPaciente;
    }

    public boolean isInternado() {
        return internado;
    }

    public void setInternado(boolean internado) {
        this.internado = internado;
    }

    public boolean isPlanoDeSaude() {
        return planoDeSaude;
    }

    public void setPlanoDeSaude(boolean planoDeSaude) {
        this.planoDeSaude = planoDeSaude;
    }

    public String getInternado() {
        if (internado) {
            return "Sim";
        } else {
            return "Não";
        }
    }

    public String getPlanoDeSaude() {
        if (planoDeSaude) {
            return "Sim";
        } else {
            return "Não";
        }
    }

    @Override
    public String toString() {
        String s = "";
        s += "Nome: " + getNome() + "\n";
        s += "CPF: " + getCpf() + "\n";
        s += "Telefone: " + getTelefone() + "\n";
        s += "Data de nascimento: " + getDataNasc() + "\n";
        s += "Sexo: " + getSexo() + "\n";
        s += "Internado: " + getInternado() + "\n";
        s += "Plano de saúde: " + getPlanoDeSaude() + "\n";
        return s;
    }
}
