window.onload = function buildTableForm() {
    let orderData = document.getElementById("orderData");
    let products = JSON.parse(orderData.innerHTML);
    const tbody = document.querySelector("#productTable");
    tbody.innerHTML = "";
    if (products.length > 0) {
        let str = "";
        for (let i = 0; i < products.length; i++) {
            let tr = "";
            if (i == 0) {
                tr +=
                    "<tr><td>" +
                    "N" +
                    "</td>" +
                    "<td>" +
                    document.getElementById("prod_name").innerText +
                    "</td>" +
                    "<td>" +
                    document.getElementById("prod_category").innerText +
                    "</td>" +
                    "<td>" +
                    document.getElementById("prod_price").innerText +
                    "</td>" +
                    "<td>" +
                    document.getElementById("prod_img").innerText +
                    "</td>" +
                    "<td>" +
                    document.getElementById("prod_amount").innerText +
                    "</td>" +
                    "</tr>";
            }
            tr +=
                "<form id='"+i+"' method='post' action='\controller'><input form='"+i+"' type='hidden' name='command' id='command' value='remove_product_from_order'/><input form='"+i+"' type='hidden' name='product_id' id='product_id' required='required' readonly='readonly' value='"+
                products[i].product.productId +
                "'/>" +
                "</form>" +
                "<tr><td>"+i+"</td>" +
                "<td>" +
                products[i].product.productName+
                "</td>" +
                "<td>" +
                products[i].product.productCategory.category +
                "</td>" +
                "<td>" +
                products[i].orderedProductFullPrice +
                "</td>" +
                "<td><img src='" +
                products[i].product.picturePath+
                "' alt='"+products[i].product.productName+"' width='120px' height='120px'/></td>" +
                "<td><input form='"+i+"' type='number' name='amount'  id='amount' value='"+products[i].orderedProductAmount+"' readonly='readonly' required='required'"+
                "/></td>" +
                "<td>" +
                "<button form='"+i+"' type='submit' class='btn btn-outline-danger'>"+document.getElementById("prod_remove_from_basket").innerText+
                "</button></td>"
                +"</tr>";
            tbody.innerHTML += tr;
        }
    } else {
        tbody.innerHTML = "<caption>" + document.getElementById("empty_order").innerText +"</caption>";
    }
}