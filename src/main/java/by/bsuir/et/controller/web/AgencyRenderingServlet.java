package by.bsuir.et.controller.web;

import by.bsuir.et.service.AgencyService;
import by.bsuir.et.service.AgencyServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AgencyRenderingServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        AgencyService agencyService = new AgencyServiceImpl();
        req.setAttribute("customers", agencyService.getCustomersList());
        req.getRequestDispatcher("/WEB-INF/pages/home.jsp").forward(req, resp);
    }
}
