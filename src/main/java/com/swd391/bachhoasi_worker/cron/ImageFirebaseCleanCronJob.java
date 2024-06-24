package com.swd391.bachhoasi_worker.cron;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ImageFirebaseCleanCronJob {
    @Scheduled(cron = "* * 23 * * ?", zone = "Asia/Ho_Chi_Minh")
    public void removeUnusedImage() {

    }
}
