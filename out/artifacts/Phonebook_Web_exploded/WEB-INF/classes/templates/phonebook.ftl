
<#import "parts/common.ftl" as c>

<@c.page>
    <div>
        <form method="post" class="form-inline mt-4">
            <input type="text" class="form-control mr-2" name="fullName" placeholder="full name" />
            <input type="text" class="form-control mr-2" name="exNumber" placeholder="extension">
            <input type="text" class="form-control mr-2" name="mobileNumber" placeholder="mobile">
            <button class="btn btn-primary my-3" type="submit">Добавить</button>
        </form>
    </div>

    <form method="get" action="/main" class="form-inline mt-4">
        <input type="text" class="form-control col-sm-3 " name="filter" placeholder="${filter?ifExists}">
        <button class="btn btn-primary ml-3" type="submit">Найти</button>
    </form>

    <div class="my-3"><b>List of records</b></div>

    <table class="table table-hover mt-3">
    <thead>
    <tr>
        <th>Full name</th>
        <th>Extension</th>
        <th>Mobile</th>
        <th class="text-center">Action</th>
    </tr>
    </thead>
    <tbody>
        <#list records as record>
            <tr data-href="/">
                <div>
                    <td>${record.fullName}</td>
                    <td>${record.exNumber}</td>
                    <td>${record.mobileNumber}</td>
                    <td>
                        <a class="btn btn-danger" href="/phonebook/del/${record.id}">delete</a>
                        <a class="btn btn-success" href="/phonebook">edit</a>
                    </td>
                </div>

            </tr>
        </#list>
</@c.page>