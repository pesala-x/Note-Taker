package lk.ijse.pesala_x.notetasker.controller;

import lk.ijse.pesala_x.notetasker.dto.UserDTO;
import lk.ijse.pesala_x.notetasker.service.UserService;
import lk.ijse.pesala_x.notetasker.util.AppUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/users")
@RequiredArgsConstructor
public class UserController {
    @Autowired
    private UserService userService;
    //Save user
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

        //Todo: send to the service Layer
//        userService.saveUser(builduserDTO);

        return new ResponseEntity<>(userService.saveUser(builduserDTO), HttpStatus.CREATED);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") String userId){ // id kiyna path variable eka udin enne kiyla hadunwanawa
        return userService.deleteUser(userId) ? new ResponseEntity<>(HttpStatus.NO_CONTENT) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public UserDTO getSelectedUser (@PathVariable ("id") String userId){
        return userService.getSelectedUser(userId);
    }
    @PatchMapping("/{id}")
    public ResponseEntity<String> updateUser( @PathVariable ("userId") String userId, @RequestBody UserDTO userDTO){
        return userService.updateUser(userId, userDTO) ? new ResponseEntity<>(HttpStatus.NO_CONTENT) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
