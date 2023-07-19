package com.example.studentmanagement.service;

import com.example.studentmanagement.dto.UserDto;
import com.example.studentmanagement.exceptions.http.BadRequestException;
import com.example.studentmanagement.exceptions.user.UserExType;
import com.example.studentmanagement.models.User;
import com.example.studentmanagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    final static String USER_PROFILE = "User/Desktop/Treinetic";

    public List<User> getUserList(){
        return repository.findAll();
    }


    public User create(UserDto userDto){

        if (repository.findAllByEmail(userDto.getEmail()).isPresent()){
            throw  new BadRequestException("email already exist", UserExType.EMAIL_ALREADY_EXIST);
        }

        User user = UserDto.init(userDto);
        user.setProfilePicture(UUID.randomUUID().toString());
        user =  repository.save(user);


        try {
            String fileName = StringUtils.cleanPath(userDto.getProfilePicture().getOriginalFilename());
            String fileExtension = StringUtils.getFilenameExtension(fileName);
            String newFileName = user.getProfilePicture()+"."+fileExtension;
            Path directoryPath = Paths.get(USER_PROFILE+"/"+user.getId()); // +"/"+newFileName

            Files.createDirectories(directoryPath);
            Path path = directoryPath.resolve(newFileName);
            byte[] bytes = userDto.getProfilePicture().getBytes();
            Files.write(path,bytes);
        }catch (Exception e){
            throw new RuntimeException(e.getCause());
        }
        return user;
    }

    public Optional<User>findById(int id){
        return repository.findById(id);
    }


    public Optional<User> findAllByEmail(User user){
        return repository.findAllByEmail(user.getEmail());
    }


}
