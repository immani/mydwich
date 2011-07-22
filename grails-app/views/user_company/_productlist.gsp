                <div id="productlist" class="list">
                    <table>
                        <thead>
                            <tr><th>&nbsp;</th>
                                <mydwich:sortloccol property="name" title="${message(code: 'product.name.label', default: 'Name')}" />
                                <mydwich:sortloccol property="desc" title="${message(code: 'product.desc.label', default: 'Descen')}" />
                                <g:sortableColumn property="price" title="${message(code: 'product.price.label', default: 'Price')}" />
                                <th>&nbsp;</th>
                            </tr>
                        </thead>
                        <tbody>
                            <g:each in="${productcategoriesInstanceList}" status="n" var="productcategoryInstance">
                                <g:if test="${productcategoryInstance.products.size() > 0}">
                                    <tr class="odd">
                                        <td colspan="5"><h4><mydwich:disploc instanceValue="${productcategoryInstance}" property="name" /></h4></td>
                                    </tr>
                                    <g:each in="${productcategoryInstance.products}" status="i" var="productInstance">
                                        <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
                                            <td>
                                                <g:if test="${productInstance?.picture}">
                                                    <img width="50" height="50" src="${resource(dir:'restimages/' + productInstance?.restaurant.name.toLowerCase().encodeAsURL() + '/products', file:'thumb_'+ productInstance?.picture.filename, absolute:'true')}" />
                                                </g:if>
                                                <g:else>
                                                    <img width="50" height="50" src="${resource(dir:'images/', file:'noimage.gif', absolute:'true')}" />
                                                </g:else>
                                            </td>
                                            <td>
                                                <g:link controller="user_company" action="showproductajax" id="${productInstance.id}"
                                                        onclick="displayproductdialog(this.href.toString()); return false;"><mydwich:disploc instanceValue="${productInstance}" property="name" /></g:link>
                                            </td>
                                            <td><mydwich:disploc instanceValue="${productInstance}" property="desc" /></td>
                                            <td>${fieldValue(bean: productInstance, field: "price")} €</td>
                                            <td>
                                                <g:link controller="user_company" action="selectproductoptions" id="${productInstance.id}"
                                                        onclick="displayproductbuydialog(this.href.toString()); return false;">Add</g:link>
                                            </td>
                                        </tr>
                                    </g:each>
                                </g:if>
                            </g:each>
                        </tbody>
                    </table>
                </div>