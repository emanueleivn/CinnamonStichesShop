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

function verificaDisponibilità(codiceProdotto, form) {
    var params = 'codiceProdotto=' + codiceProdotto;

    loadAjaxDoc('ControllaDisponibilitàServlet', "POST", params, function(request) {
        var response = JSON.parse(request.responseText);
        if (response.isDisp) {
            form.submit();
        } else {
            alert("Prodotto non più disponibile per l'acquisto.");
        }
    });

    return false;
}
