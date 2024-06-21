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
            if (this.readyState === 4) {
                if (this.status === 200) {
                    callbackFunction(this);
                } else {
                    if (this.status === 0) {
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

        if (method.toLowerCase() === "get") {
            if (params) {
                request.open("GET", url + "?" + params, true);
            } else {
                request.open("GET", url, true);
            }
            request.send(null);
        } else {
            if (params) {
                request.open("POST", url, true);
                request.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
                request.send(params);
            } else {
                alert("Usa GET se non ci sono parametri!");
                return null;
            }
        }
    }
}

function aggiornaOrdine(request) {
    var ordine = JSON.parse(request.responseText);
    var ordiniTable = document.getElementById('ordiniTable');
    ordiniTable.innerHTML = `
        <tr>
            <th>Codice ordine</th>
            <th>Codice cliente</th>
            <th>Data</th>
            <th>Indirizzo Spedizione</th>
            <th>Totale</th>
            <th></th>
        </tr>
    `;

    if (!ordine) {
        ordiniTable.innerHTML += `
            <tr>
                <td colspan="6" style="text-align: center">Nessun ordine trovato</td>
            </tr>
        `;
    } else {
		var contextPath = window.location.pathname.substring(0, window.location.pathname.indexOf("/", 2));
        ordiniTable.innerHTML += `
            <tr>
                <td>${ordine.codice}</td>
                <td>${ordine.idCliente}</td>
                <td>${ordine.data}</td>
                <td>${ordine.indirizzo}</td>
                <td>${ordine.totale}</td>
                <td>
                    <form action="`+contextPath+`/DettagliOrdine" method="post">
                        <input type="hidden" name="codice" value="${ordine.codice}" />
                        <button id="dettagli-ordine" type="submit">Dettagli ordine...</button>
                    </form>
                </td>
            </tr>
        `;
    }
}

document.addEventListener('DOMContentLoaded', function() {
    var cercaOrdineButton = document.getElementById('cercaOrdineButton');
    cercaOrdineButton.addEventListener('click', function(event) {
        event.preventDefault();
        var inputOrdine = document.getElementById('inputOrdine').value;
        if (inputOrdine) {
            var params = "inputOrdine=" + inputOrdine;
            var contextPath = window.location.pathname.substring(0, window.location.pathname.indexOf("/", 2));
            loadAjaxDoc(contextPath + '/admin/CercaByOrdine', 'POST', params, aggiornaOrdine);
        } else {
            alert("Inserisci un codice ordine valido");
        }
    });
});
