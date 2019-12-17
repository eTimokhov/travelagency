package by.bsuir.et.controller.web;

import by.bsuir.et.model.beans.Customer;
import by.bsuir.et.model.beans.Tour;
import by.bsuir.et.service.AgencyService;
import by.bsuir.et.service.AgencyServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;

public class TourRenderingServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String strId = req.getParameter("id");
        if (strId == null) {
            redirectToErrorPage(req, resp, "Id not specified.");
            return;
        }
        Integer id = null;
        try {
            id = Integer.parseInt(strId);
        } catch (NumberFormatException e) {
            redirectToErrorPage(req, resp, "Incorrect Id format");
            return;
        }
        AgencyService agencyService = new AgencyServiceImpl();
        Tour tour = agencyService.getTour(id);

        if (tour == null) {
            redirectToErrorPage(req, resp, "Tour not found");
            return;
        }
        req.setAttribute("tour", tour);
        req.getRequestDispatcher("/WEB-INF/pages/tour.jsp").forward(req, resp);
    }

    private void redirectToErrorPage(HttpServletRequest req, HttpServletResponse resp, String message) throws ServletException, IOException {
        req.setAttribute("errorMsg",message);
        req.getRequestDispatcher("/WEB-INF/pages/error.jsp").forward(req, resp);
    }
}
