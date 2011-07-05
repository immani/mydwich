<%@ page import="com.immani.mydwich.Userpayment" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <meta name="layout" content="main" />
    <g:set var="entityName" value="${message(code: 'userpayment.label', default: 'Userpayment')}" />
    <title><g:message code="default.create.label" args="[entityName]" /></title>
</head>
<body>
<div class="nav">
    <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
    <span class="menuButton"><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></span>
</div>
<div class="body">
    <h1> User payment review</h1>
    <g:if test="${flash.message}">
        <div class="message">${flash.message}</div>
    </g:if>
    <g:hasErrors bean="${userpaymentInstance}">
        <div class="errors">
            <g:renderErrors bean="${userpaymentInstance}" as="list" />
        </div>
    </g:hasErrors>

    <g:link action="create">modify</g:link>

    <FORM METHOD="post" ACTION="https://secure.ogone.com/ncol/test/orderstandard.asp" id=form1 name=form1>

        <div class="dialog">
            <table>
                <tbody>

                <tr class="prop">
                    <td valign="top" class="name">
                        <label for="amount"><g:message code="userpayment.amount.label" default="Amount" /></label>
                    </td>
                    <td valign="top" class="value ${hasErrors(bean: userpaymentInstance, field: 'amount', 'errors')}">
                        ${userpaymentInstance?.amount}
                    </td>
                </tr>

                <tr class="prop">
                    <td valign="top" class="name">
                        <label for="remark"><g:message code="userpayment.remark.label" default="Remark" /></label>
                    </td>
                    <td valign="top" class="value ${hasErrors(bean: userpaymentInstance, field: 'remark', 'errors')}">
                        ${userpaymentInstance?.remark}
                    </td>
                </tr>

                </tbody>
            </table>
        </div>

        <INPUT type="hidden" NAME="PSPID" value="${psid}">
        <INPUT type="hidden" NAME="ORDERID" value="${userpaymentInstance?.orderId}">
        <INPUT type="hidden" NAME="AMOUNT" value="${(int) userpaymentInstance?.amount * 100}">
        <INPUT type="hidden" NAME="CURRENCY" value="${userpaymentInstance?.currency}">
        <INPUT type="hidden" NAME="LANGUAGE" value="${userpaymentInstance?.user?.language}">
        <!-- lay out information -->


        <!-- or dynamic template page -->

        <!-- post-payment redirection -->
        <INPUT type="hidden" NAME="ACCEPTURL" VALUE="${userpaymentInstance?.acceptedurl}">
        <INPUT type="hidden" NAME="DECLINEURL" VALUE="${userpaymentInstance?.declinedurl}">
        <INPUT type="hidden" NAME="EXCEPTIONURL" VALUE="">
        <INPUT type="hidden" NAME="CANCELURL" VALUE="">
        <INPUT type="hidden" NAME="BACKURL" VALUE="">
        <!-- miscellanous -->
        <INPUT type="hidden" NAME="HOMEURL" VALUE="">
        <INPUT type="hidden" NAME="CATALOGURL" VALUE="">
        <INPUT type="hidden" NAME="PM" VALUE="">
        <INPUT type="hidden" NAME="BRAND" VALUE="">
        <INPUT type="hidden" NAME="OWNERZIP" VALUE="">
        <INPUT type="hidden" NAME="OWNERADDRESS" VALUE="">
        <INPUT type="hidden" NAME="OWNERADDRESS2" VALUE="">
        <INPUT type="hidden" NAME="SHASIGN" value="${shasign}">
        <INPUT type="hidden" NAME="ALIAS" VALUE="">
        <INPUT type="hidden" NAME="ALIASUSAGE" VALUE="">
        <INPUT type="hidden" NAME="ALIASOPERATION" VALUE="">
        <INPUT type="hidden" NAME="COM" VALUE="">
        <INPUT type="hidden" NAME="COMPLUS" VALUE="">
        <INPUT type="hidden" NAME="PARAMPLUS" VALUE="">
        <INPUT type="hidden" NAME="USERID" VALUE="">
        <INPUT type="hidden" NAME="CREDITCODE" VALUE="">
        <input type="submit" value="confirm" id=submit2 name=submit2>

    </form>


</div>
</body>
</html>