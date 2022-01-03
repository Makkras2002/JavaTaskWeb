window.onload = function fillPage() {
    let categoriesData = document.getElementById("categoriesForSearch");
    let categories = JSON.parse(categoriesData.innerHTML);
    let categoriesSelectList = document.getElementById("categories");
    let option = document.createElement("option");
    let initCategory = document.getElementById("productDataStartCategory");
    if(categories.length > 0) {
        for(let j = 0; j < categories.length; j++) {
            option = document.createElement("option");
            option.text = categories[j].category;
            option.value = categories[j].category;
            if(initCategory.innerText.toString().trim().localeCompare(categories[j].category.toString().trim()) == 0) {
                option.defaultSelected = true;
            }
            categoriesSelectList.appendChild(option);
        }
    }
    let startName = document.getElementById("productDataStartName");
    startName.innerText.replaceAll("\"","&quot;");
    startName.innerText.trim();
    document.getElementById("name").value = startName.innerText;
}