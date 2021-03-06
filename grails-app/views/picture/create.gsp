<%@ page import="com.immani.mydwich.Picture" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <meta name="layout" content="main" />
    <g:set var="entityName" value="${message(code: 'picture.label', default: 'Picture')}" />
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
    <g:hasErrors bean="${pictureInstance}">
        <div class="errors">
            <g:renderErrors bean="${pictureInstance}" as="list" />
        </div>
    </g:hasErrors>
    <g:uploadForm action="save"  enctype="multipart/form-data">
        <div class="dialog">
            <table>
                <tbody>
                <tr class="prop">
                    <td valign="top" class="name">
                        <label for="restaurant"><g:message code="restaurant.label" default="Restaurant" /></label>
                    </td>
                    <td valign="top" class="value ${hasErrors(bean: productInstance, field: 'restaurant', 'errors')}">
                        ${pictureInstance?.restaurant?.encodeAsHTML()}
                        <g:hiddenField name="restaurant.id" value="${pictureInstance?.restaurant?.id}" />
                    </td>
                </tr>

                <tr class="prop">
                    <td valign="top" class="name">
                        <label for="file"><g:message code="picture.file.label" default="File" /></label>
                    </td>
                    <td valign="top" class="value ${hasErrors(bean: pictureInstance, field: 'file', 'errors')}">
                        <input type="file" id="file" name="file" />
                    </td>
                </tr>

                <tr class="prop">
                    <td valign="top" class="name">
                        <label for="caption"><g:message code="picture.caption.label" default="Caption" /></label>
                    </td>
                    <td valign="top" class="value ${hasErrors(bean: pictureInstance, field: 'caption', 'errors')}">
                        <g:textField name="caption" value="${pictureInstance?.caption}" />
                    </td>
                </tr>

                <tr class="prop">
                    <td valign="top" class="name">
                        <label for="description"><g:message code="picture.description.label" default="Description" /></label>
                    </td>
                    <td valign="top" class="value ${hasErrors(bean: pictureInstance, field: 'description', 'errors')}">
                        <g:textField name="description" value="${pictureInstance?.description}" />
                    </td>
                </tr>

                </tbody>
            </table>
        </div>
        <div class="buttons">
            <span class="button"><g:submitButton name="create" class="save" value="${message(code: 'default.button.create.label', default: 'Create')}" /></span>
        </div>
    </g:uploadForm>
</div>
</body>
</html>
