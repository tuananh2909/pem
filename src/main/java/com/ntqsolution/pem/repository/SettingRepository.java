package com.ntqsolution.pem.repository;

import com.ntqsolution.pem.entities.Setting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

@Repository
public interface SettingRepository extends JpaRepository<Setting, String> {
    Setting findSettingsByKeyContainingIgnoreCase(@Param("active") String key);
}
