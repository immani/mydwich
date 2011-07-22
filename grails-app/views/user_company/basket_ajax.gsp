<%@ page import="com.immani.mydwich.Basket; com.immani.mydwich.BasketLine" %>
            <div class="homePagePanel">
                <div class="panelTop"></div>
                <div class="panelBody">
                    <h1>My Basket</h1>
                    <ul>
                        <g:each in="${basketLines}" var="basketLine">
                            <li>${basketLine.quantity} <mydwich:disploc instanceValue="${basketLine.product}" property="name" />: ${basketLine.price}€</li>
                        </g:each>
                    </ul>
                    <h1>${basketInstance? basketInstance.totalnbofarticles : 0} articles</h1>
                    <h1>Total: ${basketInstance? basketInstance.totalprice : 0 } €</h1>
                    <h1 align="right"><g:if test="${basketInstance!=null}"><g:link controller="user_company" action="showcurrentbasket">Checkout</g:link></g:if> </h1>
                </div>
                <div class="panelBtm"></div>
            </div>
