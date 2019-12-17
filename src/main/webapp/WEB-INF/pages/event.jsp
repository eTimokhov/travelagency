<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>Event information</title>
  </head>
  <body>
  <table>
    <tr><td>Id: ${event.id}</td></tr>
    <tr><td>Name: ${event.name}</td></tr>
    <tr><td>Address: ${event.address}</td></tr>
    <tr><td>Date: ${event.date}</td></tr>
  </table>
  </body>
</html>
