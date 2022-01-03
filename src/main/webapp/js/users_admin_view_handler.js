window.onload = function buildTableForm() {
    let userData = document.getElementById("userData");
    let users = JSON.parse(userData.innerHTML);
    const tbody1 = document.querySelector("#usersTable");
    tbody1.innerHTML = "";
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
}