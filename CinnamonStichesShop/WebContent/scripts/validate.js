const textRegEx = /^[a-zA-Z0-9\s]+$/;
const emailRegEx = /^[a-zA-Z0-9.!#$%&’*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\.[a-zA-Z0-9-]+)+$/;
const passwordRegEx = /^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])[A-Za-z0-9#?!@$%^&*-]{8,}$/;

function showError(input, message) {
    input.style.border = "2px solid red";
    const errorElement = document.getElementById(input.id + "-error");
    errorElement.textContent = message;
}

function clearError(input) {
    input.style.border = "none";
    const errorElement = document.getElementById(input.id + "-error");
    errorElement.textContent = "";
}

function passwordMatchValidate(password, passwordR) {
    var m = document.getElementById("errorMessage");
    if (password !== passwordR) {
        m.style.color = "red";
        m.innerHTML = "Le due password non corrispondono!";
        showError(document.getElementById("passwordR"), "Le due password non corrispondono!");
        return false;
    }
    m.innerHTML = "";
    clearError(document.getElementById("passwordR"));
    return true;
}

function passwordValidate(input) {
    const passwordResult = passwordRegEx.test(input.value);

    if (!passwordResult) {
        showError(input, "Password non valida! La password deve essere di almeno 8 caratteri, deve contenere un numero, una lettera maiuscola, una lettera minuscola e un carattere speciale.");
        return false;
    }
    clearError(input);
    return true;
}

function textValidate(input) {
    const textResult = textRegEx.test(input.value);

    if (!textResult) {
        showError(input, "Formato errato sono consentite solo a-z A-Z 0-9");
        return false;
    }
    clearError(input);
    return true;
}

function capValidate(input) {
    const capRegEx = /^[0-9]{5}$/;
    const capResult = capRegEx.test(input.value);

    if (!capResult) {
        showError(input, "CAP non valido!");
        return false;
    }
    clearError(input);
    return true;
}

function emailValidate(input) {
    const emailResult = emailRegEx.test(input.value);

    if (!emailResult) {
        showError(input, "Email non valida!");
        return false;
    }
    clearError(input);
    return true;
}

function validate() {
    const nome = document.getElementById("name");
    const cognome = document.getElementById("surname");
    const via = document.getElementById("via");
    const cap = document.getElementById("cap");
    const paese = document.getElementById("city");
    const email = document.getElementById("email");
    const password = document.getElementById("password");
    const passwordR = document.getElementById("passwordR");

    return textValidate(nome) &&
           textValidate(cognome) &&
           textValidate(via) &&
           capValidate(cap) &&
           textValidate(paese) &&
           emailValidate(email) &&
           passwordValidate(password) &&
           passwordMatchValidate(password.value, passwordR.value);
}

document.getElementById("RegForm").addEventListener("submit", function(event) {
    if (!validate()) {
        event.preventDefault();
    }
});

