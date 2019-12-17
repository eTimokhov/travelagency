package by.bsuir.et.model.parser;

import by.bsuir.et.model.beans.Agency;
import by.bsuir.et.model.beans.Customer;
import by.bsuir.et.model.beans.Tour;
import by.bsuir.et.service.AgencyService;
import by.bsuir.et.service.AgencyServiceImpl;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class TourService {
    public List<Tour> getToursList(List<Customer> customers) {
        return customers.stream()
                .map(Customer::getTours)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }

    public Tour getTour(List<Customer> customers, int id) {
        return getToursList(customers).stream()
                .filter(t -> t.getId() == id)
                .findAny()
                .orElse(null);
    }
}
