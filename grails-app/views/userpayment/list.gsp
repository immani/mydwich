
<%@ page import="com.immani.mydwich.Userpayment" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'userpayment.label', default: 'Userpayment')}" />
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
                        
                            <g:sortableColumn property="id" title="${message(code: 'userpayment.id.label', default: 'Id')}" />
                        
                            <th><g:message code="userpayment.user.label" default="User" /></th>
                        
                            <g:sortableColumn property="date" title="${message(code: 'userpayment.date.label', default: 'Date')}" />
                        
                            <g:sortableColumn property="remark" title="${message(code: 'userpayment.remark.label', default: 'Remark')}" />
                        
                            <g:sortableColumn property="amount" title="${message(code: 'userpayment.amount.label', default: 'Amount')}" />
                        
                            <g:sortableColumn property="type" title="${message(code: 'userpayment.type.label', default: 'Type')}" />
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${userpaymentInstanceList}" status="i" var="userpaymentInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" id="${userpaymentInstance.id}">${fieldValue(bean: userpaymentInstance, field: "id")}</g:link></td>
                        
                            <td>${fieldValue(bean: userpaymentInstance, field: "user")}</td>
                        
                            <td><g:formatDate date="${userpaymentInstance.date}" /></td>
                        
                            <td>${fieldValue(bean: userpaymentInstance, field: "remark")}</td>
                        
                            <td>${fieldValue(bean: userpaymentInstance, field: "amount")}</td>
                        
                            <td>${fieldValue(bean: userpaymentInstance, field: "type")}</td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${userpaymentInstanceTotal}" />
            </div>
        </div>
    </body>
</html>
