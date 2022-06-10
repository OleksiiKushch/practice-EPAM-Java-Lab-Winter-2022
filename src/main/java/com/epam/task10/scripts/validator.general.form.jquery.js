function validationRegistrationForm() {
    if (!isValidEmail($("#inputEmail").val())) {
        return false;
    }
    let password = $("#inputPassword").val();
    let confirmationPassword = $("#inputConfirmationPassword").val();
    if ((isValidPassword(password) && isValidPassword(confirmationPassword))) {
        if (password != confirmationPassword) {
            alert("Password and confirmation password must match!");
            return false;
        }
    } else {
        return false;
    }
}

function validationLoginForm() {
    if (!isValidEmail($("#inputEmail").val())) {
        return false;
    }
    if (!isValidPassword($("#inputPassword").val())) {
        return false;
    }
}

function isValidEmail(email) {
    if (isEmpty(email)) {
        alert("Email address must be filled out (required field)!");
        return false;
    } else if (!email.toLowerCase().match("^(.+)@(.+)$")) {
        alert("Invalid format email address, must contain '@' symbol!");
        return false;
    } else {
        return true;
    }
}

function isValidPassword(password) {
    if (isEmpty(password)) {
        alert("Password must be filled out (required field)!");
        return false;
    } else if (6 > password.length) {
        alert("The minimum allowed number of characters for a password is 6!");
        return false;
    } else if (password.length > 18) {
        alert("The maximum allowed number of characters for a password is 18!");
        return false;
    } else {
        return true;
    }
}

function isEmpty(value) {
    if (value == "") {
        return true;
    } else {
        return false;
    }
}

