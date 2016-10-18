<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
</head>
<body>
<div id="modal_telephone" class="modal fade" role="dialog">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">Save telephone</h4>
            </div>
            <div class="modal-body">
                <input type="hidden" id="phone_id">
                <div class="form-group">
                    <label for="country_code">Country code</label>
                    <input type="text" maxlength="3" class="form-control"
                           id="country_code"
                           name="country_code"
                           pattern="^\d{3}$">
                </div>
                <div class="form-group">
                    <label for="operator_code">Operator code</label>
                    <input type="text" maxlength="2" class="form-control"
                           id="operator_code"
                           name="operator_code"
                           pattern="^\d{2}$">
                </div>
                <div class="form-group">
                    <label for="phone_number" class="required">Phone number</label>
                    <input type="text" maxlength="7" class="form-control"
                           id="phone_number"
                           name="phone_number"
                           pattern="^\d{7}$"
                           required>
                </div>
                <div class="form-group">
                    <label for="phone_type">Phone type</label>
                    <input type="text" maxlength="32" class="form-control"
                           id="phone_type"
                           name="phone_type">
                </div>
                <div class="form-group">
                    <label for="comment">Comment</label>
                    <input type="text" maxlength="32" class="form-control" id="comment"
                           name="comment">
                </div>
            </div>
            <div class="modal-footer">
                <div class="btn-group" role="group" aria-label="...">
                    <button type="button" class="btn btn-danger" data-dismiss="modal">
                        Close
                    </button>
                    <button id="create_telephone" onclick="save_telephone()" class="btn btn-success"
                            data-dismiss="modal">
                        Save
                    </button>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
