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
                        <label for="name"><g:message code="company.name.label" default="Name" /></label>
                    </td>
                    <td valign="top" class="value ${hasErrors(bean: companyInstance, field: 'name', 'errors')}">
                        <g:textField name="name" value="${companyInstance?.name}" />
                    </td>
                </tr>

                <tr class="prop">
                    <td valign="top" class="name">
                        <label for="domain"><g:message code="company.domain.label" default="Domain" /></label>
                    </td>
                    <td valign="top" class="value ${hasErrors(bean: companyInstance, field: 'domain', 'errors')}">
                        <g:textField name="domain" value="${companyInstance?.domain}" /><b>(yourdomain.com)</b>
                    </td>
                </tr>

                <tr class="prop">
                    <td valign="top" class="name">
                        <label for="address"><g:message code="company.address.label" default="Address" /></label>
                    </td>
                    <td valign="top" class="value ${hasErrors(bean: companyInstance, field: 'address', 'errors')}">
                        <g:textField name="address" value="${companyInstance?.address}" />
                    </td>
                </tr>

                <tr class="prop">
                    <td valign="top" class="name">
                        <label for="zip"><g:message code="company.zip.label" default="Zip" /></label>
                    </td>
                    <td valign="top" class="value ${hasErrors(bean: companyInstance, field: 'zip', 'errors')}">
                        <g:textField name="zip" value="${companyInstance?.zip}" />
                    </td>
                </tr>

                <tr class="prop">
                    <td valign="top" class="name">
                        <label for="city"><g:message code="company.city.label" default="City" /></label>
                    </td>
                    <td valign="top" class="value ${hasErrors(bean: companyInstance, field: 'city', 'errors')}">
                        <g:textField name="city" value="${companyInstance?.city}" />
                    </td>
                </tr>

                <tr class="prop">
                    <td valign="top" class="name">
                        <label for="country"><g:message code="company.country.label" default="Country" /></label>
                    </td>
                    <td valign="top" class="value ${hasErrors(bean: companyInstance, field: 'country', 'errors')}">
                        <g:textField name="country" disabled="yes" value="${g.message(code:'country.Belgium')}" />
                    </td>
                </tr>

                <tr class="prop">
                    <td valign="top" class="name">
                        <label for="vat"><g:message code="company.vat.label" default="Vat" /></label>
                    </td>
                    <td valign="top" class="value ${hasErrors(bean: companyInstance, field: 'vat', 'errors')}">
                        <g:textField name="vat" value="${companyInstance?.vat}" />
                    </td>
                </tr>

                <tr class="prop">
                    <td valign="top" class="name">
                        <label for="phone"><g:message code="company.phone.label" default="Phone" /></label>
                    </td>
                    <td valign="top" class="value ${hasErrors(bean: companyInstance, field: 'phone', 'errors')}">
                        <g:textField name="phone" value="${companyInstance?.phone}" />
                    </td>
                </tr>

                <tr class="prop">
                    <td valign="top" class="name">
                        <label for="fax"><g:message code="company.fax.label" default="Fax" /></label>
                    </td>
                    <td valign="top" class="value ${hasErrors(bean: companyInstance, field: 'fax', 'errors')}">
                        <g:textField name="fax" value="${companyInstance?.fax}" />
                    </td>
                </tr>

                </tbody>
            </table>
        </div>
        <div class="buttons">
            <span class="button"><g:submitButton name="next" class="next" value="${message(code: 'default.button.next.label', default: 'Next')}" /></span>
            <span class="button"><g:submitButton name="cancel" class="cancel" value="${message(code: 'default.button.cancel.label', default: 'Cancel')}" /></span>
        </div>
    </g:form>
</div>
</body>
</html>
