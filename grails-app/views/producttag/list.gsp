
<%@ page import="com.immani.mydwich.ProductTag" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'productTag.label', default: 'ProductTag')}" />
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
                        
                            <g:sortableColumn property="id" title="${message(code: 'productTag.id.label', default: 'Id')}" />
                        
                            <g:sortableColumn property="name_fr" title="${message(code: 'productTag.name_fr.label', default: 'Namefr')}" />
                        
                            <g:sortableColumn property="name_nl" title="${message(code: 'productTag.name_nl.label', default: 'Namenl')}" />
                        
                            <g:sortableColumn property="name_en" title="${message(code: 'productTag.name_en.label', default: 'Nameen')}" />
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${productTagInstanceList}" status="i" var="productTagInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" id="${productTagInstance.id}">${fieldValue(bean: productTagInstance, field: "id")}</g:link></td>
                        
                            <td>${fieldValue(bean: productTagInstance, field: "name_fr")}</td>
                        
                            <td>${fieldValue(bean: productTagInstance, field: "name_nl")}</td>
                        
                            <td>${fieldValue(bean: productTagInstance, field: "name_en")}</td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${productTagInstanceTotal}" />
            </div>
        </div>
    </body>
</html>
