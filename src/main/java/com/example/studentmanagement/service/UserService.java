package com.example.studentmanagement.service;

import com.example.studentmanagement.dto.MailDto;
import com.example.studentmanagement.dto.UserDto;
import com.example.studentmanagement.exceptions.http.BadRequestException;
import com.example.studentmanagement.exceptions.http.UnauthorizedException;
import com.example.studentmanagement.exceptions.http.UserExceptions.ProfilePictureException;
import com.example.studentmanagement.exceptions.http.UserNotFoundException;
import com.example.studentmanagement.exceptions.user.UserExType;
import com.example.studentmanagement.models.User;
import com.example.studentmanagement.repository.UserRepository;
import com.example.studentmanagement.security.helper.Hash;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
public class UserService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private MailService mailService;

    @Autowired
    private JavaMailSender mailSender;

    final static String USER_PROFILE = "User/Desktop/Treinetic";

    final String[] imageExtensions = {"image/jpg", "image/jpeg", "image/png", "image/gif", "image/bmp", "image/tiff", "image/webp", "image/svg", "image/ico", "image/raw"};



    public User login(UserDto userDto){
        Optional<User> user = repository.findAllByEmail(userDto.getEmail());
        if (user.isEmpty()){
            throw new UnauthorizedException("Invalid Credentials");
        }
        if (!Hash.match(userDto.getPassword(),user.get().getPassword())){
            throw new UnauthorizedException("Invalid Credentials");
        }
        return user.get();
    }
    public List<User> getUserList(){
        return repository.findAll();
    }


    public User create(UserDto userDto){
        User user = UserDto.init(userDto);

        if (repository.findAllByEmail(userDto.getEmail()).isPresent()){
            throw  new BadRequestException("email already exist", UserExType.EMAIL_ALREADY_EXIST);
        }

        if (userDto.getProfilePicture()!=null){
            if (!isValid(userDto)) {
                throw new ProfilePictureException("Provided image is not valid",UserExType.PROFILE_PICTURE_NOT_VALID);
            }
            user.setProfilePicture(UUID.randomUUID().toString());




            try {
                String fileName = StringUtils.cleanPath(userDto.getProfilePicture().getOriginalFilename());
                String fileExtension = StringUtils.getFilenameExtension(fileName);
                String newFileName = user.getProfilePicture()+"."+fileExtension;
                Path directoryPath = Paths.get(USER_PROFILE+"/"+user.getId());

                Files.createDirectories(directoryPath);
                Path path = directoryPath.resolve(newFileName);
                byte[] bytes = userDto.getProfilePicture().getBytes();
                Files.write(path,bytes);
            }catch (Exception e){
                throw new RuntimeException(e.getCause());
            }
        }
        user.setPassword(Hash.make(userDto.getPassword()));
        user =  repository.save(user);
        log.info("create user. email {}",userDto.getEmail());

        /*EmailService emailService=new EmailService();
        if (userDto.getAttachment()!=null){
            emailService.sendMailWithAttachment(user.getEmail(),javaMailSender, userDto.getAttachment());
        }else {
            emailService.Mail(user.getEmail(),javaMailSender);
        }*/

        MailService emailService=new MailService();
        emailService.sendSimpleMail(user.getEmail(),mailSender);
        return user;
    }

    public Optional<User>findById(int id){
        if(repository.findById(id).isEmpty()){
            throw  new UserNotFoundException("This Id Not Valid", UserExType.USER_NOT_FOUND);
        }
        return repository.findById(id);
    }

    public void delete(Integer id){
        Optional<User> userOptional = repository.findById(id);
        if (userOptional.isEmpty()){
            throw new RuntimeException("user not found");
        }
        repository.delete(userOptional.get());
    }

    public Optional<User> findUserById(Integer id){
        return repository.findById(id);
    }

    public Boolean isValid(UserDto userDto){
        boolean valid=false;
        for (String string:imageExtensions) {
            if (Objects.equals(userDto.getProfilePicture().getContentType(), string)){
                valid=true;
            }
        }
        return valid;
    }




}
