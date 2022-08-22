function validationRegistrationForm() {
    if (!isValidEmail(document.forms["registrationForm"]["email"].value)) {
        return false;
    }
    if (!isValidName(document.forms["registrationForm"]["firstName"].value)) {
        return false;
    }
    if (!isValidName(document.forms["registrationForm"]["lastName"].value)) {
        return false;
    }
    let password = document.forms["registrationForm"]["password"].value;
    let confirmationPassword = document.forms["registrationForm"]["confirmationPassword"].value;
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
    if (!isValidEmail(document.forms["loginForm"]["email"].value)) {
        return false;
    }
    if (!isValidPassword(document.forms["loginForm"]["password"].value)) {
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

function isValidName(name) {
    if (isEmpty(name)) {
        alert("First and last name must be filled out (required fields)!");
        return false;
    } else if (name.length > 40) {
        alert("The maximum allowed number of characters for first and last name is 40!");
        return false;
    } else {
        return true;
    }
}

function isValidPassword(password) {
    if (isEmpty(password)) {
        alert("Password and confirmation password must be filled out (required fields)!");
        return false;
    } else if (6 > password.length) {
        alert("The minimum allowed number of characters for a password and a confirmation password is 6!");
        return false;
    } else if (password.length > 18) {
        alert("The maximum allowed number of characters for a password and a confirmation password is 18!");
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

