package com.doguy.restaurant.controllers;


import com.doguy.restaurant.domain.dto.PhotoDto;
import com.doguy.restaurant.domain.entities.Photo;
import com.doguy.restaurant.mappers.PhotoMapper;
import com.doguy.restaurant.services.PhotoService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.MediaTypeFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/photos")
public class PhotoController {

    private final PhotoService photoService;
    private final PhotoMapper photoMapper;


    @PostMapping
    public PhotoDto uploadPhoto( @RequestParam("file") MultipartFile file ) {
        Photo savedPhoto = photoService.uploadPhoto(file);
        return photoMapper.toDto(savedPhoto);
    }

    @GetMapping(path = "/{id:.+}")
    public ResponseEntity<Resource> getPhoto(@PathVariable String id) {
        return photoService.getPhotoAsResource(id)
                .map(photo -> ResponseEntity.ok()
                        .contentType(MediaTypeFactory.getMediaType(photo)
                                .orElse(MediaType.APPLICATION_OCTET_STREAM))
                        .header(HttpHeaders.CONTENT_DISPOSITION, "inline")
                        .body(photo))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
