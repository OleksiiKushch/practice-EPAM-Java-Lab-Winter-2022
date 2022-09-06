$(".deleteLink").click(function () {
    let id = $(this).attr("data-id");
    let helpMessage = "Are you sure you want to remove this product (with id: " + id + ") from your cart?";
    $("#modalBodyDeleteForm").html(helpMessage);
    $("#deleteFromCartProductId").attr("value", id);
});