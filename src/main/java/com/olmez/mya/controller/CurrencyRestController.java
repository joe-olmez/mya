package com.olmez.mya.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.olmez.mya.currency.CurrencyService;
import com.olmez.mya.model.CurrencyInfo;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/currency")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200") // This allows to talk to port:5000 (ui-backend)
public class CurrencyRestController {

    private final CurrencyService currencyService;

    // READ = GET
    @GetMapping("/all")
    public List<CurrencyInfo> getCurrencyInfos() {
        return currencyService.getCurrencyInfos();
    }

    // CREATE = POST
    @PostMapping("/add")
    public boolean addCurrencyInfo(@RequestBody CurrencyInfo info) {
        return currencyService.addCurrencyInfo(info);
    }

    // UPDATE = PUT
    @PutMapping("/update/{id}")
    public CurrencyInfo updateCurrencyInfo(@PathVariable("id") Long id, @RequestBody CurrencyInfo model) {
        return currencyService.updateCurrencyInfo(id, model);
    }

    // DELETE = DELETE
    @DeleteMapping("/delete/{id}")
    public boolean deleteCurrencyInfo(@PathVariable("id") Long id) {
        return currencyService.deleteCurrencyInfo(id);
    }

    // To be implemented
    // //////////////////////////////////////////////////////////////////////////////////////////////////////////~
    @GetMapping("/{id}")
    public CurrencyInfo getCurrencyInfoById(@PathVariable("id") Long id) {
        return currencyService.getCurrencyInfoById(id);
    }

    // // To be implemented
    @GetMapping("/{date}")
    public CurrencyInfo getCurrencyInfoByDate(@PathVariable("date") @DateTimeFormat(iso = ISO.DATE) LocalDate date) {
        return currencyService.getCurrencyInfoByDate(date);
    }

    /////////////////////////////////////////////////////////////////////////////
    @GetMapping("test")
    public ResponseEntity<String> hello() {
        return ResponseEntity.ok("Hello from my api. Thi is a test message!!!");
    }

    @GetMapping()
    public ResponseEntity<String> goodBy() {
        return ResponseEntity.ok("No specific path for this. This is a test!!!");
    }

}