

<%@ page import="com.immani.mydwich.Partnership" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'partnership.label', default: 'Partnership')}" />
        <title><g:message code="default.edit.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
            <span class="menuButton"><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></span>
            <span class="menuButton"><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></span>
        </div>
        <div class="body">
            <h1><g:message code="default.edit.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${partnershipInstance}">
            <div class="errors">
                <g:renderErrors bean="${partnershipInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form method="post" >
                <g:hiddenField name="id" value="${partnershipInstance?.id}" />
                <g:hiddenField name="version" value="${partnershipInstance?.version}" />
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="company"><g:message code="partnership.company.label" default="Company" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: partnershipInstance, field: 'company', 'errors')}">
                                    <g:select name="company.id" from="${com.immani.mydwich.Company.list()}" optionKey="id" value="${partnershipInstance?.company?.id}"  />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="companyisvalidated"><g:message code="partnership.companyisvalidated.label" default="Companyisvalidated" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: partnershipInstance, field: 'companyisvalidated', 'errors')}">
                                    <g:checkBox name="companyisvalidated" value="${partnershipInstance?.companyisvalidated}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="restaurant"><g:message code="partnership.restaurant.label" default="Restaurant" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: partnershipInstance, field: 'restaurant', 'errors')}">
                                    <g:select name="restaurant.id" from="${com.immani.mydwich.Restaurant.list()}" optionKey="id" value="${partnershipInstance?.restaurant?.id}"  />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="restaurantisvalidated"><g:message code="partnership.restaurantisvalidated.label" default="Restaurantisvalidated" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: partnershipInstance, field: 'restaurantisvalidated', 'errors')}">
                                    <g:checkBox name="restaurantisvalidated" value="${partnershipInstance?.restaurantisvalidated}" />
                                </td>
                            </tr>
                        
                        </tbody>
                    </table>
                </div>
                <div class="buttons">
                    <span class="button"><g:actionSubmit class="save" action="update" value="${message(code: 'default.button.update.label', default: 'Update')}" /></span>
                    <span class="button"><g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" /></span>
                </div>
            </g:form>
        </div>
    </body>
</html>
