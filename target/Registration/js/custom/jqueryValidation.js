'use strict';

var namePattern = /^[a-zA-Z]{2,16}$/,
	loginPattern = /^[a-zA-Z0-9_\-]{6,16}$/,
	passwordPattern = /^[a-zA-Z0-9_\-]{6,18}$/,
	emailPattern = /^([a-zA-Z0-9_\.\-]+)@([\da-zA-Z\.\-]+)\.([a-zA-Z\.]{2,6})$/,
	phoneNumberPattern = /\([0-9]{3}\)[0-9]{9}/,
	captchaPattern = /.+/,
	avatarPattern = /.+\.jpg/;

function validateElement(element, pattern) {
    var span = $( element ).next();
    if (!pattern.test($( element ).val())) {
        $( span ).css("display", "inline-block")
        return false;
    } else {
        $( span ).css("display", "none")
        return true;
    }
}

$("#registration_form").submit(function() {
    var dataForValidation = [
            [$("#firstName"), namePattern],
            [$("#lastName"), namePattern],
            [$("#login"), loginPattern],
            [$("#password1"), passwordPattern],
            [$("#password2"), passwordPattern],
            [$("#email"), emailPattern],
            [$("#phoneNumber"), phoneNumberPattern],
            [$("#captcha"), captchaPattern],
            [$("#avatar"), avatarPattern]
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
});
