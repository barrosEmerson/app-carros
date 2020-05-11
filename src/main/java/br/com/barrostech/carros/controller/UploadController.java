package br.com.barrostech.carros.controller;

import br.com.barrostech.carros.domain.UploadInput;
import br.com.barrostech.carros.domain.UploadOutput;
import br.com.barrostech.carros.service.FirebaseStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;


@RestController
@RequestMapping("/api/v1/upload")
public class UploadController {

    @Autowired
    private FirebaseStorageService uploadService;

    @PostMapping
    public ResponseEntity upload(@RequestBody UploadInput uploadInput)throws IOException{
        String url = uploadService.upload(uploadInput);

        return ResponseEntity.ok(new UploadOutput(url));
    }

}
