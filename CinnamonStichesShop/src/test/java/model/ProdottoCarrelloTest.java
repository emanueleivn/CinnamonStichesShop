package model;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ProdottoCarrelloTest {

    @Test
    @DisplayName("getTot should return costo * quantita")
    void getTotReflectsQuantityAndCost() {
        Prodotto prodotto = mock(Prodotto.class);
        when(prodotto.getCosto()).thenReturn(12.5f);

        ProdottoCarrello prodottoCarrello = new ProdottoCarrello(prodotto, 3);

        assertEquals(37.5f, prodottoCarrello.getTot(), 0.0001f);
    }

    @Test
    @DisplayName("incrementaQ increases quantity by one")
    void incrementaQIncreasesQuantity() {
        Prodotto prodotto = mock(Prodotto.class);
        when(prodotto.getCosto()).thenReturn(10f);

        ProdottoCarrello prodottoCarrello = new ProdottoCarrello(prodotto, 1);
        prodottoCarrello.incrementaQ();

        assertEquals(2, prodottoCarrello.getQuantita());
        assertEquals(20f, prodottoCarrello.getTot(), 0.0001f);
    }

    @Test
    @DisplayName("decrementaQ decreases quantity by one")
    void decrementaQDecreasesQuantity() {
        Prodotto prodotto = mock(Prodotto.class);
        when(prodotto.getCosto()).thenReturn(5f);

        ProdottoCarrello prodottoCarrello = new ProdottoCarrello(prodotto, 3);
        prodottoCarrello.decrementaQ();

        assertEquals(2, prodottoCarrello.getQuantita());
        assertEquals(10f, prodottoCarrello.getTot(), 0.0001f);
    }

    @Test
    @DisplayName("equals returns true when products share the same codice")
    void equalsDependsOnProductCode() {
        Prodotto prodotto1 = mock(Prodotto.class);
        Prodotto prodotto2 = mock(Prodotto.class);

        when(prodotto1.getCosto()).thenReturn(1f);
        when(prodotto1.getCodice()).thenReturn(42);
        when(prodotto2.getCosto()).thenReturn(2f);
        when(prodotto2.getCodice()).thenReturn(42);

        ProdottoCarrello first = new ProdottoCarrello(prodotto1, 1);
        ProdottoCarrello second = new ProdottoCarrello(prodotto2, 2);

        assertEquals(first, second);
        assertEquals(second, first);
    }
}
