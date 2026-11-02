package br.com.restaurante.app;

import br.com.restaurante.cardapio.ItemCardapio;
import br.com.restaurante.pedido.GerenciadorPedidos;
import br.com.restaurante.pedido.Pedido;

import java.util.ArrayList;
import java.util.HashSet;

public class Main {
    private static void adicionarItens(MenuConsole menu, RestauranteFacade restauranteFacade, Pedido pedido) {
        String maisUm;

        do {
            menu.exibirCardapio();
            int opcaoLanche = menu.lerOpcao(1, 4, "\nSelecione uma das opções acima: ");

            ArrayList<Boolean> descricao = menu.manterIngredientes();

            menu.exibirMenuAdicionais();
            ArrayList<Integer> adicionais = menu.adicionaisLanche();

            restauranteFacade.personalizarLanche(pedido, opcaoLanche, descricao, adicionais);

            maisUm = menu.lerOpcaoAdicionarItem();
        } while (maisUm.trim().equalsIgnoreCase("s"));
    }

    static void main(String[] args) {

        MenuConsole menu = new MenuConsole();
        RestauranteFacade restauranteFacade = new RestauranteFacade();

        ArrayList<Pedido> pedidos = restauranteFacade.listarPedidos();

        int opcaoMenu;

        do {
            menu.exibirMenu();
            opcaoMenu = menu.lerOpcao(0, 3, "\nSelecione uma das opções acima: ");

            switch (opcaoMenu) {
                case 1:
                    String nomeCliente = menu.lerNomeCliente();
                    Pedido pedido = restauranteFacade.registrarPedido(nomeCliente);

                    adicionarItens(menu, restauranteFacade, pedido);

                    menu.exibirPedido(pedido);
                    break;

                case 2:
                    menu.exibirPedidos(restauranteFacade.listarPedidos());
                    break;

                case 3:
                    int numeroPedido = menu.lerInteiro("Digite o número do pedido: ");

                    Pedido pedidoParaModificar = restauranteFacade.verificarPedido(numeroPedido);

                    if (pedidoParaModificar == null) {
                        menu.opcaoInexistente();
                        break;
                    } else {
                        menu.exibirPedido(pedidoParaModificar);

                        menu.exibirMenuModificacoes();

                        int opcaoModificacoes = menu.lerOpcao(0, 3, "\nSelecione uma das opções acima: ");

                        switch (opcaoModificacoes) {
                            case 1:
                                adicionarItens(menu, restauranteFacade, pedidoParaModificar);

                                menu.exibirPedido(pedidoParaModificar);
                                break;

                            case 2:
                                ArrayList<ItemCardapio> listaItensPedidoParaModificar = pedidoParaModificar.getItens();

                                if (listaItensPedidoParaModificar.isEmpty()) {
                                    menu.exibirPedidoSemItens();
                                } else {
                                    menu.exibirListaItensNumerados(listaItensPedidoParaModificar);

                                    int opcaoRemoverItem = menu.lerOpcao(1, listaItensPedidoParaModificar.size(), "\nSelecione o item que deseja remover: ");

                                    pedidoParaModificar.removerItem(opcaoRemoverItem);

                                    menu.exibirPedido(pedidoParaModificar);
                                }
                                break;

                            case 3:
                                restauranteFacade.cancelarPedido(numeroPedido);
                                break;

                            case 0:
                                break;
                        }
                    }

                    break;

                case 4:
                    double total = restauranteFacade.totalDia();
                    HashSet<String> combinacoes = restauranteFacade.combinacoesDoDia();

                    menu.exibirFechamentoDoDia(total, pedidos, combinacoes);
                    break;

                case 0:
                    menu.encerrarSistema();
                    break;
            }

        } while (opcaoMenu != 0);

    }
}
