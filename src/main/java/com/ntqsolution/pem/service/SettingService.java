package com.ntqsolution.pem.service;

import com.ntqsolution.pem.entities.Setting;

public interface SettingService {
    Setting getValueByKey(String key);
}
