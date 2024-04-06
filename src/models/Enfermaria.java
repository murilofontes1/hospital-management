package src.models;

public class Enfermaria {
    private String codEnfermaria;
    private int qtdeLeitos;
    private int leitosDisponiveis;

    public Enfermaria(String codEnfermaria, int qtdeLeitos) {
        this.codEnfermaria = codEnfermaria;
        this.qtdeLeitos = qtdeLeitos;
        this.leitosDisponiveis = qtdeLeitos;
    }

    public int getLeitosDisponiveis() {
        return leitosDisponiveis;
    }

    public void setLeitosDisponiveis(int leitosDisponiveis) {
        this.leitosDisponiveis = leitosDisponiveis;
    }

    public String getCodEnfermaria() {
        return codEnfermaria;
    }

    public void setCodEnfermaria(String codEnfermaria) {
        this.codEnfermaria = codEnfermaria;
    }

    public int getQtdeLeitos() {
        return qtdeLeitos;
    }

    public void setQtdeLeitos(int qtdeLeitos) {
        this.qtdeLeitos = qtdeLeitos;
    }

    @Override
    public String toString() {
        return "Enfermaria [codEnfermaria=" + codEnfermaria + ", leitosDisponiveis=" + leitosDisponiveis + ", qtdeLeitos="
                + qtdeLeitos + "]\n";
    }

}
