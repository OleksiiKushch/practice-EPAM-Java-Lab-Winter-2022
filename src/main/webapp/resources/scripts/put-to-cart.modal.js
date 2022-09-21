$(".putToCartButton").click(function () {
    let id = $(this).attr("data-id");
    let helpMessage = "Add this product (id: " + id + ") to cart? Select quantity:";
    $("#modalBodyPubToCartForm").html(helpMessage);
    $("#putToCartFormProductId").attr("value", id);
});