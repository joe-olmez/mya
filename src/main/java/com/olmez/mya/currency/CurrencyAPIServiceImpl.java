package com.olmez.mya.currency;

import java.io.IOException;
import java.time.LocalDate;

import org.springframework.stereotype.Service;

import com.olmez.mya.currency.parser.CurrencyRoot;
import com.olmez.mya.model.CurrencyInfo;
import com.olmez.mya.utility.FileUtility;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class CurrencyAPIServiceImpl implements CurrencyAPIService {
    /**
     * only test purpose
     */
    @Getter
    @Setter
    private boolean testMode = false;
    @Getter
    @Setter
    private String testResource = "";

    @Override
    public CurrencyInfo update() throws IOException, InterruptedException {
        return update(LocalDate.now());
    }

    @Override
    public CurrencyInfo update(LocalDate date) throws InterruptedException, IOException {

        if (date == null) {
            date = LocalDate.now();
        }

        CurrencyUrl cUrl = new CurrencyUrl(date);
        String sourceUrl = isTestMode() ? getTestResource() : cUrl.getUrl();
        CurrencyRoot root = FileUtility.readFile(testMode, sourceUrl, CurrencyRoot.class);

        if (root == null || root.getUpdatedOn() == null || root.getUpdatedOn().isEmpty()) {
            log.info("Failed to received currency data.url:{}", sourceUrl);
            return null;
        }
        log.info("Received currency data. Update on {}", root.getUpdatedOn());
        return root.getCurrencyInfo();
    }

}