<%@ page import="com.immani.mydwich.Company" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <meta name="layout" content="main" />
    <g:set var="entityName" value="${message(code: 'company.label', default: 'Company')}" />
    <title><g:message code="default.create.label" args="[entityName]" /></title>
</head>
<body>
<div class="nav">
    <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
    <span class="menuButton"><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></span>
</div>
<div class="body">
    <h1><g:message code="default.create.label" args="[entityName]" /></h1>
    <g:if test="${error}">
        <div class="errors"><ul><li>${error}</li></ul></div>
    </g:if>
    <g:hasErrors bean="${companyInstance}">
        <div class="errors">
            <g:renderErrors bean="${companyInstance}" as="list" />
        </div>
    </g:hasErrors>
    <g:form url="[controller:'registration', action:'registercompany']">
        <div class="dialog">
            <table>
                <tbody>

                <tr class="prop">
                    <td valign="top" class="name">
                        <g:message code="company.name.label" default="Name" />
                    </td>
                    <td valign="top" class="value">
                        ${companyInstance?.name}
                    </td>
                </tr>

                <tr class="prop">
                    <td valign="top" class="name">
                        <g:message code="company.domain.label" default="Domain" />
                    </td>
                    <td valign="top" class="value">
                        ${companyInstance?.domain}
                    </td>
                </tr>

                <tr class="prop">
                    <td valign="top" class="name">
                        <g:message code="company.address.label" default="Address" />
                    </td>
                    <td valign="top" class="value">
                        ${companyInstance?.address}
                    </td>
                </tr>

                <tr class="prop">
                    <td valign="top" class="name">
                        <g:message code="company.zip.label" default="Zip" />
                    </td>
                    <td valign="top" class="value">
                        ${companyInstance?.zip}
                    </td>
                </tr>

                <tr class="prop">
                    <td valign="top" class="name">
                        <g:message code="company.city.label" default="City" />
                    </td>
                    <td valign="top" class="value">
                        value="${companyInstance?.city}"
                    </td>
                </tr>

                <tr class="prop">
                    <td valign="top" class="name">
                        <g:message code="company.country.label" default="Country" />
                    </td>
                    <td valign="top" class="value">
                        value="${companyInstance?.country}"
                    </td>
                </tr>

                <tr class="prop">
                    <td valign="top" class="name">
                        <g:message code="company.vat.label" default="Vat" />
                    </td>
                    <td valign="top" class="value">
                        ${companyInstance?.vat}"
                    </td>
                </tr>

                <tr class="prop">
                    <td valign="top" class="name">
                       <g:message code="company.phone.label" default="Phone" />
                    </td>
                    <td valign="top" class="value">
                        ${companyInstance?.phone}"
                    </td>
                </tr>

                <tr class="prop">
                    <td valign="top" class="name">
                        <g:message code="company.fax.label" default="Fax" />
                    </td>
                    <td valign="top" class="value">
                        ${companyInstance?.fax}"
                    </td>
                </tr>

                </tbody>
            </table>
        </div>
        <div class="buttons">
            <span class="button"><g:submitButton name="back" class="back" value="${message(code: 'default.button.back.label', default: 'Back')}" /></span>
            <span class="button"><g:submitButton name="confirm" class="next" value="${message(code: 'default.button.confirm.label', default: 'Confirm')}" /></span>
            <span class="button"><g:submitButton name="cancel" class="cancel" value="${message(code: 'default.button.cancel.label', default: 'Cancel')}" /></span>
        </div>
    </g:form>
</div>
</body>
</html>
