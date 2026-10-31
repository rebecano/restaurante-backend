package br.com.restaurante.app;

import br.com.restaurante.pedido.GerenciadorPedidos;
import br.com.restaurante.pedido.Pedido;

import java.util.Scanner;

public class MenuConsole {

    Scanner sc = new Scanner(System.in);
    GerenciadorPedidos gerenciador = new GerenciadorPedidos();

    public void exibirMenu() {
        System.out.println("=== RESTAURANTE ===\n" +
                "1 - Registrar novo pedido\n" +
                "2 - Listar pedidos do dia\n" +
                "3 - Fechar restaurante\n" +
                "0 - Sair");
    }

    // Achar uma solução para evitar valores nulos

    public int lerOpcaoMenu() {
        System.out.print("\nSelecione uma das opções acima: ");
        int opcao = sc.nextInt();
        sc.nextLine();
        return opcao;
    }

    public String lerNomeCliente() {
        System.out.println("\nDigite o nome do cliente: ");
        String nomeCliente = sc.nextLine();
        return nomeCliente;
    }

    public String lerOpcaoLanche() {
        System.out.println("Digite o lanche escolhido: ");
        String opcaoLanche = sc.nextLine();
        return opcaoLanche;
    }

    public void exibirMenuAdicionais() {
        System.out.println("=== ADICIONAIS ===\n" +
                "1 - Cheddar (+ R$ 4,00)\n" +
                "2 - Bacon Extra (+ R$ 6,00)\n" +
                "0 - Sair");
    }

    public int lerAdicionaisLanche() {
        System.out.print("\nSelecione uma das opções acima: ");
        int opcao = sc.nextInt();
        sc.nextLine();
        return opcao;
    }

    public void exibirPedido(Pedido pedido) {
        System.out.printf("\n=== PEDIDO #%d ===\n\n", pedido.getNumero());
        pedido.getItens();
        System.out.println("\nTotal: " + pedido.calcularTotal() + "\n\n");
    }

    public void exibirFechamentoDoDia(double total, GerenciadorPedidos sistema) {
        System.out.println("\n=== RELATÓRIO DO DIA ===\n");
        sistema.getPedidos();
        System.out.printf("\n\nTotal: R$ %.2f\n", total);
    }

    public void encerrarSistema() {
        System.out.println("Encerrando sistema...");
    }

    public void opcaoInexistente() {
        System.out.println("Opção não encontrada!");
    }
}
