package com.codegym.service.bill;

import com.codegym.model.Bill;

import java.time.LocalDateTime;
import java.util.List;

public interface IBillService {
	boolean addBill(Bill bill);

	boolean updateBill(Bill bill);

	boolean removeBill(int id);

	Bill findBillById(int id);

	List<Bill> getListBill();

	int getId(LocalDateTime date);

	List<Bill> getListBillOfUserId(int id);
}
