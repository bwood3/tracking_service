package com.example.tracking_service.controller;

import com.example.tracking_service.model.Invoice;
import com.example.tracking_service.model.InvoiceItem;
import com.example.tracking_service.model.Tracking;
import com.example.tracking_service.model.invoiceItemFields.Item;
import com.example.tracking_service.repository.TrackingRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tracking")
public class TrackingController {
    TrackingRepository repository;

    public TrackingController(TrackingRepository repository)
    {
        this.repository = repository;
    }

    @GetMapping("/{orderID}/{itemID}")
    public Item getTrackingStatus(@PathVariable int orderID , @PathVariable int itemID)
    {
        Invoice i = repository.getTrackingOrderByID(orderID);
        if(i == null)
            throw new IllegalStateException("404 error\norder id does not exist in the system");
        InvoiceItem invoiceItem = i.getInvoiceItem();
        Item item = invoiceItem.findItemById(itemID);
        if(item == null)
            throw new IllegalStateException("404 error\norder with item id does not exist in the system");
        return new Item();
    }

    @ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "order with this id does not exist in the system")
    public static class IdNotFoundException extends RuntimeException {
    }
}
