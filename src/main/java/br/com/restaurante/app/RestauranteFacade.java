package br.com.restaurante.app;

import br.com.restaurante.cardapio.*;
import br.com.restaurante.pedido.GerenciadorPedidos;
import br.com.restaurante.pedido.Pedido;

public class RestauranteFacade {

    MenuConsole menuConsole = new MenuConsole();
    GerenciadorPedidos sistema = new GerenciadorPedidos();

    public Pedido registrarPedido() {
        Pedido pedido = sistema.criarPedido(menuConsole.lerNomeCliente());

        String opcaoLanche = menuConsole.lerOpcaoLanche();

        ItemCardapio lanchePedido = LancheFactory.criarLanche(opcaoLanche);

        int adicionalLanche;

        do {
            menuConsole.exibirMenuAdicionais();
            adicionalLanche = menuConsole.lerAdicionaisLanche();

            switch (adicionalLanche) {
                case 1:
                    lanchePedido = new ComCheddar(lanchePedido);
                    break;
                case 2:
                    lanchePedido = new ComBaconExtra(lanchePedido);
                case 0:
                    break;
                default:
                    menuConsole.opcaoInexistente();
            }

        } while (adicionalLanche != 0);

        pedido.adicionarItem(lanchePedido);

        return pedido;
    }

    public void finalizarPedido(Pedido pedido) {
        menuConsole.exibirPedido(pedido);
    }

    public void listarPedidos() {
        sistema.getPedidos();
    }

    public void fecharRestaurante() {
        double total = sistema.calcularTotalDoDia();
        menuConsole.exibirFechamentoDoDia(total, sistema);
    }

}
