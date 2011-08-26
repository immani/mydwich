<%@ page import="com.immani.mydwich.Picture" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'picture.label', default: 'Picture')}" />
        <title><g:message code="default.show.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
            <span class="menuButton"><g:link class="list" action="listrestaurantpictures"><g:message code="default.list.label" args="[entityName]" /></g:link></span>
            <span class="menuButton"><g:link class="create" action="createrestaurantpicture"><g:message code="default.new.label" args="[entityName]" /></g:link></span>
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
                            <td valign="top" class="name">
                                <label for="restaurant"><g:message code="restaurant.label" default="Restaurant" /></label>
                            </td>
                            <td valign="top" class="value ${hasErrors(bean: productInstance, field: 'restaurant', 'errors')}">
                                ${pictureInstance?.restaurant?.encodeAsHTML()}
                            </td>
                        </tr>

                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="picture.caption.label" default="Caption" /></td>

                            <td valign="top" class="value">${fieldValue(bean: pictureInstance, field: "caption")}</td>

                        </tr>

                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="picture.description.label" default="Description" /></td>

                            <td valign="top" class="value">${fieldValue(bean: pictureInstance, field: "description")}</td>

                        </tr>

                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="picture.filename.label" default="Filename" /></td>

                            <td valign="top" class="value">${fieldValue(bean: pictureInstance, field: "filename")}</td>

                        </tr>

                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="picture.contenttype.label" default="Content Type" /></td>

                            <td valign="top" class="value">${fieldValue(bean: pictureInstance, field: "contentType")}</td>

                        </tr>

                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="picture.label" default="Picture" /></td>

                            <td valign="top" class="value">
                                <a href="${resource(dir:'restimages/' +pictureInstance.restaurant.name.toLowerCase().encodeAsURL(), file:pictureInstance.filename, absolute:'true')}">
                                    <img src="${resource(dir:'restimages/' +pictureInstance.restaurant.name.toLowerCase().encodeAsURL(), file:'thumb_'+ pictureInstance.filename, absolute:'true')}" />
                                </a>
                            </td>

                        </tr>


                    </tbody>
                </table>
            </div>
            <div class="buttons">
                <g:form>
                    <g:hiddenField name="id" value="${pictureInstance?.id}" />
                    <span class="button"><g:actionSubmit class="edit" action="edit" value="${message(code: 'default.button.edit.label', default: 'Edit')}" /></span>
                    <span class="button"><g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" /></span>
                </g:form>
            </div>
        </div>
    </body>
</html>
