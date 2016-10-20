var form = document.getElementById("myform");
form.noValidate = true;

// обработчик выполняет проверку по событию onsubmit
// используем для поддержания кроссбраузерности
form.onsubmit = validateForm;

function validateForm(event) {

    // проверка на кроссбраузерность
    event = (event ? event : window.event);
    var
        form = (event.target ? event.target : event.srcElement),
        f, field, formvalid = true;

    // перебираем все поля
    for (f = 0; f < form.elements; f++) {

        // считываем поле
        field = form.elements[f];

        // пропуская кнопки, наборы полей, и т.п.
        if (field.nodeName !== "INPUT" && field.nodeName !== "TEXTAREA" && field.nodeName !== "SELECT") continue;

        // проверяем, возможна ли встроенная валидация?
        if (typeof field.willValidate !== "undefined") {

            // возможна
            if (field.nodeName === "INPUT" && field.type !== field.getAttribute("type")) {

                // поле ввода не поддерживается! Используем валидацию JavaScript
                field.setCustomValidity(LegacyValidation(field) ? "" : "error");


                // выполняем валидацию
                field.checkValidity();

            }
            else {

                // не возможна встроенная проверка
                field.validity = field.validity || {};

                // результат функции проверки
                field.validity.valid = LegacyValidation(field);

                // если требуются события "invalid", включайте их здесь

            }

            if (field.validity.valid) {

                // убираем сообщения об ошибках

            }
            else {

                // стилизуем сообщения, показываем ошибки, и т.д.

                // форма неверна
                formvalid = false;
            }

        }

        // если валидация не завершилась успешно, отменяем подтверждение формы
        if (!formvalid) {
            if (event.preventDefault) event.preventDefault();
        }
        return formvalid;
    }


// базовая валидация
    function LegacyValidation(field) {

        var
            valid = true,
            val = field.value,
            type = field.getAttribute("type"),
            chkbox = (type === "checkbox" || type === "radio"),
            required = field.getAttribute("required"),
            minlength = field.getAttribute("minlength"),
            maxlength = field.getAttribute("maxlength"),
            pattern = field.getAttribute("pattern");

        // недоступные поля не проверяются
        if (field.disabled) return valid;

        // требуется значение?
        valid = valid && (!required ||
                (chkbox && field.checked) ||
                (!chkbox && val !== "")
            );

        // установлены minlength или maxlength?
        valid = valid && (chkbox || (
                (!minlength || val.length >= minlength) &&
                (!maxlength || val.length <= maxlength)
            ));

        // тест паттерна
        if (valid && pattern) {
            pattern = new RegExp(pattern);
            valid = pattern.test(val);
        }

        return valid;
    }
}