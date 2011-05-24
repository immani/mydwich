

<%@ page import="com.immani.mydwich.DeliveryAddress" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'deliveryAddress.label', default: 'DeliveryAddress')}" />
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
            <g:hasErrors bean="${deliveryAddressInstance}">
            <div class="errors">
                <g:renderErrors bean="${deliveryAddressInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form method="post" >
                <g:hiddenField name="id" value="${deliveryAddressInstance?.id}" />
                <g:hiddenField name="version" value="${deliveryAddressInstance?.version}" />
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="name"><g:message code="deliveryAddress.name.label" default="Name" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: deliveryAddressInstance, field: 'name', 'errors')}">
                                    <g:textField name="name" value="${deliveryAddressInstance?.name}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="address"><g:message code="deliveryAddress.address.label" default="Address" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: deliveryAddressInstance, field: 'address', 'errors')}">
                                    <g:textField name="address" value="${deliveryAddressInstance?.address}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="zip"><g:message code="deliveryAddress.zip.label" default="Zip" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: deliveryAddressInstance, field: 'zip', 'errors')}">
                                    <g:textField name="zip" value="${deliveryAddressInstance?.zip}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="city"><g:message code="deliveryAddress.city.label" default="City" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: deliveryAddressInstance, field: 'city', 'errors')}">
                                    <g:textField name="city" value="${deliveryAddressInstance?.city}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="country"><g:message code="deliveryAddress.country.label" default="Country" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: deliveryAddressInstance, field: 'country', 'errors')}">
                                    <g:textField name="country" value="${deliveryAddressInstance?.country}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="company"><g:message code="deliveryAddress.company.label" default="Company" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: deliveryAddressInstance, field: 'company', 'errors')}">
                                    <g:select name="company.id" from="${com.immani.mydwich.Company.list()}" optionKey="id" value="${deliveryAddressInstance?.company?.id}"  />
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
