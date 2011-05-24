

<%@ page import="com.immani.mydwich.ProdOptionCategory" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'prodOptionCategory.label', default: 'ProdOptionCategory')}" />
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
            <g:hasErrors bean="${prodOptionCategoryInstance}">
            <div class="errors">
                <g:renderErrors bean="${prodOptionCategoryInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form action="save" >
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="name_fr"><g:message code="prodOptionCategory.name_fr.label" default="Namefr" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: prodOptionCategoryInstance, field: 'name_fr', 'errors')}">
                                    <g:textField name="name_fr" value="${prodOptionCategoryInstance?.name_fr}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="name_nl"><g:message code="prodOptionCategory.name_nl.label" default="Namenl" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: prodOptionCategoryInstance, field: 'name_nl', 'errors')}">
                                    <g:textField name="name_nl" value="${prodOptionCategoryInstance?.name_nl}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="name_en"><g:message code="prodOptionCategory.name_en.label" default="Nameen" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: prodOptionCategoryInstance, field: 'name_en', 'errors')}">
                                    <g:textField name="name_en" value="${prodOptionCategoryInstance?.name_en}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="type"><g:message code="prodOptionCategory.type.label" default="Type" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: prodOptionCategoryInstance, field: 'type', 'errors')}">
                                    <g:select name="type" from="${prodOptionCategoryInstance.constraints.type.inList}" value="${prodOptionCategoryInstance?.type}" valueMessagePrefix="prodOptionCategory.type"  />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="restaurant"><g:message code="prodOptionCategory.restaurant.label" default="Restaurant" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: prodOptionCategoryInstance, field: 'restaurant', 'errors')}">
                                    <g:select name="restaurant.id" from="${com.immani.mydwich.Restaurant.list()}" optionKey="id" value="${prodOptionCategoryInstance?.restaurant?.id}"  />
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
