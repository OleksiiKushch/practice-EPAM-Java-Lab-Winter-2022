function loadMainPageComponents() {
    $('#my_header').load('components/header.html', function() {
        $('#home_li').addClass('active');
    });
    $('#my_footer').load('components/footer.html');
}

function loadProductCatalogPageComponents() {
    $('#my_header').load('components/header.html');
    $('#my_footer').load('components/footer.html', function() {
        $('#product_catalog_li').addClass('active');
    });
}

function loadGeneralPageComponents() {
    $('#my_header').load('components/header.html');
    $('#my_footer').load('components/footer.html');
}