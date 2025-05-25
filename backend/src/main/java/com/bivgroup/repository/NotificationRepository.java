package com.bivgroup.repository;

import com.bivgroup.entity.Notification;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.Date;
import java.util.List;

@ApplicationScoped
public class NotificationRepository implements PanacheRepository<Notification> {

    public List<Notification> findByPeriod(Date dateFrom, Date dateTo) {
        return find("createDate between ?1 AND ?2", dateFrom, dateTo).list();
    }
}
