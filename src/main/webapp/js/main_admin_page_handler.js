window.onload = function buildTableForm() {
    let productData = document.getElementById("productData");
    let products = JSON.parse(productData.innerHTML);
    const tbody = document.querySelector("#productsTable");
    tbody.innerHTML = "";
    if (products.length > 0) {
        let str = "";
        for (let i = 0; i < products.length; i++) {
            let tr = "";
            let status = "";
            let color = "";
            if (i == 0) {
                tr +=
                    "<tr><td>" +
                    "ID" +
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
                    document.getElementById("prod_comment").innerText +
                    "</td>" +
                    "<td>" +
                    document.getElementById("prod_status").innerText +
                    "</td>" +
                    "</tr>";
            }
            if(products[i].isInStock){
                status = document.getElementById("prod_in_stock_status").innerText;
                color = "green";
            } else {
                status = document.getElementById("prod_out_of_stock_status").innerText;
                color = "red";
            }
            tr +=
                "<form id='"+i+"' method='post' action='\controller'><input form='"+i+"' type='hidden' name='command' id='command' value='change_product'/><input form='"+i+"' type='hidden' name='product_id' id='product_id' required='required' readonly='readonly' value='"+
                products[i].productId +
                "'/>" +
                "</form>" +
                "<tr><td>"+products[i].productId+"</td>" +
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
                "' alt='"+products[i].productName+"' width='80px' height='80px'/></td>" +
                "<td>" +
                products[i].productComment+
                "</td>"+
                "<td style='color:"+color+"'>"+status+"</td>" +
                "<td>" +
                "<button form='"+i+"' type='submit' class='btn btn-primary'>"+document.getElementById("prod_change").innerText+
                "</button></td>"
                +"</tr>";
            tbody.innerHTML += tr;
        }
    } else {
        tbody.innerHTML = "<caption>" + document.getElementById("prod_empty_res").innerText +"</caption>";
    }
}