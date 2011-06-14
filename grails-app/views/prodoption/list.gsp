
<%@ page import="com.immani.mydwich.ProdOption" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <meta name="layout" content="main" />
    <g:set var="entityName" value="${message(code: 'prodOption.label', default: 'ProdOption')}" />
    <title><g:message code="default.list.label" args="[entityName]" /></title>
</head>
<body>
<div class="nav">
    <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
    <span class="menuButton"><g:link class="list" controller="prodoptioncategory" action="show" id="${prodoptioncategoryInstance?.id}">Show Category: '${prodoptioncategoryInstance?.toString()}'</g:link></span>
    <span class="menuButton"><g:link class="list" controller="prodoptioncategory" action="list">List Product Options</g:link></span>
    <span class="menuButton"><g:link class="create" action="create" params="[prodoptioncategory: prodoptioncategoryInstance.id]"><g:message code="default.new.label" args="[entityName]" /></g:link></span>
</div>
<div class="body">
    <h1><g:message code="default.list.label" args="[entityName]" /> for: ${prodoptioncategoryInstance.toString()}</h1>
    <g:if test="${flash.message}">
        <div class="message">${flash.message}</div>
    </g:if>
    <div class="list">
        <table>
            <thead>
            <tr>

                <g:sortableColumn property="id" title="${message(code: 'prodOption.id.label', default: 'Id')}" />

                <g:sortableColumn property="name_fr" title="${message(code: 'prodOption.name_fr.label', default: 'Namefr')}" />

                <g:sortableColumn property="name_nl" title="${message(code: 'prodOption.name_nl.label', default: 'Namenl')}" />

                <g:sortableColumn property="name_en" title="${message(code: 'prodOption.name_en.label', default: 'Nameen')}" />

                <g:sortableColumn property="price" title="${message(code: 'prodOption.price.label', default: 'Price')}" />

                <th><g:message code="prodOption.prodOptionCategory.label" default="Prod Option Category" /></th>

            </tr>
            </thead>
            <tbody>
            <g:each in="${prodOptionInstanceList}" status="i" var="prodOptionInstance">
                <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">

                    <td><g:link action="show" id="${prodOptionInstance.id}">${fieldValue(bean: prodOptionInstance, field: "id")}</g:link></td>

                    <td>${fieldValue(bean: prodOptionInstance, field: "name_fr")}</td>

                    <td>${fieldValue(bean: prodOptionInstance, field: "name_nl")}</td>

                    <td>${fieldValue(bean: prodOptionInstance, field: "name_en")}</td>

                    <td>${fieldValue(bean: prodOptionInstance, field: "price")}</td>

                    <td>${fieldValue(bean: prodOptionInstance, field: "prodOptionCategory")}</td>

                </tr>
            </g:each>
            </tbody>
        </table>
    </div>
    <div class="paginateButtons">
        <g:paginate total="${prodOptionInstanceTotal}" />
    </div>
</div>
</body>
</html>
