const passErrorMsg="Caratteri speciali inseriti non consentiti";
const RegExp = /[!@#$%^&*()_+{}\[\]|\\:;"'<>,.?/]/;
function passwordMatchValidate(password, passwordR) {
    if (password !== passwordR) {
        alert("Le due password non corrispondo!");
        document.getElementById("password").style.borderStyle = "solid";
        document.getElementById("password").style.borderColor = "red";
        document.getElementById("passwordR").style.borderStyle = "solid";
        document.getElementById("passwordR").style.borderColor  = "red";
        return false;
    }
    return true;
}
function passwordValidate(password) {
    if (RegExp.test(password)) {
        alert(passErrorMsg);
        document.getElementById("password").style.borderStyle = "solid";
        document.getElementById("password").style.borderColor = "red";
        return false;
    }
    return true;
}
function validate() {
    let password = document.getElementById("password");
    let passwordR = document.getElementById("passwordR");
     if (!passwordValidate(password.value)) {
        return false;
    }
    if (!passwordMatchValidate(password.value, passwordR.value)) {
        return false;
    }
    
    return true;
}
