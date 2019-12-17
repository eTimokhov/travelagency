<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>Flight information</title>
  </head>
  <body>
  <table>
    <tr><td>Id: ${flight.id}</td></tr>
    <tr><td>Class: ${flight.flightClass}</td></tr>
    <tr><td>Seats: ${flight.seats}</td></tr>
    <tr><td>Departure airport: ${flight.departureAirport}</td></tr>
    <tr><td>Arrival airport: ${flight.arrivalAirport}</td></tr>
    <tr><td>Departure time: ${flight.departureTime}</td></tr>
    <tr><td>Arrival time: ${flight.arrivalTime}</td></tr>
  </table>
  </body>
</html>
