<#import "parts/common.ftl" as c>

<@c.page>

    <div>

        <form method="post" class="form-inline mt-4">
            <input type="text" disabled class="form-control mr-2" name="fullName" placeholder="Full name" />
            <input type="text" disabled class="form-control mr-2" name="exNumber" placeholder="Extension">
            <input type="text" disabled class="form-control mr-2" name="mobileNumber" placeholder="Mobile">
            <button disabled class="btn btn-primary my-3" type="submit">Add</button>
        </form>
    </div>
    <form method="get" action="/phonebook" class="form-inline mt-4">
        <input disabled type="text" class="form-control col-sm-3 " name="filter" placeholder="${filter?ifExists}">
        <button disabled class="btn btn-primary ml-3" type="submit">Search</button>
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
        <th>Group</th>
        <th class="text-center">Action</th>
    </tr>
    </thead>
    <tbody>
        <tr>
            <div>
                <form action="/phonebook/update/${record.id}" method="post">
                    <td><input type="text" class="form-control mr-2" name="fullName" value="${record.fullName}"></td>
                    <td><input type="text" class="form-control mr-2" name="exNumber" value="${record.exNumber}"></td>
                    <td><input type="text" class="form-control mr-2" name="mobileNumber" value="${record.mobileNumber}"></td>
                    <td>
                        <div class="dropdown">
                            <button class="btn btn-link dropdown-toggle" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                Group
                            </button>
                            <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
                                <a class="dropdown-item" href="#">Office</a>
                                <a class="dropdown-item" href="#">MSK</a>
                                <a class="dropdown-item" href="#">Omsk</a>
                            </div>
                        </div>
                    </td>
                    <td>
                        <a class="btn btn-danger" href="/phonebook/del/${record.id}">delete</a>
                        <button type="submit" class="btn btn-success">save</button>
                    </td>
                </form>
            </div>

        </tr>
</@c.page>