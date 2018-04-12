<!DOCTYPE html>
<html lang="en">
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="https://www.w3schools.com/lib/w3-theme-dark-grey.css">
</head>

<body>
<div class="w3-top">
    <div class="w3-bar w3-black w3-padding" id="nav">
        <a class="w3-bar-item w3-button w3-hide-small" href="/">Home</a>
        <a class="w3-bar-item w3-button w3-hide-small" href="/new-user">New User</a>
        <div class="w3-dropdown-hover w3-hide-small">
            <button class="w3-button" href="#">User List&nbsp;&nbsp;<i class="fa fa-chevron-down"></i></button>
            <div class="w3-dropdown-content w3-bar-block w3-card-4">
                <a class="w3-bar-item w3-button" href="/user-list">All Users</a>
                <a class="w3-bar-item w3-button" href="/search/Male">Male Users</a>
                <a class="w3-bar-item w3-button" href="/search/Female">Female Users</a>
                <a class="w3-bar-item w3-button" href=/search/group-by-lastname>Group By Last Name</a>
            </div>
        </div>
        <a href="javascript:void(0)" class="w3-bar-item w3-button w3-right w3-hide-large w3-hide-medium"
           onclick="myFunction()">&#9776;</a>

        <form action="/search" method="post" class="w3-right w3-hide-small">
            <button class="w3-bar-item w3-button w3-grey" type="submit" name="searchbtn" style="float: right">
                <i class="fa fa-search"></i></button>
            <div style="overflow: hidden;">
                <input placeholder="Search" class="w3-bar-item w3-input" type="text" name="search" style="width: 100%;" />
            </div>
        </form>

    </div>
    <div id="mobile" class="w3-padding w3-bar-block w3-black w3-hide w3-hide-large w3-hide-medium">
        <a class="w3-bar-item w3-button" href="/">Home</a>
        <a class="w3-bar-item w3-button" href="/new-user" >New User</a>
        <div class="w3-dropdown-hover">
            <button class="w3-button" href="#">User List&nbsp;&nbsp;<i class="fa fa-chevron-down"></i></button>
            <div class="w3-dropdown-content w3-bar-block w3-card-4">
                <a class="w3-bar-item w3-button" href="/user-list">All Users</a>
                <a class="w3-bar-item w3-button" href="/search/Male">Male Users</a>
                <a class="w3-bar-item w3-button" href="/search/Female">Female Users</a>
                <a class="w3-bar-item w3-button" href=/search/group-by-lastname>Group By Last Name</a>
            </div>
        </div>

        <form action="/mobile" method="post">
            <button class="w3-bar-item w3-button w3-grey" type="submit" name="searchbtn" style="float: right; width: 15%">
                <i class="fa fa-search"></i></button>
            <div style="overflow: hidden;">
                <input placeholder="Search" class="w3-bar-item w3-input" type="text" name="search2" style="width: 100%"/>
            </div>
        </form>
    </div>
</div>

<div class="w3-container">
    <#include "${templateName}">
</div>

<script>
    function myFunction() {
        var x = document.getElementById("mobile");
        if (x.className.indexOf("w3-show") == -1) {
            x.className += " w3-show";
        } else {
            x.className = x.className.replace(" w3-show", "");
        }
    }

    (function() {
        var nav = document.getElementById('nav'),
                anchor = nav.getElementsByTagName('a'),
                current = window.location;
        for (var i = 0; i < anchor.length; i++) {
            if(anchor[i].href == current) {
                anchor[i].className += " w3-gray";
            }
        }
    })();

    (function () {
        var gender = document.getElementById("genderVal").getAttribute("value");
        if(gender == "Male")
            document.getElementById("gender").value = "Male";
        else
            document.getElementById("gender").value = "Female";
    })();
</script>

</body>
</html>