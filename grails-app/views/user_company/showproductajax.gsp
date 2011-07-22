<%@ page import="com.immani.mydwich.Product" %>
<g:set var="entityName" value="${message(code: 'product.label', default: 'Product')}" />
<div class="body">
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

                    <g:each in="${productOptionCategories}" var="ProdOptionCat">
                        <tr>
                            <td valign="top" class="name">
                                <label><p><mydwich:disploc instanceValue="${ProdOptionCat}" property="name" /></p></label>
                            </td>

                            <td valign="top" class="name">
                                <g:if test="${ProdOptionCat.type=='radio'}">
                                    <g:each in="${ProdOptionCat.options}" var="prodOption">
                                        <mydwich:disploc instanceValue="${prodOption}" property="name" /> (${prodOption.price} €)<br/>
                                    </g:each>
                                </g:if>
                                <g:else>
                                    <g:each in="${ProdOptionCat.options}" var="prodOption">
                                        <mydwich:disploc instanceValue="${prodOption}" property="name" /> (${prodOption.price} €)<br/>
                                    </g:each>
                                </g:else>
                            </td>
                        </tr>
                    </g:each>
                </tbody>
            </table>
            <g:if test="${pictureInstance != null}" >
                <a href="${resource(dir:'restimages/' +selproduct.restaurant.name.toLowerCase().encodeAsURL() +'/products', file:pictureInstance.filename, absolute:'true')}">
                    <img src="${resource(dir:'restimages/' +selproduct.restaurant.name.toLowerCase().encodeAsURL()+'/products', file:'thumb_'+ pictureInstance.filename, absolute:'true')}" />
                </a>
            </g:if>
        </div>
    </g:formRemote>
</div>
