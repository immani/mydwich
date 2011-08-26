<html>
<head>
    <title>Welcome to Grails</title>
    <meta name="layout" content="main" />

</head>
<body>
<div id="nav">
    <div class="homePagePanel">
        <div class="panelTop"></div>
        <div class="panelBody">
            <h1><g:message code="default.list.label" args="[entityName]" /></h1>
            <ul>
                <li>App version: <g:meta name="app.version"></g:meta></li>
                <li>Grails version: <g:meta name="app.grails.version"></g:meta></li>

            </ul>
        </div>
        <div class="panelBtm"></div>
    </div>
</div>
<div id="pageBody">
    <h1>User Actions</h1>

    <div id="controllerList" class="dialog">
        <fieldset>
            <legend>User Profile</legend>
            <a href="/mydwich/user/profile/showuserprofile" class="user_action show_profile">Show Profile</a>
            <a href="/mydwich/user/profile/list" class="user_action user_list">User List</a>
        </fieldset>
        <br />

        <fieldset>
            <legend>Payments</legend>
            <a href="/mydwich/user/listuserpayment" class="user_action payments_list">List Payments</a>
            <a href="/mydwich/user/createuserpayment" class="user_action credit_account">Credit your account</a>
        </fieldset>
        <br />

        <fieldset>
            <legend>Orders</legend>
            <a href="/mydwich/user/listorders" class="user_action orders_list">List Orders</a>
            <a href="/mydwich/user/listpartnerrestaurant" class="user_action new_order">Create New Order</a>
        </fieldset>
        <br />
    </div>
</div>
</div>
</body>
</html>
