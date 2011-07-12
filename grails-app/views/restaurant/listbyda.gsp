
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

            <div id="searchBox"> Instant Search: <g:remoteField name="searchBox" update="restaurantlist" paramName="q" url="[controller:'restaurant', action:'search']" before="checksearchquery(this.value)" /></div>

        </div>
        <div class="body">
            <h1><g:message code="default.list.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="list" id="restaurantlist">
                <table>
                    <thead>
                        <tr>
                        
                            <g:sortableColumn property="id" title="${message(code: 'restaurant.id.label', default: 'Id')}" />
                        
                            <g:sortableColumn property="name" title="${message(code: 'restaurant.name.label', default: 'Name')}" />
                        
                            <g:sortableColumn property="address" title="${message(code: 'restaurant.address.label', default: 'Address')}" />
                        
                            <g:sortableColumn property="zip" title="${message(code: 'restaurant.zip.label', default: 'Zip')}" />
                        
                            <g:sortableColumn property="city" title="${message(code: 'restaurant.city.label', default: 'City')}" />
                        
                            <g:sortableColumn property="country" title="${message(code: 'restaurant.country.label', default: 'Country')}" />
                            <th>&nbsp;</th>
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${restaurantInstanceList}" status="i" var="restaurantInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" id="${restaurantInstance.id}">${fieldValue(bean: restaurantInstance, field: "id")}</g:link></td>
                        
                            <td>${fieldValue(bean: restaurantInstance, field: "name")}</td>
                        
                            <td>${fieldValue(bean: restaurantInstance, field: "address")}</td>
                        
                            <td>${fieldValue(bean: restaurantInstance, field: "zip")}</td>
                        
                            <td>${fieldValue(bean: restaurantInstance, field: "city")}</td>
                        
                            <td>${fieldValue(bean: restaurantInstance, field: "country")}</td>

                            <td><g:link controller="product" action="showrestaurantcatalog" id="${restaurantInstance.id}">View Catalog</g:link></td>

                            <td><g:link controller="partnership" action="daRequestPartnership" params="[daid: deliveryAddressInstance.id, restid: restaurantInstance.id]">Request Partnership</g:link></td>
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${restaurantInstanceTotal}" />
            </div>
            <div id="map_canvas" style="width:600px; height:500px"></div>
        </div>
    <script type="text/javascript">displayrestaurantsonmap(${restaurantInstanceList[0].lat}, ${restaurantInstanceList[0].lng})</script>
    </body>
</html>
