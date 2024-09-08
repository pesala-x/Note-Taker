package lk.ijse.pesala_x.notetasker.controller;

import lk.ijse.pesala_x.notetasker.dto.NoteDTO;
import lk.ijse.pesala_x.notetasker.dto.UserDTO;
import lk.ijse.pesala_x.notetasker.exception.UserNotFoundException;
import lk.ijse.pesala_x.notetasker.service.UserService;
import lk.ijse.pesala_x.notetasker.util.AppUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/users")
@RequiredArgsConstructor
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/health") // http://localhost:8080/NoteTaker/api/v1/users/health
    public String healthCheck(){
        return "Note Taker User is Running successfully...";
    }
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> saveUser (
            @RequestPart("firstName") String firstName,// single request part annotation
            @RequestPart("lastName") String lastName,
            @RequestPart("email") String email,
            @RequestPart("password") String password,
            @RequestPart("profilePic") String profilePic){

        //Todo: Handle profile picture
        String base64ProfilePic = AppUtil.toBase64ProfilePic(profilePic);
        //Todo: Build the user object
        UserDTO builduserDTO = new UserDTO();
        builduserDTO.setFirstName(firstName);
        builduserDTO.setLastName(lastName);
        builduserDTO.setEmail(email);
        builduserDTO.setPassword(password);
        builduserDTO.setProfilePic(base64ProfilePic);

//        userService.saveUser(builduserDTO);

        //send to the service layer
        var saveStatus = userService.saveUser(builduserDTO);
        if (saveStatus.contains("User saved successfully")){
            return new ResponseEntity<>(HttpStatus.CREATED);
        }else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") String userId){ // id kiyna path variable eka udin enne kiyla hadunwanawa
        return userService.deleteUser(userId) ? new ResponseEntity<>(HttpStatus.NO_CONTENT) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public UserDTO getSelectedUser (@PathVariable ("id") String userId){
        return userService.getSelectedUser(userId);
    }

    @GetMapping(value = "allusers", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<UserDTO>getAllUsers(){
        return userService.getAllUsers();
    }
    @PatchMapping(value = "/{id}",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> updateUser(
            @PathVariable ("id") String id,
            @RequestPart("updateFirstName") String updateFirstName,
            @RequestPart ("updateLastName") String updateLastName,
            @RequestPart ("updateEmail") String updateEmail,
            @RequestPart ("updatePassword") String updatePassword,
            @RequestPart ("updateProfilePic") String updateProfilePic
    ){
        try {

            String updateBase64ProfilePic = AppUtil.toBase64ProfilePic(updateProfilePic);
            var updateUser = new UserDTO();
            updateUser.setUserId(id);
            updateUser.setFirstName(updateFirstName);
            updateUser.setLastName(updateLastName);
            updateUser.setPassword(updatePassword);
            updateUser.setEmail(updateEmail);
            updateUser.setProfilePic(updateBase64ProfilePic);
            userService.updateUser(updateUser);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (UserNotFoundException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    /* // this is for save user using jason file
        @PatchMapping("/{id}")
    public ResponseEntity<String> updateUser( @PathVariable ("id") String userId, @RequestBody UserDTO userDTO){
        System.out.println( userId +"user Update Succesfully");
        return userService.updateUser(userId, userDTO) ? new ResponseEntity<>(HttpStatus.NO_CONTENT) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }*/
}
