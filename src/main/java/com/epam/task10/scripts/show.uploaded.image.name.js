var formatMaxLengthFileName = 24;
var input = document.getElementById('upload');
var infoArea = document.getElementById('upload-label');

input.addEventListener('change', showFileName);
function showFileName(event) {
  var input = event.srcElement;
  var fullFileName = input.files[0].name;
  var fileName = getFileNameWithoutExtension(fullFileName);
  var extension = getFileExtension(fullFileName);
  var result = fullFileName;
  if (fileName.length > formatMaxLengthFileName) {
      result = fileName.slice(0, formatMaxLengthFileName).concat('....', extension)
  }
  infoArea.textContent = 'File name: ' + result;
}

function getFileNameWithoutExtension(fullFileName) {
    var splitFileName = fullFileName.split('.');
    splitFileName.splice(splitFileName.length - 1, 1)
    return splitFileName.join('.');
}

function getFileExtension(fullFileName) {
    var splitFileName = fullFileName.split('.');
    return splitFileName[splitFileName.length - 1];
}