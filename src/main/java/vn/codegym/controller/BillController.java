package vn.codegym.controller;

import vn.codegym.database.dao.bill.BillService;
import vn.codegym.database.dao.item.ItemService;
import vn.codegym.database.dao.user.UserService;
import vn.codegym.database.model.Bill;
import vn.codegym.database.model.Item;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "Servlet6", value = "/bills")
public class BillController extends HttpServlet {
	private BillService billService;
	private ItemService itemService;
	private UserService userService;

	public void init() {
		billService = new BillService();
		itemService = new ItemService();
		userService = new UserService();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		if (action == null) {
			action = "";
		}
		try {
			switch (action) {
				case "delete":
					deleteBill(request, response);
					break;
				case "bill":
					showBillOfUser(request, response);
					break;
				case "bill-details":
					showBillDetails(request, response);
					break;
				default:
					listBills(request, response);
					break;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void showBillDetails(HttpServletRequest request, HttpServletResponse response) {
		int idBill = Integer.parseInt(request.getParameter("id"));
		Bill bill = billService.findBillById(idBill);
		List<Item> items = itemService.getListItemInBill(idBill);
		long total = 0;
		for (Item item : items) {
			total += item.getQuantity() * item.getPrice();
		}
		bill.setTotal(total);
		request.setAttribute("bill", bill);
		request.setAttribute("items", items);
		try {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/views/bill/bill-details.jsp");
			dispatcher.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void showBillOfUser(HttpServletRequest request, HttpServletResponse response) {
		int id = Integer.parseInt(request.getParameter("id"));
		List<Bill> listBill = billService.getListBillOfUserId(id);
		setTotalOfBill(listBill);
		request.setAttribute("listBills", listBill);
		RequestDispatcher dispatcher = request.getRequestDispatcher("views/bill/list.jsp");
		try {
			dispatcher.forward(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void setTotalOfBill(List<Bill> listBill) {
		for (Bill bill : listBill) {
			bill.setItems(itemService.getListItemInBill(bill.getId_bill()));
			System.out.println(bill);
			List<Item> items = itemService.getListItemInBill(bill.getId_bill());
			long total = 0;
			for (Item item : items) {
				total += item.getQuantity() * item.getPrice();
			}
			bill.setTotal(total);
		}
	}

	private void listBills(HttpServletRequest request, HttpServletResponse response) {
		List<Bill> listBill = billService.getListBill();
		setTotalOfBill(listBill);
		request.setAttribute("listBills", listBill);
		RequestDispatcher dispatcher = request.getRequestDispatcher("views/bill/list.jsp");
		try {
			dispatcher.forward(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// co ban se xoa cac item co trong bill roi se xoa bill
	private void deleteBill(HttpServletRequest request, HttpServletResponse response) {
		int idBill = Integer.parseInt(request.getParameter("id"));
		RequestDispatcher dispatcher = request.getRequestDispatcher("bills?action=list");
		try {
			if (itemService.removeItem(idBill) && billService.removeBill(idBill)) {
				request.setAttribute("status", "success");
			} else {
				request.setAttribute("status", "fail");
			}
			dispatcher.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}
}
