package com.swd391.bachhoasi_worker.cron;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.swd391.bachhoasi_worker.model.constant.Role;
import com.swd391.bachhoasi_worker.model.constant.ShipperStatus;
import com.swd391.bachhoasi_worker.model.entity.Admin;
import com.swd391.bachhoasi_worker.model.entity.QAdmin;
import com.swd391.bachhoasi_worker.model.entity.Shipper;
import com.swd391.bachhoasi_worker.repository.AdminRepository;

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
    private final AdminRepository adminRepository;
    @Value("${bot.username}")
    private String botUsername;
    @Value("${bot.password}")
    private String botPassword;

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
    @EventListener
    public void updateBotUserAccount(ApplicationReadyEvent event) {
        var currentAccount = adminRepository.findOne(QAdmin.admin.username.eq(botUsername)).orElse(Admin.builder()
        .username(botUsername)
        .hashPassword(botPassword)
        .fullName("BOT")
        .role(Role.ADMIN)
        .isActive(false)
        .isLocked(false)
        .build());
        adminRepository.save(currentAccount);
    }
}
