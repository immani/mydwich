<div id="searchResults" class="searchResults">
<g:if test="${productsResults?.results}">
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
        <g:each in="${productsResults.results}" status="i" var="productInstance">
            <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
                <td><g:link action="show" id="${productInstance.id}"><mydwich:disploc instanceValue="${productInstance}" property="name" /></g:link></td>
                <td><mydwich:disploc instanceValue="${productInstance}" property="desc" /></td>
                <td>${fieldValue(bean: productInstance, field: "price")} €</td>
                <td><g:link controller="basket" action="selectproductpptions" id="${productInstance.id}" onclick="displayproductbuydialog(this.href.toString()); return false;">Add</g:link></td>
            </tr>
        </g:each>

        </tbody>
    </table>
</g:if>