<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="/customcss.css">
</head>

<body>
<div class="custom">
    <div class="w3-container" style="padding-left: 10pxpx">
        <h2>Update the New User Information</h2>
        <p>Below is your user information. Please update the incorrect data.</p>
    </div>

    <div class="w3-card">
        <div class="w3-container w3-theme">
            <h3>Update User Information</h3>
        </div>
        <#if document? has_content>
        <form class="w3-container w3-text-theme" style="margin-bottom: 15px" method="post" action="/update-user/${document["_id"]}">
            <p><label for="firstname">First Name</label>
                <input type="text" class="w3-input w3-border" name="firstname" id="firstname" required value="${document["firstname"]}"></p>

            <p><label for="lastname">Last Name</label>
                <input type="text" class="w3-input w3-border" name="lastname" id="lastname" required value="${document["lastname"]}"></p>

            <p><label for="age">Age</label>
                <input type="text" class="w3-input w3-border" name="age" id="age" required value="${document["age"]}"></p>

            <p><label for="gender">Gender</label>
                <input type="hidden" value="${document["gender"]}" id="genderVal">
                <select class="w3-select w3-border" name="gender" id="gender">
                    <option value="Male">Male</option>
                    <option value="Female">Female</option>
                </select>
            </p>

            <p><label for="address">Address</label>
                <input type="text" class="w3-input w3-border" name="address" id="address" required value="${document["address"]}"></p>

            <p><label for="phone">Phone Number (optional)</label>
                <input type="text" class="w3-input w3-border" name="phone" id="phone" value="${document["phone"]}"></p>

            <p><label for="email">Email Address (optional)</label>
                <input type="text" class="w3-input w3-border" name="email" id="email" value="${document["email"]}"></p>

            <p class="w3-right">
                <input type="submit" class="w3-button w3-theme-l3 w3-hover-dark-grey" value="Update">
            </p>
        </form>
        </#if>
    </div>
</div>
</body>
</html>

