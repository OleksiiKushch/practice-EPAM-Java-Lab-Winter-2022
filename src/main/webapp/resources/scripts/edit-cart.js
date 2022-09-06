function editCart() {
    let productId = $("#editCartFormProductId").attr("value");
    data = new FormData();
    data.append('editCartFormInputAmount', $('#editCartFormInputAmount').val());
    let amount = data.get('editCartFormInputAmount');
    let url = "http://localhost:8080/practice-EPAM-Java-Lab-Winter-2022-1.0-SNAPSHOT/edit_cart?product_id=" + productId + "&amount=" + amount;
    var xhttp = new XMLHttpRequest();
    xhttp.open("GET", url, true);
    xhttp.send();
}