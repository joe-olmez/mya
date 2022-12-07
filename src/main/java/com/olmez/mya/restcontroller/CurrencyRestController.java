package com.olmez.mya.restcontroller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.SecurityFilterChain;
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
@RequestMapping("/api/currency")
@RequiredArgsConstructor
public class CurrencyRestController {

    private final CurrencyService currencyService;

    // CREATE = POST
    @PostMapping("/add")
    public boolean addCurrencyInfo(@RequestBody CurrencyInfo info) {
        return currencyService.addCurrencyInfo(info);
    }

    // READ = GET
    @GetMapping("/all")
    public List<CurrencyInfo> getCurrencyInfos() {
        return currencyService.getCurrencyInfos();
    }

    // UPDATE = PUT
    @PutMapping("/update/{ciId}")
    public CurrencyInfo updateCurrencyInfo(@PathVariable("ciId") Long id, @RequestBody CurrencyInfo model) {
        return currencyService.updateCurrencyInfo(id, model);
    }

    // DELETE = DELETE
    @DeleteMapping("/delete/{ciId}")
    public boolean deleteCurrencyInfo(@PathVariable("ciId") Long id) {
        return currencyService.deleteCurrencyInfo(id);
    }

    //
    @GetMapping("/{ciId}")
    public CurrencyInfo getCurrencyInfoById(@PathVariable("ciId") Long id) {
        return currencyService.getCurrencyInfoById(id);
    }

    //
    @GetMapping("/{date}")
    public CurrencyInfo getCurrencyInfoByDate(@PathVariable("date") @DateTimeFormat(iso = ISO.DATE) LocalDate date) {
        return currencyService.getCurrencyInfoByDate(date);
    }

    /////////////////////////////////////////////////////////////////////////////
    @GetMapping()
    public ResponseEntity<String> hello() {
        return ResponseEntity.ok("Hello from our API");
    }

    @GetMapping("/say-good-bye")
    public ResponseEntity<String> goodBy() {
        return ResponseEntity.ok("Good by and see you later");
    }

}