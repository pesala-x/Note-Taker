package lk.ijse.pesala_x.notetasker.service.impl;

import jakarta.transaction.Transactional;
import lk.ijse.pesala_x.notetasker.dao.UserDao;
import lk.ijse.pesala_x.notetasker.dto.NoteDTO;
import lk.ijse.pesala_x.notetasker.dto.UserDTO;
import lk.ijse.pesala_x.notetasker.entity.NoteEntity;
import lk.ijse.pesala_x.notetasker.entity.UserEntity;
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
        userDao.save(mapping.convertToUserEntity(userDTO));

//        return"User Saved Successfully..." + userDTO.getProfilePic();
        return "User Saved Successfully...";
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
public boolean updateUser(UserDTO userDTO) {
    Optional<UserEntity> tmpUser = userDao.findById(userDTO.getUserId());
    if(!tmpUser.isPresent()){
        return false;
    }else {
        tmpUser.get().setFirstName(userDTO.getFirstName());
        tmpUser.get().setLastName(userDTO.getLastName());
        tmpUser.get().setEmail(userDTO.getEmail());
        tmpUser.get().setPassword(userDTO.getPassword());
        tmpUser.get().setProfilePic(userDTO.getProfilePic());
        userDao.save(tmpUser.get());
    }
    return true;
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
    public UserDTO getSelectedUser(String userId) {
        UserEntity userEntitiesByUserId = userDao.getUserEntitiesByUserId(userId);
        return mapping.convertToUserDTO(userEntitiesByUserId);
    }


    @Override
    public List<UserDTO> getAllUsers() {
        List<UserEntity> getAllUsers = userDao.findAll();
        return mapping.convertUserToDTOList(getAllUsers);
    }
}
