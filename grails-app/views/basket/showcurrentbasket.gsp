<%@ page import="com.immani.mydwich.Basket" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'basket.label', default: 'Basket')}" />
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
                        <th><g:message code="basketLine.product.label" default="Product" /></th>

                        <g:sortableColumn property="quantity" title="${message(code: 'basketLine.quantity.label', default: 'Quantity')}" />

                        <g:sortableColumn property="price" title="${message(code: 'basketLine.price.label', default: 'Price')}" />

                        <g:sortableColumn property="comment" title="${message(code: 'basketLine.comment.label', default: 'Comment')}" />



                    </tr>
                    </thead>
                    <tbody>
                    <g:each in="${basketLines}" status="i" var="basketLineInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">

                            <td><g:link action="show" id="${basketLineInstance.id}">${fieldValue(bean: basketLineInstance, field: "product")}</g:link></td>

                            <td>${fieldValue(bean: basketLineInstance, field: "quantity")}</td>

                            <td>${fieldValue(bean: basketLineInstance, field: "price")}</td>

                            <td>${fieldValue(bean: basketLineInstance, field: "comment")}</td>

                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
        </div>
    </body>
</html>
