<%@ page import="com.immani.mydwich.Restaurant" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <meta name="layout" content="main" />
    <g:set var="entityName" value="${message(code: 'restaurant.label', default: 'Restaurant')}" />
    <title><g:message code="default.create.label" args="[entityName]" /></title>
</head>
<body>
<div class="nav">
    <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
    <span class="menuButton"><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></span>
</div>
<div class="body">
    <h1><g:message code="default.create.label" args="[entityName]" /></h1>
    <g:if test="${flash.message}">
        <div class="message">${flash.message}</div>
    </g:if>
    <g:hasErrors bean="${restaurantInstance}">
        <div class="errors">
            <g:renderErrors bean="${restaurantInstance}" as="list" />
        </div>
    </g:hasErrors>
    <g:form url="[controller:'registration', action:'registerrestaurant']" >
        <div class="dialog">
            <table>
                <tbody>

                <tr class="prop">
                    <td valign="top" class="name">
                        <label for="name"><g:message code="restaurant.name.label" default="Name" /></label>
                    </td>
                    <td valign="top" class="value ${hasErrors(bean: restaurantInstance, field: 'name', 'errors')}">
                        <g:textField name="name" value="${restaurantInstance?.name}" />
                    </td>
                </tr>

                <tr class="prop">
                    <td valign="top" class="name">
                        <label for="address"><g:message code="restaurant.address.label" default="Address" /></label>
                    </td>
                    <td valign="top" class="value ${hasErrors(bean: restaurantInstance, field: 'address', 'errors')}">
                        <g:textField name="address" value="${restaurantInstance?.address}" />
                    </td>
                </tr>

                <tr class="prop">
                    <td valign="top" class="name">
                        <label for="zip"><g:message code="restaurant.zip.label" default="Zip" /></label>
                    </td>
                    <td valign="top" class="value ${hasErrors(bean: restaurantInstance, field: 'zip', 'errors')}">
                        <g:textField name="zip" value="${restaurantInstance?.zip}" />
                    </td>
                </tr>

                <tr class="prop">
                    <td valign="top" class="name">
                        <label for="city"><g:message code="restaurant.city.label" default="City" /></label>
                    </td>
                    <td valign="top" class="value ${hasErrors(bean: restaurantInstance, field: 'city', 'errors')}">
                        <g:textField name="city" value="${restaurantInstance?.city}" />
                    </td>
                </tr>

                <tr class="prop">
                    <td valign="top" class="name">
                        <label for="country"><g:message code="restaurant.country.label" default="Country" /></label>
                    </td>
                    <td valign="top" class="value ${hasErrors(bean: restaurantInstance, field: 'country', 'errors')}">
                        <g:textField name="country" value="${restaurantInstance?.country}" />
                    </td>
                </tr>

                <tr class="prop">
                    <td valign="top" class="name">
                        <label for="vat"><g:message code="restaurant.vat.label" default="Vat" /></label>
                    </td>
                    <td valign="top" class="value ${hasErrors(bean: restaurantInstance, field: 'vat', 'errors')}">
                        <g:textField name="vat" value="${restaurantInstance?.vat}" />
                    </td>
                </tr>

                <tr class="prop">
                    <td valign="top" class="name">
                        <label for="phone"><g:message code="restaurant.phone.label" default="Phone" /></label>
                    </td>
                    <td valign="top" class="value ${hasErrors(bean: restaurantInstance, field: 'phone', 'errors')}">
                        <g:textField name="phone" value="${restaurantInstance?.phone}" />
                    </td>
                </tr>

                <tr class="prop">
                    <td valign="top" class="name">
                        <label for="fax"><g:message code="restaurant.fax.label" default="Fax" /></label>
                    </td>
                    <td valign="top" class="value ${hasErrors(bean: restaurantInstance, field: 'fax', 'errors')}">
                        <g:textField name="fax" value="${restaurantInstance?.fax}" />
                    </td>
                </tr>

                <tr class="prop">
                    <td valign="top" class="name">
                        <label for="desc_fr"><g:message code="restaurant.desc_fr.label" default="Description" /></label>
                    </td>
                    <td valign="top" class="value ${hasErrors(bean: restaurantInstance, field: 'desc_fr', 'errors')}">
                        <g:textField name="desc_fr" value="${restaurantInstance?.desc_fr}" />
                    </td>
                </tr>

                <tr class="prop">
                    <td valign="top" class="name">
                        <label for="desc_nl"><g:message code="restaurant.desc_nl.label" default="Description" /></label>
                    </td>
                    <td valign="top" class="value ${hasErrors(bean: restaurantInstance, field: 'desc_nl', 'errors')}">
                        <g:textField name="desc_nl" value="${restaurantInstance?.desc_nl}" />
                    </td>
                </tr>

                <tr class="prop">
                    <td valign="top" class="name">
                        <label for="desc_en"><g:message code="restaurant.desc_en.label" default="Description" /></label>
                    </td>
                    <td valign="top" class="value ${hasErrors(bean: restaurantInstance, field: 'desc_en', 'errors')}">
                        <g:textField name="desc_en" value="${restaurantInstance?.desc_en}" />
                    </td>
                </tr>


                <tr class="prop">
                    <td valign="top" class="name">
                        <label for="restaurantcategories"><g:message code="product.restaurant.label" default="Restaurant categories" /></label>
                    </td>
                    <td valign="top">
                        <g:select name="restaurantcategories" from="${com.immani.mydwich.RestaurantCategory.list()}" multiple="yes" optionKey="id" />
                    </td>
                </tr>

                </tbody>
            </table>
        </div>
        <div class="buttons">
            <span class="button"><g:submitButton name="next" class="next" value="${message(code: 'default.button.next.label', default: 'Next')}" /></span>
            <span class="button"><g:submitButton name="cancel" class="cancel" value="${message(code: 'default.button.cancel.label', default: 'cCancel')}" /></span>
        </div>
    </g:form>
</div>
</body>
</html>
