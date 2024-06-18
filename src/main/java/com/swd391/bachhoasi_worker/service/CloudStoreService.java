package com.swd391.bachhoasi_worker.service;

import org.springframework.web.multipart.MultipartFile;


public interface CloudStoreService {
    public String save (MultipartFile file);
    public void delete (String name);
}
