
<%@ page import="com.immani.mydwich.ProductCategory" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'productCategory.label', default: 'ProductCategory')}" />
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
                            <td valign="top" class="name"><g:message code="productCategory.restaurant.label" default="Restaurant" /></td>
                            <td valign="top" class="value">${fieldValue(bean: productCategoryInstance, field: "restaurant")}</td>
                        </tr>

                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="productCategory.name_fr.label" default="Namefr" /></td>
                            <td valign="top" class="value">${fieldValue(bean: productCategoryInstance, field: "name_fr")}</td>
                        </tr>

                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="productCategory.name_nl.label" default="Namenl" /></td>
                            <td valign="top" class="value">${fieldValue(bean: productCategoryInstance, field: "name_nl")}</td>
                        </tr>

                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="productCategory.name_en.label" default="Nameen" /></td>
                            <td valign="top" class="value">${fieldValue(bean: productCategoryInstance, field: "name_en")}</td>
                        </tr>

                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="productCategory.catorder.label" default="Category Order" /></td>
                            <td valign="top" class="value">${fieldValue(bean: productCategoryInstance, field: "catorder")}</td>
                        </tr>

                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="productCategory.prodOptionCategories.label" default="Prod Option Categories" /></td>
                            <td valign="top" style="text-align: left;" class="value">
                                <ul>
                                    <g:each in="${productCategoryInstance.prodOptionCategories}" var="p">
                                        <li><g:link controller="prodoptioncategory" action="show" id="${p.id}">${p?.encodeAsHTML()}</g:link></li>
                                    </g:each>
                                </ul>
                            </td>
                        </tr>

                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="productCategory.products.label" default="Products" /></td>
                            <td valign="top" style="text-align: left;" class="value">
                                <ul>
                                    <g:each in="${productCategoryInstance.products}" var="p">
                                        <li><g:link controller="anonymous_Product" action="show" id="${p.id}">${p?.encodeAsHTML()}</g:link></li>
                                    </g:each>
                                </ul>
                            </td>
                        </tr>
                    </tbody>
                </table>
            </div>
            <div class="buttons">
                <g:form>
                    <g:hiddenField name="id" value="${productCategoryInstance?.id}" />
                    <span class="button"><g:actionSubmit class="edit" action="edit" value="${message(code: 'default.button.edit.label', default: 'Edit')}" /></span>
                    <span class="button"><g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" /></span>
                </g:form>
            </div>
        </div>
    </body>
</html>