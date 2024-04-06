package src.models;

public class Medicamento {
    private String codMedicamento;
    private String nome;

    public String getCodMedicamento() {
        return codMedicamento;
    }

    public void setCodMedicamento(String codMedicamento) {
        this.codMedicamento = codMedicamento;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return "Medicamento [codMedicamento=" + codMedicamento + ", nome=" + nome + "]\n";
    }
}
