var infoArea = document.getElementById('upload-label');

$(document).ready(function() {
    $("#inputImage button").on('click', function(event) {
        $('#imageResult').removeAttr('src');
        infoArea.textContent = 'Choose file';
        var input = document.getElementById('upload');
        input.value = '';
    });
});