<%@ page import="com.immani.mydwich.Company" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <meta name="layout" content="main" />
    <g:set var="entityName" value="${message(code: 'company.label', default: 'Company')}" />
    <title><g:message code="default.create.label" args="[entityName]" /></title>
</head>
<body>
<div class="nav">
    <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
    <span class="menuButton"><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></span>
</div>
<div class="body">
    <h1><g:message code="default.create.label" args="[entityName]" /></h1>
    <g:if test="${error}">
        <div class="errors"><ul><li>${error}</li></ul></div>
    </g:if>
    <g:hasErrors bean="${companyInstance}">
        <div class="errors">
            <g:renderErrors bean="${companyInstance}" as="list" />
        </div>
    </g:hasErrors>
    <g:form url="[controller:'registration', action:'registercompany']">
        <div class="dialog">
            <table>
                <tbody>

                <tr class="prop">
                    <td valign="top" class="name">
                        <g:message code="company.name.label" default="Name" />
                    </td>
                    <td valign="top" class="value">
                        ${companyInstance?.name}
                    </td>
                </tr>

                <tr class="prop">
                    <td valign="top" class="name">
                        <g:message code="company.domain.label" default="Domain" />
                    </td>
                    <td valign="top" class="value">
                        ${companyInstance?.domain}
                    </td>
                </tr>

                <tr class="prop">
                    <td valign="top" class="name">
                        <g:message code="company.address.label" default="Address" />
                    </td>
                    <td valign="top" class="value">
                        ${companyInstance?.address}
                    </td>
                </tr>

                <tr class="prop">
                    <td valign="top" class="name">
                        <g:message code="company.zip.label" default="Zip" />
                    </td>
                    <td valign="top" class="value">
                        ${companyInstance?.zip}
                    </td>
                </tr>

                <tr class="prop">
                    <td valign="top" class="name">
                        <g:message code="company.city.label" default="City" />
                    </td>
                    <td valign="top" class="value">
                        ${companyInstance?.city}
                    </td>
                </tr>

                <tr class="prop">
                    <td valign="top" class="name">
                        <g:message code="company.country.label" default="Country" />
                    </td>
                    <td valign="top" class="value">
                        ${companyInstance?.country}
                    </td>
                </tr>

                <tr class="prop">
                    <td valign="top" class="name">
                        <g:message code="company.vat.label" default="Vat" />
                    </td>
                    <td valign="top" class="value">
                        ${companyInstance?.vat}"
                    </td>
                </tr>

                <tr class="prop">
                    <td valign="top" class="name">
                        <g:message code="company.phone.label" default="Phone" />
                    </td>
                    <td valign="top" class="value">
                        ${companyInstance?.phone}"
                    </td>
                </tr>

                <tr class="prop">
                    <td valign="top" class="name"><g:message code="company.fax.label" default="Fax" /></td>
                    <td valign="top" class="value">${companyInstance?.fax}"</td>
                </tr>
                </tbody>
            </table>
            <div id="comap_canvas" style="width:600px; height:300px"></div>
            <script type="text/javascript">
                var contentString = '<div><h1 class="firstHeading">${fieldValue(bean: companyInstance, field: "name")}</h1><br />' +
                        '${fieldValue(bean: companyInstance, field: "address")}, ${fieldValue(bean: companyInstance, field: "zip")} ${fieldValue(bean: companyInstance, field: "city")}, ${fieldValue(bean: companyInstance, field: "country")}</div>';

                var map = displaymap(${fieldValue(bean: companyInstance, field: "lat")}, ${fieldValue(bean: companyInstance, field: "lng")}, '${fieldValue(bean: companyInstance, field: "name")}', contentString, {mapid:'comap_canvas'});
            </script>

            <table>
                <tbody>
                <!--User Instance review-->
                <tr class="prop">
                    <td valign="top" class="name"><g:message code="user.username.label" default="Username" /></td>
                    <td valign="top" class="value">${fieldValue(bean: userInstance, field: "username")}</td>
                </tr>

                <tr class="prop">
                    <td valign="top" class="name"><g:message code="user.sex.label" default="Sex" /></td>
                    <td valign="top" class="value">${fieldValue(bean: userInstance, field: "sex")}</td>
                </tr>

                <tr class="prop">
                    <td valign="top" class="name"><g:message code="user.firstname.label" default="Firstname" /></td>
                    <td valign="top" class="value">${fieldValue(bean: userInstance, field: "firstname")}</td>
                </tr>

                <tr class="prop">
                    <td valign="top" class="name"><g:message code="user.lastname.label" default="Lastname" /></td>
                    <td valign="top" class="value">${fieldValue(bean: userInstance, field: "lastname")}</td>
                </tr>

                <tr class="prop">
                    <td valign="top" class="name"><g:message code="user.mobile.label" default="Mobile" /></td>
                    <td valign="top" class="value">${fieldValue(bean: userInstance, field: "mobile")}</td>
                </tr>

                <tr class="prop">
                    <td valign="top" class="name"><g:message code="user.language.label" default="Language" /></td>
                    <td valign="top" class="value">${fieldValue(bean: userInstance, field: "language")}</td>
                </tr>

                <tr class="prop">
                    <td valign="top" class="name"><g:message code="user.permissions.label" default="Permissions" /></td>
                    <td valign="top" class="value">${fieldValue(bean: userInstance, field: "permissions")}</td>
                </tr>
                </tbody>
            </table>


            <table>
                <tbody>
                <!--Delivery Address Review-->
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
                </tbody>
            </table>
            <div id="damap_canvas" style="width:600px; height:300px"></div>
            <script type="text/javascript">
                var contentString = '<div><h1 class="firstHeading">${fieldValue(bean: deliveryAddressInstance, field: "name")}</h1><br />' +
                        '${fieldValue(bean: deliveryAddressInstance, field: "address")}, ${fieldValue(bean: deliveryAddressInstance, field: "zip")} ${fieldValue(bean: deliveryAddressInstance, field: "city")}, ${fieldValue(bean: deliveryAddressInstance, field: "country")}</div>';

                var map = displaymap(${fieldValue(bean: deliveryAddressInstance, field: "lat")}, ${fieldValue(bean: deliveryAddressInstance, field: "lng")}, '${fieldValue(bean: deliveryAddressInstance, field: "name")}', contentString,{mapid:'damap_canvas', icon: 'images/deliveryaddress.png'});
            </script>
        </div>
        <div class="buttons">
            <span class="button"><g:submitButton name="back" class="back" value="${message(code: 'default.button.back.label', default: 'Back')}" /></span>
            <span class="button"><g:submitButton name="confirm" class="next" value="${message(code: 'default.button.confirm.label', default: 'Confirm')}" /></span>
            <span class="button"><g:submitButton name="cancel" class="cancel" value="${message(code: 'default.button.cancel.label', default: 'Cancel')}" /></span>
        </div>
    </g:form>
</div>
</body>
</html>
