package com.swd391.bachhoasi_worker.cron;

import java.sql.Date;
import java.time.LocalDate;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.swd391.bachhoasi_worker.model.entity.QOrder;
import com.swd391.bachhoasi_worker.repository.OrderRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class MetricReportCronJob {
    private OrderRepository orderRepository;
    @Scheduled(cron = "* 23 * * * *")
    public void makeDailyMetricReport() {
        var qOrder = QOrder.order;
        var today = LocalDate.now();
        LocalDate yesterday = today.minusDays(1);
        var order = orderRepository.findAll(qOrder.createdDate.between(Date.valueOf(yesterday), Date.valueOf(today)));
    }
}
