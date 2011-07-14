
<%@ page import="com.immani.mydwich.Restaurant" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <meta name="layout" content="main" />
    <g:set var="entityName" value="${message(code: 'restaurant.label', default: 'Restaurant')}" />
    <title><g:message code="default.show.label" args="[entityName]" /></title>
    <g:javascript src="jquery.galleriffic.js" />
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

                <td valign="top" class="value"><g:message code="country.${restaurantInstance.country}"/></td>

            </tr>

            <tr class="prop">
                <td valign="top" class="name"><g:message code="restaurant.vat.label" default="Vat" /></td>

                <td valign="top" class="value">${fieldValue(bean: restaurantInstance, field: "vat")}</td>

            </tr>

            <tr class="prop">
                <td valign="top" class="name"><g:message code="restaurant.phone.label" default="Phone" /></td>

                <td valign="top" class="value">${fieldValue(bean: restaurantInstance, field: "phone")}</td>

            </tr>

            <tr class="prop">
                <td valign="top" class="name"><g:message code="restaurant.fax.label" default="Fax" /></td>

                <td valign="top" class="value">${fieldValue(bean: restaurantInstance, field: "fax")}</td>

            </tr>

            <tr class="prop">
                <td valign="top" class="name"><g:message code="restaurant.desc.label" default="Description" /></td>

                <td valign="top" class="value"><mydwich:disploc instanceValue="${restaurantInstance}" property="desc" /></td>

            </tr>

           <tr class="prop">
                <td valign="top" class="name"><g:message code="restaurant.categories.label" default="Restaurant Categories" /></td>

                <td valign="top" style="text-align: left;" class="value">
                    <ul>
                        <g:each in="${restaurantInstance.restaurantcategories}" var="r">
                            <li>${r?.encodeAsHTML()}</li>
                        </g:each>
                    </ul>
                </td>

            </tr>

                        </tbody>
        </table>
        <div id="map_canvas" style="width:600px; height:400px"></div>

        <g:each in="${pictureInstanceList}" var="pic">
                <a href="${resource(dir:'restimages/' +pic.restaurant.name.toLowerCase().encodeAsURL(), file:pic.filename, absolute:'true')}">
                        <img src="${resource(dir:'restimages/' +pic.restaurant.name.toLowerCase().encodeAsURL(), file:'thumb_'+ pic.filename, absolute:'true')}" />
                </a>
        </g:each>

    </div>

</div>
<script type="text/javascript">
    var contentString = '<h1 class="firstHeading">${restaurantInstance.name}</h1><br /> ${restaurantInstance.address}, ${restaurantInstance.zip} ${restaurantInstance.city}, ${restaurantInstance.country}';
    displaymap(${restaurantInstance.lat}, ${restaurantInstance.lng}, '${restaurantInstance.name}', contentString, {icon: '/images/restaurant.png'})

</script>

</body>
</html>
