function deleteFromCart() {
    let productId = $("#editCartFormProductId").attr("value");
    let url = "http://localhost:8080/practice-EPAM-Java-Lab-Winter-2022-1.0-SNAPSHOT/delete_from_cart?product_id=" + productId;
    var xhttp = new XMLHttpRequest();
    xhttp.open("GET", url, true);
    xhttp.send();
}