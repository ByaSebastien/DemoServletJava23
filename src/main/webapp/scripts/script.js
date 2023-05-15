const authorForm = document.getElementById("authorForm");
const authorSelect = document.getElementById("authorId");
const wantToAddAuthor = document.getElementById("wantToAddAuthor");

function switchFormType(){
    const switchButton = document.getElementById("switchButton");
    if(switchButton.getAttribute("name") == "+"){
        wantToAddAuthor.checked = true;
        authorForm.classList.remove("hidden");
        authorSelect.classList.add("hidden");
        switchButton.setAttribute("name","-");
        switchButton.innerHTML = "➖";
    }
    else{
        wantToAddAuthor.checked = false;
        authorForm.classList.add("hidden");
        authorSelect.classList.remove("hidden");
        switchButton.setAttribute("name","+");
        switchButton.innerHTML = "➕";
    }
}