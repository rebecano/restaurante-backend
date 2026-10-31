package br.com.restaurante.app;

import br.com.restaurante.pedido.GerenciadorPedidos;
import br.com.restaurante.pedido.Pedido;

public class Main {
    static void main() {

        MenuConsole menu = new MenuConsole();
        RestauranteFacade restauranteFacade = new RestauranteFacade();

        int opcaoMenu;

        do {
            menu.exibirMenu();
            opcaoMenu = menu.lerOpcaoMenu();

            switch (opcaoMenu) {
                case 1:
                    Pedido novoPedido = restauranteFacade.registrarPedido();
                    restauranteFacade.finalizarPedido(novoPedido);
                    break;

                case 2:
                    restauranteFacade.listarPedidos();
                    break;

                case 3:
                    restauranteFacade.fecharRestaurante();
                    break;

                case 0:
                    menu.encerrarSistema();
                    break;

                default:
                    menu.opcaoInexistente();
                    break;
            }

        } while (opcaoMenu != 0);

    }
}
