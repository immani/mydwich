

<%@ page import="com.immani.mydwich.Product" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <meta name="layout" content="main" />
    <g:set var="entityName" value="${message(code: 'product.label', default: 'Product')}" />
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
    <g:hasErrors bean="${productInstance}">
        <div class="errors">
            <g:renderErrors bean="${productInstance}" as="list" />
        </div>
    </g:hasErrors>
    <g:form method="post" >
        <g:hiddenField name="id" value="${productInstance?.id}" />
        <g:hiddenField name="version" value="${productInstance?.version}" />
        <div class="dialog">
            <table>
                <tbody>

                <tr class="prop">
                    <td valign="top" class="name">
                        <label for="name_fr"><g:message code="product.name_fr.label" default="Namefr" /></label>
                    </td>
                    <td valign="top" class="value ${hasErrors(bean: productInstance, field: 'name_fr', 'errors')}">
                        <g:textField name="name_fr" value="${productInstance?.name_fr}" />
                    </td>
                </tr>

                <tr class="prop">
                    <td valign="top" class="name">
                        <label for="name_nl"><g:message code="product.name_nl.label" default="Namenl" /></label>
                    </td>
                    <td valign="top" class="value ${hasErrors(bean: productInstance, field: 'name_nl', 'errors')}">
                        <g:textField name="name_nl" value="${productInstance?.name_nl}" />
                    </td>
                </tr>

                <tr class="prop">
                    <td valign="top" class="name">
                        <label for="name_en"><g:message code="product.name_en.label" default="Nameen" /></label>
                    </td>
                    <td valign="top" class="value ${hasErrors(bean: productInstance, field: 'name_en', 'errors')}">
                        <g:textField name="name_en" value="${productInstance?.name_en}" />
                    </td>
                </tr>

                <tr class="prop">
                    <td valign="top" class="name">
                        <label for="desc_fr"><g:message code="product.desc_fr.label" default="Descfr" /></label>
                    </td>
                    <td valign="top" class="value ${hasErrors(bean: productInstance, field: 'desc_fr', 'errors')}">
                        <g:textField name="desc_fr" value="${productInstance?.desc_fr}" />
                    </td>
                </tr>

                <tr class="prop">
                    <td valign="top" class="name">
                        <label for="desc_nl"><g:message code="product.desc_nl.label" default="Descnl" /></label>
                    </td>
                    <td valign="top" class="value ${hasErrors(bean: productInstance, field: 'desc_nl', 'errors')}">
                        <g:textField name="desc_nl" value="${productInstance?.desc_nl}" />
                    </td>
                </tr>

                <tr class="prop">
                    <td valign="top" class="name">
                        <label for="desc_en"><g:message code="product.desc_en.label" default="Descen" /></label>
                    </td>
                    <td valign="top" class="value ${hasErrors(bean: productInstance, field: 'desc_en', 'errors')}">
                        <g:textField name="desc_en" value="${productInstance?.desc_en}" />
                    </td>
                </tr>

                <tr class="prop">
                    <td valign="top" class="name">
                        <label for="price"><g:message code="product.price.label" default="Price" /></label>
                    </td>
                    <td valign="top" class="value ${hasErrors(bean: productInstance, field: 'price', 'errors')}">
                        <g:textField name="price" value="${fieldValue(bean: productInstance, field: 'price')}" />
                    </td>
                </tr>

                <tr class="prop">
                    <td valign="top" class="name">
                        <label for="productCategories"><g:message code="product.productCategories.label" default="Product Categories" /></label>
                    </td>
                    <td valign="top" class="value ${hasErrors(bean: productInstance, field: 'productCategories', 'errors')}">
                        <g:select name="productCategories" from="${productInstance.restaurant.productsCategories}" multiple="yes" optionKey="id" size="5" value="${productInstance?.productCategories*.id}" />
                    </td>
                </tr>

                <tr class="prop">
                    <td valign="top" class="name">
                        <label for="productTags"><g:message code="product.productTags.label" default="Product Tags" /></label>
                    </td>
                    <td valign="top" class="value ${hasErrors(bean: productInstance, field: 'productTags', 'errors')}">
                        <g:select name="productTags" from="${com.immani.mydwich.ProductTag.list()}" multiple="yes" optionKey="id" size="5" value="${productInstance?.productTags*.id}" />
                    </td>
                </tr>

                <tr class="prop">
                    <td valign="top" class="name">
                        <label for="restaurant"><g:message code="product.restaurant.label" default="Restaurant" /></label>
                    </td>
                    <td valign="top" class="value ${hasErrors(bean: productInstance, field: 'restaurant', 'errors')}">
                        <g:select name="restaurant.id" from="${com.immani.mydwich.Restaurant.list()}" optionKey="id" value="${productInstance?.restaurant?.id}"  />
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
