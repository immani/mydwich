        <table>
            <thead>
            <tr>
                <g:sortableColumn property="quantity" title="${message(code: 'basketLine.quantity.label', default: 'Quantity')}" />
                <g:sortableColumn property="product.label" title="${message(code: 'basketLine.product.label', default: 'Product')}" />
                <g:sortableColumn property="product.price" title="${message(code: 'basketLine.product.unitprice', default: 'Unit Price')}" />
                <g:sortableColumn property="price" title="${message(code: 'basketLine.price.label', default: 'Price')}" />
                <th>Remove</th>
                <g:sortableColumn property="comment" title="${message(code: 'basketLine.comment.label', default: 'Options/Comment')}" />
            </tr>
            </thead>
            <tbody>
            <g:each in="${basketLines}" status="i" var="basketLineInstance">
                <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">

                    <td class="editable" id="qty${i}"> ${fieldValue(bean: basketLineInstance, field: "quantity")}</td>
                    <td><mydwich:disploc instanceValue="${basketLineInstance.product}" property="name" /></td>
                    <td>${fieldValue(bean: basketLineInstance.product, field: "price")} €</td>
                    <td>${fieldValue(bean: basketLineInstance, field: "price")} €</td>
                    <td><g:link class="action_remove" controller="user_company" action="removeline" id="${i}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');">&nbsp;</g:link></td>
                    <td>
                        ${fieldValue(bean: basketLineInstance, field: "comment")} <br/>
                        <g:each in="${basketLineInstance.prodOptions}" status="j" var="prodOption">
                            <g:if test="${prodOption}">
                                <mydwich:disploc instanceValue="${prodOption}" property="name" /> (${prodOption.price} €),
                            </g:if>
                        </g:each>
                    </td>
                </tr>
            </g:each>
            </tbody>
        </table>

        <h1>${basketInstance? basketInstance.totalnbofarticles : 0} articles - Total: ${basketInstance? basketInstance.totalprice : 0 } €</h1></h1>