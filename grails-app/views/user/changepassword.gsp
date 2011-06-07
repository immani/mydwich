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

                        <g:if test="${userInstance.roles.name.contains('company')}">
                            <g:render template="companyinfo"/>
                        </g:if>
                        <g:if test="${userInstance.roles.name.contains('restaurant')}">
                            <g:render template="restaurantinfo"/>
                        </g:if>

                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="title"><g:message code="user.oldpassword.label" default="Old Password" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: userInstance, field: 'oldpassword', 'errors')}">
                                    <g:passwordField name="oldpassword" value="" />
                                </td>
                            </tr>
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="username"><g:message code="user.newpassword.label" default="New Password" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: userInstance, field: 'newpassword', 'errors')}">
                                    <g:passwordField name="newpassword" value="" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="firstname"><g:message code="user.newpassword.confirm" default="Confirm new Password" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: userInstance, field: 'newpasswordconfirm', 'errors')}">
                                    <g:passwordField name="newpasswordconfirm" value="" />
                                </td>
                            </tr>

                        </tbody>
                    </table>
                </div>
                <div class="buttons">
                    <span class="button"><g:actionSubmit class="cancel" action="cancel" value="${message(code: 'default.button.cancel.label', default: 'Cancel')}" /></span>
                    <span class="button"><g:actionSubmit class="save" action="changepassword" value="${message(code: 'default.button.submit.label', default: 'Submit')}" /></span>
                </div>
            </g:form>
        </div>
    </body>
</html>
