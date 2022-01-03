window.onload = function buildTableForm() {
    let orderData = document.getElementById("orderData");
    let orders = JSON.parse(orderData.innerHTML);
    const tbody2 = document.querySelector("#ordersTable");
    tbody2.innerHTML = "";
    if (orders.length > 0) {
        let str = "";
        for (let i = 0; i < orders.length; i++) {
            let tr = "";
            let status = "";
            let color = "";
            if (i == 0) {
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
            tbody2.innerHTML += tr;
        }
    } else {
        tbody2.innerHTML = "<caption>" + document.getElementById("empty_orders").innerText +"</caption>";
    }
}