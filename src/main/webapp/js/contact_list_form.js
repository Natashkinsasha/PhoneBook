function toggle(source, name) {
    checkboxes = document.getElementsByName(name);
    for (var i = 0, n = checkboxes.length; i < n; i++) {
        checkboxes[i].checked = source.checked;
    }
}




function deletecontact(text, name) {
    checkboxes = document.querySelector('input:checked');
    if (checkboxes >0) {
        if (confirm(text)) {
            var act = document.forms["checkbox_form"];
            act.action = "/deletesomecontact";
            act.method = "post";
            act.submit();
        }
    } 

}

