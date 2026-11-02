package br.com.restaurante.cardapio;

import java.util.ArrayList;
import java.util.List;

public class LancheFactory {

    public static Lanche xBurger() {
        return new Lanche("X-Burger", 16.00);
    }

    public static Lanche xSalada() {
        return new Lanche("X-Salada", 18.00);
    }

    public static Lanche xBacon() {
        return new Lanche("X-Bacon", 22.00);
    }

    public static Lanche criarLanche(int tipo) {
        switch (tipo) {
            case 1:
                return xBurger();

            case 2:
                return xSalada();

            case 3:
                return xBacon();
        }
        throw new IllegalArgumentException("Tipo de item inválido: " + tipo);
    }
}


