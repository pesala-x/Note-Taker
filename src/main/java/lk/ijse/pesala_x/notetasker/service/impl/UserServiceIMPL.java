package lk.ijse.pesala_x.notetasker.service.impl;

import jakarta.transaction.Transactional;
import lk.ijse.pesala_x.notetasker.CustomOBJ.UserErrorResponse;
import lk.ijse.pesala_x.notetasker.CustomOBJ.UserResponse;
import lk.ijse.pesala_x.notetasker.dao.UserDao;
import lk.ijse.pesala_x.notetasker.dto.NoteDTO;
import lk.ijse.pesala_x.notetasker.dto.UserDTO;
import lk.ijse.pesala_x.notetasker.entity.NoteEntity;
import lk.ijse.pesala_x.notetasker.entity.UserEntity;
import lk.ijse.pesala_x.notetasker.exception.UserNotFoundException;
import lk.ijse.pesala_x.notetasker.service.UserService;
import lk.ijse.pesala_x.notetasker.util.AppUtil;
import lk.ijse.pesala_x.notetasker.util.Mapping;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceIMPL implements UserService {

    @Autowired
    private final UserDao userDao;

    @Autowired
    private final Mapping mapping;

    @Override
    public String saveUser(UserDTO userDTO) {
        userDTO.setUserId(AppUtil.generateUserId());
        UserEntity savedUser =
                userDao.save(mapping.convertToUserEntity(userDTO));
        if(savedUser != null && savedUser.getUserId() != null ) {
            return "User saved successfully";
        }else {
            return "Save failed";
        }
    }

    /*@Override
    public boolean updateUser(String userId, UserDTO incomminguserDTO) {
        Optional<UserEntity> tempUserEntity = userDao.findById(userId);
        if (!tempUserEntity.isPresent()) {
            return false;
        }else {
            tempUserEntity.get().setFirstName(incomminguserDTO.getFirstName());
            tempUserEntity.get().setLastName(incomminguserDTO.getLastName());
            tempUserEntity.get().setEmail(incomminguserDTO.getEmail());
            tempUserEntity.get().setPassword(incomminguserDTO.getPassword());
            tempUserEntity.get().setProfilePic(incomminguserDTO.getProfilePic());
        }
        return true;
    }*/
public void updateUser(UserDTO userDTO) {
    Optional<UserEntity> tmpUser = userDao.findById(userDTO.getUserId());
    if(!tmpUser.isPresent()){
        throw new UserNotFoundException("User not found");
    }else {
        tmpUser.get().setFirstName(userDTO.getFirstName());
        tmpUser.get().setLastName(userDTO.getLastName());
        tmpUser.get().setEmail(userDTO.getEmail());
        tmpUser.get().setPassword(userDTO.getPassword());
        tmpUser.get().setProfilePic(userDTO.getProfilePic());
        userDao.save(tmpUser.get());
    }
}
    @Override
    public boolean deleteUser(String userId) {
        if (userDao.existsById(userId)) {
            userDao.deleteById(userId);
            return true;
        }else {
            return false;
        }
    }

    @Override
    public UserResponse getSelectedUser(String userId) {
        if(userDao.existsById(userId)){
            UserEntity userEntityByUserId = userDao.getUserEntitiesByUserId(userId);
            return mapping.convertToUserDTO(userEntityByUserId);
        }else {
            return new UserErrorResponse(0, "User not found");
        }
    }


    @Override
    public List<UserDTO> getAllUsers() {
        List<UserEntity> getAllUsers = userDao.findAll();
        return mapping.convertUserToDTOList(getAllUsers);
    }
}
