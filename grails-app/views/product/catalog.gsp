<%@ page import="com.immani.mydwich.Product" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <meta name="layout" content="main" />
    <g:set var="entityName" value="${message(code: 'product.label', default: 'Product')}" />
    <title><g:message code="default.list.label" args="[entityName]" /></title>
</head>
<body>
<div id="nav">
    &nbsp;
</div>
<div id="pageBody">

    <div class="body">
        <h1><g:message code="default.list.label" args="[entityName]" /> for restaurant ${fieldValue(bean: restaurantInstance, field: "name")}</h1>
        <div id="searchBox"> Instant Search: <g:remoteField name="searchBox" update="restaurantlist" paramName="q" url="[controller:'restaurant', action:'search']" before="checksearchquery(this.value)" /></div>

        <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
        </g:if>
        <h1><g:select name="productcategory" from="${restaurantInstance.productsCategories}" value="${productcategory?.id}" optionKey="id" noSelection="${['null':'']}" onchange="location.href=location.pathname + '?productcategory=' + this.value;"></g:select></h1>
                <div class="list">
            <table>
                <thead>
                <tr>
                    <g:sortableColumn property="name_${lg}" title="${message(code: 'product.name.label', default: 'Name')}" />
                    <g:sortableColumn property="desc_${lg}" title="${message(code: 'product.desc.label', default: 'Descen')}" />
                    <g:sortableColumn property="price" title="${message(code: 'product.price.label', default: 'Price')}" />
                    <td>&nbsp</td>
                </tr>
                </thead>
                <tbody>
                <g:each in="${productcategoriesInstanceList}" status="n" var="productcategoryInstance">
                    <g:if test="${productcategoryInstance.products.size() > 0}">
                        <tr class="odd">
                            <td colspan="7">${fieldValue(bean: productcategoryInstance, field: "name_fr")}</td>
                        </tr>
                        <g:each in="${productcategoryInstance.products}" status="i" var="productInstance">
                            <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
                                <td><g:link action="show" id="${productInstance.id}">${fieldValue(bean: productInstance, field: "name_" + lg)}</g:link></td>
                                <td>${fieldValue(bean: productInstance, field: "desc_" + lg)}</td>
                                <td>${fieldValue(bean: productInstance, field: "price")} €</td>
                                <td><g:link controller="basket" action="selectproductpptions" id="${productInstance.id}" onclick="displayproductbuydialog(this.href.toString()); return false;">Add</g:link></td>
                            </tr>
                        </g:each>
                    </g:if>
                </g:each>
                </tbody>
            </table>
        </div>
    </div>
</div>
<script type="text/javascript">refreshbasket()</script>
<div id="proddialog">&nbsp;</div>
</body>
</html>
