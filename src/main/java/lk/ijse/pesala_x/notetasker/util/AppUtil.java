package lk.ijse.pesala_x.notetasker.util;

import org.modelmapper.ModelMapper;

import java.util.Base64;
import java.util.UUID;

public class AppUtil {

    public static String generateNoteId(){
        return "NOTE-"+UUID.randomUUID();
    }

    //Mapping
    //Note Details
    public static String generateUserId(){
        return "USER-"+UUID.randomUUID();
    }

    public static String toBase64ProfilePic(String profilePic){
        return Base64.getEncoder().encodeToString(profilePic.getBytes());
    }

}
