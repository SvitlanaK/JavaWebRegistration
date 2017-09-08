$(document).on("click", ".add_to_cart_button", function () {

});

$(".add_to_cart_button").click(function (e) {
    e.preventDefault();
    var value = $(this).attr("href");
    value = value.split("=")[1];

    var data = {
        itemId: value
    };

    $.ajax({
        type: "POST",
        url: "/addItemToCart",
        dataType: 'JSON',
        data: data,
        success: function (json) {
            var totalSum = json.totalSum,
                itemsQuantity = json.itemsQuantity;
            updateCartHeaderInfo(totalSum, itemsQuantity);
        }
    });
});

$(".quantity-minus").click(function (e) {
    e.preventDefault();
    var currentElement = $(this),
        value = currentElement.siblings(".item-info-field").val();
    value = value.split("=")[1];

    executeAjaxOnCartPage(value, currentElement, "/cartItemQuantitySubtraction");
});

$(".quantity-plus").click(function (e) {
    e.preventDefault();
    var currentElement = $(this),
        value = currentElement.siblings(".item-info-field").val();
    value = value.split("=")[1];

    executeAjaxOnCartPage(value, currentElement, "/cartItemQuantityAddition");
});

$(".product-remove .remove").click(function (e) {
    e.preventDefault();
    var currentElement = $(this),
        value = currentElement.attr("href");
    value = value.split("=")[1];

    executeAjaxOnCartPage(value, currentElement, "/cartItemRemoving");
});

$(".actions .button.clear-cart").click(function (e) {
    e.preventDefault();

    $.ajax({
        type: "GET",
        url: "/cartClearing",
        contextType: "application/json",
        success: function (json) {
            var totalSum = json.totalSum,
                itemsQuantity = json.itemsQuantity;
            updateCartHeaderInfo(totalSum, itemsQuantity);
            updateCartTotalInfo(totalSum, itemsQuantity);
            clearCartTable();
            updateProceedButtonOnCartPage(itemsQuantity);
        }
    });
});

function updateCartHeaderInfo(totalSum, itemsQuantity) {
    $(".shopping-item .cart-amunt").html("$" + parseNumber(totalSum));
    $(".shopping-item .product-count").html(itemsQuantity);
}

function updateRowInCartTable(element, currentItemTotalSum, currentItemQuantity) {
    if (currentItemQuantity === 0) {
        $(element).closest("tr").remove();
        return;
    }
    $(element).closest("tr").find(".product-subtotal .amount").html("$" + parseNumber(currentItemTotalSum));
    $(element).closest("tr").find(".input-text.qty.text").val(currentItemQuantity);
}

function updateCartTotalInfo(totalSum, itemsQuantity) {
    $(".cart_totals .order-items-quantity td").html(itemsQuantity);
    $(".cart_totals .order-total td .amount").html("$" + parseNumber(totalSum));
}

function clearCartTable() {
    $("tr.cart_item").remove();
}

function executeAjaxOnCartPage(value, currentElement, url) {
    var data = {
        itemId: value
    };

    $.ajax({
        type: "POST",
        url: url,
        dataType: 'JSON',
        data: data,
        success: function (json) {
            var currentItemTotalSum = json.currentItemTotalSum,
                currentItemQuantity = json.currentItemQuantity,
                totalSum = json.totalSum,
                itemsQuantity = json.itemsQuantity;
            updateRowInCartTable(currentElement, currentItemTotalSum, currentItemQuantity);
            updateCartHeaderInfo(totalSum, itemsQuantity);
            updateCartTotalInfo(totalSum, itemsQuantity);
            updateProceedButtonOnCartPage(itemsQuantity);
        }
    });
}

function updateProceedButtonOnCartPage(itemsQuantity) {
    if (itemsQuantity === 0) {
        $(".shop_table .actions .checkout-button").remove();
    }
}

function parseNumber(number) {
    var decimal = (number - Math.floor(number)) * 100;
    decimal = Math.round(decimal);
    if (decimal % 10 === 0) {
        return number.toFixed(1);
    } else {
        return number.toFixed(2);
    }
}