<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>$Title$</title>
  </head>
  <body>
  <table>
  <c:forEach items="${customers}" var="customer">
    <tr>
      <td>${customer.id}</td>
      <td><a href="${pageContext.request.contextPath}/customer?id=${customer.id}">${customer.firstName} ${customer.lastName}</a></td>
    </tr>
  </c:forEach>
  </table>
  </body>
</html>
