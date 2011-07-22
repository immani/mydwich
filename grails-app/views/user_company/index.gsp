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
            <g:link controller="user" action="showuserprofile">Show Profile</g:link><br/>
            <g:link controller="user" action="list"><g:message code="user.list.label" /></g:link>
        </fieldset>
        <br />

        <fieldset>
            <legend>Payments</legend>
            <g:link controller="user_company" action="listuserpayment">List Payments</g:link><br/>
            <g:link controller="user_company" action="createuserpayment">Credit your account</g:link><br/>
        </fieldset>
        <br />

        <fieldset>
            <legend>Orders</legend>
            <g:link controller="user_company" action="listorders">List Orders</g:link><br/>
            <g:link controller="user_company" action="listpartnerrestaurant">Create New Order</g:link><br/>
        </fieldset>
        <br />
    </div>
</div>
</body>
</html>
