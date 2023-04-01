package com.example.tracking_service.controller;

import com.example.tracking_service.handler.ErrorResponse;
import com.example.tracking_service.model.Invoice;
import com.example.tracking_service.model.InvoiceItem;
import com.example.tracking_service.model.Tracking;
import com.example.tracking_service.model.invoiceItemFields.Item;
import com.example.tracking_service.repository.TrackingRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

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
            throw new ResourceNotFoundException("order id does not exist in the system");
        InvoiceItem invoiceItem = i.getInvoiceItem();
        Item item = invoiceItem.findItemById(itemID);
        if(item == null)
            throw new ResourceNotFoundException("order with item id does not exist in the system");
        return item;
    }

    public class ResourceNotFoundException extends RuntimeException {
        public ResourceNotFoundException(String message) {
            super(message);
        }
    }
    @ControllerAdvice
    public class ExceptionHandlerController {

        @ExceptionHandler(TrackingController.ResourceNotFoundException.class)
        @ResponseStatus(HttpStatus.NOT_FOUND)
        @ResponseBody
        public ErrorResponse handleResourceNotFoundException(TrackingController.ResourceNotFoundException ex) {
            return new ErrorResponse(ex.getMessage(), LocalDateTime.now());
        }
    }

    @ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "order with this id does not exist in the system")
    public static class IdNotFoundException extends RuntimeException {
    }

    @PutMapping("/{orderID}")
    public void updateTracking(@RequestBody Tracking t, @PathVariable int orderID)
    {
        System.out.println(t.getItemId());
        System.out.println(t.getStatus());

        repository.update(t, orderID);
    }
}
