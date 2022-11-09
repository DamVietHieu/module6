package com.team.case6.core.controller;

import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletContext;
import java.io.IOException;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/image")
public class ImageController {
    @Autowired
    ServletContext context;

    @Value("${file-upload-system}")
    private String uploadPath;
    @Value("${file-upload-user}")
    private String uploadPathUser;
    @Value("${file-upload-blog}")
    private String uploadPathBlog;
    @Value("${file-upload-category}")
    private String uploadPathCategory;

//xem anh bang link localhost8080:/Image/ten anh
    @GetMapping(value = "/system/{nameImage}",
            produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<InputStreamResource> getImage(@PathVariable String nameImage) throws IOException {

        var imgFile = new ClassPathResource(uploadPath +nameImage);

        return ResponseEntity
                .ok()
                .contentType(MediaType.IMAGE_PNG)
                .body(new InputStreamResource(imgFile.getInputStream()));
    }

    @GetMapping(value = "/user/{nameImage}",
            produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<InputStreamResource> getImageUser(@PathVariable String nameImage) throws IOException {

        var imgFile = new ClassPathResource(uploadPathUser +nameImage);

        return ResponseEntity
                .ok()
                .contentType(MediaType.IMAGE_PNG)
                .body(new InputStreamResource(imgFile.getInputStream()));
    }

    @GetMapping(value = "/blog/{nameImage}",
            produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<InputStreamResource> getImageBlog(@PathVariable String nameImage) throws IOException {

        var imgFile = new ClassPathResource(uploadPathBlog +nameImage);

        return ResponseEntity
                .ok()
                .contentType(MediaType.IMAGE_PNG)
                .body(new InputStreamResource(imgFile.getInputStream()));
    }
    @GetMapping(value = "/category/{nameImage}",
            produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<InputStreamResource> getImageCategory(@PathVariable String nameImage) throws IOException {

        var imgFile = new ClassPathResource(uploadPathCategory +nameImage);

        return ResponseEntity
                .ok()
                .contentType(MediaType.IMAGE_PNG)
                .body(new InputStreamResource(imgFile.getInputStream()));
    }
}
