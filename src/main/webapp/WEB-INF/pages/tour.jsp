<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>Tour information</title>
  </head>
  <body>
  <table>
    <tr><td>Id: ${tour.id}</td></tr>
    <tr><td>Price: ${tour.price}</td></tr>
    <tr><td>Country: ${tour.country}</td></tr>
    <tr><td>Start date: ${tour.startDate}</td></tr>
    <tr><td>End date: ${tour.endDate}</td></tr>
    <tr><td>Person Amount: ${tour.personAmount}</td></tr>
    <tr><td>Hotel: <a href="${pageContext.request.contextPath}/hotel?id=${tour.hotel.id}">${tour.hotel.name}</a></td></tr>
    <tr><td>Is hot: ${tour.hot}</td></tr>
    <tr><td>Outbound flight:
      <a href="${pageContext.request.contextPath}/flight?id=${tour.outboundFlight.id}">
        ${tour.outboundFlight.flightClass}–${tour.outboundFlight.departureTime}–${tour.outboundFlight.departureAirport}
      </a>
    </td></tr>
    <tr><td>Return flight:
      <a href="${pageContext.request.contextPath}/flight?id=${tour.returnFlight.id}">
        ${tour.returnFlight.flightClass}–${tour.returnFlight.departureTime}–${tour.returnFlight.departureAirport}
      </a>
    </td></tr>
    <tr><td>Events:</td></tr>

     <c:forEach items="${tour.events}" var="event">
       <tr><td><a href="${pageContext.request.contextPath}/event?id=${event.id}">${event.name}</a></td></tr>
     </c:forEach>
  </table>
  </body>
</html>
