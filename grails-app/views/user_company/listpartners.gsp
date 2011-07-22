<%@ page import="com.immani.mydwich.Restaurant" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'restaurant.label', default: 'Restaurant')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>
        <script type="text/javascript" src="http://maps.google.com/maps/api/js?sensor=false"></script>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
            <span class="menuButton"><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></span>
        </div>
        <div class="body">
            <h1><g:message code="default.list.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
                <div class="message">${flash.message}</div>
            </g:if>
            <div class="list" id="restaurantlist">
                <g:if test="${dalist.size()>1}">
                    <g:form controller="user_company" action="listpartnerrestaurant">
                        Delivery Address: <g:select name="da" from="${dalist}" optionKey="id" value="${da?.id}" onchange="submit()" />
                    </g:form>
                </g:if>
                <br/>
                <table>
                    <thead>
                        <tr>
                            <th><g:message code="restaurant.name.label" default="Name"/></th>
                            <th><g:message code="restaurant.desc.label" default="Description"/></th>
                            <th>&nbsp;</th>
                        </tr>
                    </thead>

                    <tbody>
                        <g:each in="${restaurantInstanceList}" status="i" var="restaurantInstance">
                            <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                                <td>${fieldValue(bean: restaurantInstance, field: "name")}</td>
                                <td><mydwich:disploc instanceValue="${restaurantInstance}" property="desc"/></td>
                                <td><g:link controller="user_company" action="showrestaurantcatalog" id="${restaurantInstance.id}">View Catalog</g:link></td>
                            </tr>
                        </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${restaurantInstanceTotal}" />
            </div>
        </div>
    </body>
</html>
