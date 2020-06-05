
<#import "parts/common.ftl" as c>

<@c.page>
    <form method="post">
        <div class="form-inline mt-4 row">
            <div class="col-sm-12 col-lg-3" style="margin-top: 10px">
                <input type="text" class="form-control" style="width: 100%" required minlength="3" name="fullName" placeholder="Full name" />
            </div>
            <div class="col-sm-12 col-lg-3" style="margin-top: 10px">
                <input type="text" class="form-control" style="width: 100%" pattern="^[ 0-9]+$" minlength="4" maxlength="5"  name="exNumber" placeholder="Extension">
            </div>
            <div class="col-sm-12 col-lg-3" style="margin-top: 10px">
                <input type="text" class="form-control" style="width: 100%" pattern="^[ 0-9]+$" minlength="11" maxlength="11" name="mobileNumber" placeholder="Mobile">
            </div>
            <div class=" col-sm-12 col-lg-2" style="margin-top: 10px">
                <select class="custom-select" style="width: 100%" name="groupName">
                    <#list groups as group>
                        <option value="${group.groupName}">${group.groupName}</option>
                    </#list>
                </select>
            </div>
            <div class="col-sm-12 col-lg-1" style="margin-top: 10px">
                <button class="btn btn-primary" style="width: 100%" type="submit">Add</button>
            </div>
        </div>
    </form>

    <form method="get" action="/phonebook" class="form-inline mt-4">
        <input type="text" class="form-control col-sm-3" name="filter" value="${filter?ifExists}">
        <button class="btn btn-primary ml-3" type="submit">Search</button>
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
                            <td>${record.fullName}</td>
                            <td>${record.exNumber}</td>
                            <td>${record.mobileNumber}</td>
                            <td>${record.groupName}</td>
                            <td class="text-center">
                                <a class="btn btn-danger" href="/phonebook/del/${record.id}">delete</a>
                                <a class="btn btn-success" href="/phonebook/${record.id}">edit</a>
                            </td>
                        </tr>
                    </#list>
                </tbody>
            </table>
        </div>
    </div>


</@c.page>