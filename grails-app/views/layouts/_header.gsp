<div id="fbHeader">
    <%--TODO: Check to improve language select--%>
    <div id="header_container">
        <div id="language_box">
            <g:link controller="${controllerName}" action="${actionName}" params="[lang:'fr']">Fr</g:link>
            <g:link controller="${controllerName}" action="${actionName}" params="[lang:'nl']">Nl</g:link>
            <g:link controller="${controllerName}" action="${actionName}" params="[lang:'en']">En</g:link>
        </div>
        <div id="spinner" class="spinner" style="display:none;">
            <img src="${resource(dir:'images',file:'spinner.gif')}" alt="${message(code:'spinner.alt',default:'Loading...')}" />
        </div>
        <div id="logo">
            <g:link controller="home"><img src="${resource(dir:'images',file:'logo.png')}" alt="Have a nice dwich!" border="0" /></g:link>
        </div>
        <div id="loginHeader"><g:loginControl /></div>
        <p class="header-sub">When's your next dwich?</p>
        <div class="clearboth">&nbsp;</div>
        <div id="info" class="message" style="display: none;">default message</div>
    </div>
</div>