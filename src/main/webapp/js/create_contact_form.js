document.addEventListener("DOMContentLoaded", ready);
var numberOfRows;
function ready() {
    numberOfRows = document.querySelector("table").rows.length;
}
function choosePhoto(event, image) {
    var input = event.target;
    var reader = new FileReader();
    reader.onload = function () {
        var dataURL = reader.result;
        var output = document.getElementById(image);
        output.src = dataURL;
    };
    reader.readAsDataURL(input.files[0]);
}

function click_btn(btn, id) {
    var act = document.forms["create_contact_form"];
    switch (btn.id) {
        case "create_telephone":
            act.action = "/createtelephone";
            act.method = "post";
            act.submit();
            break
        case "update_telephone":
            act.action = "/updatetelephone?id=" + id;
            act.method = "post";
            break
        case "delete_telephone":
            act.action = "/deletetelephone?id=" + id;
            act.method = "post";
            act.submit();
            break
        case "create_attachment":
            act.action = "/createattachment";
            act.method = "post";
            act.submit();
            break
        case "update_attachment":
            act.action = "/updateattachment?id=" + id;
            act.method = "post";
            act.submit();
            break
        case "delete_attachment":
            act.action = "/deleteattachment?id=" + id;
            act.method = "post";
            act.submit();
            break
        case "create_contact":
            act.action = "/createcontact";
            act.method = "post";
            act.submit();
            break
        case "create_telephone_modal":
            break;
    }
}
function create_telephone(event) {
    document.getElementById('phone_id').value = -1 * generateId();
    document.getElementById('country_code').value = '';
    document.getElementById('operator_code').value = '';
    document.getElementById('phone_number').value = '';
    document.getElementById('phone_type').value = '';
    document.getElementById('comment').value = '';
    openModal(event, save_telephone);
}

function edit_telephone(event, telephone_id) {
    document.getElementById('phone_id').value = telephone_id;
    document.getElementById('country_code').value = document.getElementById('telephone_' + telephone_id + '_country_code').innerHTML;
    document.getElementById('operator_code').value = document.getElementById('telephone_' + telephone_id + '_operator_code').innerHTML;
    document.getElementById('phone_number').value = document.getElementById('telephone_' + telephone_id + '_number').innerHTML;
    document.getElementById('phone_type').value = document.getElementById('telephone_' + telephone_id + '_type').innerHTML;
    document.getElementById('comment').value = document.getElementById('telephone_' + telephone_id + '_comments').innerHTML;
    openModal(event, save_telephone);
}

function delete_telephone(telephone_id, text) {
    if (confirm(text)) {
        var choose_telephone = document.getElementById("telephone_" + telephone_id);
        document.getElementById("telephones").removeChild(choose_telephone);
    }
}



function save_telephone() {
    var form = document.forms['telephone_form'];
    if (form.checkValidity()) {
        var telephone = {};
        telephone.id = document.getElementById('phone_id').value;
        telephone.country_code = document.getElementById('country_code').value;
        telephone.operator_code = document.getElementById('operator_code').value;
        telephone.number = document.getElementById('phone_number').value;
        telephone.type = document.getElementById('phone_type').value;
        telephone.comment = document.getElementById('comment').value;
        var choose_telephone = document.getElementById("telephone_" + telephone.id);
        document.getElementById("telephones").insertBefore(generateTRForTelephone(telephone), choose_telephone);
        if (choose_telephone != null) {
            document.getElementById("telephones").removeChild(choose_telephone);
        }
        clear_form(form)
        return true;
    } else {
        valid_form(form);
        return false;
    }
    function generateTRForTelephone(telephone) {
        var tr = document.createElement('tr');
        tr.id = "telephone_" + telephone.id;
        var temp = document.createElement('td');
        temp.innerHTML = "<label><input type=\"checkbox\"></label>";
        tr.appendChild(temp);
        var temp = document.createElement('td');
        temp.id = "telephone_" + telephone.id + "_country_code"
        temp.innerHTML = telephone.country_code;
        tr.appendChild(temp);
        var temp = document.createElement('td');
        temp.id = "telephone_" + telephone.id + "_operator_code"
        temp.innerHTML = telephone.operator_code;
        tr.appendChild(temp);
        var temp = document.createElement('td');
        temp.id = "telephone_" + telephone.id + "_number"
        temp.innerHTML = telephone.number;
        tr.appendChild(temp);
        var temp = document.createElement('td');
        temp.id = "telephone_" + telephone.id + "_type"
        temp.innerHTML = telephone.type;
        tr.appendChild(temp);
        var temp = document.createElement('td');
        temp.id = "telephone_" + telephone.id + "_comments"
        temp.innerHTML = telephone.comment;
        tr.appendChild(temp);
        var temp = document.createElement('td');
        temp.innerHTML = "<button id=\"delete_telephone\" onclick=\"delete_telephone(\'" + telephone.id + "\','Are you sure?'" + ")\" class=\"btn btn-default\" type=\"button\" aria-haspopup=\"true\" aria-expanded=\"true\"style=\"border: 0px\"> <span class=\"glyphicon glyphicon-trash\"></span> </button> <button id=\"edit_telephone\" data-toggle=\"modal\" data-target=\"#modal_telephone\" onclick=\"edit_telephone(event, \'" + telephone.id + "\')\"class=\"btn btn-default \"type=\"button\" aria-haspopup=\"true\" aria-expanded=\"true\" style=\"border: 0px\" > <span class=\"glyphicon glyphicon-pencil\"></span> </button>";
        tr.appendChild(temp);
        return tr;
    }
}


function create_attachment(event) {
    var id = -1 * generateId();
    if (document.getElementById('attachment_div') != null) {
        var attachment_div = document.querySelector('#modal_attachment .modal-body');
        attachment_div.removeChild(document.getElementById('attachment_div'));
    }
    document.getElementById('attachment_id').value = id;
    document.getElementById('attachment_name').value = '';
    document.getElementById('attachment_comment').value = '';
    var div = document.createElement('div');
    div.id = "attachment_div";
    div.class = "\"form-group\"";
    var temp = "<label for=\"up_file\" class=\"required\">Attachment:</label> <input id=\"up_file_" + id + "\" type=\"file\" name=\"up_file_" + id + "\" accept=\"*\" required/><small class=\"error-text help-block\">Choose file</small>"
    div.innerHTML = temp;
    document.querySelector('#attachment_form').appendChild(div);
    openModal(event, save_attachment);
}

function edit_attachment(event, attachment_id) {
    if (document.getElementById('attachment_div') != null) {
        var attachment_div = document.querySelector('#modal_attachment .modal-body');
        attachment_div.removeChild(document.getElementById('attachment_div'));
    }
    document.getElementById('attachment_id').value = attachment_id;
    document.getElementById('attachment_name').value = document.getElementById('attachment_' + attachment_id + '_name').innerHTML;
    document.getElementById('attachment_comment').value = document.getElementById('attachment_' + attachment_id + '_comment').innerHTML;
    openModal(event, save_attachment);
}

function delete_attachment(attachment_id, text) {

    if (confirm(text)) {
        document.getElementById("attachments").removeChild(document.getElementById("attachment_" + attachment_id));
        document.getElementById("files_form").removeChild(document.getElementById("up_file_" + attachment_id));
    }
}

function save_attachment() {
    var form = document.forms['attachment_form'];
    if (form.checkValidity()) {
        var attachment = {};
        attachment.id = document.getElementById('attachment_id').value;
        attachment.name = document.getElementById('attachment_name').value;
        attachment.comment = document.getElementById('attachment_comment').value;
        attachment.data = formatDate(new Date());
        var choose_attachment = document.getElementById("attachment_" + attachment.id);
        var file = document.getElementById("up_file_" + attachment.id);
        document.getElementById("attachments").insertBefore(generateTRForAttachment(attachment), choose_attachment);
        if (choose_attachment != null) {
            document.getElementById("attachments").removeChild(choose_attachment);
        }
        if (file != null) {
            document.getElementById("files_form").appendChild(file)
        }
        clear_form(form)
        return true;
    } else {
        valid_form(form);
        return false;
    }
    function generateTRForAttachment(attachment) {
        var tr = document.createElement('tr');
        tr.id = "attachment_" + attachment.id;
        var temp = document.createElement('td');
        temp.innerHTML = "<label><input type=\"checkbox\"></label>";
        tr.appendChild(temp);
        var temp = document.createElement('td');
        temp.id = "attachment_" + attachment.id + "_name"
        temp.innerHTML = attachment.name;
        tr.appendChild(temp);
        var temp = document.createElement('td');
        temp.id = "attachment_" + attachment.id + "_creationDate"
        temp.innerHTML = attachment.data;
        tr.appendChild(temp);
        var temp = document.createElement('td');
        temp.id = "attachment_" + attachment.id + "_comment"
        temp.innerHTML = attachment.comment;
        tr.appendChild(temp);
        var temp = document.createElement('td');
        temp.innerHTML = "<button id=\"delete_attachment\" onclick=\"delete_attachment(\'" + attachment.id + "\', \'Are you sure\'" + ")\" class=\"btn btn-default\" type=\"button\" aria-haspopup=\"true\" aria-expanded=\"true\" style=\"border: 0px\"> <span class=\"glyphicon glyphicon-trash\"></span> </button> <button id=\"edit_attachment\" data-toggle=\"modal\"data-target=\"#modal_attachment\" onclick=\"edit_attachment(event,\'" + attachment.id + "\')\"class=\"btn btn-default \"type=\"button\" aria-haspopup=\"true\" aria-expanded=\"true\" style=\"border: 0px\"> <span class=\"glyphicon glyphicon-pencil\"></span> </button>";
        tr.appendChild(temp);
        return tr;
    }
}

function generateId() {
    return ++numberOfRows;
}

function formatDate(date) {
    var mm = date.getMonth() + 1;
    var dd = date.getDate();

    return [date.getFullYear(), '-', String(mm).length == 1 ? '0' : '',
        mm, '-', String(dd).length == 1 ? '0' : '', dd].join('');
}

function makeForm() {
    var form = document.forms['contact_form'];
    form.enctype = "multipart/form-data";
    form.action = "/save_contact";
    return form;
}


function valid_form(form) {
    var elements = form.getElementsByTagName("input");
    for (var i = 0; i < elements.length; i++) {
        if (!elements[i].checkValidity()) {
            elements[i].classList.add('has-error');
        } else if (elements[i].checkValidity()) {
            if (elements[i].classList.contains("has-error")) {
                elements[i].classList.remove("has-error");
            }
            if (elements[i].hasAttribute("required")) {
                elements[i].classList.add('has-success');
            }
        }
    }
}

function clear_form(form) {
    var elements = form.getElementsByTagName("input");
    for (var i = 0; i < elements.length; i++) {
        if (elements[i].classList.contains("has-error")) {
            elements[i].classList.remove("has-error");
        }
        if (elements[i].classList.contains("has-succes")) {
            elements[i].classList.remove("has-succes");
        }
    }

}

function save_contact() {
    var form = makeForm();
    if (form.checkValidity()) {
        addAttachmentsFromTable(form);
        addPhonesFromTable(form);
        addFromForm('files_form', form);
        //addFromForm('contact_form', form);
        form.method = "post";
        form.submit();
        return true;
    } else {
        valid_form(form);
        return false;
    }

    function addFromForm(from_form_id, from) {
        var formFiles = document.getElementById(from_form_id);
        for (var i = 0; i < formFiles.elements.length;) {
            //formFiles.elements[i].type="hidden";
            form.appendChild(formFiles.elements[i]);
        }
    }

    function addParameterToForm(form, name, value) {
        var parameter = document.createElement("input");
        parameter.type = 'hidden';
        parameter.name = name;
        parameter.value = value;
        form.appendChild(parameter);
    }


    function addPhonesFromTable(form) {
        var tbody = document.getElementById("telephones");
        var phonesIds = '';
        for (var i = 0; i < tbody.rows.length; i++) {
            phonesIds = phonesIds.concat("," + tbody.rows[i].id);
            addParameterToForm(form, tbody.rows[i].cells[1].id, tbody.rows[i].cells[1].innerHTML);
            addParameterToForm(form, tbody.rows[i].cells[2].id, tbody.rows[i].cells[2].innerHTML);
            addParameterToForm(form, tbody.rows[i].cells[3].id, tbody.rows[i].cells[3].innerHTML);
            addParameterToForm(form, tbody.rows[i].cells[4].id, tbody.rows[i].cells[4].innerHTML);
            addParameterToForm(form, tbody.rows[i].cells[5].id, tbody.rows[i].cells[5].innerHTML);
        }
        addParameterToForm(form, 'phonesIds', phonesIds);
    }

    function addAttachmentsFromTable(form) {
        var tbody = document.getElementById("attachments");
        var attachmentsIds = '';
        for (var i = 0; i < tbody.rows.length; i++) {
            attachmentsIds = attachmentsIds.concat("," + tbody.rows[i].id);
            addParameterToForm(form, tbody.rows[i].cells[1].id, tbody.rows[i].cells[1].innerHTML);
            addParameterToForm(form, tbody.rows[i].cells[2].id, tbody.rows[i].cells[2].innerHTML);
            addParameterToForm(form, tbody.rows[i].cells[3].id, tbody.rows[i].cells[3].innerHTML);
        }
        addParameterToForm(form, 'attachmentsIds', attachmentsIds);
    }

}

function deletecontact(id, text) {
    if (confirm(text)) {
        window.location.href = '/deletecontact?id=' + id;
    } else {
        return false;
    }
}

