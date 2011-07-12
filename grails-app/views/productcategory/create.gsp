<%@ page import="com.immani.mydwich.ProductCategory" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'productCategory.label', default: 'ProductCategory')}" />
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
            <g:hasErrors bean="${productCategoryInstance}">
                <div class="errors">
                    <g:renderErrors bean="${productCategoryInstance}" as="list" />
                </div>
            </g:hasErrors>
            <g:form action="save" >
                <div class="dialog">
                    <table>
                        <tbody>
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="restaurant"><g:message code="productCategory.restaurant.label" default="Restaurant" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: productCategoryInstance, field: 'restaurant', 'errors')}">
                                    ${productCategoryInstance?.restaurant?.encodeAsHTML()}
                                    <g:hiddenField name="restaurant.id" value="${productCategoryInstance?.restaurant?.id}" />
                                </td>
                            </tr>

                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="name_fr"><g:message code="productCategory.name_fr.label" default="Namefr" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: productCategoryInstance, field: 'name_fr', 'errors')}">
                                    <g:textField name="name_fr" value="${productCategoryInstance?.name_fr}" />
                                </td>
                            </tr>

                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="name_nl"><g:message code="productCategory.name_nl.label" default="Namenl" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: productCategoryInstance, field: 'name_nl', 'errors')}">
                                    <g:textField name="name_nl" value="${productCategoryInstance?.name_nl}" />
                                </td>
                            </tr>

                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="name_en"><g:message code="productCategory.name_en.label" default="Nameen" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: productCategoryInstance, field: 'name_en', 'errors')}">
                                    <g:textField name="name_en" value="${productCategoryInstance?.name_en}" />
                                </td>
                            </tr>

                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="name_en"><g:message code="productCategory.catorder.label" default="Category Order" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: productCategoryInstance, field: 'catorder', 'errors')}">
                                    <g:textField name="catorder" value="${productCategoryInstance?.catorder}" />
                                </td>
                            </tr>

                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="prodOptionCategories"><g:message code="productCategory.prodOptionCategories.label" default="Prod Option Categories" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: productCategoryInstance, field: 'prodOptionCategories', 'errors')}">
                                    <g:select name="prodOptionCategories" from="${prodOptionCategoryList}" multiple="yes" optionKey="id" size="5" value="${productCategoryInstance?.prodOptionCategories*.id}" />
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
