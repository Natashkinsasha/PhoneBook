<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
</head>
<body>
<div id="modal_attachment" class="modal fade" role="dialog">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">Save attachment</h4>
            </div>
            <div class="modal-body">
                <input type="hidden" id="attachment_id">
                <div class="form-group">
                    <label for="attachment_name" class="required">Name</label>
                    <input type="text" maxlength="32" class="form-control"
                           id="attachment_name"
                           name="attachment_name"
                           required>
                </div>
                <div class="form-group">
                    <label for="attachment_comment">Comment</label>
                    <input type="text" maxlength="32" class="form-control"
                           id="attachment_comment"
                           name="attachment_comment">
                </div>
            </div>
            <div class="modal-footer">
                <div class="btn-group" role="group" aria-label="...">
                    <button type="button" class="btn btn-danger" data-dismiss="modal">
                        Close
                    </button>
                    <button id="create_attachment" onclick="save_attachment(this)" class="btn btn-success" data-dismiss="modal">
                        Save
                    </button>
                </div>
            </div>

        </div>
    </div>
</div>
</body>
</html>
