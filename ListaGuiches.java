package atendimento;

// A lista de guichês é utilizada para auxiliar o gerenciamento do conjunto de guichês.
// Como o número de guichês é fixo e conhecido, é necessário apenas uma lista estática
public class ListaGuiches {
    public int quantidadeElementos;
    public Guiche lista[];
    public int inicio;
    public int fim;

    public ListaGuiches(int tamanho) {
        this.quantidadeElementos = 0;
        this.lista = new Guiche[tamanho];
        this.inicio = 0;
        this.fim = 0;
    }

    public boolean adicionar(Guiche valor) {
        if (listaCheia()) {
            return false;
        } else if (listaVazia()) {
            this.lista[inicio] = valor;
            this.quantidadeElementos++;
            this.inicio++;
            this.fim++;
            return true;
        } else {
            this.lista[quantidadeElementos] = valor;
            this.quantidadeElementos++;
            this.fim++;
            return true;
        }
    }

    private boolean listaCheia() {
        return false;
    }

    private boolean listaVazia() {
        return false;
    }

    public boolean checar(int indice) {
        return lista[indice].livre;
    }

    public Guiche obter(int indice) {
        return lista[indice];
    }

    public int tamanho() {
        return this.quantidadeElementos;
    }
}


