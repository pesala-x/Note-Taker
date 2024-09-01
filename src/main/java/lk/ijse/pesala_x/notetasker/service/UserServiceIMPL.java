package lk.ijse.pesala_x.notetasker.service;

import jakarta.transaction.Transactional;
import lk.ijse.pesala_x.notetasker.dto.UserDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class UserServiceIMPL implements UserService{
    @Override
    public String saveUser(UserDTO userDTO) {
        return "";
    }

    @Override
    public boolean updateUser(String userId, UserDTO userDTO) {
        return false;
    }

    @Override
    public boolean deleteUser(String userId) {
        return false;
    }

    @Override
    public UserDTO getSelectedUser(String userId) {
        return null;
    }

    @Override
    public List<UserDTO> getAllUsers() {
        return List.of();
    }
}
