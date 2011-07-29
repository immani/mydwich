<%@ page import="com.immani.mydwich.Company" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'company.label', default: 'Company')}" />
        <title><g:message code="default.show.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
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
                            <td valign="top" class="name"><g:message code="company.name.label" default="Name" /></td>
                            <td valign="top" class="value">${fieldValue(bean: companyInstance, field: "name")}</td>
                        </tr>

                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="company.domain.label" default="Domain" /></td>
                            <td valign="top" class="value">${fieldValue(bean: companyInstance, field: "domain")}</td>
                        </tr>

                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="company.address.label" default="Address" /></td>
                            <td valign="top" class="value">${fieldValue(bean: companyInstance, field: "address")}</td>
                        </tr>

                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="company.zip.label" default="Zip" /></td>
                            <td valign="top" class="value">${fieldValue(bean: companyInstance, field: "zip")}</td>
                        </tr>

                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="company.city.label" default="City" /></td>
                            <td valign="top" class="value">${fieldValue(bean: companyInstance, field: "city")}</td>
                        </tr>

                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="company.country.label" default="Country" /></td>
                            <td valign="top" class="value">${fieldValue(bean: companyInstance, field: "country")}</td>
                        </tr>

                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="company.vat.label" default="Vat" /></td>
                            <td valign="top" class="value">${fieldValue(bean: companyInstance, field: "vat")}</td>
                        </tr>

                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="company.phone.label" default="Phone" /></td>
                            <td valign="top" class="value">${fieldValue(bean: companyInstance, field: "phone")}</td>
                        </tr>

                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="company.fax.label" default="Fax" /></td>
                            <td valign="top" class="value">${fieldValue(bean: companyInstance, field: "fax")}</td>
                        </tr>

                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="company.deliveryAddresses.label" default="Delivery Addresses" /></td>
                            <td valign="top" style="text-align: left;" class="value">
                                <ul>
                                    <g:each in="${companyInstance.deliveryAddresses}" var="d">
                                        <li><g:link controller="deliveryaddress" action="show" id="${d.id}">${d?.encodeAsHTML()}</g:link></li>
                                    </g:each>
                                </ul>
                            </td>
                        </tr>

                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="company.users.label" default="Users" /></td>
                            <td valign="top" style="text-align: left;" class="value">
                                <ul>
                                    <g:each in="${companyInstance.users}" var="u">
                                        <li><g:link controller="user" action="show" id="${u.id}">${u?.encodeAsHTML()}</g:link></li>
                                    </g:each>
                                </ul>
                            </td>
                        </tr>

                    </tbody>
                </table>
            </div>
            <div class="buttons">
                <g:form>
                    <g:hiddenField name="id" value="${companyInstance?.id}" />
                    <span class="button"><g:actionSubmit class="edit" action="edit" value="${message(code: 'default.button.edit.label', default: 'Edit')}" /></span>
                    <span class="button"><g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" /></span>
                </g:form>
            </div>
            <div id="map_canvas" style="width:600px; height:400px"></div>
        </div>

        <script type="text/javascript">
            var contentString = '<h1 class="firstHeading">${companyInstance.name}</h1><br /> ${companyInstance.address}, ${companyInstance.zip} ${companyInstance.city}, ${companyInstance.country}';
            var map = displaymap(${companyInstance.lat}, ${companyInstance.lng}, '${companyInstance.name}', contentString, {icon: '/images/company.png'});
            displayrestaurantsnear(${companyInstance.lat}, ${companyInstance.lng}, {map: map});
            displaydeliveryaddressonmap(${companyInstance.lat}, ${companyInstance.lng}, {map: map});
        </script>
    </body>
</html>
