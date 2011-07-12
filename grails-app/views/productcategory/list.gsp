<%@ page import="com.immani.mydwich.ProductCategory" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'productCategory.label', default: 'ProductCategory')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
            <span class="menuButton"><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></span>
        </div>
        <div class="body">
            <h1><g:message code="default.list.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
                <div class="message">${flash.message}</div>
            </g:if>
            <div class="list">
                <table>
                    <thead>
                        <tr>
                            <th>&nbsp;</th>
                            <g:sortableColumn property="name_fr" title="${message(code: 'productCategory.name_fr.label', default: 'Name Fr')}" />
                            <g:sortableColumn property="name_nl" title="${message(code: 'productCategory.name_en.label', default: 'Name Nl')}" />
                            <g:sortableColumn property="name_en" title="${message(code: 'productCategory.name_nl.label', default: 'Name En')}" />
                            <g:sortableColumn property="catorder" title="${message(code: 'productCategory.catorder.label', default: 'Category Order')}" />
                            <td><g:message code="productCategory.prodOptionCategories.label" default="Prod Option Categories" /></td>
                        </tr>
                    </thead>
                    <tbody>
                        <g:each in="${productCategoryInstanceList}" status="i" var="productCategoryInstance">
                            <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                                <td><g:link action="show" id="${productCategoryInstance.id}">${message(code: 'productCategory.show.label', default: 'Show')}</g:link></td>
                                <td>${fieldValue(bean: productCategoryInstance, field: "name_fr")}</td>
                                <td>${fieldValue(bean: productCategoryInstance, field: "name_nl")}</td>
                                <td>${fieldValue(bean: productCategoryInstance, field: "name_en")}</td>
                                <td>${fieldValue(bean: productCategoryInstance, field: "catorder")}</td>
                                <td><mydwich:disarrayploc instanceValue="${productCategoryInstance?.prodOptionCategories}" property="name"/></td>
                            </tr>
                        </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${productCategoryInstanceTotal}" />
            </div>
        </div>
    </body>
</html>
