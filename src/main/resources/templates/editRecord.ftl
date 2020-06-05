<#import "parts/common.ftl" as c>

<@c.page>

    <form method="post">
        <div class="form-inline mt-4 row">
            <div class="col-sm-12 col-lg-3" style="margin-top: 10px">
                <input disabled type="text" class="form-control" style="width: 100%" required minlength="3" name="fullName" placeholder="Имя/Должность*" />
            </div>
            <div class="col-sm-12 col-lg-2" style="margin-top: 10px">
                <input disabled type="text" class="form-control" style="width: 100%" pattern="^[ 0-9]+$" minlength="4" maxlength="5"  name="exNumber" placeholder="Вн. номер">
            </div>
            <div class="col-sm-12 col-lg-3" style="margin-top: 10px">
                <input disabled type="text" class="form-control" style="width: 100%" pattern="^[ 0-9]+$" minlength="11" maxlength="11" name="mobileNumber" placeholder="Моб. номер">
            </div>
            <div class=" col-sm-12 col-lg-2" style="margin-top: 10px">
                <select disabled class="custom-select" style="width: 100%" name="groupName">
                    <#list groups as group>
                        <option value="${group.groupName}">${group.groupName}</option>
                    </#list>
                </select>
            </div>
            <div class="col-sm-12 col-lg-2" style="margin-top: 10px">
                <button disabled class="btn btn-primary" style="width: 100%" type="submit">Добавить</button>
            </div>
        </div>
    </form>

    <form method="get" action="/phonebook">
        <div class="form-inline mt-4 row">
            <div class="col-sm-12 col-lg-3">
                <input disabled type="text" class="form-control" style="width: 100%" name="filter" value="${filter?ifExists}" placeholder="Поиск">
            </div>
            <div class="col-sm-12 col-lg-2 my-2">
                <button disabled class="btn btn-primary" style="width: 100%" type="submit">Найти</button>
            </div>
        </div>
    </form>

    <div class="my-3">
        <a href="/phonebook" class="mr-3"><b>List of records</b></a>
        <a href="/groupList"><b>Add group</b></a>
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
                    <tr>
                        <div>
                            <form action="/phonebook/update/${record.id}" method="post">
                                <td><input type="text" class="form-control mr-2" minlength="3" name="fullName" value="${record.fullName}"></td>
                                <td><input type="text" class="form-control mr-2" pattern="^[ 0-9]+$" minlength="4" maxlength="5" name="exNumber"  value="${record.exNumber}"></td>
                                <td><input type="text" class="form-control mr-2" pattern="^[ 0-9]+$" minlength="11" maxlength="11" name="mobileNumber" value="${record.mobileNumber}"></td>
                                <td>
                                    <div class="col-auto">
                                        <select class="custom-select mr-sm-2" name="groupName">
                                            <#list groups as group>
                                                <option>${group.groupName}</option>
                                            </#list>
                                        </select>
                                    </div>
                                </td>
                                <td>
                                    <button type="submit" class="btn btn-success">сохранить</button>
                                    <a class="btn btn-danger" href="/phonebook">отмена</a>
                                </td>
                            </form>
                        </div>
                    </tr>
                </tbody>
            </table>
        </div>
    </div>
</@c.page>