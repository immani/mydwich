
<%@ page import="com.immani.mydwich.Product" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <meta name="layout" content="main" />
    <g:set var="entityName" value="${message(code: 'product.label', default: 'Product')}" />
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
                <td valign="top" class="name"><g:message code="product.restaurant.label" default="Restaurant" /></td>
                <td valign="top" class="value">${productInstance?.restaurant?.encodeAsHTML()}</td>
            </tr>

            <tr class="prop">
                <td valign="top" class="name"><g:message code="product.name.label" default="Name" /></td>
                <td valign="top" class="value"><mydwich:disploc instanceValue="${productInstance}" property="name" /></td>
            </tr>

            <tr class="prop">
                <td valign="top" class="name"><g:message code="product.desc.label" default="Description" /></td>
                <td valign="top" class="value"><mydwich:disploc instanceValue="${productInstance}" property="desc" /></td>
            </tr>

            <tr class="prop">
                <td valign="top" class="name"><g:message code="product.price.label" default="Price" /></td>
                <td valign="top" class="value">${fieldValue(bean: productInstance, field: "price")}</td>
            </tr>

            <tr class="prop">
                <td valign="top" class="name"><g:message code="product.productCategories.label" default="Product Categories" /></td>

                <td valign="top" style="text-align: left;" class="value">
                    <ul>
                        <g:each in="${productInstance.productCategories}" var="p">
                            <li><g:link controller="productCategory" action="show" id="${p.id}">${p?.encodeAsHTML()}</g:link></li>
                        </g:each>
                    </ul>
                </td>

            </tr>

            <tr class="prop">
                <td valign="top" class="name"><g:message code="product.productTags.label" default="Product Tags" /></td>

                <td valign="top" style="text-align: left;" class="value">
                    <ul>
                        <g:each in="${productInstance.productTags}" var="p">
                            <li><g:link controller="productTag" action="show" id="${p.id}">${p?.encodeAsHTML()}</g:link></li>
                        </g:each>
                    </ul>
                </td>

            </tr>

            </tbody>
        </table>
        <g:if test="${pictureInstance != null}" >
            <a href="${resource(dir:'restimages/' +productInstance.restaurant.name.toLowerCase().encodeAsURL() +'/products', file:pictureInstance.filename, absolute:'true')}">
                <img src="${resource(dir:'restimages/' +productInstance.restaurant.name.toLowerCase().encodeAsURL()+'/products', file:'thumb_'+ pictureInstance.filename, absolute:'true')}" />
            </a>
        </g:if>

    </div>
    <div class="buttons">
        <g:form>
            <g:hiddenField name="id" value="${productInstance?.id}" />
            <span class="button"><g:actionSubmit class="edit" action="edit" value="${message(code: 'default.button.edit.label', default: 'Edit')}" /></span>
            <span class="button"><g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" /></span>
        </g:form>
    </div>
</div>
</body>
</html>
