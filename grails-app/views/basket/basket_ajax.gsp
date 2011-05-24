<%@ page import="com.immani.mydwich.Basket; com.immani.mydwich.BasketLine" %>
            <div class="homePagePanel">
                <div class="panelTop"></div>
                <div class="panelBody">
                    <h1>My Basket</h1>
                    <ul>
                        <g:each in="${basketLines}" var="basketLine">
                            <li>${basketLine.quantity} ${basketLine.product.name_en}: ${basketLine.price}€</li>
                        </g:each>
                    </ul>
                    <h1>${basketInstance? basketInstance.totalnbofarticles : 0} articles</h1>
                    <h1>${basketInstance? basketInstance.totalprice : 0 } €</h1>
                    <h1><g:if test="${basketInstance!=null}"><g:link controller="basket" action="showcurrentbasket">Checkout</g:link></g:if> </h1>
                </div>
                <div class="panelBtm"></div>
            </div>
