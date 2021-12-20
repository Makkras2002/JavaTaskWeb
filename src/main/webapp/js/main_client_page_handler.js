window.onload = function buildTableForm() {
    let productData = document.getElementById("productData");
    let products = JSON.parse(productData.innerHTML);
    const tbody = document.querySelector("#productTable");
    tbody.innerHTML = "";
    if (products.length > 0) {
        let str = "";
        for (let i = 0; i < products.length; i++) {
            let tr = "";
            if (i == 0) {
                tr +=
                    "<tr><td>" +
                    " ID" +
                    "</td>" +
                    "<td>" +
                    " Название " +
                    "</td>" +
                    "<td>" +
                    " Категория " +
                    "</td>" +
                    "<td>" +
                    " Цена " +
                    "</td>" +
                    "<td>" +
                    " Изображение " +
                    "</td>" +
                    "<td>" +
                    " Комментарий " +
                    "</td>" +
                    "</tr>";
            }
            tr +=
                "<form id='"+i+"' method='post' action='\controller'><input form='"+i+"' type='hidden' name='command' id='command' value='add_product_to_bucket'/>" +
                "</form>" +
                "<tr><td><input form='"+i+"' type='text' name='product_id' id='product_id' required='required' readonly='readonly' value='"+
                products[i].productId +
                "'/></td>" +
                "<td>" +
                products[i].productName+
                "</td>" +
                "<td>" +
                products[i].productCategory.category +
                "</td>" +
                "<td>" +
                products[i].productPrice +
                "</td>" +
                "<td><img src='" +
                products[i].picturePath+
                "' alt='"+products[i].productName+"' width='120px' height='120px'/></td>" +
                "<td>" +
                products[i].productComment+
                "</td>"+
                "<td><input form='"+i+"' type='number' name='amount'  id='amount' min='1' max='100000' step='1' required='required'"+
                "/></td>" +
                "<td>" +
                "<button form='"+i+"' type='submit' class='btn btn-primary'>Добавить в корзину"+
                "</button></td>"
                +"</tr>";
            tbody.innerHTML += tr;
        }
    } else {
        tbody.innerHTML = `<caption>No products are available.</caption>`;
    }
}