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
                <div id="searchBox">
                    Instant Search: <g:remoteField id="search" name="searchBox" update="productlist" paramName="q" url="[controller:'user_company', action:'search']" />
                    <span onclick="$('#search').val('')">reset</span>
                </div>

                <g:if test="${flash.message}">
                    <div class="message">${flash.message}</div>
                </g:if>

                <g:select name="productcategory" from="${productcategoriesInstanceList}" value="${productcategory?.id}" optionKey="id" noSelection="${['all':'all']}"
                          onchange="${remoteFunction(controller:'user_company', action:'showproductlist', id:restaurantInstance.id, params:'\'productcategory=\' + this.value', update:'productlist')}"></g:select>

                <g:render template="productlist"/>
            </div>
        </div>
        <div id="proddialog">&nbsp;</div>
        <script type="text/javascript">refreshbasket()</script>
    </body>
</html>
