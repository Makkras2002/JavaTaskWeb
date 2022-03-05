var current_page = 1;
var records_per_page = 3;
let orderData = document.getElementById("orderData");
let orders = JSON.parse(orderData.innerHTML);

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
    var listing_table = document.querySelector("#ordersTable");
    listing_table.innerHTML = "";
    var page_span = document.getElementById("page");

    if (page < 1) page = 1;
    if (page > numPages()) page = numPages();

    listing_table.innerHTML = "";

    for (var i = (page-1) * records_per_page; i < (page * records_per_page) && i < orders.length; i++) {
        let tr = "";
        let status = "";
        let color = "";
        if (i == (page-1) * records_per_page) {
            tr +=
                "<tr><td>" +
                "ID" +
                "</td>" +
                "<td>" +
                document.getElementById("order_user_login").innerText +
                "</td>" +
                "<td>" +
                document.getElementById("order_user_email").innerText +
                "</td>" +
                "<td>" +
                document.getElementById("order_status").innerText +
                "</td>" +
                "<td>" +
                document.getElementById("order_date").innerText +
                "</td>" +
                "<td>" +
                document.getElementById("order_components").innerText +
                "</td>" +
                "</tr>";
        }
        if(orders[i].isCompleted){
            status = document.getElementById("order_status_completed").innerText;
            color = "green";
        } else {
            status = document.getElementById("order_status_not_completed").innerText;
            color = "red";
        }
        let components = "<table class='table'><tbody>";
        for(let j =0; j < orders[i].componentOrders.length; j++) {
            components+="<tr><td>"+orders[i].componentOrders[j].product.productName +
                "</td><td>"+ orders[i].componentOrders[j].orderedProductAmount +"</td><td>"+
                orders[i].componentOrders[j].orderedProductFullPrice + "</td></tr>";
        }
        components+="</tbody></table>";
        tr +=
            "<form id='"+i+"' method='post' action='\controller'><input form='"+i+"' type='hidden' name='command' id='command' value='complete_order'/><input form='"+i+"' type='hidden' name='order_id' id='order_id' required='required' readonly='readonly' value='"+
            orders[i].completeOrderId +
            "'/>" +
            "</form>" +
            "<tr><td>"+orders[i].completeOrderId+"</td>" +
            "<td>" +
            orders[i].user.login+
            "</td>" +
            "<td>" +
            orders[i].user.email +
            "</td>" +
            "<td style='color:"+color+"'>" +
            status +
            "</td>" +
            "<td>"+
            orders[i].completeOrderDate.year+"."+orders[i].completeOrderDate.month+"."+orders[i].completeOrderDate.day+
            "</td>" +
            "<td>" +
            components+
            "</td>"+
            "<td>" +
            "<button form='"+i+"' type='submit' class='btn btn-success'>"+document.getElementById("order_complete").innerText+
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
    return Math.ceil(orders.length / records_per_page);
}

window.onload = function() {
    changePage(1);
};