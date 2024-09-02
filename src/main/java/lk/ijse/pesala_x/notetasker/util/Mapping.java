package lk.ijse.pesala_x.notetasker.util;

import lk.ijse.pesala_x.notetasker.dto.NoteDTO;
import lk.ijse.pesala_x.notetasker.dto.UserDTO;
import lk.ijse.pesala_x.notetasker.entity.NoteEntity;
import lk.ijse.pesala_x.notetasker.entity.UserEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Mapping {

    //Mapping
    @Autowired
    private ModelMapper modelMapper;

    //matters of noteEntity and DTO
    public NoteDTO convertToDTO (NoteEntity note){
        return modelMapper.map(note, NoteDTO.class);
    }
    public NoteEntity convertToEntity(NoteDTO dto) {
        return modelMapper.map(dto, NoteEntity.class);
    }
    public List<NoteDTO> convertToDTO(List<NoteEntity> notes) {
        return modelMapper.map(notes, List.class);
    }

    //User matters mapping
    public UserEntity convertToUserEntity(UserDTO userDTO) {
        return modelMapper.map(userDTO, UserEntity.class);
    }
    public UserDTO convertToUserDTO(UserEntity userEntity) {
        return modelMapper.map(userEntity, UserDTO.class);
    }
    public List<UserDTO> convertUserToDTO(List<UserEntity> userEntities) {
        return modelMapper.map(userEntities, List.class);
    }

}
