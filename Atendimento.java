package atendimento;

//Roger Cara, Anthony Yan e Mario Chotte

import java.util.Random;

public class Atendimento {

    // Instância de gerador de números aleatórios
    public static Random randomizador = new Random();

    public static void main(String[] args) {

        // Criação das estruturas de dados
        FilaClientes filaClientes = new FilaClientes();
        ListaGuiches listaGuiches = new ListaGuiches(3);

        // Variáveis de estatísticas
        int totalClientes = 0, saques = 0, depositos = 0, pagamentos = 0, somaEspera = 0, tempoTransacao = 0;
        double mediaEspera = 0;

        // Criando e adicionando guichês à lista
        Guiche guiche1 = new Guiche(true);
        Guiche guiche2 = new Guiche(true);
        Guiche guiche3 = new Guiche(true);
        listaGuiches.adicionar(guiche1);
        listaGuiches.adicionar(guiche2);
        listaGuiches.adicionar(guiche3);

        int tempo = 0;
        int tempoExtra = 0;

        while (tempo <= 21600 || !filaClientes.estaVazia()) { // Horário de atendimento

            if (tempo > 21600) {
                tempoExtra++;
            }

            if (tempo <= 21600) { // Impede a chegada de clientes após o expediente

                if (chegouCliente()) {
                    filaClientes.enfileirar(tempo); // Adiciona um cliente na fila com o segundo atual
                    totalClientes++;
                }
            }

            if (guicheLivre(listaGuiches) && !filaClientes.estaVazia()) { // Verifica se há guichês livres e clientes na fila

                somaEspera += tempo - filaClientes.desenfileirar(); // Soma o tempo de espera de cada cliente

                for (int i = 0; i < 3; i++) { // Verifica qual guichê está livre
                    if (listaGuiches.obter(i).livre) {
                        listaGuiches.obter(i).livre = false; // Ocupa o guichê
                        int escolhaTransacao = randomizador.nextInt(3);
                        switch (escolhaTransacao) {
                            case 0:
                                tempoTransacao = tempo + 60; // Transação de saque
                                saques++;
                                break;
                            case 1:
                                tempoTransacao = tempo + 90; // Transação de depósito
                                depositos++;
                                break;
                            case 2:
                                tempoTransacao = tempo + 120; // Transação de pagamento
                                pagamentos++;
                                break;
                        }
                        listaGuiches.obter(i).tempoOcupado = tempoTransacao; // Tempo que o guichê ficará indisponível para atendimento
                        break;
                    }
                }
            }

            for (int i = 0; i < 3; i++) {
                if (!listaGuiches.obter(i).livre && tempo == listaGuiches.obter(i).tempoOcupado) {
                    listaGuiches.obter(i).livre = true;  // Libera o guichê se o cliente terminou a transação
                }
            }

            tempo++;
        }

        if (totalClientes > 0) {
            mediaEspera = somaEspera / totalClientes;
        }

        // Cálculos para exibir os resultados no console
        int segundosMedia = (int) mediaEspera % 60;
        mediaEspera /= 60;
        int minutosMedia = (int) mediaEspera % 60;

        int segundosExtra = (int) tempoExtra % 60;
        tempoExtra /= 60;
        int minutosExtra = (int) tempoExtra % 60;

        // Resultados
        System.out.println("Número total de clientes: " + totalClientes);
        System.out.println("Número de clientes que fizeram saque: " + saques);
        System.out.println("Número de clientes que fizeram depósito: " + depositos);
        System.out.println("Número de clientes que fizeram pagamento: " + pagamentos);
        System.out.println("Tempo médio de espera: " + minutosMedia + " minutos e " + segundosMedia + " segundos.");
        System.out.println("Tempo extra de expediente: " + minutosExtra + " minutos e " + segundosExtra + " segundos.");

    }

    // Ao chegar um cliente retorna true
    public static boolean chegouCliente() {
        int escolha = randomizador.nextInt(30);
        return escolha == 0;
    }

    // Verifica se há algum guichê livre
    public static boolean guicheLivre(ListaGuiches listaGuiches) {
        for (int i = 0; i < 3; i++) {
            if (listaGuiches.checar(i)) {
                return true;
            }
        }
        return false;
    }
}