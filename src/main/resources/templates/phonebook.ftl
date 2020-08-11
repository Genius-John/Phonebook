
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
                        <option>${group.groupName}</option>
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
                <input type="text" class="form-control" style="width: 100%" minlength="3" name="filter" value="${filter?ifExists}" placeholder="Поиск">
            </div>
            <div class="col-sm-12 col-lg-2">
                <select style="width: 100%" class="custom-select" style="width: 100%" name="filterGroupName">
                    <option selected>Все группы</option>
                    <#list groups as group>
                        <option value="${group.groupName}">${group.groupName}</option>
                    </#list>
                </select>
            </div>
            <div class="col-sm-12 col-lg-2 my-2">
                <button class="btn btn-primary" style="width: 100%" type="submit">Найти</button>
            </div>
        </div>
    </form>

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
                                <a class="btn btn-danger" href="/phonebook/del/${record.id}" onClick="return confirm('Вы уверены что хотите удалить запись?')">удалить</a>
                            </td>
                        </tr>
                    </#list>
                </tbody>
            </table>
        </div>
    </div>


</@c.page>