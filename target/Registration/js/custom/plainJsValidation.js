"use strict";

var namePattern = /^[a-zA-Z]{2,16}$/,
	loginPattern = /^[a-zA-Z0-9_\-]{6,16}$/,
	passwordPattern = /^[a-zA-Z0-9_\-]{6,18}$/,
	emailPattern = /^([a-zA-Z0-9_\.\-]+)@([\da-zA-Z\.\-]+)\.([a-zA-Z\.]{2,6})$/,
	phoneNumberPattern = /\([0-9]{3}\)[0-9]{9}/,
	captchaPattern = /.+/;

document.getElementById("registration_form").onsubmit = function() {
    return validateRegistrationForm();
};

function validateElement(element, pattern) {
    var span = element.nextElementSibling;
    if (!pattern.test(element.value)) {
        span.style.display = "inline-block";
        return false;
    } else {
        span.style.display = "none";
        return true;
    }
}

function validateRegistrationForm() {
    var dataForValidation = [
            [document.getElementById("registration_first_name"), namePattern],
            [document.getElementById("registration_last_name"), namePattern],
            [document.getElementById("registration_login"), loginPattern],
            [document.getElementById("registration_password"), passwordPattern],
            [document.getElementById("registration_email"), emailPattern],
            [document.getElementById("registration_phone_number"), phoneNumberPattern]
        ],
        valid = true;
    
    for (var item in dataForValidation) {
        valid = validateElement(
            dataForValidation[item][0],
            dataForValidation[item][1]
        ) && valid;
    }

    if (valid) {
        alert("All data is valid. Please, wait for response from server.")
    }

	return valid;
}