package br.com.restaurante.cardapio;

public class ComBaconExtra extends LancheDecorator {

    public ComBaconExtra(ItemCardapio itemCardapio) {
        super(itemCardapio);
    }

    @Override
    public String getDescricao() {
        return (lancheEmbrulhado.getDescricao() + " + bacon extra");
    }

    @Override
    public double getPreco() {
        return (lancheEmbrulhado.getPreco() + 6.00);
    }

}
