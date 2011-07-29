

<%@ page import="com.immani.mydwich.ProdOption" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'prodOption.label', default: 'ProdOption')}" />
        <title><g:message code="default.edit.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
            <span class="menuButton">
                <g:link class="list" controller="prodoption" action="listforprodoptioncategory" params="[prodoptcatid: prodOptionInstance.prodOptionCategory.id]">
                    <g:message code="default.list.label" args="[entityName]" /> for '${prodOptionInstance.prodOptionCategory}'
                </g:link>
            </span>
        </div>
        <div class="body">
            <h1><g:message code="default.edit.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
                <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${prodOptionInstance}">
                <div class="errors">
                    <g:renderErrors bean="${prodOptionInstance}" as="list" />
                </div>
            </g:hasErrors>
            <g:form method="post" >
                <g:hiddenField name="id" value="${prodOptionInstance?.id}" />
                <g:hiddenField name="version" value="${prodOptionInstance?.version}" />
                <div class="dialog">
                    <table>
                        <tbody>
                            <tr class="prop">
                                <td valign="top" class="name"><g:message code="prodOption.prodOptionCategory.label" default="Prod Option Category" /></td>
                                <td valign="top" class="value"><g:link controller="prodoptioncategory" action="show" id="${prodOptionInstance?.prodOptionCategory?.id}">${prodOptionInstance?.prodOptionCategory?.encodeAsHTML()}</g:link></td>
                            </tr>
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="name_fr"><g:message code="prodOption.name_fr.label" default="Namefr" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: prodOptionInstance, field: 'name_fr', 'errors')}">
                                    <g:textField name="name_fr" value="${prodOptionInstance?.name_fr}" />
                                </td>
                            </tr>

                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="name_nl"><g:message code="prodOption.name_nl.label" default="Namenl" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: prodOptionInstance, field: 'name_nl', 'errors')}">
                                    <g:textField name="name_nl" value="${prodOptionInstance?.name_nl}" />
                                </td>
                            </tr>

                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="name_en"><g:message code="prodOption.name_en.label" default="Nameen" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: prodOptionInstance, field: 'name_en', 'errors')}">
                                    <g:textField name="name_en" value="${prodOptionInstance?.name_en}" />
                                </td>
                            </tr>

                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="price"><g:message code="prodOption.price.label" default="Price" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: prodOptionInstance, field: 'price', 'errors')}">
                                    <g:textField name="price" value="${fieldValue(bean: prodOptionInstance, field: 'price')}" />
                                </td>
                            </tr>

                        </tbody>
                    </table>
                </div>
                <div class="buttons">
                    <span class="button"><g:actionSubmit class="save" action="update" value="${message(code: 'default.button.update.label', default: 'Update')}" /></span>
                    <span class="button"><g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" /></span>
                </div>
            </g:form>
        </div>
    </body>
</html>
