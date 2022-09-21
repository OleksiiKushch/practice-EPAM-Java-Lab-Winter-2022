function putToCart() {
    let productId = $("#putToCartFormProductId").attr("value");
    data = new FormData();
    data.append('putToCartFormInputAmount', $('#putToCartFormInputAmount').val());
    let amount = data.get('putToCartFormInputAmount');
    let url = "http://localhost:8080/practice-EPAM-Java-Lab-Winter-2022-1.0-SNAPSHOT/put_to_cart?product_id=" + productId + "&amount=" + amount;
    var xhttp = new XMLHttpRequest();
    xhttp.open("GET", url, true);
    xhttp.send();
}