
<%@ page import="com.immani.mydwich.Product" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'product.label', default: 'Product')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>
        <style type="text/css" media="screen">

        #nav {
            margin-top:20px;
            margin-left:30px;
            width:228px;
            float:left;

        }
        .homePagePanel * {
            margin:0px;
        }
        .homePagePanel .panelBody ul {
            list-style-type:none;
            margin-bottom:10px;
        }
        .homePagePanel .panelBody h1 {
            text-transform:uppercase;
            font-size:1.1em;
            margin-bottom:10px;
        }

        .homePagePanel .panelBody {
            background: url(${resource(dir:'images',file:'leftnav_midstretch.png')}) repeat-y scroll center top transparent;

            margin:0px;
            padding:15px;
        }
        .homePagePanel .panelBtm {
            background: url(${resource(dir:'images',file:'leftnav_btm.png')}) no-repeat top;
            height:20px;
            margin:0px;
        }

        .homePagePanel .panelTop {
            background: url(${resource(dir:'images',file:'leftnav_top.png')}) no-repeat top;
            height:11px;
            margin:0px;
        }
        h2 {
            margin-top:15px;
            margin-bottom:15px;
            font-size:1.2em;
        }
        #pageBody {
            margin-left:280px;
            margin-right:20px;
        }
        </style>
    </head>
    <body>
        <div id="nav">
            Empty basket
        </div>
        <div id="pageBody">

            <div class="body">
                <h1><g:message code="default.list.label" args="[entityName]" /> for restaurant ${fieldValue(bean: restaurantInstance, field: "name")}</h1>
                <h1><g:select name="productcategory" from="${restaurantInstance.productsCategories}" value="id" noSelection="${['null':'']}" onchange="displaycatalogcategory(this.value)"></g:select></h1>
                <g:if test="${flash.message}">
                <div class="message">${flash.message}</div>
                </g:if>
                <div class="list">
                    <table>
                        <thead>
                            <tr>
                                <g:sortableColumn property="id" title="${message(code: 'product.id.label', default: 'Id')}" />
                                <g:sortableColumn property="name_fr" title="${message(code: 'product.name_fr.label', default: 'Namefr')}" />
                                <g:sortableColumn property="name_nl" title="${message(code: 'product.name_nl.label', default: 'Namenl')}" />
                                <g:sortableColumn property="name_en" title="${message(code: 'product.name_en.label', default: 'Nameen')}" />
                                <g:sortableColumn property="price" title="${message(code: 'product.price.label', default: 'Price')}" />
                                <g:sortableColumn property="desc_en" title="${message(code: 'product.desc_en.label', default: 'Descen')}" />
                                <td>&nbsp</td>
                            </tr>
                        </thead>
                        <tbody>
                        <g:each in="${productInstanceList}" status="i" var="productInstance">
                            <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                                <td><g:link action="show" id="${productInstance.id}">${fieldValue(bean: productInstance, field: "id")}</g:link></td>
                                <td>${fieldValue(bean: productInstance, field: "name_fr")}</td>
                                <td>${fieldValue(bean: productInstance, field: "name_nl")}</td>
                                <td>${fieldValue(bean: productInstance, field: "name_en")}</td>
                                <td>${fieldValue(bean: productInstance, field: "price")} €</td>
                                <td>${fieldValue(bean: productInstance, field: "desc_en")}</td>
                                <td>${fieldValue(bean: productInstance, field: "desc_en")}</td>
                                <td><g:link controller="basket" action="selectproductpptions" id="${productInstance.id}" onclick="displayproductbuydialog(this.href.toString()); return false;">Add</g:link></td>

                            </tr>
                        </g:each>
                        </tbody>
                    </table>
                </div>
                <div class="paginateButtons">
                    <g:paginate total="${productInstanceTotal}" />
                </div>
            </div>
        </div>
        <script type="text/javascript">refreshbasket()</script>
        <div id="proddialog">&nbsp;</div>
    </body>
</html>
