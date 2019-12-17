package by.bsuir.et.controller.web;

import by.bsuir.et.model.beans.Event;
import by.bsuir.et.model.beans.Hotel;
import by.bsuir.et.model.parser.DataSourceException;
import by.bsuir.et.service.AgencyService;
import by.bsuir.et.service.AgencyServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class EventRenderingServlet extends HttpServlet {

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
        Event event;
        try {
            event = agencyService.getEvent(id);
        } catch (DataSourceException e) {
            e.printStackTrace();
            redirectToErrorPage(req, resp, "Xml file is invalid");
            return;
        }

        if (event == null) {
            redirectToErrorPage(req, resp, "Event not found");
            return;
        }
        req.setAttribute("event", event);
        req.getRequestDispatcher("/WEB-INF/pages/event.jsp").forward(req, resp);
    }

    private void redirectToErrorPage(HttpServletRequest req, HttpServletResponse resp, String message) throws ServletException, IOException {
        req.setAttribute("errorMsg",message);
        req.getRequestDispatcher("/WEB-INF/pages/error.jsp").forward(req, resp);
    }
}
