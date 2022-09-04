import PhoneBookService from "./src/api/PhoneBookService.js";   

var modalWrap = null;
var phoneRegex = /^(\+7|7|8)?[\s\-]?\(?[489][0-9]{2}\)?[\s\-]?[0-9]{3}[\s\-]?[0-9]{2}[\s\-]?[0-9]{2}$/

function showAlert(alertName) {
    var alert = document.getElementsByClassName(alertName)[0];
    alert.classList.remove("hide");
     alert.classList.add("show");
        setTimeout(function () {
            alert.classList.add("hide");
            alert.classList.remove("show");
        }, 2000)
}

async function buildTable() {
    var data = await PhoneBookService.getAll();

    if(data !== null) {
        var table = document.getElementById("myTable");
        table.innerHTML = "";

        for(var i = 0; i < data.length; i++) {
            var row = ` <tr id="entry_${data[i].id}">
                            <td scope="row">${data[i].name}</td>
                            <td>${data[i].phone}</td>
                            <td>${data[i].update}</td>
                            <td>${data[i].actualized}</td>
                            <td>
                                <button type="button" class="btn btn-success" onclick="showModalUpdate(this)"><i class="fas fa-edit"></i></button>
                                <button type="button" class="btn btn-danger" onclick="remove(this)"><i class="far fa-trash-alt"></i></button>
                            </td>
                        </tr>`;
            table.innerHTML += row;
        }
    } else {
        document.getElementById("data-alert").classList.remove("d-none");
    }
}

function remove(e) {
    var id = e.parentNode.parentNode.id.replace("entry_", "");
    PhoneBookService.remove(id).then(buildTable);
    showAlert("delete-alert");
}

function showModalCreate() {
    if (modalWrap !== null) {
        modalWrap.remove();
      }
    
      modalWrap = document.createElement('div');
      modalWrap.innerHTML = `
        <div class="modal fade" id="staticBackdrop" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="staticBackdropLabel">Создание записи</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <form id="createForm" class="needs-validation" novalidate>
                            <div class="mb-3">
                                <label for="createName" class="form-label">Имя клиента</label>
                                <input type="text" class="form-control" id="createName" name="createName">
                            </div>
                            <div class="mb-3">
                                <label for="createPhone" class="form-label">Номер телефона</label>
                                <input type="text" class="form-control" id="createPhone" name="createPhone">
                                <div id="invalid-phone-message" class="d-none error-feedback">
                                    Корректный формат номера:
                                    <ul>
                                        <li>+70000000000</li>
                                        <li>+7(000)000-00-00</li>
                                        <li>+7(000)-000-00-00</li>
                                        <li>+7(000)0000000</li>
                                        <li>80000000000</li>
                                    </ul>
                                </div>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Закрыть</button>
                                <button type="submit" class="btn btn-success modal-success-btn">Сохранить</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
        `;

        modalWrap.querySelector('.modal-success-btn').addEventListener("click", function(e) {
            e.preventDefault();
            var name = document.getElementById('createName');
            var phone = document.getElementById('createPhone');
            if(phoneRegex.test(phone.value)) {
                if(phone.classList.contains("invalid-input")) {
                    document.getElementById("invalid-phone-message").classList.add("d-none");
                    phone.classList.remove("invalid");
                }
                var createEntry = {
                    "name" : name.value,
                    "phone" : phone.value
                }
                create(createEntry);
                var myModalEl = document.getElementById('staticBackdrop');
                var modal = bootstrap.Modal.getInstance(myModalEl)
                modal.hide();
                showAlert("create-alert");
            } else {
                e.stopPropagation();
                phone.classList.add("invalid-input");
                document.getElementById("invalid-phone-message").classList.remove("d-none");
            }
        });

        document.body.append(modalWrap);

        var modal = new bootstrap.Modal(modalWrap.querySelector('.modal'));
        modal.show();
}

async function create(entry) {
    return await PhoneBookService.save(entry).then(buildTable);
}

async function showModalUpdate(e) {
    var id = e.parentNode.parentNode.id.replace("entry_", "");

    var entry = await PhoneBookService.getOne(id);

    if (modalWrap !== null) {
        modalWrap.remove();
      }
    
      modalWrap = document.createElement('div');
      modalWrap.innerHTML = `
        <div class="modal fade" id="staticBackdrop" tabindex="-1">
          <div class="modal-dialog">
            <div class="modal-content">
              <div class="modal-header bg-light">
                <h5 class="modal-title">Изменение</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
              </div>
              <div class="modal-body">
                <form id="updateForm">
                    <div class="mb-3">
                        <label for="inputName" class="form-label">Имя клиента</label>
                        <input type="text" class="form-control" id="updateName" name="updateName" value="${entry.name}">
                    </div>
                    <div class="mb-3">
                        <label for="inputPhone" class="form-label">Номер телефона</label>
                        <input type="text" class="form-control" id="updatePhone" name="updatePhone" value="${entry.phone}">
                        <div id="invalid-phone-message" class="d-none error-feedback">
                            Корректный формат номера:
                            <ul>
                                <li>+70000000000</li>
                                <li>+7(000)000-00-00</li>
                                <li>+7(000)-000-00-00</li>
                                <li>+7(000)0000000</li>
                                <li>80000000000</li>
                            </ul>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Закрыть</button>
                        <button type="submit" class="btn btn-success modal-success-btn">Изменить</button>
                    </div>
                </form>
              </div>
            </div>
          </div>
        </div>
        `;

        modalWrap.querySelector('.modal-success-btn').addEventListener("click", function(e) {
            e.preventDefault();
            var name = document.getElementById('updateName');
            var phone = document.getElementById('updatePhone');
            if(phoneRegex.test(phone.value)) {
                if(phone.classList.contains("invalid-input")) {
                    phone.classList.remove("invalid-input");
                    document.getElementById("invalid-phone-message").classList.add("d-none");
                }
                var updatedEntry = {
                    "id" : entry.id,
                    "name" : name.value,
                    "phone" : phone.value
                }
                update(updatedEntry);
                var myModalEl = document.getElementById('staticBackdrop');
                var modal = bootstrap.Modal.getInstance(myModalEl)
                modal.hide();
            } else {
                e.stopPropagation();
                phone.classList.add("invalid-input");
                document.getElementById("invalid-phone-message").classList.remove("d-none");
            }
        });

        document.body.append(modalWrap);

        var modal = new bootstrap.Modal(modalWrap.querySelector('.modal'));
        modal.show();
}

function update(entry) {
    PhoneBookService.update(entry).then(buildTable);
    showAlert("update-alert");
}

async function actualizeAllRecords() {
    PhoneBookService.actualize().then(buildTable);
    showAlert("actualize-alert");
}

document.addEventListener("DOMContentLoaded", () => {
    window.remove = remove;
    window.showModalUpdate = showModalUpdate;
    window.showModalCreate = showModalCreate;
    window.actualizeAllRecords = actualizeAllRecords;
    buildTable();
});
