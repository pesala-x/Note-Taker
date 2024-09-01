package lk.ijse.pesala_x.notetasker.dao;

import lk.ijse.pesala_x.notetasker.entity.NoteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NoteDao extends JpaRepository<NoteEntity, String> {

}
