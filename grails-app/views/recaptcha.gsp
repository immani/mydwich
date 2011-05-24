<%--
  Created by IntelliJ IDEA.
  User: ts
  Date: 29/04/11
  Time: 10:58
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head><title>Simple GSP page</title></head>
<body>Place your content here
<g:form action="save" controller="registration" method="post" >

    <recaptcha:ifEnabled>
        <recaptcha:recaptcha theme="clean"/>
        <recaptcha:ifFailed>CAPTCHA Failed</recaptcha:ifFailed>
    </recaptcha:ifEnabled><br />

    <g:if test="${recaptcha}">
        "captcha succeeded"
    </g:if>
    <g:actionSubmit value="Submit" action="save" />

</g:form>
</body>
</html>