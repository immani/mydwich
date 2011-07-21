
<%@ page import="com.immani.mydwich.DeliveryAddress" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'deliveryAddress.label', default: 'DeliveryAddress')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
        </div>
        <div class="body">
            <h1><g:message code="default.list.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="list">
                <table>
                    <thead>
                        <tr>
                        
                            <g:sortableColumn property="id" title="${message(code: 'deliveryAddress.id.label', default: 'Id')}" />

                            <g:sortableColumn property="company" title="${message(code: 'deliveryAddress.company.label', default: 'Company')}" />

                            <g:sortableColumn property="name" title="${message(code: 'deliveryAddress.name.label', default: 'Name')}" />
                        
                            <g:sortableColumn property="address" title="${message(code: 'deliveryAddress.address.label', default: 'Address')}" />
                        
                            <g:sortableColumn property="zip" title="${message(code: 'deliveryAddress.zip.label', default: 'Zip')}" />
                        
                            <g:sortableColumn property="city" title="${message(code: 'deliveryAddress.city.label', default: 'City')}" />
                        
                            <g:sortableColumn property="country" title="${message(code: 'deliveryAddress.country.label', default: 'Country')}" />

                            <th>partnerships</th>
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${deliveryAddressInstanceList}" status="i" var="deliveryAddressInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" id="${deliveryAddressInstance.id}">${fieldValue(bean: deliveryAddressInstance, field: "id")}</g:link></td>

                            <td>${fieldValue(bean: deliveryAddressInstance, field: "company")}</td>

                            <td>${fieldValue(bean: deliveryAddressInstance, field: "name")}</td>
                        
                            <td>${fieldValue(bean: deliveryAddressInstance, field: "address")}</td>
                        
                            <td>${fieldValue(bean: deliveryAddressInstance, field: "zip")}</td>
                        
                            <td>${fieldValue(bean: deliveryAddressInstance, field: "city")}</td>
                        
                            <td>${fieldValue(bean: deliveryAddressInstance, field: "country")}</td>

                            <td><g:link controller="partnership" action="restRequestPartnership" id="${deliveryAddressInstance.id}">select</g:link></td>

                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${deliveryAddressInstanceTotal}" />
            </div>
        </div>
    </body>
</html>
