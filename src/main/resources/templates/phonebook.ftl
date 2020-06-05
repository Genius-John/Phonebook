
<#import "parts/common.ftl" as c>

<@c.page>
    <div class="form-inline mt-4 row">
        <form method="post">
            <input type="text" class="form-control mr-2" required minlength="3" name="fullName" placeholder="Full name" />
            <input type="text" class="form-control mr-2" pattern="^[ 0-9]+$" minlength="4" maxlength="5"  name="exNumber" placeholder="Extension">
            <input type="text" class="form-control mr-2" pattern="^[ 0-9]+$" minlength="11" maxlength="11" name="mobileNumber" placeholder="Mobile">
            <div class="col-auto form-check">
                <select class="custom-select mr-sm-2" name="groupName">
                    <#list groups as group>
                        <option value="${group.groupName}">${group.groupName}</option>
                    </#list>
                </select>
            </div>
            <button class="btn btn-primary my-3" type="submit">Add</button>
        </form>
    </div>

    <form method="get" action="/phonebook" class="form-inline mt-4">
        <input type="text" class="form-control col-sm-3" name="filter" value="${filter?ifExists}">
        <button class="btn btn-primary ml-3" type="submit">Search</button>
    </form>

    <div class="my-3">
        <a href="/phonebook"><b class="mr-3">List of records</b></a>
        <a href="/groupList"><b>Groups</b></a>
    </div>

    <table class="table table-hover mt-3">
    <thead>
    <tr>
        <th>Full name</th>
        <th>Extension</th>
        <th>Mobile</th>
        <th>Group</th>
        <th class="text-center">Action</th>
    </tr>
    </thead>
    <tbody>
    <#list records as record>
        <tr>
            <div>
                <td>${record.fullName}</td>
                <td>${record.exNumber}</td>
                <td>${record.mobileNumber}</td>
                <td>${record.groupName}</td>
                <td class="text-center">
                    <a class="btn btn-danger" href="/phonebook/del/${record.id}">delete</a>
                    <a class="btn btn-success" href="/phonebook/${record.id}">edit</a>
                </td>
            </div>
        </tr>
    </#list>

</@c.page>