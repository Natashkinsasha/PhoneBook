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
function create_telephone(btn) {
    document.getElementById('phone_id').value = generateId();
    document.getElementById('country_code').value = '';
    document.getElementById('operator_code').value = '';
    document.getElementById('phone_number').value = '';
    document.getElementById('phone_type').value = '';
    document.getElementById('comment').value = '';
    openModal(btn);
}

function edit_telephone(btn, telephone_id) {
    document.getElementById('phone_id').value = telephone_id;
    document.getElementById('country_code').value = document.getElementById('telephone_' + telephone_id + '_country_code').innerHTML;
    document.getElementById('operator_code').value = document.getElementById('telephone_' + telephone_id + '_operator_code').innerHTML;
    document.getElementById('phone_number').value = document.getElementById('telephone_' + telephone_id + '_number').innerHTML;
    document.getElementById('phone_type').value = document.getElementById('telephone_' + telephone_id + '_type').innerHTML;
    document.getElementById('comment').value = document.getElementById('telephone_' + telephone_id + '_comments').innerHTML;
    openModal(btn);
}

function delete_telephone(telephone_id, text) {
    if (confirm(text)) {
        var choose_telephone = document.getElementById("telephone_" + telephone_id);
        document.getElementById("telephones").removeChild(choose_telephone);
    }
}

function save_telephone() {
    var telephone = {};
    telephone.id = document.getElementById('phone_id').value;
    telephone.country_code = document.getElementById('country_code').value;
    telephone.operator_code = document.getElementById('operator_code').value;
    telephone.number = document.getElementById('phone_number').value;
    telephone.type = document.getElementById('phone_type').value;
    telephone.comment = document.getElementById('comment').value;
    var choose_telephone = document.getElementById("telephone_" + telephone.id);
    document.getElementById("telephones").insertBefore(generateTRForTelephone(telephone), choose_telephone);
    if (document.getElementById(choose_telephone)) {
        document.getElementById("telephones").removeChild(choose_telephone);
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
        temp.innerHTML = "<button id=\"delete_telephone\" onclick=\"delete_telephone(this, \'" + telephone.id + "\')\" class=\"btn btn-default\" type=\"button\" aria-haspopup=\"true\" aria-expanded=\"true\"style=\"border: 0px\"> <span class=\"glyphicon glyphicon-trash\"></span> </button> <button id=\"edit_telephone\" data-toggle=\"modal\" data-target=\"#modal_telephone\" onclick=\"edit_telephone(this, \'" + telephone.id + "\')\"class=\"btn btn-default \"type=\"button\" aria-haspopup=\"true\" aria-expanded=\"true\" style=\"border: 0px\" > <span class=\"glyphicon glyphicon-pencil\"></span> </button>";
        tr.appendChild(temp);
        return tr;
    }
}


function create_attachment(btn) {
    var id = generateId();
    if(document.getElementById("attachment_div")!=null){
        document.removeChild(document.getElementById("attachment_div"));
    }
    document.getElementById('attachment_id').value = id;
    document.getElementById('attachment_name').value = '';
    document.getElementById('attachment_comment').value = '';
    var div = document.createElement('div');
    div.id = "attachment_div";
    div.class = "\"form-group\"";
    var temp = "<label for=\"up_file\" class=\"required\">Attachment:</label> <input id=\"up_file_" + id + "\" type=\"file\" name=\"up_file_" + id + "\" accept=\"*\" required>"
    div.innerHTML = temp;
    document.querySelector('#modal_attachment .modal-body').appendChild(div);
    openModal(btn);
}

function edit_attachment(btn, attachment_id) {
    if(document.getElementById("attachment_div")!=null){
        document.removeChild(document.getElementById("attachment_div"));
    }
    document.getElementById('attachment_id').value = attachment_id;
    document.getElementById('attachment_name').value = document.getElementById('attachment_' + attachment_id + '_name').innerHTML;
    document.getElementById('attachment_comment').value = document.getElementById('attachment_' + attachment_id + '_comment').innerHTML;
    openModal(btn);
}

function delete_attachment(attachment_id, text) {
    if (confirm(text)) {
        document.getElementById("attachments").removeChild(document.getElementById("attachment_" + attachment_id));
        document.getElementById("attachments").removeChild(document.getElementById("up_file_" + attachment_id));
    }
}

function save_attachment() {
    var attachment = {};
    attachment.id = document.getElementById('attachment_id').value;
    attachment.name = document.getElementById('attachment_name').value;
    attachment.comment = document.getElementById('attachment_comment').value;
    attachment.data = formatDate(new Date());
    var choose_attachment = document.getElementById("attachment_" + attachment.id);
    document.getElementById("attachments").insertBefore(generateTRForAttachment(attachment), choose_attachment);
    if (document.getElementById(choose_attachment) != null) {
        document.getElementById("attachments").removeChild(choose_attachment);
    }
    document.getElementById("files_form").appendChild(document.getElementById("up_file_" + attachment.id))
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
        temp.id = "attachment_" + attachment.id + "_data"
        temp.innerHTML = attachment.data;
        tr.appendChild(temp);
        var temp = document.createElement('td');
        temp.id = "attachment_" + attachment.id + "_comment"
        temp.innerHTML = attachment.comment;
        tr.appendChild(temp);
        var temp = document.createElement('td');
        temp.innerHTML = "<button id=\"delete_attachment\" onclick=\"delete_attachment(this, \'" + attachment.id + "\')\" class=\"btn btn-default\" type=\"button\" aria-haspopup=\"true\" aria-expanded=\"true\" style=\"border: 0px\"> <span class=\"glyphicon glyphicon-trash\"></span> </button> <button id=\"edit_attachment\" data-toggle=\"modal\"data-target=\"#modal_attachment\" onclick=\"edit_attachment(this,\'" + attachment.id + "\')\"class=\"btn btn-default \"type=\"button\" aria-haspopup=\"true\" aria-expanded=\"true\" style=\"border: 0px\"> <span class=\"glyphicon glyphicon-pencil\"></span> </button>";
        tr.appendChild(temp);
        return tr;
    }
}

function generateId() {
    return '_' + Math.random().toString(36).substr(2, 9);
}

function formatDate(date) {
    var mm = date.getMonth() + 1;
    var dd = date.getDate();

    return [date.getFullYear(), '-', String(mm).length == 1 ? '0' : '',
        mm, '-', String(dd).length == 1 ? '0' : '', dd].join('');
}

