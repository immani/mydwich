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
    <h1>Restaurant Admin Actions</h1>

    <div id="controllerList" class="dialog">
        <h2>Restaurant Action:</h2>
        <fieldset>
            <legend>Restaurant Profile</legend>
            <g:link controller="restaurant" action="showprofilerestaurant">Show Profile</g:link>
        </fieldset>
        <br />
        <fieldset>
            <legend>Users</legend>
            <g:link controller="user" action="list">List Users</g:link><br/>
            <g:link controller="user" action="create">New User</g:link>
        </fieldset>
        <br />
        <fieldset>
            <legend>Product Categories</legend>
            <g:link controller="productcategory" action="list">List Product Categories</g:link><br/>
        </fieldset>
        <br />
        <fieldset>
            <legend>Product Options Categories</legend>
            <g:link controller="prodoptioncategory" action="list">List Product Option Categories</g:link>
        </fieldset>
        <br />
        <fieldset>
            <legend>Product Options</legend>
            <g:link controller="prodoption" action="list">List Product Options</g:link>
        </fieldset>
    </div>
</div>

</body>
</html>
