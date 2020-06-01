
<#import "parts/common.ftl" as c>

<@c.page>

    <div>

        <form method="post" class="form-inline mt-4">
            <input type="text" class="form-control mr-2" name="mobileNumber" placeholder="group name">
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
<tr>
    <div>
        <form action="/phonebook/update/" method="post">
            <td><input type="text" class="form-control mr-2 col-sm-4" name="groupName" value=""></td>
            <td>
                <a class="btn btn-danger text-center" href="/phonebook/">delete</a>
            </td>
        </form>
    </div>

</tr>
</@c.page>