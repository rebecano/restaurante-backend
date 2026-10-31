package br.com.restaurante.cardapio;

import java.util.ArrayList;
import java.util.List;

public class LancheFactory {

    public static Lanche xBurger() {
        ArrayList<String> ingredientes = new ArrayList<>(List.of("pão", "hambúrguer", "queijo", "alface", "tomate"));
        return new Lanche("X-Burger", 16.00, ingredientes);
    }

    public static Lanche xSalada() {
        ArrayList<String> ingredientes = new ArrayList<>(List.of("pão", "hambúrguer", "queijo", "alface", "tomate", "cebola"));
        return new Lanche("X-Salada", 18.00, ingredientes);
    }

    public static Lanche xBacon() {
        ArrayList<String> ingredientes = new ArrayList<>(List.of("pão", "hambúrguer", "queijo", "bacon", "alface", "tomate", "cebola"));
        return new Lanche("X-Bacon", 22.00, ingredientes);
    }

    public static Lanche criarLanche(String tipo) {
        if (tipo.trim().equalsIgnoreCase("X-Burger")) {
            return xBurger();
        } else if (tipo.equalsIgnoreCase("X-Salada")) {
            return xSalada();
        } else if (tipo.equalsIgnoreCase("X-Bacon")) {
            return xBacon();
        }

        throw new IllegalArgumentException("Tipo de item inválido: " + tipo);
    }
}


