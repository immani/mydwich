<!DOCTYPE html>
<html>
    <head>
        <title><g:layoutTitle default="Grails" /></title>
        <link rel="stylesheet" href="${resource(dir:'css',file:'main.css')}" />
        <link rel="shortcut icon" href="${resource(dir:'images',file:'favicon.ico')}" type="image/x-icon" />
        <g:layoutHead />
        <g:javascript library="jquery" plugin="jquery" />
        <jqui:resources/>
        <script type="text/javascript" src="http://maps.google.com/maps/api/js?sensor=false"></script>
        <g:javascript src="application.js" />
        <g:javascript>var apppath="${resource(dir:'')}/"; </g:javascript>
    </head>
    <body>
        <div id="spinner" class="spinner" style="display:none;">
            <img src="${resource(dir:'images',file:'spinner.gif')}" alt="${message(code:'spinner.alt',default:'Loading...')}" />
        </div>
        <div id="grailsLogo">
            <img src="${resource(dir:'images',file:'mydwich_logo.png')}" alt="Have a nice dwich!" border="0" />
        </div>
        <div id="info" class="message" style="display:none">default message</div>
        <g:render template="/layouts/header" />
        <g:layoutBody />
        <g:render template="/layouts/footer" />
    </body>
</html>