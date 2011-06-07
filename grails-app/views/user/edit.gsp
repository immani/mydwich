

<%@ page import="com.immani.mydwich.User" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <meta name="layout" content="main" />
    <g:set var="entityName" value="${message(code: 'user.label', default: 'User')}" />
    <title><g:message code="default.edit.label" args="[entityName]" /></title>
</head>
<body>
<div class="nav">
    <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
    <span class="menuButton"><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></span>
    <span class="menuButton"><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></span>
</div>
<div class="body">
    <h1><g:message code="default.edit.label" args="[entityName]" /></h1>
    <g:if test="${flash.message}">
        <div class="message">${flash.message}</div>
    </g:if>
    <g:hasErrors bean="${userInstance}">
        <div class="errors">
            <g:renderErrors bean="${userInstance}" as="list" />
        </div>
    </g:hasErrors>
    <g:form method="post" >
        <g:hiddenField name="id" value="${userInstance?.id}" />
        <g:hiddenField name="version" value="${userInstance?.version}" />
        <div class="dialog">
            <table>
                <tbody>

                <shiro:hasAnyRole in="['companyadmin','company']">
                    <g:render template="companyinfo"/>
                </shiro:hasAnyRole>

                <shiro:hasAnyRole in="['restaurantadmin','restaurant']">
                    <g:render template="restaurantinfo"/>
                </shiro:hasAnyRole>

                <tr class="prop">
                    <td valign="top" class="name">
                        <label for="title"><g:message code="user.title.label" default="Title" /></label>
                    </td>
                    <td valign="top" class="value ${hasErrors(bean: userInstance, field: 'title', 'errors')}">
                        <g:textField name="title" value="${userInstance?.title}" />
                    </td>
                </tr>
                <tr class="prop">
                    <td valign="top" class="name">
                        <label for="username"><g:message code="user.username.label" default="Username" /></label>
                    </td>
                    <td valign="top" class="value ${hasErrors(bean: userInstance, field: 'username', 'errors')}">
                        <g:textField name="username" value="${userInstance?.username}" />
                    </td>
                </tr>

                <tr class="prop">
                    <td valign="top" class="name">
                        <label for="firstname"><g:message code="user.firstname.label" default="Firstname" /></label>
                    </td>
                    <td valign="top" class="value ${hasErrors(bean: userInstance, field: 'firstname', 'errors')}">
                        <g:textField name="firstname" value="${userInstance?.firstname}" />
                    </td>
                </tr>

                <tr class="prop">
                    <td valign="top" class="name">
                        <label for="lastname"><g:message code="user.lastname.label" default="Lastname" /></label>
                    </td>
                    <td valign="top" class="value ${hasErrors(bean: userInstance, field: 'lastname', 'errors')}">
                        <g:textField name="lastname" value="${userInstance?.lastname}" />
                    </td>
                </tr>

                <tr class="prop">
                    <td valign="top" class="name">
                        <label for="mobile"><g:message code="user.mobile.label" default="Mobile" /></label>
                    </td>
                    <td valign="top" class="value ${hasErrors(bean: userInstance, field: 'mobile', 'errors')}">
                        <g:textField name="mobile" value="${userInstance?.mobile}" />
                    </td>
                </tr>

                <tr class="prop">
                    <td valign="top" class="name">
                        <label for="language"><g:message code="user.language.label" default="Language" /></label>
                    </td>
                    <td valign="top" class="value ${hasErrors(bean: userInstance, field: 'language', 'errors')}">
                        <g:textField name="language" value="${userInstance?.language}" />
                    </td>
                </tr>

                </tbody>
            </table>
        </div>
        <div class="buttons">
            <span class="button"><g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" /></span>
        </div>
    </g:form>
</div>
</body>
</html>
