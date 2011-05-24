

<%@ page import="com.immani.mydwich.Userpayment" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'userpayment.label', default: 'Userpayment')}" />
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
            <g:hasErrors bean="${userpaymentInstance}">
            <div class="errors">
                <g:renderErrors bean="${userpaymentInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form action="save" >
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="user"><g:message code="userpayment.user.label" default="User" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: userpaymentInstance, field: 'user', 'errors')}">
                                    <g:hiddenField type="hidden" name="user.id" value="${userpaymentInstance?.user?.id}" />
                                    ${userpaymentInstance?.user?.encodeAsHTML()}
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="date"><g:message code="userpayment.date.label" default="Date" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: userpaymentInstance, field: 'date', 'errors')}">
                                    <g:datePicker name="date" precision="day" value="${userpaymentInstance?.date}"  />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="remark"><g:message code="userpayment.remark.label" default="Remark" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: userpaymentInstance, field: 'remark', 'errors')}">
                                    <g:textField name="remark" value="${userpaymentInstance?.remark}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="amount"><g:message code="userpayment.amount.label" default="Amount" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: userpaymentInstance, field: 'amount', 'errors')}">
                                    <g:textField name="amount" value="${fieldValue(bean: userpaymentInstance, field: 'amount')}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="type"><g:message code="userpayment.type.label" default="Type" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: userpaymentInstance, field: 'type', 'errors')}">
                                    <g:select name="type" from="${userpaymentInstance.constraints.type.inList}" value="${userpaymentInstance?.type}" valueMessagePrefix="userpayment.type"  />
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
