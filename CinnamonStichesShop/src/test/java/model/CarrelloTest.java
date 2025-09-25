package model;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CarrelloTest {

    private Carrello carrello;

    @BeforeEach
    void setUp() {
        carrello = new Carrello();
    }

    @Test
    @DisplayName("aggiungiAlCarrello adds new product and updates totals")
    void aggiungiAlCarrelloAddsNewProduct() {
        ProdottoCarrello prodottoCarrello = mock(ProdottoCarrello.class);
        when(prodottoCarrello.getTot()).thenReturn(25f);

        carrello.aggiungiAlCarrello(prodottoCarrello);

        assertEquals(1, carrello.getNumeroProdotti());
        assertEquals(25f, carrello.getTotale(), 0.0001f);
    }

    @Test
    @DisplayName("aggiungiAlCarrello merges duplicates by summing quantity and total")
    void aggiungiAlCarrelloMergesDuplicates() {
        ProdottoCarrello prodottoCarrello = mock(ProdottoCarrello.class);
        when(prodottoCarrello.getTot()).thenReturn(10f);
        when(prodottoCarrello.getQuantita()).thenReturn(1);

        carrello.aggiungiAlCarrello(prodottoCarrello);
        carrello.aggiungiAlCarrello(prodottoCarrello);

        verify(prodottoCarrello).setQuantity(2);
        verify(prodottoCarrello).setTot(20f);
        assertEquals(1, carrello.getNumeroProdotti());
    }

    @Test
    @DisplayName("rimuoviDalCarrello removes product and updates totals")
    void rimuoviDalCarrelloRemovesProduct() {
        ProdottoCarrello prodottoCarrello = mock(ProdottoCarrello.class);
        when(prodottoCarrello.getTot()).thenReturn(15f);

        carrello.aggiungiAlCarrello(prodottoCarrello);
        carrello.rimuoviDalCarrello(prodottoCarrello);

        assertEquals(0, carrello.getNumeroProdotti());
        assertEquals(0f, carrello.getTotale(), 0.0001f);
    }

    @Test
    @DisplayName("setProdottiCarrello replaces the current list and recalculates totals")
    void setProdottiCarrelloRecalculatesTotal() {
        ProdottoCarrello first = mock(ProdottoCarrello.class);
        ProdottoCarrello second = mock(ProdottoCarrello.class);
        Prodotto firstProduct = mock(Prodotto.class);
        Prodotto secondProduct = mock(Prodotto.class);

        when(first.getProdotto()).thenReturn(firstProduct);
        when(second.getProdotto()).thenReturn(secondProduct);
        when(firstProduct.getCosto()).thenReturn(5f);
        when(secondProduct.getCosto()).thenReturn(7.5f);
        when(first.getQuantita()).thenReturn(2);
        when(second.getQuantita()).thenReturn(1);
        when(first.getTot()).thenReturn(10f);
        when(second.getTot()).thenReturn(7.5f);

        ArrayList<ProdottoCarrello> prodotti = new ArrayList<>(List.of(first, second));
        carrello.setProdottiCarrello(prodotti);

        assertEquals(2, carrello.getNumeroProdotti());
        assertEquals(17.5f, carrello.getTotale(), 0.0001f);
    }

    @Test
    @DisplayName("incrementa increments quantity and increases internal total")
    void incrementaUpdatesQuantityAndTotal() {
        ProdottoCarrello prodottoCarrello = mock(ProdottoCarrello.class);
        Prodotto prodotto = mock(Prodotto.class);
        when(prodottoCarrello.getProdotto()).thenReturn(prodotto);
        when(prodottoCarrello.getQuantita()).thenReturn(1);
        when(prodottoCarrello.getTot()).thenReturn(5f, 10f);
        when(prodotto.getCosto()).thenReturn(5f);

        carrello.setProdottiCarrello(new ArrayList<>(List.of(prodottoCarrello)));

        carrello.incrementa(prodottoCarrello);

        verify(prodottoCarrello).incrementaQ();
        assertEquals(10f, carrello.getTotale(), 0.0001f);
    }

    @Test
    @DisplayName("decrementa decreases quantity and decreases total")
    void decrementaUpdatesQuantityAndTotal() {
        ProdottoCarrello prodottoCarrello = mock(ProdottoCarrello.class);
        Prodotto prodotto = mock(Prodotto.class);
        when(prodottoCarrello.getProdotto()).thenReturn(prodotto);
        when(prodottoCarrello.getQuantita()).thenReturn(2);
        when(prodottoCarrello.getTot()).thenReturn(10f, 5f);
        when(prodotto.getCosto()).thenReturn(5f);

        carrello.setProdottiCarrello(new ArrayList<>(List.of(prodottoCarrello)));

        carrello.decrementa(prodottoCarrello);

        verify(prodottoCarrello).decrementaQ();
        assertEquals(5f, carrello.getTotale(), 0.0001f);
    }

    @Test
    @DisplayName("equals compares cart identifiers")
    void equalsComparesId() {
        Carrello primo = new Carrello();
        Carrello secondo = new Carrello();
        primo.setId(1);
        secondo.setId(1);

        assertEquals(primo, secondo);
        secondo.setId(2);
        assertNotEquals(primo, secondo);
    }
}
