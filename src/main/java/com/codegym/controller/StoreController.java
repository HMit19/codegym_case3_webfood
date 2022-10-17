package com.codegym.controller;

import com.codegym.model.Bill;
import com.codegym.model.Item;
import com.codegym.model.Product;
import com.codegym.model.User;
import com.codegym.service.bill.BillService;
import com.codegym.service.item.ItemService;
import com.codegym.service.product.ProductService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

@WebServlet(name = "Servlet4", urlPatterns = "/stores")
public class StoreController extends HttpServlet {
    ProductService productService = null;
    BillService billService = null;
    ItemService itemService = null;

    public void init() {
        productService = new ProductService();
        billService = new BillService();
        itemService = new ItemService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        try {
            switch (action) {
                case "cart":
                    break;
                case "pay":
                    payBill(request, response);
                    break;
                default:
                    viewStore(request, response);
                    break;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void payBill(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        Map<Integer, List<Item>> order = (Map<Integer, List<Item>>) request.getSession().getAttribute("order");
        User user = (User) request.getSession().getAttribute("Member");
        List<Item> items = order.get(user.getId());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime date = LocalDateTime.parse(LocalDateTime.now().format(formatter), formatter);
        Bill bill = new Bill(items, date, true, user);
        RequestDispatcher dispatcher = request.getRequestDispatcher("store/cart.jsp");
        System.out.println(bill);
        if (billService.addBill(bill)) {
            for (Item item : order.get(user.getId())) {
                // lay ra id cua bill vua them
                int id = billService.getId(date);
                item.setId_bill(id);
                itemService.addItem(item);
            }
            order.get(user.getId()).clear();
            session.setAttribute("order", order);
            request.setAttribute("status", "success");
        } else {
            request.setAttribute("status", "fail");
        }
        try {
            dispatcher.forward(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }


    private void viewStore(HttpServletRequest request, HttpServletResponse response) {
        RequestDispatcher dispatcher = request.getRequestDispatcher("store/home-store.jsp");
        List<Product> listProduct;
        try {
            listProduct = productService.getListFood();
            request.setAttribute("products", listProduct);
            dispatcher.forward(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        try {
            switch (action) {
                case "cart":
                    break;
                default:
                    viewStore(request, response);
                    break;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
