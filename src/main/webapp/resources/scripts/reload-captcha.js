function reloadCaptcha() {
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function() {
        if (this.readyState === 4 && this.status === 200) {
            document.getElementById("captchaContainer").innerHTML = this.responseText;
        }
    };
    xhttp.open("GET", "http://localhost:8080/practice-EPAM-Java-Lab-Winter-2022-1.0-SNAPSHOT/reloadCaptcha", true);
    xhttp.send();
}

window.onload = function() {
    reloadCaptcha();
};