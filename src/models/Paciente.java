package src.models;

import java.util.Date;

public class Paciente extends Pessoa {
    private String codPaciente;
    private boolean interado;
    private boolean planoDeSaude;

    public Paciente(String nome, String cpf, String telefone, Date dataNasc, boolean sexo, String codPaciente,
            boolean interado, boolean planoDeSaude) {
        super(nome, cpf, telefone, dataNasc, sexo);
        this.codPaciente = codPaciente;
        this.interado = interado;
        this.planoDeSaude = planoDeSaude;
    }

    public String getCodPaciente() {
        return codPaciente;
    }

    public void setCodPaciente(String codPaciente) {
        this.codPaciente = codPaciente;
    }

    public boolean isInterado() {
        return interado;
    }

    public void setInterado(boolean interado) {
        this.interado = interado;
    }

    public boolean isPlanoDeSaude() {
        return planoDeSaude;
    }

    public void setPlanoDeSaude(boolean planoDeSaude) {
        this.planoDeSaude = planoDeSaude;
    }

    public String getInterado() {
        if (interado) {
            return "Internado";
        } else {
            return "Não internado";
        }
    }

    public String getPlanoDeSaude() {
        if (planoDeSaude) {
            return "Possui plano de saúde";
        } else {
            return "Não possui plano de saúde";
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
        s += "Internado: " + getInterado() + "\n";
        s += "Plano de saúde: " + getPlanoDeSaude() + "\n";
        return s;
    }
}
