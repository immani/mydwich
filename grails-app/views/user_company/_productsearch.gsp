<div id="productlist" class="list">
<g:if test="${productsResults?.results}">
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
            <g:each in="${productsResults.results}" status="i" var="productInstance">
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

        </tbody>
    </table>
</g:if>