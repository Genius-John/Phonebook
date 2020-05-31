
<#import "parts/common.ftl" as c>

<@c.page>

    <div>
        <form method="post" class="form-inline mt-4">
            <input type="text" disabled class="form-control mr-2" name="fullName" placeholder="full name" />
            <input type="text" disabled class="form-control mr-2" name="exNumber" placeholder="extension">
            <input type="text" disabled class="form-control mr-2" name="mobileNumber" placeholder="mobile">
            <button disabled class="btn btn-primary my-3" type="submit">Добавить</button>
        </form>
    </div>

    <form method="get" action="/main" class="form-inline mt-4">
        <input disabled type="text" class="form-control col-sm-3 " name="filter" placeholder="${filter?ifExists}">
        <button disabled class="btn btn-primary ml-3" type="submit">Найти</button>
    </form>

    <div class="my-3">
        <a href="/phonebook"><b>List of records</b></a>
    </div>

    <table class="table table-hover mt-3">
    <thead>
    <tr>
        <th>Full name</th>
        <th>Extension</th>
        <th>Mobile</th>
        <th class="text-center">Action</th>
    </tr>
    </thead>
    <tbody>`
        <tr>
            <div>
                <td><input type="text" class="form-control mr-2" name="fullName" value="${record.fullName}"></td>
                <td><input type="text" class="form-control mr-2" name="exNumber" value="${record.exNumber}"></td>
                <td><input type="text" class="form-control mr-2" name="mobileNumber" value="${record.mobileNumber}"></td>
                <td>
                    <a class="btn btn-danger" href="/phonebook/del/${record.id}">delete</a>
                    <a class="btn btn-success" href="/phonebook/update/${record.id}">save</a>
                </td>
            </div>

        </tr>
</@c.page>