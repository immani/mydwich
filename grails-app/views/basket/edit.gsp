

<%@ page import="com.immani.mydwich.Basket" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'basket.label', default: 'Basket')}" />
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
            <g:hasErrors bean="${basketInstance}">
            <div class="errors">
                <g:renderErrors bean="${basketInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form method="post" >
                <g:hiddenField name="id" value="${basketInstance?.id}" />
                <g:hiddenField name="version" value="${basketInstance?.version}" />
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="basketline"><g:message code="basket.basketline.label" default="Basketline" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: basketInstance, field: 'basketline', 'errors')}">
                                    
                                <ul>
                                <g:each in="${basketInstance?.basketline?}" var="b">
                                    <li><g:link controller="basketline" action="show" id="${b.id}">${b?.encodeAsHTML()}</g:link></li>
                                </g:each>
                                </ul>
                                <g:link controller="basketline" action="create" params="['basket.id': basketInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'basketline.label', default: 'Basketline')])}</g:link>

                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="remark"><g:message code="basket.remark.label" default="Remark" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: basketInstance, field: 'remark', 'errors')}">
                                    <g:textField name="remark" value="${basketInstance?.remark}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="user"><g:message code="basket.user.label" default="User" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: basketInstance, field: 'user', 'errors')}">
                                    <g:select name="user.id" from="${com.immani.mydwich.User.list()}" optionKey="id" value="${basketInstance?.user?.id}"  />
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
