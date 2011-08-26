<div id="header">
    <%--TODO: Check to improve language select--%>
    <%--
    <shiro:hasRole name="company"><p><g:link class="header-main" controller="company">Company Home</g:link></p></shiro:hasRole>
    <shiro:hasRole name="restaurant"><p><g:link class="header-main" controller="restaurant">Restaurant Home</g:link></p></shiro:hasRole>
    <shiro:hasRole name="user"><p><g:link class="header-main" controller="user">User Home</g:link></p></shiro:hasRole>
    --%>
    <p><g:link class="header-main" controller="home">Home</g:link>
    <p class="header-sub">When's your next dwich?</p>
    <shiro:isLoggedIn>
         <p>Account: ${fieldValue(bean: session.user, field: "balance")}  </p>
    </shiro:isLoggedIn>
    <g:link controller="${controllerName}" action="${actionName}" params="[lang:'fr']">Fr</g:link>
    <g:link controller="${controllerName}" action="${actionName}" params="[lang:'nl']">Nl</g:link>
    <g:link controller="${controllerName}" action="${actionName}" params="[lang:'en']">En</g:link>
    <div id="loginHeader"><g:loginControl /></div>
</div>