<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="/customcss.css">
</head>

<body>

<div class="custom">
    <#if document??>
    <div class="w3-container" style="padding-left: 10px">
        <h2>Hi, <span class="w3-text-gray">${document["firstname"]} </span></h2>
        <p>Below is your user information. Please check if the data are all correct.</p>
    </div>

    <div class="w3-card">
        <div class="w3-container w3-theme">
            <h3>User Information</h3>
        </div>
        <div class="w3-container w3-text-theme" style="margin-bottom: 15px">
            <p><h3 class="w3-border-bottom">${document["firstname"]}</h3>
            <label>First Name</label>
            </p>
            <p><h3 class="w3-border-bottom">${document["lastname"]}</h3>
            <label>Last Name</label>
            </p>
            <p><h3 class="w3-border-bottom">${document["age"]}</h3>
            <label>Age</label>
            </p>
            <p><h3 class="w3-border-bottom">${document["gender"]}</h3>
            <label>Gender</label>
            </p>
            <p><h3 class="w3-border-bottom">${document["address"]}</h3>
            <label>Address</label>
            </p>
            <p><h3 class="w3-border-bottom">${document["phone"]}</h3>
            <label>Phone No.</label>
            </p>
            <p><h3 class="w3-border-bottom">${document["email"]}</h3>
            <label>Email Address</label>
            </p>
            <p class="w3-right">
                <button onclick="document.getElementById('confirmDelete').style.display='block'" class="w3-button w3-theme-l3 w3-hover-dark-grey">Delete</button>
                <a href="/update-user/${document["_id"]}">
                    <button class="w3-button w3-theme-l3 w3-hover-dark-grey">Edit</button>
                </a></p>
        </div>

    </div>
        <div class="w3-modal" id="confirmDelete">
            <div class="w3-modal-content">
                <header class="w3-container w3-theme">
                    <span onclick="document.getElementById('confirmDelete').style.display='none'"
                          class="w3-button w3-display-topright">&times;</span>
                    <h4><i class="fa fa-trash"></i> Confirm Delete</h4>
                </header>
                <div class="w3-container">
                    <p>Would you like to remove ${document["firstname"]} from the list of users?</p>
                </div>
                <div class="w3-container" style="padding-bottom: 15px">
                    <p class="w3-right">
                        <a href="/profile/deleted/${document["_id"]}" style="text-decoration: none">
                            <button class="w3-button w3-theme-l3 w3-hover-dark-grey">OK</button>
                        </a>
                        <button class="w3-button w3-theme-l3 w3-hover-dark-grey"
                                onclick="document.getElementById('confirmDelete').style.display='none'">Cancel</button>
                    </p>
                </div>
            </div>
        </div>
    <#else>
        <div class="w3-container" style="padding-left: 10px">
            <h2>User Not Found</h2>
            <p>The user you are trying to look for was probably removed from the system.</p>
            <a href="/user-list" style="text-decoration: none">
                <button class="w3-button w3-theme-l3 w3-hover-dark-grey">Go to All Users <i class="fa fa-angle-right"></i></button>
            </a>
        </div>
    </#if>
</div>
</body>
</html>