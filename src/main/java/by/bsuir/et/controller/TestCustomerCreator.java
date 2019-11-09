package by.bsuir.et.controller;

import by.bsuir.et.model.beans.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TestCustomerCreator {

    private static Date getDateFromString(String stringDate) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        try {
            return formatter.parse(stringDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static Date getTimeFromString(String stringTime) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm");
        try {
            return formatter.parse(stringTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Customer createTestCustomer1() {
        Tour tour1 = new Tour();
        tour1.setPrice(1000);
        tour1.setCountry("Egypt");
        tour1.setStartDate(getDateFromString("7-10-2019"));
        tour1.setEndDate(getDateFromString("21-10-2019"));
        tour1.setPersonAmount(1);
        tour1.setHotel(new Hotel("Royal Paradise Resort", "Egypt",
                "Sharm Al Shiekh, South Sinai Governorate"));
        tour1.setOutboundFlight(new Flight(Flight.FlightClass.ECONOMY, 1,
                getTimeFromString("7-10-2019 16:00"), getTimeFromString("7-10-2019 22:00"),
                "Minsk National Airport", "Sharm El Sheikh International Airport"));
        tour1.setReturnFlight(new Flight(Flight.FlightClass.ECONOMY, 1,
                getTimeFromString("21-10-2019 12:00"), getTimeFromString("21-10-2019 20:00"),
                "Sharm El Sheikh International Airport", "Minsk National Airport"));

        List<Event> events = new ArrayList<>();
        events.add(new Event("Party", "Qesm Sharm Ash Sheikh, South Sinai Governorate",
                getTimeFromString("9-10-2019 15:30")));
        events.add(new Event("Excursion", "1 Four Seasons Boulevard, Sharm El Sheikh, South Sinai Governorate",
                getTimeFromString("14-10-2019 8:30")));
        tour1.setEvents(events);
        List<Tour> tours = new ArrayList<>();
        tours.add(tour1);

        return new Customer("Jodie", "Jordan",
                "2674 Quitzon Creek Parkerfort, CT 72714", "077 2654 3599", tours);
    }

}
