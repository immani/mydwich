
<%@ page import="com.immani.mydwich.DeliveryAddress" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <meta name="layout" content="main" />
    <g:set var="entityName" value="${message(code: 'deliveryAddress.label', default: 'DeliveryAddress')}" />
    <title><g:message code="default.show.label" args="[entityName]" /></title>
</head>
<body>
<div class="nav">
    <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
    <span class="menuButton"><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></span>
    <span class="menuButton"><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></span>
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
                <td valign="top" class="name"><g:message code="deliveryAddress.company.label" default="Company" /></td>
                <td valign="top" class="value"><g:link controller="company" action="show">${deliveryAddressInstance?.company?.encodeAsHTML()}</g:link></td>
            </tr>

            <tr class="prop">
                <td valign="top" class="name"><g:message code="deliveryAddress.name.label" default="Name" /></td>
                <td valign="top" class="value">${fieldValue(bean: deliveryAddressInstance, field: "name")}</td>
            </tr>

            <tr class="prop">
                <td valign="top" class="name"><g:message code="deliveryAddress.address.label" default="Address" /></td>
                <td valign="top" class="value">${fieldValue(bean: deliveryAddressInstance, field: "address")}</td>
            </tr>

            <tr class="prop">
                <td valign="top" class="name"><g:message code="deliveryAddress.zip.label" default="Zip" /></td>
                <td valign="top" class="value">${fieldValue(bean: deliveryAddressInstance, field: "zip")}</td>
            </tr>

            <tr class="prop">
                <td valign="top" class="name"><g:message code="deliveryAddress.city.label" default="City" /></td>
                <td valign="top" class="value">${fieldValue(bean: deliveryAddressInstance, field: "city")}</td>
            </tr>

            <tr class="prop">
                <td valign="top" class="name"><g:message code="deliveryAddress.country.label" default="Country" /></td>
                <td valign="top" class="value">${fieldValue(bean: deliveryAddressInstance, field: "country")}</td>
            </tr>

            <tr class="prop">
                <td valign="top" class="name"><g:message code="deliveryAddress.latitude_longitude.label" default="Latitude/Longitude" /></td>
                <td valign="top" class="value">${fieldValue(bean: deliveryAddressInstance, field: "lat")}/${fieldValue(bean: deliveryAddressInstance, field: "lng")}</td>
            </tr>

            </tbody>
        </table>
    </div>
    <div class="buttons">
        <g:form>
            <g:hiddenField name="id" value="${deliveryAddressInstance?.id}" />
            <span class="button"><g:actionSubmit class="edit" action="edit" value="${message(code: 'default.button.edit.label', default: 'Edit')}" /></span>
            <span class="button"><g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" /></span>
        </g:form>
    </div>
    <div id="map_canvas" style="width:950px; height:400px"></div>
</div>
<script type="text/javascript">
    var contentString = '<div><h1 class="firstHeading">${deliveryAddressInstance.name}</h1><br />' +
            '${fieldValue(bean: deliveryAddressInstance, field: "address")}, ${fieldValue(bean: deliveryAddressInstance, field: "zip")} ${fieldValue(bean: deliveryAddressInstance, field: "city")}, ${fieldValue(bean: deliveryAddressInstance, field: "country")}</div>';

    var map = displaymap(${deliveryAddressInstance.lat}, ${deliveryAddressInstance.lng}, '${deliveryAddressInstance.name}', contentString, {icon: '/images/deliveryaddress.png'});
    displayrestaurantsnear(${deliveryAddressInstance.lat}, ${deliveryAddressInstance.lng}, {map: map});
</script>
</body>
</html>
