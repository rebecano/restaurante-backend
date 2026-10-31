package br.com.restaurante.cardapio;

public class ComCheddar extends LancheDecorator{

    public ComCheddar(ItemCardapio itemCardapio) {
        super(itemCardapio);
    }

    @Override
    public String getDescricao() {
        return (lancheEmbrulhado.getDescricao() + " + cheddar");
    }

    @Override
    public double getPreco() {
        return (lancheEmbrulhado.getPreco() + 4.00);
    }

}
