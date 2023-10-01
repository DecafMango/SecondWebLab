let dots = [];
let dotsCounter = 0;

window.onload = function () {
    for (let row of document.querySelectorAll(".table-data-row")) {
        row = row.innerHTML.replaceAll("<td>", "").replaceAll("</td>", "|").split("|");
        row.splice(row.length - 1, 1);
        dots[dotsCounter] = {
            x: row[0],
            y: row[1],
        }
        dotsCounter++;
    }
    repaintCanvas(3, dots);
}

function repaintCanvas(r) {
    const canvas = document.getElementById("field-canvas");
    if (canvas.getContext) {
        const ctx = canvas.getContext("2d");
        ctx.clearRect(0, 0, 415, 415);
        ctx.translate(415 / 2 + 1, 415 / 2 + 1);
        ctx.font = "24px Roboto";
        ctx.textAlign = "center";

        ctx.fillStyle = "#247FFF";
        ctx.beginPath();
        ctx.moveTo(0, 0);
        ctx.lineTo(0, -r * 40);
        ctx.lineTo(-r * 40 / 2, 0);
        ctx.fill();

        ctx.beginPath();
        ctx.arc(0, 0, r * 40, Math.PI / 2, Math.PI);
        ctx.moveTo(-r * 40, 0);
        ctx.lineTo(0, 0);
        ctx.lineTo(0, r * 40);
        ctx.fill();

        ctx.fillRect(0, 0, r * 40, r / 2 * 40);

        ctx.strokeText("0", -10, 20);

        ctx.beginPath();
        ctx.moveTo(0, -415 / 2 + 1);
        ctx.lineTo(0, 415 / 2 + 1);
        ctx.stroke();

        ctx.beginPath();
        ctx.moveTo(-415 / 2 - 1, 0);
        ctx.lineTo(415 / 2 + 1, 0);
        ctx.stroke();

        ctx.beginPath();
        ctx.moveTo(40, 10);
        ctx.lineTo(40, -10);
        ctx.stroke();
        ctx.strokeText("1", 40, -15);

        ctx.beginPath();
        ctx.moveTo(80, 10);
        ctx.lineTo(80, -10);
        ctx.stroke();
        ctx.strokeText("2", 80, -15);

        ctx.beginPath();
        ctx.moveTo(120, 10);
        ctx.lineTo(120, -10);
        ctx.stroke();
        ctx.strokeText("3", 120, -15);

        ctx.beginPath();
        ctx.moveTo(160, 10);
        ctx.lineTo(160, -10);
        ctx.stroke();
        ctx.strokeText("4", 160, -15);

        ctx.beginPath();
        ctx.moveTo(200, 10);
        ctx.lineTo(200, -10);
        ctx.stroke();
        ctx.strokeText("5", 200, -15);

        ctx.beginPath();
        ctx.moveTo(-10, -40);
        ctx.lineTo(10, -40);
        ctx.stroke();
        ctx.strokeText("1", 15, -40);

        ctx.beginPath();
        ctx.moveTo(-10, -80);
        ctx.lineTo(10, -80);
        ctx.stroke();
        ctx.strokeText("2", 15, -80);

        ctx.beginPath();
        ctx.moveTo(-10, -120);
        ctx.lineTo(10, -120);
        ctx.stroke();
        ctx.strokeText("3", 15, -120);

        ctx.beginPath();
        ctx.moveTo(-10, -160);
        ctx.lineTo(10, -160);
        ctx.stroke();
        ctx.strokeText("4", 15, -160);

        ctx.beginPath();
        ctx.moveTo(-10, -200);
        ctx.lineTo(10, -200);
        ctx.stroke();
        ctx.strokeText("5", 15, -200);

        ctx.beginPath();
        ctx.moveTo(-40, -10);
        ctx.lineTo(-40, 10);
        ctx.stroke();
        ctx.strokeText("-1", -40, -15);

        ctx.beginPath();
        ctx.moveTo(-80, -10);
        ctx.lineTo(-80, 10);
        ctx.stroke();
        ctx.strokeText("-2", -80, -15);

        ctx.beginPath();
        ctx.moveTo(-120, -10);
        ctx.lineTo(-120, 10);
        ctx.stroke();
        ctx.strokeText("-3", -120, -15);

        ctx.beginPath();
        ctx.moveTo(-160, -10);
        ctx.lineTo(-160, 10);
        ctx.stroke();
        ctx.strokeText("-4", -160, -15);

        ctx.beginPath();
        ctx.moveTo(-200, -10);
        ctx.lineTo(-200, 10);
        ctx.stroke();
        ctx.strokeText("-5", -200, -15);

        ctx.beginPath();
        ctx.moveTo(-10, 40);
        ctx.lineTo(10, 40);
        ctx.stroke();
        ctx.strokeText("-1", 15, 40);

        ctx.beginPath();
        ctx.moveTo(-10, 80);
        ctx.lineTo(10, 80);
        ctx.stroke();
        ctx.strokeText("-2", 15, 80);

        ctx.beginPath();
        ctx.moveTo(-10, 120);
        ctx.lineTo(10, 120);
        ctx.stroke();
        ctx.strokeText("-3", 15, 120);

        ctx.beginPath();
        ctx.moveTo(-10, 160);
        ctx.lineTo(10, 160);
        ctx.stroke();
        ctx.strokeText("-4", 15, 160);

        ctx.beginPath();
        ctx.moveTo(-10, 200);
        ctx.lineTo(10, 200);
        ctx.stroke();
        ctx.strokeText("-5", 15, 200);

        ctx.fillStyle = "purple";

        for (let dot of dots) {
            ctx.beginPath();
            ctx.arc(dot.x * 40, -dot.y * 40, 5, 0, Math.PI * 2);
            ctx.fill();
        }

        ctx.translate(-(415 / 2 + 1), -(415 / 2 + 1));
    }
}


document.getElementById("field-canvas").onclick = function (event) {
    let x = (event.pageX - document.body.offsetWidth / 2);
    let y = (160 + 415 / 2 + 1 - event.pageY);

    // calculate x
    x /= 40;
    if (x - Math.round(x) >= 0) {
        if (x - Math.round(x) < 0.25)
            x = Math.round(x);
        else
            x = Math.round(x) + 0.5;
    } else {
        if (Math.round(x) - x <= 0.25)
            x = Math.round(x);
        else
            x = Math.round(x) - 0.5;
    }

    if (x < -2 || x > 2) {
        alert("Unable to calculate coordinates (try not to use zoom)");
        return;
    }

    // calculate y
    y /= 40
    if (y < - 3 || y > 5) {
        alert("Unable to calculate coordinates (try not to use zoom)");
        return;
    }

    console.log(x + " " + y);

    submit(x, y, getPressedRValue());
}

function checkFields() {
    let isValid = true;
    let xValueField = document.querySelector(".form-field:first-child");
    let yValueField = document.querySelector(".form-field:nth-child(2)");

    // check x
    let xValueCheckedCounter = 0;
    for (let xValueCheckBox of document.querySelectorAll("input[name='x']")) {
        if (xValueCheckBox.checked)
            xValueCheckedCounter++;
    }
    if (xValueCheckedCounter === 1)
        xValueField.classList.remove("purple-border");
    else {
        isValid = false;
        xValueField.classList.add("purple-border");
    }

    // check y
    let yValue = document.querySelector("fieldset:nth-child(2) input").value;
    if (yValue == "-0") {
        yValueField.classList.add("purple-border");
        isValid = false;
    } else {
        yValue = yValue.replace(",", ".");
        yValue = Number.parseFloat(yValue);
        if (!isNaN(yValue)) {
            if (!(yValue > -3 && yValue < 5)) {
                yValueField.classList.add("purple-border");
                isValid = false;
            } else
                yValueField.classList.remove("purple-border")
        } else {
            yValueField.classList.add("purple-border")
            isValid = false;
        }
    }

    return isValid;
}

async function submit(x, y, r) {
    let url= "http://" + window.location.host + "/" + window.location.pathname.split("/")[1] + "/second-web-lab/check?"
        + new URLSearchParams({
            x: x,
            y: y,
            r: r
        });
    console.log(url);
    let response = await fetch(url);
    addAttemptToTable(await response.json());
}

function getPressedXValue() {
    for (let xValueCheckBox of document.querySelectorAll("input[name='x']")) {
        if (xValueCheckBox.checked)
            return xValueCheckBox.value;
    }
    return null;
}

function getTypedYValue() {
    return document.querySelector("fieldset:nth-child(2) input").value;
}

let rButtons = document.querySelectorAll("fieldset:last-child button");
for (let rButton of rButtons) {
    rButton.onclick = function () {
        let pressedButton = getPressedRButton();
        if (pressedButton)
            pressedButton.classList.remove("button-pressed");
        rButton.classList.add("button-pressed");
        repaintCanvas(rButton.textContent);
    }
}

function getPressedRButton() {
    for (let rButton of rButtons) {
        if (rButton.classList.contains("button-pressed"))
            return rButton;
    }
    return null;
}

function getPressedRValue() {
    for (let rButton of rButtons) {
        if (rButton.classList.contains("button-pressed"))
            return rButton.textContent;
    }
    return null;
}

let submitButton = document.querySelector(".submit-button");
submitButton.onclick = async function () {
    if (!checkFields())
        return;
    let x = getPressedXValue();
    let y = getTypedYValue();
    let r = getPressedRValue();
    submit(x, y, r);
}

let resetTableButton = document.querySelector(".reset-table-button");
resetTableButton.onclick = async function () {
    dots = [];
    dotsCounter = 0;

    let url = "http://" + window.location.host + "/" + window.location.pathname.split("/")[1] + "/second-web-lab/reset";
    fetch(url);

    document.querySelector(".results-table").innerHTML = " <tr class=\"table-header-row\">\n" +
        "                <th>X</th>\n" +
        "                <th>Y</th>\n" +
        "                <th>R</th>\n" +
        "                <th>Date and Time/th>\n" +
        "                <th>Duration, ns.</th>\n" +
        "                <th>Result</th>\n" +
        "            </tr>"
    repaintCanvas();
}

function addAttemptToTable(attempt) {
    let table = document.querySelector(".results-table");
    console.log(attempt);
    table.innerHTML += convertToTableRow(attempt);
    dots[dotsCounter] = attempt;
    dotsCounter++;
    repaintCanvas(getPressedRValue());
}

function convertToTableRow(attempt) {
    return "<tr><td>" + attempt.x + "</td><td>" + attempt.y + "</td><td>" + attempt.r + "</td><td>" + attempt.attemptTime + "</td><td>" + attempt.scriptDuration + "</td><td>" + attempt.hit + "</td></tr>";
}

let exitButton = document.querySelector(".exit-button");
exitButton.onclick = async function () {
    window.location = "http://" + window.location.host + "/" + window.location.pathname.split("/")[1] + "/second-web-lab/login";
}

