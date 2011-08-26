<%@ page import="com.immani.mydwich.ProdOption" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'prodOption.label', default: 'ProdOption')}" />
        <title><g:message code="default.create.label" args="[entityName]" /></title>
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
            <h1><g:message code="default.create.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
                <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${prodOptionInstance}">
                <div class="errors">
                    <g:renderErrors bean="${prodOptionInstance}" as="list" />
                </div>
            </g:hasErrors>
            <g:form action="save" >
                <div class="dialog">
                    <table>
                        <tbody>
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="restaurant"><g:message code="prodOptionCategory.restaurant.label" default="Restaurant" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: prodOptionCategoryInstance, field: 'restaurant', 'errors')}">
                                    ${prodOptionInstance?.prodOptionCategory?.restaurant?.encodeAsHTML()}
                                </td>
                            </tr>

                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="prodOptionCategory"><g:message code="prodOption.prodOptionCategory.label" default="Prod Option Category" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: prodOptionInstance, field: 'prodOptionCategory', 'errors')}">
                                    ${prodOptionInstance?.prodOptionCategory?.encodeAsHTML()}
                                    <g:hiddenField name="prodOptionCategory.id" value="${prodOptionInstance?.prodOptionCategory?.id}" />
                                </td>
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
                    <span class="button"><g:submitButton name="create" class="save" value="${message(code: 'default.button.create.label', default: 'Create')}" /></span>
                </div>
            </g:form>
        </div>
    </body>
</html>
