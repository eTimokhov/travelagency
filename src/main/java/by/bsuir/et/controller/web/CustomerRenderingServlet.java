package by.bsuir.et.controller.web;

import by.bsuir.et.model.beans.Customer;
import by.bsuir.et.service.AgencyService;
import by.bsuir.et.service.AgencyServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CustomerRenderingServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        AgencyService agencyService = new AgencyServiceImpl();
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
        Customer customer = agencyService.getCustomer(id);
        if (customer == null) {
            redirectToErrorPage(req, resp, "Customer not found");
            return;
        }
        req.setAttribute("customer", customer);
        req.getRequestDispatcher("/WEB-INF/pages/customer.jsp").forward(req, resp);
    }

    private void redirectToErrorPage(HttpServletRequest req, HttpServletResponse resp, String message) throws ServletException, IOException {
        req.setAttribute("errorMsg",message);
        req.getRequestDispatcher("/WEB-INF/pages/error.jsp").forward(req, resp);
    }
}
