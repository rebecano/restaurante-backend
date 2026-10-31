package br.com.restaurante.pedido;

import br.com.restaurante.cardapio.ItemCardapio;
import br.com.restaurante.cardapio.Lanche;

import java.util.ArrayList;
import java.util.List;

public class Pedido {

    private int numero;
    private String nomeCliente;

    private ArrayList<ItemCardapio> itens;

    public Pedido(int numero, String nomeCliente) {
        this.numero = numero;
        setNomeCliente(nomeCliente);
    }

    private void setNomeCliente(String nomeCliente) {
        if (nomeCliente == null || nomeCliente.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome inválido!");
        } else {
            this.nomeCliente = nomeCliente;
        }
    }

    public int getNumero() {
        return numero;
    }

    public void adicionarItem(ItemCardapio itemCardapio) {
        itens.add(itemCardapio);
    }

    // tentar melhorar pra printar só no menu console
    public void getItens() {
        for (ItemCardapio item : itens) {
            System.out.printf("%s (R$ %.2f)\n", item.getDescricao(), item.getPreco());
        }
    }

    public double calcularTotal() {
        double total = 0.0;
        for (ItemCardapio item : itens) {
            total = (total + item.getPreco());
        }
        return total;
    }


}
