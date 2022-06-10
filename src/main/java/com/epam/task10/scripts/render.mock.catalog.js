function renderProductCatalog() {
    const count = 10;
    const container = document.getElementById("container");
    for (let i = 0; i < count; i++) {
        container.appendChild(document.getElementById("template-product-catalog").content.cloneNode(true));
    }
}
