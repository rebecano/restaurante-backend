package br.com.restaurante.app;

import br.com.restaurante.cardapio.ComBaconExtra;
import br.com.restaurante.cardapio.ComCheddar;
import br.com.restaurante.cardapio.ItemCardapio;
import br.com.restaurante.cardapio.Lanche;
import br.com.restaurante.pedido.GerenciadorPedidos;
import br.com.restaurante.pedido.Pedido;
import lombok.NonNull;

import java.util.*;

public class MenuConsole {

    Scanner sc = new Scanner(System.in);

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
                "3 - X-Bacon\n");
    }

    public void exibirMenuModificacoes() {
        System.out.println("\n=== OPÇÕES ===\n" +
                "1 - Acrescentar\n" +
                "2 - Remover\n" +
                "3 - Cancelar\n" +
                "0 - Sair");
    }

    public void exibirListaItensNumerados(ArrayList<ItemCardapio> itensPedido) {
        System.out.println();

        for (int i = 0; i < itensPedido.size(); i++) {
            ItemCardapio item = itensPedido.get(i);
            System.out.printf("%d - %s (R$ %.2f)\n", i + 1, item.getDescricao(), item.getPreco());
        }
    }

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
                System.out.println("Digite apenas números inteiros!\n");
                sc.nextLine();
            }
        }

        return numero;
    }

    public int lerOpcao(int menorValorAceito, int maiorValorAceito, String pergunta) {
        int opcao;

        do {
            opcao = lerInteiro(pergunta);

            if (opcao >= menorValorAceito && opcao <= maiorValorAceito) {
                return opcao;
            } else {
                opcaoInexistente();
            }

        } while (true);
    }

    public String lerNomeCliente() {
        String nomeCliente = "";

        try {
            System.out.println("\nDigite o nome do cliente: ");
            nomeCliente = sc.nextLine();
        } catch (IllegalArgumentException illegalArgumentException) {
            opcaoInexistente();
        }

        return nomeCliente;
    }

    public ArrayList<Boolean> manterIngredientes() {
        ArrayList<String> ingredientes = new ArrayList<>(List.of("queijo", "alface", "tomate", "cebola"));
        ArrayList<Boolean> respostas = new ArrayList<>();
        String opcao = "";

        for (String ingrediente : ingredientes) {
            while (!opcao.trim().equalsIgnoreCase("s") && !opcao.trim().equalsIgnoreCase("n")) {
                System.out.printf("\nTirar %s? (s/n)\n", ingrediente);
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

    public String lerOpcaoAdicionarItem() {
        String opcao = "";

        while (!opcao.trim().equalsIgnoreCase("s") && !opcao.trim().equalsIgnoreCase("n")) {
            System.out.println("\nAdicionar mais um item? (s/n)");
            opcao = sc.nextLine();

            if (!opcao.trim().equalsIgnoreCase("s") && !opcao.trim().equalsIgnoreCase("n")) {
                opcaoInexistente();
            }
        }

        return opcao;
    }


    public void exibirMenuAdicionais() {
        System.out.println("\n=== ADICIONAIS ===\n" +
                "1 - Cheddar (+ R$ 4,00)\n" +
                "2 - Bacon Extra (+ R$ 6,00)\n" +
                "0 - Sair");
    }

    public ArrayList<Integer> adicionaisLanche() {
        ArrayList<Integer> respostas = new ArrayList<>();
        int opcao;

        do {
            opcao = lerOpcao(0, 2, "\nSelecione uma das opções acima: ");
            respostas.add(opcao);

        } while (opcao != 0);

        return respostas;
    }

    public void exibirPedido(Pedido pedido) {
        System.out.printf("\n--- PEDIDO #%d ---\n\n", pedido.getNumero());
        System.out.printf("Nome cliente: %s\n\n", pedido.getNomeCliente());
        for (ItemCardapio item : pedido.getItens()) {
            System.out.printf("%s (R$ %.2f)\n", item.getDescricao(), item.getPreco());
        }
        System.out.printf("\nTotal: R$ %.2f\n\n", pedido.calcularTotal());
    }

    public void exibirPedidos(ArrayList<Pedido> listaPedidos) {
        System.out.println("\n=== PEDIDOS ===");

        for (Pedido pedido : listaPedidos) {
            exibirPedido(pedido);
        }
    }

    public void exibirFechamentoDoDia(double total, ArrayList<Pedido> listaPedidos, HashSet<String> combinacoes) {
        System.out.println("\n=== RELATÓRIO DO DIA ===\n");

        for (Pedido pedido : listaPedidos) {
            System.out.printf("Pedido #%d - R$ %.2f",pedido.getNumero(), pedido.calcularTotal());
        }

        System.out.printf("Combinações diferentes: %d\n", combinacoes.size());
        for (String combinacao : combinacoes) {
            System.out.println("- " + combinacao);
        }

        System.out.printf("\nTotal final: R$ %.2f\n\n", total);
    }

    public void encerrarSistema() {
        System.out.println("Encerrando sistema...");
    }

    public void opcaoInexistente() {
        System.out.println("Opção não encontrada!");
    }

    public void exibirPedidoSemItens() {
        System.out.println("\nO pedido não tem itens para remover.");
    }
}
