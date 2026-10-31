package br.com.restaurante.cardapio;

import java.util.ArrayList;
import java.util.List;

public class Lanche implements ItemCardapio {

    private String nome;
    private double preco;
    private ArrayList<String> ingredientes;

    private boolean queijo = true;
    private boolean alface = true;
    private boolean tomate = true;
    private boolean cebola = true;

    public Lanche(String nome, double preco, ArrayList<String> ingredientes) {
        setNome(nome);
        setPreco(preco);
        this.ingredientes = ingredientes;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        if (nome == null || nome.isEmpty()) {
            throw new IllegalArgumentException("Inválido! Mantido " + nome);
        } else {
            this.nome = nome;
        }
    }

    @Override
    public String getDescricao() {
        /*
        String descricao = null;
        for (String ingrediente : ingredientes) {
            if (descricao == null || descricao.isEmpty()) {
                descricao = ingrediente;
            } else {
                descricao = nome + ": " + descricao + ", " + ingrediente;
            }
        }
        return descricao;
         */

        String desc = nome;

        if (!queijo) desc += " sem queijo";

        if (!alface) desc += " sem alface";

        if (!tomate) desc += " sem tomate";

        if (!cebola) desc += " sem cebola";

        return desc;
    }

    @Override
    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        if (preco < 0) {
            throw new IllegalArgumentException("Valor inválido!");
        } else {
            this.preco = preco;
        }
    }

}
