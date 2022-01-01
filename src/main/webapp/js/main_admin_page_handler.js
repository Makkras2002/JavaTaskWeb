window.onload = function buildTableForm() {
    let productData = document.getElementById("productData");
    let userData = document.getElementById("userData");
    let orderData = document.getElementById("orderData");
    let products = JSON.parse(productData.innerHTML);
    let users = JSON.parse(userData.innerHTML);
    let orders = JSON.parse(orderData.innerHTML);
    const tbody = document.querySelector("#productsTable");
    const tbody1 = document.querySelector("#usersTable");
    const tbody2 = document.querySelector("#ordersTable");
    tbody.innerHTML = "";
    tbody1.innerHTML = "";
    tbody2.innerHTML = "";
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
    if (users.length > 0) {
        let str = "";
        let status = "";
        let role = "";
        let color = "";
        for (let i = 0; i < users.length; i++) {
            let tr = "";
            if (i == 0) {
                tr +=
                    "<tr>"+
                    "<td>" +
                    document.getElementById("user_login").innerText +
                    "</td>" +
                    "<td>" +
                    document.getElementById("user_email").innerText +
                    "</td>" +
                    "<td>" +
                    document.getElementById("user_status").innerText +
                    "</td>" +
                    "<td>" +
                    document.getElementById("user_role").innerText +
                    "</td>" +
                    "</tr>";
            }
            if(users[i].isOnline){
                status = document.getElementById("user_status_online").innerText;
                color = "green";
            } else {
                status = document.getElementById("user_status_offline").innerText;
                color = "red";
            }
            if(users[i].userRole.toString() === "ADMIN"){
                role = document.getElementById("user_admin").innerText;
            } else {
                role = document.getElementById("user_client").innerText;
            }
            tr +=
                "<form id='"+i+"' method='post' action='\controller'><input form='"+i+"' type='hidden' name='command' id='command' value='delete_user'/><input form='"+i+"' type='hidden' name='login' id='login' required='required' readonly='readonly' value='"+
                users[i].login +
                "'/>" +
                "</form>" +
                "<tr>"+
                "<td>" +
                users[i].login+
                "</td>" +
                "<td>" +
                users[i].email +
                "</td>" +
                "<td style='color: "+color+"'>" +
                status +
                "</td>" +
                "<td>" +
                role +
                "</td>" +
                "<td>"+
                "<button form='"+i+"' type='submit' class='btn btn-outline-danger'>"+document.getElementById("user_delete_user").innerText+
                "</button></td>"
                +"</tr>";
            tbody1.innerHTML += tr;
        }
    } else {
        tbody1.innerHTML = "<caption>" + document.getElementById("empty_users").innerText +"</caption>";
    }
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