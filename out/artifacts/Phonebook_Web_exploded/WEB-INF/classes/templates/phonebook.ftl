
<#import "parts/common.ftl" as c>

<@c.page>
    <form method="post">
        <div class="form-inline mt-4 row">
            <div class="col-sm-12 col-lg-3" style="margin-top: 10px">
                <input type="text" class="form-control" style="width: 100%" required minlength="3" name="fullName" placeholder="Имя/Должность*" />
            </div>
            <div class="col-sm-12 col-lg-2" style="margin-top: 10px">
                <input type="text" class="form-control" style="width: 100%" pattern="^[ 0-9]+$" minlength="4" maxlength="5"  name="exNumber" placeholder="Вн. номер">
            </div>
            <div class="col-sm-12 col-lg-3" style="margin-top: 10px">
                <input type="text" class="form-control" style="width: 100%" pattern="^[ 0-9]+$" minlength="11" maxlength="11" name="mobileNumber" placeholder="Моб. номер">
            </div>
            <div class=" col-sm-12 col-lg-2" style="margin-top: 10px">
                <select class="custom-select" style="width: 100%" name="groupName">
                    <#list groups as group>
                        <option value="${group.groupName}">${group.groupName}</option>
                    </#list>
                </select>
            </div>
            <div class="col-sm-12 col-lg-2" style="margin-top: 10px">
                <button class="btn btn-primary" style="width: 100%" type="submit">Добавить</button>
            </div>
        </div>
    </form>

    <form method="get" action="/phonebook">
        <div class="form-inline mt-4 row">
            <div class="col-sm-12 col-lg-3">
                <input type="text" class="form-control" style="width: 100%" name="filter" value="${filter?ifExists}" placeholder="Поиск">
            </div>
            <div class="col-sm-12 col-lg-2 my-2">
                <button class="btn btn-primary" style="width: 100%" type="submit">Найти</button>
            </div>
        </div>
    </form>

    <div class="my-3">
        <a href="/phonebook"><b class="mr-3">List of records</b></a>
        <a href="/groupList"><b>Groups</b></a>
    </div>

    <div class="row  mt-3">
        <div class="col">
            <table class="table table-hover">
                <thead>
                <tr>
                    <th>Имя/должность</th>
                    <th>Вн. номер</th>
                    <th>Моб. номер</th>
                    <th>Группа</th>
                    <th class="text-center">Действие</th>
                </tr>
                </thead>
                <tbody>
                    <#list records as record>
                        <tr>
                            <td>${record.fullName}</td>
                            <td>${record.exNumber}</td>
                            <td>${record.mobileNumber}</td>
                            <td>${record.groupName}</td>
                            <td class="text-center">
                                <a class="btn btn-success" href="/phonebook/${record.id}">изменить</a>
                                <a class="btn btn-danger" data-toggle="modal" data-target="#exampleModal">удалить</a>
                            </td>
                        </tr>
                    </#list>
                </tbody>
            </table>
        </div>
    </div>

    <!-- Modal -->
    <div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">Modal title</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    ...
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                    <button type="submit" class="btn btn-primary" href="/phonebook/del/${record.id}">Save changes</button>
                </div>
            </div>
        </div>
    </div>


</@c.page>