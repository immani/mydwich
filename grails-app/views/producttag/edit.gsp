

<%@ page import="com.immani.mydwich.ProductTag" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'productTag.label', default: 'ProductTag')}" />
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
            <g:hasErrors bean="${productTagInstance}">
            <div class="errors">
                <g:renderErrors bean="${productTagInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form method="post" >
                <g:hiddenField name="id" value="${productTagInstance?.id}" />
                <g:hiddenField name="version" value="${productTagInstance?.version}" />
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="name_fr"><g:message code="productTag.name_fr.label" default="Namefr" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: productTagInstance, field: 'name_fr', 'errors')}">
                                    <g:textField name="name_fr" value="${productTagInstance?.name_fr}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="name_nl"><g:message code="productTag.name_nl.label" default="Namenl" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: productTagInstance, field: 'name_nl', 'errors')}">
                                    <g:textField name="name_nl" value="${productTagInstance?.name_nl}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="name_en"><g:message code="productTag.name_en.label" default="Nameen" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: productTagInstance, field: 'name_en', 'errors')}">
                                    <g:textField name="name_en" value="${productTagInstance?.name_en}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="products"><g:message code="productTag.products.label" default="Products" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: productTagInstance, field: 'products', 'errors')}">
                                    
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
