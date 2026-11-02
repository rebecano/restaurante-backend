package br.com.restaurante.pedido;

import br.com.restaurante.cardapio.ItemCardapio;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class GerenciadorPedidos {

    private HashMap<Integer, Pedido> pedidos = new HashMap<>();
    private int contadorPedidos = 1;

    public Pedido criarPedido(String cliente) {
        Pedido novoPedido = new Pedido(contadorPedidos, cliente);
        pedidos.put(novoPedido.getNumero(), novoPedido);
        contadorPedidos++;
        return novoPedido;
    }

    public Pedido buscarPedido(int numeroPedido) {
        return pedidos.getOrDefault(numeroPedido, null);
    }

    public HashMap<Integer, Pedido> cancelarPedido(int numeroPedido) {
        pedidos.remove(numeroPedido);

        return new HashMap<>(pedidos);
    }

    public HashSet<String> combinacoesDoDia() {
        HashSet<String> combinacoes = new HashSet<>();

        for (Pedido pedido : pedidos.values()) {
            for (ItemCardapio item : pedido.getItens()) {
                combinacoes.add(item.getDescricao());
            }
        }

        return combinacoes;
    }

    public ArrayList<Pedido> getPedidos() {
       return new ArrayList<>(pedidos.values());
    }

    public double calcularTotalDoDia() {
        double total = 0.0;
        for (Pedido pedido : pedidos.values()) {
            total += pedido.calcularTotal();
        }
        return total;
    }

}
