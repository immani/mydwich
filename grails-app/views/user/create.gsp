<%@ page import="com.immani.mydwich.User" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <meta name="layout" content="main" />
    <g:set var="entityName" value="${message(code: 'user.label', default: 'User')}" />
    <title><g:message code="default.create.label" args="[entityName]" /></title>
</head>
<body>
<div class="nav">
    <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
    <span class="menuButton"><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></span>
</div>
<div class="body">
    <h1><g:message code="default.create.label" args="[entityName]" /></h1>
    <g:if test="${flash.message}">
        <div class="message">${flash.message}</div>
    </g:if>
    <g:hasErrors bean="${userInstance}">
        <div class="errors">
            <g:renderErrors bean="${userInstance}" as="list" />
        </div>
    </g:hasErrors>
    <g:form action="save" >
        <div class="dialog">
            <table>
                <tbody>

                <shiro:hasRole name="company">
                    <g:render template="companyinfo"/>
                </shiro:hasRole>

                <shiro:hasRole name="restaurant">
                    <g:render template="restaurantinfo"/>
                </shiro:hasRole>


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

                <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="passwordHash"><g:message code="user.passwordHash.label" default="Password Hash" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: userInstance, field: 'passwordHash', 'errors')}">
                                    <g:textField name="passwordHash" value="${userInstance?.passwordHash}" />
                                </td>
                            </tr>


                </tbody>
            </table>
        </div>
        <div class="buttons">
            <span class="button"><g:submitButton name="create" class="save" value="${message(code: 'default.button.create.label', default: 'Create')}" /></span>
        </div>
    </g:form>
</div>
</body>
</html>
