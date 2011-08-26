<%@ page import="com.immani.mydwich.Product" %>
<g:set var="entityName" value="${message(code: 'product.label', default: 'Product')}" />
<g:formRemote name="addproduct" url="[controller:'user_company',action:'addproduct']" method="post" onSuccess="closeproductbuydialog(data)">
    <div class="dialog">
        <table>
            <tbody>

            <tr class="prop">
                <td valign="top" class="name"><g:message code="product.name.label" default="Namefr" /></td>
                <td valign="top" class="value"><mydwich:disploc instanceValue="${selproduct}" property="name" /></td>
            </tr>

            <tr class="prop">
                <td valign="top" class="name"><g:message code="product.price.label" default="Price" /></td>
                <td valign="top" class="value">${fieldValue(bean: selproduct, field: "price")} €</td>
            </tr>

            <tr class="prop">
                <td valign="top" class="name"><g:message code="product.qty.label" default="Quantity" /></td>
                <td valign="top" class="value"><g:textField name="product.quantity" value="1" /></td>
            </tr>

            <g:render template="prodoptioncat"/>

            <tr class="prop">
                <td valign="top" class="name"><g:message code="product.comment.label" default="Comment" /></td>
                <td valign="top" class="value"><g:textField name="product.comment" value="" /></td>
            </tr>

            </tbody>
        </table>
    </div>
    <div class="buttons">
        <g:hiddenField name="product.productid" value="${selproduct?.id}" />
        <span class="button"><g:actionSubmit class="save" action="addproduct" value="Add" /></span>
    </div>
</g:formRemote>

