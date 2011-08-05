<!DOCTYPE html>
<html>
<head>
    <title><g:layoutTitle default="Grails" /></title>
    <%--<link rel="stylesheet" href="${resource(dir:'css',file:'main.css')}" />--%>
    <link rel="stylesheet" href="http://www.immani.com/mydwich/css/main.css" />
    <link rel="shortcut icon" href="${resource(dir:'images',file:'favicon.ico')}" type="image/x-icon" />
    <g:layoutHead />
    <g:javascript library="jquery" plugin="jquery" />
    <jqui:resources/>
    <script type="text/javascript" src="http://maps.google.com/maps/api/js?sensor=false"></script>
    <g:javascript src="application.js" />
    <g:javascript src="liquid.js" />
    <g:javascript src="iscroll.js" />
    <script type="text/javascript">var apppath="${resource(dir:'')}/"</script>
</head>
<body>
<g:render template="/layouts/header" />
<div id="fbContainer">
    <div id="wrapper">
        <g:layoutBody />
    </div>
</div>
<g:render template="/layouts/footer" />
</body>
</html>