window.onload = function fillPage() {
    let categoriesData = document.getElementById("categoriesForSearch");
    let categories = JSON.parse(categoriesData.innerHTML);
    let categoriesSelectList = document.getElementById("categories");
    let option = document.createElement("option");
    if(categories.length > 0) {
        for(let j = 0; j < categories.length; j++) {
            option = document.createElement("option");
            option.text = categories[j].category;
            option.value = categories[j].category;
            categoriesSelectList.appendChild(option);
        }
    }
}