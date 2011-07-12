
<%@ page import="com.immani.mydwich.User" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'user.label', default: 'User')}" />
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

                            <g:sortableColumn property="username" title="${message(code: 'user.username.label', default: 'Username')}" />
                        
                            <g:sortableColumn property="firstname" title="${message(code: 'user.firstname.label', default: 'Firstname')}" />
                        
                            <g:sortableColumn property="lastname" title="${message(code: 'user.lastname.label', default: 'Lastname')}" />
                        
                            <g:sortableColumn property="mobile" title="${message(code: 'user.mobile.label', default: 'Mobile')}" />
                        
                            <g:sortableColumn property="language" title="${message(code: 'user.language.label', default: 'Language')}" />

                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${userInstanceList}" status="i" var="userInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" id="${userInstance.id}">${fieldValue(bean: userInstance, field: "username")}</g:link></td>

                            <td>${fieldValue(bean: userInstance, field: "firstname")}</td>
                        
                            <td>${fieldValue(bean: userInstance, field: "lastname")}</td>
                        
                            <td>${fieldValue(bean: userInstance, field: "mobile")}</td>
                        
                            <td>${fieldValue(bean: userInstance, field: "language")}</td>

                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${userInstanceTotal}" />
            </div>
        </div>
    </body>
</html>
