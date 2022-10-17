package com.codegym.service.bill;

import com.codegym.model.Bill;
import com.codegym.model.Item;
import com.codegym.model.User;
import com.codegym.service.DatabaseHandle;
import com.codegym.service.user.UserService;

import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BillService implements IBillService {
    DatabaseHandle databaseHandle = null;

    public BillService() {
        databaseHandle = new DatabaseHandle();
    }

    @Override
    public boolean addBill(Bill bill) {
        boolean checkAdd = false;
        try {
            checkAdd = databaseHandle.addRecord("bill", "date_bill, detail, status, id",
                    "'" + bill.getDate_bill() + "', '" + bill.getDetail() + "', " + bill.isStatus() + ", '" + bill.getUser().getId() + "'");
            System.out.println("Add bill status: " + checkAdd);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return checkAdd;
    }

    @Override
    public boolean updateBill(Bill bill) {
        return false;
    }

    @Override
    public boolean removeBill(String id) {
        return false;
    }

    @Override
    public Bill findBillById(String id) {
        return null;
    }



    @Override
    public List<Bill> getListBill() {
        List<Bill> listBill = null;
        try {
            listBill = new ArrayList<>();
            ResultSet result = databaseHandle.getRecords("*", "bill", "");
            while (result.next()) {
                int idBill = result.getInt("id_bill");
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                LocalDateTime date = LocalDateTime.parse(result.getString("date_bill"), formatter);
                String detail = result.getString("detail");
                boolean status = Integer.valueOf(result.getString("status")) > 0;
                int id = result.getInt("id");
                User user = new UserService().findUserById(id);
                List<Item> items = new ArrayList<>();
                listBill.add(new Bill(idBill, items, date, detail, status, user));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listBill;
    }

    @Override
    public int getId(LocalDateTime date) {
        int id = 0;
        try {
            ResultSet result = databaseHandle.getRecords("*", "bill", "date_bill = '" + date + "'");
            while (result.next()) {
                id = result.getInt("id_bill");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return id;
    }

    // test
    public static void main(String[] args) {

        // test get list bill
        BillService billService = new BillService();
        System.out.println(billService.getListBill());

        // test add bill
        // formatter date time ve dang yyyy-MM-dd HH:mm:ss
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//        // sau do lay thoi gian hien tai va format theo dang tren
//        LocalDateTime date = LocalDateTime.parse(LocalDateTime.now().format(formatter), formatter);
//        System.out.println("date " + date);
//        Bill bill = new Bill(0, null, date, "test", true, 1);
//        System.out.println(bill);
//        System.out.println(billService.addBill(bill));
//        test getId
        System.out.println(billService.getId(LocalDateTime.parse("2022-10-17T09:52:13")));
    }
}
