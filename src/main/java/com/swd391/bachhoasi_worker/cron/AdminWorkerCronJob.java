package com.swd391.bachhoasi_worker.cron;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.swd391.bachhoasi_worker.model.constant.ShipperStatus;
import com.swd391.bachhoasi_worker.model.entity.Shipper;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaUpdate;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class AdminWorkerCronJob {
    private final EntityManager em;
    @Scheduled(cron = "* 1 * * * *", zone = "Asia/Ho_Chi_Minh")
    public void changeReviewStatusForShipper() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        // update shipper which over ex day and on process to failed
        CriteriaUpdate<Shipper> shipperUpdateQuery = cb.createCriteriaUpdate(Shipper.class);
        Root<Shipper> shipper = shipperUpdateQuery.from(Shipper.class);
        List<Predicate> predicates = new ArrayList<>();
        shipperUpdateQuery.set(shipper.get("status"), ShipperStatus.DENIED);
        predicates.add(cb.equal(shipper.get("status"), ShipperStatus.ON_PROCESSED));
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -2); // Subtract 2 days
        Date twoDaysAgo = cal.getTime();
        predicates.add(cb.lessThan(shipper.get("createdDate"), twoDaysAgo));
        shipperUpdateQuery.where(cb.or(predicates.toArray(new Predicate[0])));

        int updateCount = em.createQuery(shipperUpdateQuery).executeUpdate();
    }
}
