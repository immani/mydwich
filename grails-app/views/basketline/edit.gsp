

<%@ page import="com.immani.mydwich.BasketLine" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'basketLine.label', default: 'BasketLine')}" />
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
            <g:hasErrors bean="${basketLineInstance}">
            <div class="errors">
                <g:renderErrors bean="${basketLineInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form method="post" >
                <g:hiddenField name="id" value="${basketLineInstance?.id}" />
                <g:hiddenField name="version" value="${basketLineInstance?.version}" />
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="quantity"><g:message code="basketLine.quantity.label" default="Quantity" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: basketLineInstance, field: 'quantity', 'errors')}">
                                    <g:textField name="quantity" value="${fieldValue(bean: basketLineInstance, field: 'quantity')}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="price"><g:message code="basketLine.price.label" default="Price" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: basketLineInstance, field: 'price', 'errors')}">
                                    <g:textField name="price" value="${fieldValue(bean: basketLineInstance, field: 'price')}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="prodOptions"><g:message code="basketLine.prodOptions.label" default="Prod Options" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: basketLineInstance, field: 'prodOptions', 'errors')}">
                                    <g:select name="prodOptions" from="${com.immani.mydwich.ProdOption.list()}" multiple="yes" optionKey="id" size="5" value="${basketLineInstance?.prodOptions*.id}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="comment"><g:message code="basketLine.comment.label" default="Comment" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: basketLineInstance, field: 'comment', 'errors')}">
                                    <g:textField name="comment" value="${basketLineInstance?.comment}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="product"><g:message code="basketLine.product.label" default="Product" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: basketLineInstance, field: 'product', 'errors')}">
                                    <g:select name="product.id" from="${com.immani.mydwich.Product.list()}" optionKey="id" value="${basketLineInstance?.product?.id}"  />
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
