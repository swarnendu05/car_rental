package com.realProject.controller;

import com.realProject.service.S3Service1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;

@RestController
@RequestMapping("/api/files")
public class FileUploadController {

    private final S3Service1 s3Service1;

    @Autowired
    public FileUploadController(S3Service1 s3Service1) {
        this.s3Service1 = s3Service1;
    }

    @PostMapping("/upload")
    public ResponseEntity<List<String>> uploadFiles(@RequestParam("files") List<MultipartFile> files) {
        List<String> uploadedUrls = s3Service1.uploadFiles(files);
        return ResponseEntity.ok(uploadedUrls);
    }
}
