$(".product-pagination.text-center .pagination a").click(function(e) {
    e.preventDefault();
    var params = getJsonFromUrl();
    if ('page' in params) {
        var param = params['page'];
    }
    var value = $(this).attr('href');
    params['page'] = value;
    var newUrl = "?" + $.param(params);
    window.location.href = newUrl;
});

function getJsonFromUrl() {
    var query = location.search.substr(1);
    var result = {};
    query.split("&").forEach(function(part){
                             var item = part.split("=");
    result[item[0]] = decodeURIComponent(item[1]);
    });
    delete result[""];
    return result;
}