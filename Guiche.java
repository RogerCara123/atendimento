package atendimento;

// A classe Guiche é utilizada para indicar o estado do guichê
public class Guiche {
    boolean livre; // Indica se ele está disponível
    int tempoOcupado; // Indica por quanto tempo estará ocupado

    public Guiche(boolean livre) {
        this.livre = livre;
        this.tempoOcupado = 0;
    }
}