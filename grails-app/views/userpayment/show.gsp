
<%@ page import="com.immani.mydwich.Userpayment" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'userpayment.label', default: 'Userpayment')}" />
        <title><g:message code="default.show.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
            <span class="menuButton"><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></span>
            <span class="menuButton"><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></span>
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
                            <td valign="top" class="name"><g:message code="userpayment.id.label" default="Id" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: userpaymentInstance, field: "id")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="userpayment.user.label" default="User" /></td>
                            
                            <td valign="top" class="value"><g:link controller="user" action="show" id="${userpaymentInstance?.user?.id}">${userpaymentInstance?.user?.encodeAsHTML()}</g:link></td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="userpayment.remark.label" default="Remark" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: userpaymentInstance, field: "remark")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="userpayment.amount.label" default="Amount" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: userpaymentInstance, field: "amount")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="userpayment.currency.label" default="Currency" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: userpaymentInstance, field: "currency")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="userpayment.paymentMethod.label" default="Payment Method" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: userpaymentInstance, field: "paymentMethod")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="userpayment.cardBrand.label" default="Card Brand" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: userpaymentInstance, field: "cardBrand")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="userpayment.cardHolderName.label" default="Card Holder Name" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: userpaymentInstance, field: "cardHolderName")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="userpayment.ogonePaymentId.label" default="Ogone Payment Id" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: userpaymentInstance, field: "ogonePaymentId")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="userpayment.status.label" default="Status" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: userpaymentInstance, field: "status")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="userpayment.ncerror.label" default="Ncerror" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: userpaymentInstance, field: "ncerror")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="userpayment.ipAddress.label" default="Ip Address" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: userpaymentInstance, field: "ipAddress")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="userpayment.acceptance.label" default="Acceptance" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: userpaymentInstance, field: "acceptance")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="userpayment.acceptedurl.label" default="Acceptedurl" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: userpaymentInstance, field: "acceptedurl")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="userpayment.declinedurl.label" default="Declinedurl" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: userpaymentInstance, field: "declinedurl")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="userpayment.orderId.label" default="Order Id" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: userpaymentInstance, field: "orderId")}</td>
                            
                        </tr>
                    
                    </tbody>
                </table>
            </div>
            <div class="buttons">
                <g:form>
                    <g:hiddenField name="id" value="${userpaymentInstance?.id}" />
                    <span class="button"><g:actionSubmit class="edit" action="edit" value="${message(code: 'default.button.edit.label', default: 'Edit')}" /></span>
                    <span class="button"><g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" /></span>
                </g:form>
            </div>
        </div>
    </body>
</html>
