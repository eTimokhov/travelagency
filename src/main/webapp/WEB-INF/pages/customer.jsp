<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>Customer information</title>
  </head>
  <body>
  <table>
    <tr><td>Id: ${customer.id}</td></tr>
    <tr><td>First name: ${customer.firstName}</td></tr>
    <tr><td>Last name: ${customer.lastName}</td></tr>
    <tr><td>Address: ${customer.address}</td></tr>
    <tr><td>Phone number: ${customer.phoneNumber}</td></tr>
    <tr><td>Tours:
       <c:forEach items="${customer.tours}" var="tour">
         <a href="${pageContext.request.contextPath}/tour?id=${tour.id}">${tour.country} – ${tour.startDate}</a><br>
       </c:forEach></td>
    </tr>
  </table>
  </body>
</html>
