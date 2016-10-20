function toggle(source, name) {
    var checkboxes = document.getElementsByName(name);
    for (var i = 0, n = checkboxes.length; i < n; i++) {
        checkboxes[i].checked = source.checked;
    }
}




function deletecontact(text, name) {
    var checkboxes = document.querySelectorAll('input:checked');
    if (checkboxes.length >0) {
        if (confirm(text)) {
            var act = document.forms["checkbox_form"];
            act.action = "/deletesomecontact";
            act.method = "post";
            act.submit();
        }
    } 

}

