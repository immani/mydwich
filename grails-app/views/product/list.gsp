
<%@ page import="com.immani.mydwich.Product" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'product.label', default: 'Product')}" />
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

                            <g:sortableColumn property="id" title="${message(code: 'product.id.label', default: 'Id')}" />
                            <mydwich:sortloccol property="name" title="${message(code: 'product.name.label', default: 'Name')}" />
                            <mydwich:sortloccol property="desc" title="${message(code: 'product.desc.label', default: 'Description')}" />
                            <g:sortableColumn property="productCategories" title="${message(code: 'product.categories.label', default: 'Categories')}" />

                        </tr>
                    </thead>
                    <tbody>
                        <g:each in="${productInstanceList}" status="i" var="productInstance">
                            <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                                <td>
                                    <g:link action="show" id="${productInstance.id}">
                                        <g:if test="${productInstance?.picture}">

                                            <img width="50" height="50" src="${resource(dir:'restimages/' + productInstance?.restaurant.name.toLowerCase().encodeAsURL() + '/products', file:'thumb_'+ productInstance?.picture.filename, absolute:'true')}" />
                                        </g:if>
                                        <g:else>
                                            <img width="50" height="50" src="${resource(dir:'images/', file:'noimage.gif', absolute:'true')}" />
                                        </g:else>
                                    </g:link>
                                </td>

                                <td><g:link action="show" id="${productInstance.id}"><mydwich:disploc instanceValue="${productInstance}" property="name"/></g:link></td>
                                <td><mydwich:disploc instanceValue="${productInstance}" property="desc"/></td>
                                <td><mydwich:disarrayploc instanceValue="${productInstance.productCategories}" property="name"/></td>
                            </tr>
                        </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${productInstanceTotal}" />
            </div>
        </div>
    </body>
</html>
