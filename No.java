package atendimento;

// A classe No é utilizada para representar cada nó da fila de clientes
public class No {
    public Cliente cliente = new Cliente();
    public No proximo;

    public No(int horaDeEntrada) {
        cliente = new Cliente(horaDeEntrada);
        this.proximo = null;
    }
}