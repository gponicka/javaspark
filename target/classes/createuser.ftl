<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="/customcss.css">
</head>

<body>

<div class="custom">
    <div class="w3-container" style="padding-left: 10px">
        <h2>Create a New User</h2>
        <p>Please provide the required information</p>
    </div>
    <#if saved==true>
        <div class="w3-card w3-panel w3-border w3-display-container">
            <i class="fa fa-check-square-o" style="font-size:100%; margin-top: 15px"></i>
            &nbsp;&nbsp;The user information has been successfully saved.
            <span class="w3-right w3-text-grey w3-hover-text-black" onclick="this.parentElement.style.display='none'" style="margin: 10px; cursor: default">&times;</span>
        </div>
    </#if>

    <div class="w3-card">
        <div class="w3-container w3-theme">
            <h3>New User Information</h3>
        </div>
        <form class="w3-container w3-text-theme" style="margin-bottom: 15px" method="post" action="/new-user">
            <p><label for="firstname">First Name</label>
            <input type="text" class="w3-input w3-border" name="firstname" id="firstname" required></p>

            <p><label for="lastname">Last Name</label>
            <input type="text" class="w3-input w3-border" name="lastname" id="lastname" required></p>

            <p><label for="age">Age</label>
            <input type="text" class="w3-input w3-border" name="age" id="age" required></p>

            <p><label for="gender">Gender</label>
            <select class="w3-select w3-border" name="gender" id="gender">
                <option value="Male">Male</option>
                <option value="Female">Female</option>
            </select></p>

            <p><label for="address">Address</label>
            <input type="text" class="w3-input w3-border" name="address" id="address" required></p>

            <p><label for="phone">Phone Number (optional)</label>
            <input type="text" class="w3-input w3-border" name="phone" id="phone"></p>

            <p><label for="email">Email Address (optional)</label>
            <input type="text" class="w3-input w3-border" name="email" id="email"></p>

            <p class="w3-right"><input type="submit" class="w3-button w3-theme-l3 w3-hover-dark-grey" value="Save"></p>
        </form>
    </div>
</div>
</body>
</html>