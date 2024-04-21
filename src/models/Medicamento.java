package src.models;
/*
 * Classe para representar um medicamento
 * Medicamento é um remédio que pode ser prescrito em uma consulta
 * Medicamento contém um código e um nome
 */
public class Medicamento {
    private String codMedicamento;
    private String nome;

    public Medicamento() {
    }

    public Medicamento(String codMedicamento, String nome) {
        this.codMedicamento = codMedicamento;
        this.nome = nome;
    }

    public Medicamento(String nome) {
        this.nome = nome;
    }

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
        String s = "";
        s += "Código do medicamento: " + getCodMedicamento() + "\n";
        s += "Nome do medicamento: " + getNome() + "\n";
        return s;
    }
}
