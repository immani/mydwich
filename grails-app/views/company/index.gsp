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
    <h1>Company Admin Actions</h1>

    <div id="controllerList" class="dialog">

        <fieldset>
            <legend>Company Profile</legend>
            <g:link controller="company" action="show">Show Profile</g:link>
        </fieldset>
        <br />

        <fieldset>
            <legend>Users</legend>
            <g:link controller="user">My Profile</g:link><br/>
            <g:link controller="user" action="list">List Users</g:link><br/>
            <g:link controller="user" action="create">New User</g:link><br/>
            <g:link controller="userpayment" action="listcompanyuserpayment">List Payments</g:link>
        </fieldset>
        <br />

        <fieldset>
            <legend>Delivery Addresses</legend>
            <g:link controller="deliveryaddress" action="list">List Delivery Address</g:link><br/>
            <g:link controller="deliveryaddress" action="create">New Delivery Address</g:link>
        </fieldset>
        <br />

        <fieldset>
            <legend>Orders</legend>
            <g:link controller="basket" action="listbycompany">List Orders</g:link>
        </fieldset>
        <br />
        <fieldset>
            <legend>Restaurants</legend>
            <g:link controller="restaurant" action="">List Partner Restaurants</g:link>
        </fieldset>
    </div>
</div>

</body>
</html>
