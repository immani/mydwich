

<%@ page import="com.immani.mydwich.Partnership" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <meta name="layout" content="main" />
    <g:set var="entityName" value="${message(code: 'partnership.label', default: 'Partnership')}" />
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
    <g:hasErrors bean="${partnershipInstance}">
        <div class="errors">
            <g:renderErrors bean="${partnershipInstance}" as="list" />
        </div>
    </g:hasErrors>
    <g:form action="savePartnership" >
        <div class="dialog">
            <table>
                <tbody>

                <tr class="prop">
                    <td valign="top" class="name">
                        <label for="restaurant"><g:message code="partnership.restaurant.label" default="Restaurant" /></label>
                    </td>
                    <td valign="top" class="value ${hasErrors(bean: partnershipInstance, field: 'restaurant', 'errors')}">
                        ${partnershipInstance?.restaurant?.encodeAsHTML()}
                         <g:hiddenField name="restaurantid" value="${partnershipInstance?.restaurant?.id}"/>
                    </td>
                </tr>

                <tr class="prop">
                    <td valign="top" class="name">
                        <label for="deliveryAddress"><g:message code="partnership.deliveryAddress.label" default="deliveryAddress" /></label>
                    </td>
                    <td valign="top" class="value ${hasErrors(bean: partnershipInstance, field: 'deliveryAddress', 'errors')}">
                        ${partnershipInstance?.deliveryAddress?.encodeAsHTML()}
                        <g:hiddenField name="deliveryaddressid" value="${partnershipInstance?.deliveryAddress?.id}"/>
                    </td>
                </tr>

                <tr class="prop">
                    <td valign="top" class="name">
                        <label for="comment"><g:message code="partnership.comment.label" default="Comment" /></label>
                    </td>
                    <td valign="top" class="value ${hasErrors(bean: partnershipInstance, field: 'comment', 'errors')}">
                        <g:textField name="comment" value="${partnershipInstance?.comment?.encodeAsHTML()}" />
                    </td>
                </tr>

                 <g:hiddenField name="originator" value="${partnershipInstance?.originator}"/>

                </tbody>
            </table>
        </div>
        <div class="buttons">
            <span class="button"><g:submitButton name="submit" class="submit" value="${message(code: 'default.button.submit.label', default: 'Submit')}" /></span>
        </div>
    </g:form>
</div>
</body>
</html>
