package atendimento;

// Interface para garantir a integridade dos métodos da fila de clientes
public interface IFilaCliente {
    public void enfileirar(int horaDeEntrada);
    public int desenfileirar();
    public int frente();
    public boolean estaVazia();
    public int tamanho();
    public void mostrar();
}