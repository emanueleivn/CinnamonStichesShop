function createXMLHttpRequest() {
    var request;
    try {
        request = new XMLHttpRequest();
    } catch (e) {
        try {
            request = new ActiveXObject("Msxml2.XMLHTTP");
        } catch (e) {
            try {
                request = new ActiveXObject("Microsoft.XMLHTTP");
            } catch (e) {
                alert("Il browser non supporta AJAX");
                return null;
            }
        }
    }
    return request;
}

function loadAjaxDoc(url, method, params, callbackFunction) {
    var request = createXMLHttpRequest();
    if (request) {
        request.onreadystatechange = function() {
            if (this.readyState == 4) {
                if (this.status == 200) {
                    callbackFunction(this);
                } else {
                    if (this.status == 0) {
                        alert("Problemi nell'esecuzione della richiesta: nessuna risposta ricevuta nel tempo limite");
                    } else {
                        alert("Problemi nell'esecuzione della richiesta:\n" + this.statusText);
                    }
                    return null;
                }
            }
        };

        setTimeout(function() {
            if (request.readyState < 4) {
                request.abort();
            }
        }, 15000);

        if (method.toLowerCase() == "get") {
            if (params) {
                request.open("GET", url + "?" + params, true);
            } else {
                request.open("GET", url, true);
            }
            request.setRequestHeader("Connection", "close");
            request.send(null);
        } else {
            if (params) {
                request.open("POST", url, true);
                request.setRequestHeader("Connection", "close");
                request.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
                request.send(params);
            } else {
                alert("Usa GET se non ci sono parametri!");
                return null;
            }
        }
    }
}

function aggiornaCatalogo(request) {
    var prodotti = JSON.parse(request.responseText);
    var catalogo = document.querySelector('.catalogo');
    catalogo.innerHTML = '';

    // Ricostruisci il form delle selezioni
    var formOrdina = document.createElement('form');
    formOrdina.className = 'ordina';
    formOrdina.innerHTML = `
        <label for="ordinePrezzo">Ordina per prezzo:</label> 
        <select id="ordinePrezzo">
            <option value="prezzocrescente">Prezzo crescente</option>
            <option value="prezzodecrescente">Prezzo decrescente</option>
        </select> 
        <label for="ordineNome">Ordina per nome:</label> 
        <select id="ordineNome">
            <option value="a-z">A-Z</option>
            <option value="z-a">Z-A</option>
        </select>
    `;
    catalogo.appendChild(formOrdina);

    var ordinePrezzoCorrente = sessionStorage.getItem('ordinePrezzo') || 'prezzocrescente';
    var ordineNomeCorrente = sessionStorage.getItem('ordineNome') || 'a-z';
    document.getElementById('ordinePrezzo').value = ordinePrezzoCorrente;
    document.getElementById('ordineNome').value = ordineNomeCorrente;

    document.getElementById('ordinePrezzo').addEventListener('change', function() {
        var ordine = this.value;
        sessionStorage.setItem('ordinePrezzo', ordine);
        loadAjaxDoc('OrdinaElementi', 'POST', 'ordine=' + ordine, aggiornaCatalogo);
    });

    document.getElementById('ordineNome').addEventListener('change', function() {
        var ordine = this.value;
        sessionStorage.setItem('ordineNome', ordine);
        loadAjaxDoc('OrdinaElementi', 'POST', 'ordine=' + ordine, aggiornaCatalogo);
    });

    prodotti.forEach(function(prodotto) {
        var prodottoDiv = document.createElement('div');
        prodottoDiv.className = 'prodotto';
        var immagineDiv = document.createElement('div');
        immagineDiv.className = 'immagine';
        var img = document.createElement('img');
        img.src = 'images/products/' + prodotto.immagine;
        img.alt = 'Immagine Prodotto';
        immagineDiv.appendChild(img);
        var dettagliDiv = document.createElement('div');
        dettagliDiv.className = 'dettagli';
        var nomeP = document.createElement('p');
        nomeP.innerHTML = '<strong>Nome:</strong> ' + prodotto.nome;
        dettagliDiv.appendChild(nomeP);
        var descrizioneP = document.createElement('p');
        descrizioneP.innerHTML = '<strong>Descrizione:</strong> ' + prodotto.descrizione;
        dettagliDiv.appendChild(descrizioneP);
        var costoP = document.createElement('p');
        costoP.innerHTML = '<strong>Costo:</strong> ' + prodotto.costo;
        dettagliDiv.appendChild(costoP);
        var disponibilitaP = document.createElement('p');
        disponibilitaP.innerHTML = '<strong>Disponibilità:</strong> ' + (prodotto.isDisp ? 'Disponibile' : 'Non disponibile');
        dettagliDiv.appendChild(disponibilitaP);
        var actionDiv = document.createElement('div');
        actionDiv.className = 'action';
        var carrelloForm = document.createElement('form');
        carrelloForm.action = 'Carrello';
        carrelloForm.method = 'post';
        carrelloForm.innerHTML = 
            '<input type="hidden" name="action" value="add" />' +
            '<input type="hidden" name="codice" value="' + prodotto.codice + '" />' +
            '<label for="quantita_' + prodotto.codice + '">Quantità:</label>' +
            '<input type="number" id="quantita_' + prodotto.codice + '" name="quantita_' + prodotto.codice + '" min="1" max="10" value="1" required>' +
            '<button type="submit">Aggiungi al Carrello</button>';
        actionDiv.appendChild(carrelloForm);
        var preferitiForm = document.createElement('form');
        preferitiForm.action = 'Preferiti';
        preferitiForm.method = 'post';
        preferitiForm.innerHTML = 
            '<input type="hidden" name="codiceProdotto" value="' + prodotto.codice + '" />' +
            '<input type="hidden" name="actionFav" value="addFavorite" />' +
            '<button type="submit">Aggiungi ai Preferiti</button>';
        actionDiv.appendChild(preferitiForm);
        dettagliDiv.appendChild(actionDiv);
        prodottoDiv.appendChild(immagineDiv);
        prodottoDiv.appendChild(dettagliDiv);
        catalogo.appendChild(prodottoDiv);
    });
}

document.addEventListener('DOMContentLoaded', function() {
    var ordinePrezzo = document.getElementById('ordinePrezzo');
    var ordineNome = document.getElementById('ordineNome');
    var ordinePrezzoCorrente = sessionStorage.getItem('ordinePrezzo') || 'prezzocrescente';
    var ordineNomeCorrente = sessionStorage.getItem('ordineNome') || 'a-z';
    ordinePrezzo.value = ordinePrezzoCorrente;
    ordineNome.value = ordineNomeCorrente;
    ordinePrezzo.addEventListener('change', function() {
        var ordine = this.value;
        sessionStorage.setItem('ordinePrezzo', ordine);
        loadAjaxDoc('OrdinaElementi', 'POST', 'ordine=' + ordine, aggiornaCatalogo);
    });
    ordineNome.addEventListener('change', function() {
        var ordine = this.value;
        sessionStorage.setItem('ordineNome', ordine);
        loadAjaxDoc('OrdinaElementi', 'POST', 'ordine=' + ordine, aggiornaCatalogo);
    });
    loadAjaxDoc('OrdinaElementi', 'POST', 'ordine=' + ordinePrezzoCorrente, aggiornaCatalogo);
});
