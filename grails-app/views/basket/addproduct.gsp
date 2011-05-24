<%@ page import="com.immani.mydwich.Product" %>
    <g:set var="entityName" value="${message(code: 'product.label', default: 'Product')}" />
    <div class="body">
        <g:formRemote name="addproduct" url="[controller:'basket',action:'addproduct']" method="post" onSuccess="closeproductbuydialog(data)">
            <div class="dialog">
                <table>
                    <tbody>

                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="product.name_fr.label" default="Namefr" /></td>
                            <td valign="top" class="value">${fieldValue(bean: selproduct, field: "name_fr")}</td>
                        </tr>

                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="product.name_nl.label" default="Namenl" /></td>
                            <td valign="top" class="value">${fieldValue(bean: selproduct, field: "name_nl")}</td>
                        </tr>

                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="product.name_en.label" default="Nameen" /></td>
                            <td valign="top" class="value">${fieldValue(bean: selproduct, field: "name_en")}</td>
                        </tr>

                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="product.price.label" default="Price" /></td>
                            <td valign="top" class="value">${fieldValue(bean: selproduct, field: "price")} €</td>
                        </tr>

                        <tr class="prop">
                            <td valign="top" class="name">Quantity</td>
                            <td valign="top" class="value"><g:textField name="product.quantity" value="1" /></td>
                        </tr>

                        <g:render template="prodoptioncat"/>

                    </tbody>
                </table>
            </div>
            <div class="buttons">
            <g:hiddenField name="product.productid" value="${selproduct?.id}" />
            <span class="button"><g:actionSubmit class="save" action="addproduct" value="Add" /></span>
        </div>
        </g:formRemote>
    </div>
