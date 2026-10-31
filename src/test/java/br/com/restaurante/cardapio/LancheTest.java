package br.com.restaurante.cardapio;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LancheTest {

    @Test
    void verificarSeUmPrecoNegativoEhRecusado() {
        ArrayList<String> ingredientes = new ArrayList<>(List.of("pão", "carne", "queijo"));
        assertThrows(IllegalArgumentException.class, () -> {
            new Lanche("X-Invalido", -10.0, ingredientes);
        });
    }
}