package com.ntqsolution.pem.service.impl;

import com.ntqsolution.pem.entities.Setting;
import com.ntqsolution.pem.repository.SettingRepository;
import com.ntqsolution.pem.service.SettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SettingServiceImpl implements SettingService {
    @Autowired
    private SettingRepository settingRepository;

    @Override
    public Setting getValueByKey(String key) {
        return settingRepository.findSettingsByKeyContainingIgnoreCase(key);
    }
}
