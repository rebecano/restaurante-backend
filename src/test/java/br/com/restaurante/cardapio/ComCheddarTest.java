package br.com.restaurante.cardapio;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ComCheddarTest {

    @Test
    void xBaconComCheddarCusta26() {
        ItemCardapio lanche = new ComCheddar(LancheFactory.xBacon());
        assertEquals(26.00, lanche.getPreco(), 0.001);
    }

}