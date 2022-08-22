var infoArea = document.getElementById('upload-label');
var input = document.getElementById('upload');

$(document).ready(function() {
    $("#inputImage button").on('click', function(event) {
        $('#imageResult').removeAttr('src');
        infoArea.textContent = 'Choose file';
        input.value = '';
    });
});