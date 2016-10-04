function notify(input, message) {
  var notification = document.createElement("div");
  notification.className = "validation";
  notification.innerHTML = message;
  input.parentElement.appendChild(notification);
}

function clear() {
  var notifications = document.querySelectorAll("div.validation");
  for(var i = 0; i < notifications.length; i++)
    notifications[i].parentElement.removeChild(notifications[i]);
}

function required(input) {
  return input.value.trim() != '';
}

function pattern(input, regex) {
  return regex.test(input.value);
}

function maxlength(input, length) {
  return input.value.length <= length;
}

function validate(field, validator, message) {
  var form = field.form;
  var submit = form.onsubmit || function() {clear(); return true;};
  form.onsubmit = function() {
    var result = validator(field);
    var nestedResult = submit();
    if (!result)
      notify(field, message);
    return nestedResult && result;
  }
}

function validateRequired() {
  var inputs = document.querySelectorAll("[required]");
  for(var i = 0; i < inputs.length; i++)
    validate(inputs[i], required, "Field is required");
}

function validateMaxLength() {
  var inputs = document.querySelectorAll("[maxlength]");
  for(var i = 0; i < inputs.length; i++) {
    var length = parseInt(inputs[i].getAttribute("maxlength"));
    validate(
      inputs[i], 
      function(input) {return maxlength(input, length)}, 
      "Max length is " + length
    );
  }
}

function validatePattern() {
  var inputs = document.querySelectorAll("[pattern]");
  for(var i = 0; i < inputs.length; i++) {
    var regex = new RegExp(inputs[i].getAttribute("pattern"));
    validate(
      inputs[i], 
      function(input) {return pattern(input, regex)}, 
      "Field does not match pattern"
    );
  }
}

window.onload = function() {
  validateRequired();
  validateMaxLength();
  validatePattern();
}