package com.olmez.mya.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    // GET
    @GetMapping("/all")
    public List<CurrencyInfo> getCurrencyInfos() {
        return currencyService.getCurrencyInfos();
    }

    // CREATE
    @PostMapping("/add")
    public boolean addCurrencyInfo(@RequestBody CurrencyInfo info) {
        return currencyService.addCurrencyInfo(info);
    }

    // UPDATE with Path
    @PutMapping("/update/{id}")
    public CurrencyInfo updateCurrencyInfoWithPath(@PathVariable("id") Long id, @RequestBody CurrencyInfo model) {
        return currencyService.updateCurrencyInfo(id, model);
    }

    // UPDATE with Param
    @PutMapping("/update")
    public CurrencyInfo updateCurrencyInfoWithParam(@RequestParam() Long id, @RequestBody CurrencyInfo model) {
        return currencyService.updateCurrencyInfo(id, model);
    }

    // DELETE with Path
    @DeleteMapping("/delete/{id}")
    public boolean deleteCurrencyInfoWithPath(@PathVariable("id") Long id) {
        return currencyService.deleteCurrencyInfo(id);
    }

    // DELETE with Param
    @DeleteMapping("/delete")
    public boolean deleteCurrencyInfoWithParam(@RequestParam Long id) {
        return currencyService.deleteCurrencyInfo(id);
    }

    // GET with Path
    @GetMapping("/{id}")
    public CurrencyInfo getCurrencyInfoByIdWithPath(@PathVariable("id") Long id) {
        return currencyService.getCurrencyInfoById(id);
    }

    // GET with Param
    @GetMapping("/")
    public CurrencyInfo getCurrencyInfoByIdWithParam(@RequestParam("id") Long id) {
        return currencyService.getCurrencyInfoById(id);
    }

    // GET with Param
    // @GetMapping("/")
    // public CurrencyInfo getCurrencyInfoByDate(@RequestParam("date")
    // @DateTimeFormat(iso = ISO.DATE) LocalDate date) {
    // return currencyService.getCurrencyInfoByDate(date);
    // }

    @GetMapping("/test")
    public ResponseEntity<String> hello() {
        return ResponseEntity.ok("Hello!");
    }

}