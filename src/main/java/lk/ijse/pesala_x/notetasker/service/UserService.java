package lk.ijse.pesala_x.notetasker.service;

import lk.ijse.pesala_x.notetasker.CustomOBJ.UserResponse;
import lk.ijse.pesala_x.notetasker.dto.UserDTO;

import java.util.List;

public interface UserService {
     String saveUser(UserDTO userDTO);
    void updateUser(UserDTO userDTO);
    boolean deleteUser(String userId);
    UserResponse getSelectedUser(String userId);
    List<UserDTO> getAllUsers();
}
