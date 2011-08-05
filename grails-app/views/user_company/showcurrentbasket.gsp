<%@ page import="com.immani.mydwich.Basket" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <meta name="layout" content="main" />
    <g:set var="entityName" value="${message(code: 'basket.label', default: 'Basket')}" />
    <title><g:message code="default.list.label" args="[entityName]" /></title>
</head>
<body>
<g:javascript src="jeditable.js" />
<div class="nav">
    <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
    <span class="menuButton"><g:link class="create" action="showrestaurantcatalog" id="${session.basket.restaurant.id}"><g:message code="basket.continue.order"/></g:link></span>
</div>
<div class="body">
    <h1><g:message code="default.list.label" args="[entityName]" /></h1>
    <g:if test="${flash.message}">
        <div class="message">${flash.message}</div>
    </g:if>
    <div class="list" id="basketcontent">
        <g:render template="basket"></g:render>
    </div>
</div>
<script type="text/javascript">
    $(document).ready(function() {
        makeqtyeditable()
    })
</script>
</body>
</html>
