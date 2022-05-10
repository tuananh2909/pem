package com.ntqsolution.pem.service.impl;

import com.ntqsolution.pem.entities.Language;
import com.ntqsolution.pem.repository.LanguageRepository;
import com.ntqsolution.pem.service.LanguageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LanguageServiceImpl implements LanguageService {
    @Autowired
    private LanguageRepository languageRepository;

    @Override
    public List<Language> getAllLanguage() {
        return languageRepository.findAll();
    }
}
