
<%@ page import="com.immani.mydwich.Restaurant" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <meta name="layout" content="main" />
    <g:set var="entityName" value="${message(code: 'restaurant.label', default: 'Restaurant')}" />
    <title><g:message code="default.show.label" args="[entityName]" /></title>
</head>
<body>
<div class="nav">
    <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
</div>
<div class="body">
    <h1><g:message code="default.show.label" args="[entityName]" /></h1>
    <g:if test="${flash.message}">
        <div class="message">${flash.message}</div>
    </g:if>
    <div class="dialog">
        <table>
            <tbody>

            <tr class="prop">
                <td valign="top" class="name"><g:message code="restaurant.name.label" default="Name" /></td>
                <td valign="top" class="value">${fieldValue(bean: restaurantInstance, field: "name")}</td>
            </tr>

            <tr class="prop">
                <td valign="top" class="name"><g:message code="restaurant.address.label" default="Address" /></td>
                <td valign="top" class="value">${fieldValue(bean: restaurantInstance, field: "address")}</td>
            </tr>

            <tr class="prop">
                <td valign="top" class="name"><g:message code="restaurant.zip.label" default="Zip" /></td>
                <td valign="top" class="value">${fieldValue(bean: restaurantInstance, field: "zip")}</td>
            </tr>

            <tr class="prop">
                <td valign="top" class="name"><g:message code="restaurant.city.label" default="City" /></td>
                <td valign="top" class="value">${fieldValue(bean: restaurantInstance, field: "city")}</td>
            </tr>

            <tr class="prop">
                <td valign="top" class="name"><g:message code="restaurant.country.label" default="Country" /></td>
                <td valign="top" class="value">${fieldValue(bean: restaurantInstance, field: "country")}</td>
            </tr>

            </tbody>
        </table>
        <div id="map_canvas" style="width:800px; height:600px"></div>
    </div>
</div>
<script type="text/javascript">
    var contentString = '<h1 class="firstHeading">${restaurantInstance.name}</h1><br /> ${restaurantInstance.address}, ${restaurantInstance.zip} ${restaurantInstance.city}, ${restaurantInstance.country}';
    displaymap(${restaurantInstance.lat}, ${restaurantInstance.lng}, '${restaurantInstance.name}', contentString, {icon: '/images/restaurant.png'})

</script>

</body>
</html>
