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
            <span class="menuButton"><g:link class="create" action="showrestaurantcatalog" id="${session.basket.restaurant.id}"><g:message code="basket.continue.order"/></g:link></span>
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
                            <g:sortableColumn property="quantity" title="${message(code: 'basketLine.quantity.label', default: 'Quantity')}" />
                            <g:sortableColumn property="product.label" title="${message(code: 'basketLine.product.label', default: 'Product')}" />
                            <g:sortableColumn property="product.price" title="${message(code: 'basketLine.product.unitprice', default: 'Unit Price')}" />
                            <g:sortableColumn property="price" title="${message(code: 'basketLine.price.label', default: 'Price')}" />
                            <th>Remove</th>
                            <g:sortableColumn property="comment" title="${message(code: 'basketLine.comment.label', default: 'Options/Comment')}" />
                        </tr>
                    </thead>
                    <tbody>
                        <g:each in="${basketLines}" status="i" var="basketLineInstance">
                            <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">

                                <td>${fieldValue(bean: basketLineInstance, field: "quantity")}</td>
                                <td><mydwich:disploc instanceValue="${basketLineInstance.product}" property="name" /></td>
                                <td>${fieldValue(bean: basketLineInstance.product, field: "price")} €</td>
                                <td>${fieldValue(bean: basketLineInstance, field: "price")} €</td>
                                <td><g:link controller="user_company" action="removeline" id="${i}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');">remove</g:link></td>
                                <td>
                                    ${fieldValue(bean: basketLineInstance, field: "comment")} <br/>
                                    <g:each in="${basketLineInstance.prodOptions}" status="j" var="prodOption">
                                        <g:if test="${prodOption}">
                                            <mydwich:disploc instanceValue="${prodOption}" property="name" /> (${prodOption.price} €),
                                        </g:if>
                                    </g:each>
                                </td>
                            </tr>
                        </g:each>
                    </tbody>
                </table>

                <h1>${basketInstance? basketInstance.totalnbofarticles : 0} articles - Total: ${basketInstance? basketInstance.totalprice : 0 } €</h1></h1>
            </div>
        </div>
    </body>
</html>
