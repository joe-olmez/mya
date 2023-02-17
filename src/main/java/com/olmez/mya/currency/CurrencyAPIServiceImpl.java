package com.olmez.mya.currency;

import java.io.IOException;
import java.time.LocalDate;

import org.springframework.stereotype.Service;

import com.olmez.mya.currency.parser.CurRoot;
import com.olmez.mya.model.CurrencyRate;
import com.olmez.mya.model.TestMode;
import com.olmez.mya.utility.FileUtility;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CurrencyAPIServiceImpl implements CurrencyAPIService {

    /**
     * For testing purposes
     */
    @Setter
    private TestMode testMode = new TestMode();

    @Override
    public CurrencyRate update() throws IOException, InterruptedException {
        return update(LocalDate.now());
    }

    @Override
    public CurrencyRate update(LocalDate date) throws InterruptedException, IOException {

        CurRoot root = null;
        String source = null;
        if (testMode.isTest()) {
            source = testMode.getResource();
            root = FileUtility.readFileOnTestMode(source, CurRoot.class);
        } else {
            CurrencyUrl url = new CurrencyUrl(date);
            source = url.getUrl();
            root = FileUtility.readFile(source, CurRoot.class);
        }

        if (root == null || root.getUpdatedOn() == null || root.getUpdatedOn().isEmpty()) {
            log.info("Failed to received currency data.url:{}", source);
            return null;
        }
        log.info("Received currency data. Update on {}", root.getUpdatedOn());
        return root.createCurrencyRate();
    }

}