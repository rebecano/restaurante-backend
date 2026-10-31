package br.com.restaurante.pedido;

import java.util.HashMap;

public class GerenciadorPedidos {

    private HashMap<Integer, Pedido> pedidos = new HashMap<>();
    private int contadorPedidos = 1;

    public Pedido criarPedido(String cliente) {
        Pedido novoPedido = new Pedido(contadorPedidos, cliente);
        pedidos.put(novoPedido.getNumero(), novoPedido);
        contadorPedidos++;
        return novoPedido;
    }

    public Pedido buscarPedido(int numero) {
        return pedidos.getOrDefault(numero, null);
    }

    // tentar melhorar pra printar só no menu console
    public void getPedidos() {
        pedidos.forEach((numero, pedido) -> {
            System.out.println("\nNúmero do pedido: " + numero);
            System.out.println("Detalhes: ");
            pedido.getItens();
            double total = pedido.calcularTotal();
            System.out.printf("\nTotal: R$ %.2f\n\n", total);

        });
    }

    public double calcularTotalDoDia() {
        double total = 0.0;
        for (Pedido pedido : pedidos.values()) {
            total += pedido.calcularTotal();
        }
        return total;
    }

}
