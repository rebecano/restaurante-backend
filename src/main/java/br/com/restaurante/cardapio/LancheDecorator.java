package br.com.restaurante.cardapio;

public abstract class LancheDecorator implements ItemCardapio {

    protected ItemCardapio lancheEmbrulhado;

    public LancheDecorator(ItemCardapio itemCardapio) {
        this.lancheEmbrulhado = itemCardapio;
    }

}
