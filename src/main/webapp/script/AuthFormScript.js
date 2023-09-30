let loginInput = document.getElementById("login-input");
let passwordInput = document.getElementById("password-input");
let submitButton = document.getElementById("submit-button");

console.log(window.location.href);

submitButton.onclick = function(e) {
    if (!validate())
        return;
    submit();
}

async function submit() {
    let checkedAuthType = document.querySelector('input[name="Auth-Type"]:checked').value;

    let url = null;
    if (checkedAuthType === "login")
        url = "http://" + window.location.host + "/" + window.location.pathname.split("/")[1] + "/second-web-lab/login";
    else
        url = "http://" + window.location.host + "/" + window.location.pathname.split("/")[1] + "/second-web-lab/register"

    let response = await fetch(url,{
        headers: {
            "X-User-Login": loginInput.value,
            "X-User-Password": passwordInput.value
        }
    });

    if (response.ok)
        window.location = "http://" + window.location.host + "/" + window.location.pathname.split("/")[1] + "/second-web-lab";
    else
        console.log("not ok");
}

function validate() {
    let valid = true;
    let login = loginInput.value;
    let password = passwordInput.value;

    if (login.trim().length === 0) {
        loginInput.classList.add("purple-border");
        valid = false;
    } else {
        loginInput.classList.remove("purple-border");
    }

    if (password.trim().length === 0) {
        passwordInput.classList.add("purple-border");
        valid = false;
    } else {
        passwordInput.classList.remove("purple-border");
    }

    return valid;
}