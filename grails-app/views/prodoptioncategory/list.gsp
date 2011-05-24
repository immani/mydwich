
<%@ page import="com.immani.mydwich.ProdOptionCategory" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'prodOptionCategory.label', default: 'ProdOptionCategory')}" />
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
                        
                            <g:sortableColumn property="id" title="${message(code: 'prodOptionCategory.id.label', default: 'Id')}" />
                        
                            <g:sortableColumn property="name_fr" title="${message(code: 'prodOptionCategory.name_fr.label', default: 'Namefr')}" />
                        
                            <g:sortableColumn property="name_nl" title="${message(code: 'prodOptionCategory.name_nl.label', default: 'Namenl')}" />
                        
                            <g:sortableColumn property="name_en" title="${message(code: 'prodOptionCategory.name_en.label', default: 'Nameen')}" />
                        
                            <g:sortableColumn property="type" title="${message(code: 'prodOptionCategory.type.label', default: 'Type')}" />
                        
                            <th><g:message code="prodOptionCategory.restaurant.label" default="Restaurant" /></th>
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${prodOptionCategoryInstanceList}" status="i" var="prodOptionCategoryInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" id="${prodOptionCategoryInstance.id}">${fieldValue(bean: prodOptionCategoryInstance, field: "id")}</g:link></td>
                        
                            <td>${fieldValue(bean: prodOptionCategoryInstance, field: "name_fr")}</td>
                        
                            <td>${fieldValue(bean: prodOptionCategoryInstance, field: "name_nl")}</td>
                        
                            <td>${fieldValue(bean: prodOptionCategoryInstance, field: "name_en")}</td>
                        
                            <td>${fieldValue(bean: prodOptionCategoryInstance, field: "type")}</td>
                        
                            <td>${fieldValue(bean: prodOptionCategoryInstance, field: "restaurant")}</td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${prodOptionCategoryInstanceTotal}" />
            </div>
        </div>
    </body>
</html>
