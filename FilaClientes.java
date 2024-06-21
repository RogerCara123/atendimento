package atendimento;

// A FilaClientes armazena os nós de cada cliente
public class FilaClientes implements IFilaCliente {
    public int quantidadeElementos;
    public No inicio;

    public FilaClientes() {
        this.quantidadeElementos = 0;
        this.inicio = null;
    }

    @Override
    public void enfileirar(int horaDeEntrada) {
        No novo = new No(horaDeEntrada);
        if (estaVazia()) {
            novo.proximo = null;
            this.inicio = novo;
            quantidadeElementos++;
        } else {
            No auxiliar = inicio;
            while (auxiliar.proximo != null) {
                auxiliar = auxiliar.proximo;
            }
            auxiliar.proximo = novo;
            quantidadeElementos++;
        }
    }

    @Override
    public int desenfileirar() {
        if (estaVazia()) {
            return -1;
        } else {
            No retorno = inicio;
            inicio = inicio.proximo;
            quantidadeElementos--;
            return retorno.cliente.horaDeEntrada;
        }
    }

    @Override
    public int frente() {
        if (estaVazia()) {
            return -1;
        } else {
            return this.inicio.cliente.horaDeEntrada;
        }
    }

    @Override
    public boolean estaVazia() {
        return this.quantidadeElementos == 0;
    }

    @Override
    public int tamanho() {
        return this.quantidadeElementos;
    }

    @Override
    public void mostrar() {
        No auxiliar = inicio;
        if (!estaVazia()) {
            while (auxiliar != null) {
                System.out.print(auxiliar.cliente.horaDeEntrada + ", ");
                auxiliar = auxiliar.proximo;
            }
        }
    }
}