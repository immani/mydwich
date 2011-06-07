<%--
  Created by IntelliJ IDEA.
  User: majaxish
  Date: 6/6/11
  Time: 11:25 AM
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
  <head><title>Simple GSP page</title></head>
  <body>

  <FORM METHOD="post" ACTION="https://secure.ogone.com/ncol/test/orderstandard.asp" id=form1 name=form1>
      <INPUT type="hidden" NAME="AMOUNT" value="1500">
      <INPUT type="hidden" NAME="CURRENCY" value="EUR">
      <INPUT type="hidden" NAME="LANGUAGE" value="en_US">
      <INPUT type="hidden" NAME="ORDERID" value="2">
      <INPUT type="hidden" NAME="PSPID" value="immanitest">
      <INPUT type="hidden" NAME="SHASIGN" value="B6D3386E82EE33BFB2439671F7096B55878222E7">
      <input type="submit" value="submit to test" id=submit2 name=submit2>
  </form>




  </body>
</html>