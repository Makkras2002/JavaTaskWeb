var current_page = 1;
var records_per_page = 3;
let userData = document.getElementById("userData");
let users = JSON.parse(userData.innerHTML);

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
    var listing_table = document.querySelector("#usersTable");
    listing_table.innerHTML = "";
    var page_span = document.getElementById("page");

    if (page < 1) page = 1;
    if (page > numPages()) page = numPages();

    listing_table.innerHTML = "";

    for (var i = (page-1) * records_per_page; i < (page * records_per_page) && i < users.length; i++) {
        let tr = "";
        let status = "";
        let role = "";
        let color = "";
        if (i == (page-1) * records_per_page) {
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
    return Math.ceil(users.length / records_per_page);
}

window.onload = function() {
    changePage(1);
};