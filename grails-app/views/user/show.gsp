
<%@ page import="com.immani.mydwich.User" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <meta name="layout" content="main" />
    <g:set var="entityName" value="${message(code: 'user.label', default: 'User')}" />
    <title><g:message code="default.show.label" args="[entityName]" /></title>
</head>
<body>
<div class="nav">
    <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
    <span class="menuButton"><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></span>
    <span class="menuButton"><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></span>
</div>
<div class="body">
    <h1><g:message code="default.show.label" args="[entityName]" /></h1>
    <g:if test="${flash.message}">
        <div class="message">${flash.message}</div>
    </g:if>
    <div class="dialog">
        <table>
            <tbody>

            <g:if test="${userInstance.roles.name.contains('company')}">
                <g:render template="companyinfo"/>
            </g:if>
            <g:if test="${userInstance.roles.name.contains('restaurant')}">
                <g:render template="restaurantinfo"/>
            </g:if>

            <tr class="prop">
                <td valign="top" class="name"><g:message code="user.username.label" default="Username" /></td>

                <td valign="top" class="value">${fieldValue(bean: userInstance, field: "username")}</td>

            </tr>

            <tr class="prop">
                <td valign="top" class="name"><g:message code="user.firstname.label" default="Firstname" /></td>

                <td valign="top" class="value">${fieldValue(bean: userInstance, field: "firstname")}</td>

            </tr>

            <tr class="prop">
                <td valign="top" class="name"><g:message code="user.lastname.label" default="Lastname" /></td>

                <td valign="top" class="value">${fieldValue(bean: userInstance, field: "lastname")}</td>

            </tr>

            <tr class="prop">
                <td valign="top" class="name"><g:message code="user.sex.label" default="Sex" /></td>

                <td valign="top" class="value"><g:message code="user.sex.${userInstance.sex}"/></td>

            </tr>
            <tr class="prop">
                <td valign="top" class="name"><g:message code="user.security.label" default="Security" /></td>

                <td valign="top" class="value"><g:link controller="user" action="changepasswordinit"><g:message code="user.changepassword.label" default="Change Password" /></g:link> </td>

            </tr>

            <tr class="prop">
                <td valign="top" class="name"><g:message code="user.mobile.label" default="Mobile" /></td>

                <td valign="top" class="value">${fieldValue(bean: userInstance, field: "mobile")}</td>

            </tr>

            <tr class="prop">
                <td valign="top" class="name"><g:message code="user.language.label" default="Language" /></td>

                <td valign="top" class="value">${fieldValue(bean: userInstance, field: "language")}</td>

            </tr>

            <tr class="prop">
                <td valign="top" class="name"><g:message code="user.permissions.label" default="Permissions" /></td>

                <td valign="top" class="value">${fieldValue(bean: userInstance, field: "permissions")}</td>

            </tr>

            <tr class="prop">
                <td valign="top" class="name"><g:message code="user.roles.label" default="Roles" /></td>

                <td valign="top" style="text-align: left;" class="value">
                    <ul>
                        <g:each in="${userInstance.roles}" var="r">
                            <li>${r?.encodeAsHTML()}</li>
                        </g:each>
                    </ul>
                </td>

            </tr>

            </tbody>
        </table>
    </div>
    <div class="buttons">
        <g:form>
            <g:hiddenField name="id" value="${userInstance?.id}" />
            <span class="button"><g:actionSubmit class="edit" action="edit" value="${message(code: 'default.button.edit.label', default: 'Edit')}" /></span>
            <span class="button"><g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" /></span>
        </g:form>
    </div>
</div>
</body>
</html>
