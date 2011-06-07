            <table>
                <thead>
                <tr>
                    <td>${message(code: 'product.name.label', default: 'Name')}</td>
                    <td>${message(code: 'product.desc.label', default: 'Descen')}</td>
                    <td>${message(code: 'product.price.label', default: 'Price')}</td>
                    <td>&nbsp</td>
                </tr>
                </thead>
                <tbody>
                <g:each in="${productcategoriesInstanceList}" status="n" var="productcategoryInstance">
                    <g:if test="${productcategoryInstance.products.size() > 0}">
                        <tr class="odd">
                            <td colspan="7">${fieldValue(bean: productcategoryInstance, field: "name_fr")}</td>
                        </tr>
                        <g:each in="${productcategoryInstance.products}" status="i" var="productInstance">
                            <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
                                <td><g:link action="show" id="${productInstance.id}"><mydwich:disploc instanceValue="${productInstance}" property="name" /></g:link></td>
                                <td><mydwich:disploc instanceValue="${productInstance}" property="desc" /></td>
                                <td>${fieldValue(bean: productInstance, field: "price")} €</td>
                                <td><g:link controller="basket" action="selectproductoptions" id="${productInstance.id}" onclick="displayproductbuydialog(this.href.toString()); return false;">Add</g:link></td>
                            </tr>
                        </g:each>
                    </g:if>
                </g:each>
                </tbody>
            </table>