package com.mac_vendor.mac_vendor.controllers;


import com.mac_vendor.mac_vendor.services.Upload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.IOException;

@Controller
public class UploadController {
    private final Upload uploadService;

    @Autowired
    public UploadController(Upload uploadService) {
        this.uploadService = uploadService;
    }

    @PostMapping("/upload")
    void uploadFIle(@RequestBody MultipartFile file) throws IOException {
        uploadService.saveDataFromExcelToDatabase((FileInputStream) file.getInputStream());
    }
}
