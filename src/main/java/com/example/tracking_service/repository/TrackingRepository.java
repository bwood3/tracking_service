package com.example.tracking_service.repository;

import com.example.tracking_service.model.Invoice;
import com.example.tracking_service.model.InvoiceItem;
import com.example.tracking_service.model.invoiceItemFields.Item;
import com.example.tracking_service.model.invoiceItemFields.ShippingAddress;
import com.example.tracking_service.model.paymentFields.BillingAddress;
import com.example.tracking_service.model.paymentFields.Payment;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class TrackingRepository {
    private List<Invoice> invoices = loadTempDatabase();

    public Invoice getTrackingOrderByID(int id)
    {
        Invoice r = invoices.stream().filter(x -> x.getOrderID() == id).findAny().orElse(null);
        return r;
    }
    private List<Invoice> loadTempDatabase()
    {
        Invoice i = new Invoice();
        i.setOrderPlaced("3/4/2023");
        i.setTotal(60.26);
        i.setOrderID(1);
        InvoiceItem invoiceItem = new InvoiceItem();
        invoiceItem.setStatus("shipping now");
        List<Item> itemList = new ArrayList<>();
        Item item1 = new Item();
        item1.setItemId(1);
        item1.setItem("Tea");
        item1.setQuantity(2);
        item1.setPrice(24.84);
        item1.setItemStatus("waiting to ship");
        item1.setShipDate("N/A");
        Item item2 = new Item();
        item2.setItemId(2);
        item2.setItem("Turmeric Paste");
        item2.setQuantity(1);
        item2.setPrice(22.99);
        item2.setItemStatus("shipped");
        item2.setShipDate("3/28/2023");
        itemList.add(item1);
        itemList.add(item2);
        invoiceItem.setItems(itemList);
        invoiceItem.setStatus("shipping now");
        invoiceItem.setOn("3/28/2023");
        ShippingAddress shippingAddress = new ShippingAddress();
        shippingAddress.setState("Indiana");
        shippingAddress.setCity("Bloomington");
        shippingAddress.setPostalCode(47408);
        invoiceItem.setShippingAddress(shippingAddress);
        Payment payment = new Payment();
        payment.setMethod("Discover");
        payment.setNumber("12345678");
        BillingAddress billingAddress = new BillingAddress();
        billingAddress.setState("Indiana");
        billingAddress.setCity("Bloomington");
        billingAddress.setPostalCode(47408);
        i.setPayment(payment);
        i.setInvoiceItem(invoiceItem);

        ArrayList<Invoice> dummyData = new ArrayList<>();
        dummyData.add(i);
        return dummyData;
    }
}
