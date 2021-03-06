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

        <fieldset>
            <legend>Restaurant Profile</legend>
            <g:link controller="restaurant" action="showprofilerestaurant">Show Restaurant Profile</g:link><br/>
            <g:link controller="picture" action="listrestaurantpictures">Show Restaurant Pictures</g:link>
        </fieldset>
        <br />
        <fieldset>
            <legend>Users</legend>
            <g:link controller="user" action="showuserprofile">My Profile</g:link><br/>
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
            <legend>Products</legend>
            <g:link controller="product" action="list">List</g:link><br/>
            <g:link controller="product" action="listfull">List Full</g:link><br/>
            <g:link controller="product" action="listbycategory">List by Category</g:link>
        </fieldset>
        <br />
        <fieldset>
            <legend>Product Options Categories</legend>
            <g:link controller="prodoptioncategory" action="list">List Product Option Categories</g:link>
        </fieldset>
        <br />
        <fieldset>
            <legend>Companies</legend>
            <g:link controller="partnership" action="restListValidatedPartnerships">List validated Partnerships</g:link><br/>
            <g:link controller="partnership" action="restListRequestedPartnerships">List requested Partnerships</g:link><br/>
            <g:link controller="partnership" action="restListWaitingPartnerships">List waiting Partnerships</g:link><br/>
            <g:link controller="restaurant" action="listdeliveryaddressinrange">Create New Partnership</g:link>
        </fieldset>
    </div>
</div>

</body>
</html>
