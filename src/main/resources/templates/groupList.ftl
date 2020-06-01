
<#import "parts/common.ftl" as c>

<@c.page>

    <div>

        <form method="post" class="form-inline mt-4">
            <input type="text" class="form-control mr-2" name="groupName" placeholder="group name">
            <button class="btn btn-primary my-3" type="submit">Add</button>
        </form>
    </div>

    <div class="my-3">
        <a href="/phonebook" class="mr-3"><b>List of records</b></a>
    </div>
    <table class="table table-hover mt-3">
    <thead>
    <tr>
        <th>Group name</th>
        <th>Action</th>
    </tr>
    </thead>
    <tbody>
    <#list groups as group>
        <tr>
            <div>
                <form action="/groupList/${group.id}" method="post">
                    <td><input type="text" class="form-control mr-2 col-sm-4" name="groupName" value="${group.groupName}"></td>
                    <td>
                        <a class="btn btn-danger text-center" href="/groupList/del/${group.id}">delete</a>
                        <button type="submit" class="btn btn-success">save</button>
                    </td>
                </form>
            </div>

        </tr>
    </#list>
</@c.page>