<div id="searchResults" class="searchResults">
<g:if test="${restaurantResults?.results}">
    <div id="restResults" class="resultsPane">
        <h2>Restaurants Results</h2>
        <ul>
            <g:each in="${restaurantResults.results}" var="rest"> <li><g:link controller="anonymous_Restaurant" action="showrestaurant" id="${rest?.name}"> ${rest?.name}</g:link></li></g:each>
        </ul>
    </div>
</g:if>