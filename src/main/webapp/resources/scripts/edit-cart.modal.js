$(".editLink").click(function () {
    let id = $(this).attr("data-id");
    let helpMessage = "Edit amount of product (with id: " + id + ") in cart.";
    $("#modalBodyEditForm").html(helpMessage);
    $("#editCartFormProductId").attr("value", id);
});