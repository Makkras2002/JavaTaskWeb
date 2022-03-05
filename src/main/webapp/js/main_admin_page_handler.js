var current_page = 1;
var records_per_page = 3;
let productData = document.getElementById("productData");
let products = JSON.parse(productData.innerHTML);

function prevPage()
{
    if (current_page > 1) {
        current_page--;
        changePage(current_page);
    }
}

function nextPage()
{
    if (current_page < numPages()) {
        current_page++;
        changePage(current_page);
    }
}

function changePage(page)
{
    var btn_next = document.getElementById("next");
    var btn_prev = document.getElementById("prev");
    var listing_table = document.querySelector("#productsTable");
    listing_table.innerHTML = "";
    var page_span = document.getElementById("page");

    if (page < 1) page = 1;
    if (page > numPages()) page = numPages();

    listing_table.innerHTML = "";

    for (var i = (page-1) * records_per_page; i < (page * records_per_page) && i < products.length; i++) {
        let tr = "";
        let status = "";
        let color = "";
        if (i == (page-1) * records_per_page) {
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
        listing_table.innerHTML += tr;
    }
    page_span.innerHTML = page;

    if (page == 1) {
        btn_prev.style.visibility = "hidden";
    } else {
        btn_prev.style.visibility = "visible";
    }

    if (page == numPages()) {
        btn_next.style.visibility = "hidden";
    } else {
        btn_next.style.visibility = "visible";
    }
}

function numPages()
{
    return Math.ceil(products.length / records_per_page);
}

window.onload = function() {
    changePage(1);
};