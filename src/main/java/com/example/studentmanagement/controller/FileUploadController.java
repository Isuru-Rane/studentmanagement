package com.example.studentmanagement.controller;

import com.example.studentmanagement.Utility.FileUploadUtil;
import com.example.studentmanagement.models.User;
import com.example.studentmanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
public class FileUploadController {

    @Autowired
    UserService service;

    @PostMapping("/uploadFile")
    public ResponseEntity<User> uploadFile(@RequestParam("name") String name ,@RequestParam("email") String email, @RequestParam("psw") String psw, @RequestParam("file") MultipartFile multipartFile) throws IOException {

        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        String filecode = FileUploadUtil.saveFile(fileName, multipartFile);

        User user=new User();
        user.setName(name);
        user.setEmail(email);
        user.setPassword(psw);
        user.setProfilePic(filecode);
        return ResponseEntity.ok(service.create(user));
    }
}
