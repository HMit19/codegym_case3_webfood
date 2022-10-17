package com.codegym.service.bill;

import com.codegym.model.Bill;

import java.time.LocalDateTime;
import java.util.List;

public interface IBillService {
    public boolean addBill(Bill bill);
    public boolean updateBill(Bill bill);
    public boolean removeBill(String id);
    public Bill findBillById(String id);
    public List<Bill> getListBill();
    public int getId(LocalDateTime date);
}
