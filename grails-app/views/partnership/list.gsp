
<%@ page import="com.immani.mydwich.Partnership" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'partnership.label', default: 'Partnership')}" />
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
                        
                            <g:sortableColumn property="id" title="${message(code: 'partnership.id.label', default: 'Id')}" />
                        
                            <th><g:message code="partnership.company.label" default="Company" /></th>
                        
                            <g:sortableColumn property="companyisvalidated" title="${message(code: 'partnership.companyisvalidated.label', default: 'Companyisvalidated')}" />
                        
                            <th><g:message code="partnership.restaurant.label" default="Restaurant" /></th>
                        
                            <g:sortableColumn property="restaurantisvalidated" title="${message(code: 'partnership.restaurantisvalidated.label', default: 'Restaurantisvalidated')}" />
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${partnershipInstanceList}" status="i" var="partnershipInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" id="${partnershipInstance.id}">${fieldValue(bean: partnershipInstance, field: "id")}</g:link></td>
                        
                            <td>${fieldValue(bean: partnershipInstance, field: "company")}</td>
                        
                            <td><g:formatBoolean boolean="${partnershipInstance.companyisvalidated}" /></td>
                        
                            <td>${fieldValue(bean: partnershipInstance, field: "restaurant")}</td>
                        
                            <td><g:formatBoolean boolean="${partnershipInstance.restaurantisvalidated}" /></td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${partnershipInstanceTotal}" />
            </div>
        </div>
    </body>
</html>
