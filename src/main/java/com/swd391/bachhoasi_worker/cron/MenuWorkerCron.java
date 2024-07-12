package com.swd391.bachhoasi_worker.cron;

import java.sql.Date;
import java.util.AbstractMap;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.swd391.bachhoasi_worker.model.entity.Admin;
import com.swd391.bachhoasi_worker.model.entity.Menu;
import com.swd391.bachhoasi_worker.model.entity.QAdmin;
import com.swd391.bachhoasi_worker.model.entity.QMenu;
import com.swd391.bachhoasi_worker.model.entity.StoreLevel;
import com.swd391.bachhoasi_worker.model.entity.StoreType;
import com.swd391.bachhoasi_worker.repository.AdminRepository;
import com.swd391.bachhoasi_worker.repository.MenuRepository;
import com.swd391.bachhoasi_worker.repository.StoreLevelRepository;
import com.swd391.bachhoasi_worker.repository.StoreTypeRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class MenuWorkerCron {
    private final MenuRepository menuRepository;
    private final AdminRepository adminRepository;
    private final StoreLevelRepository storeLevelRepository;
    private final StoreTypeRepository storeTypeRepository;
    private final JPAQueryFactory queryFactory;
    private final Logger logger = LoggerFactory.getLogger(MenuWorkerCron.class);

    @Scheduled(cron = "1 * * * * *")
    public void createMenu() {
        var storeLevel = storeLevelRepository.findAll();
        var storeType = storeTypeRepository.findAll();
        var menuList = createMenuList(storeLevel, storeType);
        try {
            menuRepository.saveAll(menuList);
            logger.info(String.format("Import Menu Success At: %s", new Date(System.currentTimeMillis())));
        } catch (Exception e) {
            logger.info(String.format("Import Menu Failed At: %s With Reason: %s", new Date(System.currentTimeMillis()), e.getMessage()));
        }
    }


    public List<Menu> createMenuList (Collection<StoreLevel> storeLevels, Collection<StoreType> storeTypes) {
        var bot = adminRepository.findOne(QAdmin.admin.username.eq("")).orElseThrow();
        var queryCheck = QMenu.menu.storeLevel.in(storeLevels).and(QMenu.menu.storeType.in(storeTypes));
        var menuFound = queryFactory.selectFrom(QMenu.menu).where(queryCheck).fetch();
        Map<AbstractMap.SimpleEntry<StoreLevel, StoreType>, Menu> existingMenusMap = menuFound.stream()
            .collect(Collectors.toMap(menu -> new AbstractMap.SimpleEntry<>(menu.getStoreLevel(), menu.getStoreType()), Function.identity()));
        return storeLevels.stream()
        .flatMap(storeLevel -> storeTypes.stream()
                .map(storeType -> new AbstractMap.SimpleEntry<>(storeLevel, storeType)))
        .filter(entry -> !existingMenusMap.containsKey(entry))
        .map(entry -> createMenuEntity(entry.getKey(), entry.getValue(), bot))
        .collect(Collectors.toList());
    }
    private Menu createMenuEntity(StoreLevel storeLevel, StoreType storeType, Admin admin) {
        var currentDate = new Date(System.currentTimeMillis());
        return Menu.builder()
        .storeLevel(storeLevel)
        .storeType(storeType)
        .createdDate(currentDate)
        .updatedDate(currentDate)
        .createdBy(admin)
        .updatedBy(admin)
        .build();
    }
}
