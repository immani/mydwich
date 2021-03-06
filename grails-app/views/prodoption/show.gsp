
<%@ page import="com.immani.mydwich.ProdOption" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'prodOption.label', default: 'ProdOption')}" />
        <title><g:message code="default.show.label" args="[entityName]" /></title>
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
            <h1><g:message code="default.show.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
                <div class="message">${flash.message}</div>
            </g:if>
            <div class="dialog">
                <table>
                    <tbody>
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="prodOption.prodOptionCategory.label" default="Prod Option Category" /></td>
                            <td valign="top" class="value"><g:link controller="prodoptioncategory" action="show" id="${prodOptionInstance?.prodOptionCategory?.id}">${prodOptionInstance?.prodOptionCategory?.encodeAsHTML()}</g:link></td>
                        </tr>

                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="prodOption.name_fr.label" default="Namefr" /></td>
                            <td valign="top" class="value">${fieldValue(bean: prodOptionInstance, field: "name_fr")}</td>
                        </tr>

                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="prodOption.name_nl.label" default="Namenl" /></td>
                            <td valign="top" class="value">${fieldValue(bean: prodOptionInstance, field: "name_nl")}</td>
                        </tr>

                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="prodOption.name_en.label" default="Nameen" /></td>
                            <td valign="top" class="value">${fieldValue(bean: prodOptionInstance, field: "name_en")}</td>
                        </tr>

                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="prodOption.price.label" default="Price" /></td>
                            <td valign="top" class="value">${fieldValue(bean: prodOptionInstance, field: "price")}</td>
                        </tr>
                    </tbody>
                </table>
            </div>
            <div class="buttons">
                <g:form>
                    <g:hiddenField name="id" value="${prodOptionInstance?.id}" />
                    <span class="button"><g:actionSubmit class="edit" action="edit" value="${message(code: 'default.button.edit.label', default: 'Edit')}" /></span>
                    <span class="button"><g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" /></span>
                </g:form>
            </div>
        </div>
    </body>
</html>
