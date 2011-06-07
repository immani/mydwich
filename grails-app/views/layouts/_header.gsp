<div id="header">
    <%--TODO: Check to improve language select--%>
    <shiro:hasRole name="company"><p><g:link class="header-main" controller="company">Company Home</g:link></p></shiro:hasRole>
    <shiro:hasRole name="restaurant"><p><g:link class="header-main" controller="restaurant">Restaurant Home</g:link></p></shiro:hasRole>
    <shiro:hasRole name="user"><p><g:link class="header-main" controller="user">User Home</g:link></p></shiro:hasRole>
    <p class="header-sub">When's your next dwich?</p>
    <g:link controller="${controllerName}" action="${actionName}" params="[lang:'fr']">fr</g:link>
    <g:link controller="${controllerName}" action="${actionName}" params="[lang:'nl']">nl</g:link>
    <div id="loginHeader"><g:loginControl /></div>
</div>