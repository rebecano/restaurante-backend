package br.com.restaurante.app;

import br.com.restaurante.cardapio.*;
import br.com.restaurante.pedido.GerenciadorPedidos;
import br.com.restaurante.pedido.Pedido;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Objects;

public class RestauranteFacade {

    GerenciadorPedidos sistema = new GerenciadorPedidos();

    public Pedido registrarPedido(String nomeCliente) {
        return sistema.criarPedido(nomeCliente);
    }

    public void personalizarLanche(Pedido novoPedido, int opcaoLanche, ArrayList<Boolean> descricao, ArrayList<Integer> adicionais) {
        Lanche lanchePedido = LancheFactory.criarLanche(opcaoLanche);

        lanchePedido.setQueijo(descricao.get(0));
        lanchePedido.setAlface(descricao.get(1));
        lanchePedido.setTomate(descricao.get(2));
        lanchePedido.setCebola(descricao.get(3));

        ItemCardapio adicional = lanchePedido;

        for (int resposta : adicionais) {
            if (resposta == 1) {
                adicional = new ComCheddar(adicional);
            }
            if (resposta == 2) {
                adicional = new ComBaconExtra(adicional);
            }
        }

        novoPedido.adicionarItem(adicional);
    }

    public Pedido verificarPedido(int numeroPedido) {
        return sistema.buscarPedido(numeroPedido);
    }

    public HashMap<Integer, Pedido> cancelarPedido(int numeroPedido) {
        return sistema.cancelarPedido(numeroPedido);
    }

    public ArrayList<Pedido> listarPedidos() {
        return sistema.getPedidos();
    }

    public HashSet<String> combinacoesDoDia() {
        return sistema.combinacoesDoDia();
    }

    public Double totalDia() {
        return sistema.calcularTotalDoDia();
    }

}
