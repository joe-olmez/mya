package com.olmez.mya.services;

import java.io.IOException;

public interface ScheduledService {

    void scheduledDailyUpdateCurrencyData() throws IOException, InterruptedException;

}
