package com.oyameen.SpringBootWebServiceBasics.controller;

import com.oyameen.SpringBootWebServiceBasics.model.Mobile;
import com.oyameen.SpringBootWebServiceBasics.service.MobileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
public class MobilesController {

    @Autowired
    private MobileService mobileService;

    @PostMapping(value = "/mobile")
    public ResponseEntity<Mobile> addMobile(@RequestBody Mobile mobile) {
        return ResponseEntity.status(201).body(mobileService.saveMobile(mobile));
    }

    @PutMapping(value = "/mobile")
    public ResponseEntity<Mobile> updateMobile(@RequestBody Mobile mobile) {
        Mobile mobileResult = mobileService.updateMobile(mobile);
        if (mobileResult != null)
            return ResponseEntity.ok(mobileResult);
        return ResponseEntity.notFound().build();
    }

    @GetMapping(value = "/mobile")
    public ResponseEntity<List<Mobile>> getMobiles() {
        return ResponseEntity.ok(mobileService.getMobiles());
    }

    @GetMapping(value = "/mobile/{id}")
    public ResponseEntity<Mobile> getMobile(@PathVariable("id") Long id) {
        Mobile mobile = mobileService.getMobile(id);
        if (mobile != null) {
            return ResponseEntity.ok(mobile);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping(value = "/mobile/{id}")
    public ResponseEntity<Mobile> deleteMobile(@PathVariable("id") Long id) {
        mobileService.deleteMobile(id);
        return ResponseEntity.noContent().build();
    }
}
