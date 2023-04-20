package com.example.tracking_service.controller;

import com.example.tracking_service.model.Tracking;
import com.example.tracking_service.model.UpdateTracking;
import com.example.tracking_service.repository.TrackingRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/tracking")
public class TrackingController {
    private TrackingRepository repository;

    public TrackingController(TrackingRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/{orderId}/{itemId}")
    public List<Tracking> find(@PathVariable int orderId,
                               @PathVariable int itemId){
        List<Tracking> trackingList = repository.findByOrderIdAndItemId(orderId, itemId);

        if (trackingList.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Order item with this id does not exist in the system");
        }

        return repository.findByOrderIdAndItemId(orderId, itemId);
    }

    @PutMapping("/{orderId}")
    public Tracking updateTrackingStatus(
            @PathVariable int orderId,
            @RequestBody UpdateTracking request) {

        List<Tracking> trackingList = repository.findByOrderIdAndItemId(orderId, request.getItemId());
        if (trackingList.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Order item with this id does not exist in the system");
        }

        Tracking tracking = trackingList.get(0);
        tracking.setStatus(request.getStatus());
        return repository.save(tracking);
    }

}
