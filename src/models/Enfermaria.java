package src.models;
/*
 * Classe para representar uma enfermaria
 * Enfermaria contem os pacientes internados
 * Enfermaria contém um código, quantidade de leitos e leitos disponíveis
 */
public class Enfermaria {
    private String codEnfermaria;
    private int qtdeLeitos;
    private int leitosDisponiveis;

    public Enfermaria(String codEnfermaria, int qtdeLeitos) {
        this.codEnfermaria = codEnfermaria;
        this.qtdeLeitos = qtdeLeitos;
        this.leitosDisponiveis = qtdeLeitos;
    }

    public Enfermaria(String codEnfermaria, int qtdeLeitos, int qtdeLeitosDisponiveis) {
        this.codEnfermaria = codEnfermaria;
        this.qtdeLeitos = qtdeLeitos;
        this.leitosDisponiveis = qtdeLeitosDisponiveis;
    }

    public Enfermaria(int qtdeLeitos) {
        this.qtdeLeitos = qtdeLeitos;
        this.leitosDisponiveis = qtdeLeitos;
    }

    public Enfermaria() {
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
        String s = "";
        s += "Código da enfermaria: " + getCodEnfermaria() + "\n";
        s += "Quantidade de leitos: " + getQtdeLeitos() + "\n";
        s += "Leitos disponíveis: " + getLeitosDisponiveis() + "\n";
        return s;
    }

}
