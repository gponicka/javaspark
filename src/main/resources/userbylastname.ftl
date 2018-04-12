<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="/customcss.css">
    <title>List of Users</title>
</head>
<body>
<div class="custom">
    <div class="w3-container" style="padding-left: 10px">
        <h2>${title}</h2>
        <p>${description}</p>
    </div>

    <#if searchResult? has_content>
        <table class="w3-card w3-table-all">
            <thead>
                <tr class="w3-theme">
                    <th>Last Name</th>
                    <th>No. of Users</th>
                    <th>Details</th>
                </tr>
            </thead>
            <#list searchResult as result>
                <tr class="w3-hover-black">
                    <td>${result["_id"]}</td>
                    <td style="padding-left: 10%">${result["count"]}</td>
                    <td><a target="_blank" href="/search/${result["_id"]}">View
                        <i class="fa fa-angle-double-right"></i></a></td>
                </tr>
            </#list>
        </table>
    </#if>
</div>
</body>
</html>