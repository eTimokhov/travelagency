<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>Hotel information</title>
  </head>
  <body>
  <table>
    <tr><td>Id: ${hotel.id}</td></tr>
    <tr><td>Name: ${hotel.name}</td></tr>
    <tr><td>Country: ${hotel.country}</td></tr>
    <tr><td>Address: ${hotel.address}</td></tr>
  </table>
  </body>
</html>
