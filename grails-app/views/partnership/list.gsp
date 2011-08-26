
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
                            <g:sortableColumn property="comment" title="${message(code: 'partnership.comment.label', default: 'Comment')}" />
                            <th><g:message code="partnership.deliveryAddress.label" default="Delivery Address" /></th>
                            <g:sortableColumn property="isvalidated" title="${message(code: 'partnership.isvalidated.label', default: 'Isvalidated')}" />
                            <g:sortableColumn property="originator" title="${message(code: 'partnership.originator.label', default: 'Originator')}" />
                            <th><g:message code="partnership.restaurant.label" default="Restaurant" /></th>
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${partnershipInstanceList}" status="i" var="partnershipInstance">

                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" id="${partnershipInstance.id}">${fieldValue(bean: partnershipInstance, field: "id")}</g:link></td>
                            <td>${fieldValue(bean: partnershipInstance, field: "comment")}</td>
                            <td>${fieldValue(bean: partnershipInstance, field: "deliveryAddress")}</td>
                            <td><g:formatBoolean boolean="${partnershipInstance.isvalidated}" /></td>
                            <td>${fieldValue(bean: partnershipInstance, field: "originator")}</td>
                            <td>${fieldValue(bean: partnershipInstance, field: "restaurant")}</td>

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
