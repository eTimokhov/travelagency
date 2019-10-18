package by.bsuir.et.model.dao;

import by.bsuir.et.model.beans.Tour;

import java.util.List;

public interface TourDao {
    Tour getTour(String id);
    List<Tour> getTours();
    void saveTour(Tour tour);
    void deleteTour(String id);
}
