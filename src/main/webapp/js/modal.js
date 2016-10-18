var Modal = function (el, options) {
    var self = this;

    this.el = document.querySelector(el);
    this.options = options;

    try {
        var list = document.querySelectorAll('#' + this.el.id + ' [data-dismiss="modal"]');
        for (var x = 0; x < list.length; x++) {
            list[x].addEventListener('click', function (e) {
                if (e) e.preventDefault();
                self.hide();
            });
        }
    }
    catch (e) {
        console.log(e);
    }
};

Modal.prototype.show = function () {
    var self = this;
    if (self.options.backdrop) {
        var backdrop = document.getElementById('bs.backdrop');
        if (backdrop === null) {
            backdrop = document.createElement('div');
            backdrop.id = "bs.backdrop";
            backdrop.className = "modal-backdrop fade in";
            document.body.appendChild(backdrop);
        }
    }
    this.el.classList.add('in');
    this.el.style.display = 'block';
    document.body.style.paddingRight = '13px';
    document.body.classList.add('modal-open');
};

Modal.prototype.hide = function () {
    var self = this;
    this.el.classList.remove('in');
    this.el.style.display = 'none';
    document.body.style = '';
    document.body.classList.remove('modal-open');
    if (self.options.backdrop) {
        var backdrop = document.getElementById('bs.backdrop');
        if (backdrop !== null) document.body.removeChild(backdrop);
    }
};

function openModal(btn, event) {
    var idModal = btn.getAttribute('data-target');
    if (event) event.preventDefault();
    var modal = new Modal(idModal, {
        backdrop: true
    });
    modal.show();
}
