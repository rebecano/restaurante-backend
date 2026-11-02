package br.com.restaurante.app;

import br.com.restaurante.cardapio.ItemCardapio;
import br.com.restaurante.pedido.Pedido;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class MenuConsole {

    private Scanner sc = new Scanner(System.in);

    // ---------------------------------------------------------------
    // Leituras básicas
    // ---------------------------------------------------------------

    public int lerInteiro(String pergunta) {
        boolean leituraValida = false;
        int numero = 0;

        while (!leituraValida) {
            try {
                System.out.print(pergunta);
                numero = sc.nextInt();
                sc.nextLine();
                leituraValida = true;
            } catch (InputMismatchException ime) {
                System.out.println("Digite apenas números inteiros!");
                sc.nextLine();
            }
        }

        return numero;
    }

    public int lerOpcao(int menorValorAceito, int maiorValorAceito, String pergunta) {
        do {
            int opcao = lerInteiro(pergunta);

            if (opcao >= menorValorAceito && opcao <= maiorValorAceito) {
                return opcao;
            } else {
                opcaoInexistente();
            }

        } while (true);
    }

    public String lerNomeCliente() {
        String nomeCliente = "";

        System.out.println();

        while (nomeCliente.trim().isEmpty()) {
            System.out.print("Digite o nome do cliente: ");
            nomeCliente = sc.nextLine();

            if (nomeCliente.trim().isEmpty()) {
                System.out.println("O nome não pode ficar em branco!");
            }
        }

        return nomeCliente.trim();
    }

    // ---------------------------------------------------------------
    // Perguntas compostas
    // ---------------------------------------------------------------

    // Respostas na ordem queijo, alface, tomate, cebola (true = mantém o ingrediente)
    public ArrayList<Boolean> manterIngredientes() {
        ArrayList<String> ingredientes = new ArrayList<>(List.of("queijo", "alface", "tomate", "cebola"));
        ArrayList<Boolean> respostas = new ArrayList<>();
        String opcao = "";

        System.out.println();

        for (String ingrediente : ingredientes) {
            while (!opcao.trim().equalsIgnoreCase("s") && !opcao.trim().equalsIgnoreCase("n")) {
                System.out.printf("Tirar %s? (s/n): ", ingrediente);
                opcao = sc.nextLine();

                if (opcao.trim().equalsIgnoreCase("s")) {
                    respostas.add(false);
                } else if (opcao.trim().equalsIgnoreCase("n")) {
                    respostas.add(true);
                } else {
                    opcaoInexistente();
                }
            }
            opcao = "";
        }

        return respostas;
    }

    public ArrayList<Integer> adicionaisLanche() {
        ArrayList<Integer> respostas = new ArrayList<>();
        int opcao;

        do {
            opcao = lerOpcao(0, 2, "Selecione uma das opções acima: ");
            respostas.add(opcao);

        } while (opcao != 0);

        return respostas;
    }

    public String lerOpcaoAdicionarItem() {
        String opcao = "";

        System.out.println();

        while (!opcao.trim().equalsIgnoreCase("s") && !opcao.trim().equalsIgnoreCase("n")) {
            System.out.print("Adicionar mais um item? (s/n): ");
            opcao = sc.nextLine();

            if (!opcao.trim().equalsIgnoreCase("s") && !opcao.trim().equalsIgnoreCase("n")) {
                opcaoInexistente();
            }
        }

        return opcao;
    }

    // ---------------------------------------------------------------
    // Menus
    // ---------------------------------------------------------------

    public void exibirMenu() {
        System.out.println("\n=== RESTAURANTE ===\n" +
                "1 - Registrar novo pedido\n" +
                "2 - Listar pedidos do dia\n" +
                "3 - Modificar pedido\n" +
                "4 - Fechar restaurante\n" +
                "0 - Sair");
    }

    public void exibirCardapio() {
        System.out.println("\n=== CARDÁPIO ===\n" +
                "1 - X-Burger\n" +
                "2 - X-Salada\n" +
                "3 - X-Bacon");
    }

    public void exibirMenuAdicionais() {
        System.out.println("\n=== ADICIONAIS ===\n" +
                "1 - Cheddar (+ R$ 4,00)\n" +
                "2 - Bacon Extra (+ R$ 6,00)\n" +
                "0 - Sair");
    }

    public void exibirMenuModificacoes() {
        System.out.println("\n=== MODIFICAR PEDIDO ===\n" +
                "1 - Acrescentar item\n" +
                "2 - Remover item\n" +
                "3 - Cancelar pedido\n" +
                "0 - Voltar");
    }

    // ---------------------------------------------------------------
    // Exibição de dados
    // ---------------------------------------------------------------

    public void exibirPedido(Pedido pedido) {
        System.out.printf("\n--- PEDIDO #%d ---\n", pedido.getNumero());
        System.out.printf("Cliente: %s\n\n", pedido.getNomeCliente());

        for (ItemCardapio item : pedido.getItens()) {
            System.out.printf("%s (R$ %.2f)\n", item.getDescricao(), item.getPreco());
        }

        System.out.printf("\nTotal: R$ %.2f\n", pedido.calcularTotal());
    }

    public void exibirPedidos(ArrayList<Pedido> listaPedidos) {
        System.out.println("\n=== PEDIDOS ===");

        for (Pedido pedido : listaPedidos) {
            exibirPedido(pedido);
        }
    }

    public void exibirListaItensNumerados(ArrayList<ItemCardapio> itensPedido) {
        System.out.println("\n=== ITENS DO PEDIDO ===");

        for (int i = 0; i < itensPedido.size(); i++) {
            ItemCardapio item = itensPedido.get(i);
            System.out.printf("%d - %s (R$ %.2f)\n", i + 1, item.getDescricao(), item.getPreco());
        }
    }

    public void exibirPedidoCancelado(int numeroPedido) {
        System.out.printf("Pedido #%d cancelado.\n", numeroPedido);
    }

    public void exibirFechamentoDoDia(double total, ArrayList<Pedido> listaPedidos, HashSet<String> combinacoes) {
        System.out.println("\n=== RELATÓRIO DO DIA ===\n");

        for (Pedido pedido : listaPedidos) {
            System.out.printf("Pedido #%d - R$ %.2f\n", pedido.getNumero(), pedido.calcularTotal());
        }

        System.out.printf("\nCombinações diferentes: %d\n", combinacoes.size());
        for (String combinacao : combinacoes) {
            System.out.println("- " + combinacao);
        }

        System.out.printf("\nTotal final: R$ %.2f\n", total);
    }

    // ---------------------------------------------------------------
    // Mensagens
    // ---------------------------------------------------------------

    public void opcaoInexistente() {
        System.out.println("Opção não encontrada!");
    }

    public void exibirPedidoSemItens() {
        System.out.println("O pedido não tem itens para remover.");
    }

    public void encerrarSistema() {
        System.out.println("\nEncerrando sistema...");
    }

}