
<#import "parts/common.ftl" as c>

<@c.page>

    <div>

        <form method="post" class="form-inline mt-4">
            <input type="text" class="form-control mr-2" name="orderGroup" placeholder="order group">
            <input type="text" class="form-control mr-2" name="groupName" placeholder="group name">
            <button class="btn btn-primary my-3" type="submit">Add</button>
        </form>
    </div>

    <div class="my-3">
        <a href="/phonebook" class="mr-3"><b>List of records</b></a>
        <a href="/phonebook/getMenuXml" class="mr-3"><b>menuXML</b></a>
    </div>
    <table class="table table-hover mt-3">
    <thead>
    <tr>
        <th>Order</th>
        <th>Group name</th>
        <th>Action</th>
    </tr>
    </thead>
    <tbody>
    <#list groups as group>
        <tr>
            <form action="/phonebook/groupList/${group.id}" method="post">
                <td><input type="text" class="form-control mr-2 col-sm-4" name="orderGroup" value="${group.orderGroup}"></td>
                <td><input type="text" class="form-control mr-2 col-sm-4" name="groupName" value="${group.groupName}"></td>
                <td>
                    <a class="btn btn-danger text-center" href="/phonebook/groupList/del/${group.id}">delete</a>
                    <button type="submit" class="btn btn-success">save</button>
                    <a class="btn btn-primary text-center" href="/phonebook/getGroupXml/${group.id}">toXml</a>
                </td>
            </form>
        </tr>
    </#list>
</@c.page>