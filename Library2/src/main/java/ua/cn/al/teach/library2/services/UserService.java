/*
 * 
 * 
 */
package ua.cn.al.teach.library2.services;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.crypto.codec.Hex;
import org.springframework.stereotype.Service;
import ua.cn.al.teach.library2.jpa.Appuser;
import ua.cn.al.teach.library2.jpa.Ugroup;
import ua.cn.al.teach.library2.jpa.Userdetails;
import ua.cn.al.teach.library2.repository.UserRepository;
import ua.cn.al.teach.library2.repository.UserdetailsRepository;

/**
 *
 * @author al
 */
@Service
public class UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    UserRepository userRepository;
    @Autowired
    UserdetailsRepository detailsRepository;

    public List<Appuser> getAllUsers() {
        return userRepository.findAll();
    }

    public Appuser getUserById(Long id) {
        Appuser u = userRepository.findOne(id);
        return u;
    }

    public List<Appuser> findUserByName(String firstName, String lastName) {
        List<Userdetails> udl = detailsRepository.findByFirstNameAndLastName(firstName, lastName);
        List<Appuser> res = new ArrayList<>();
        udl.forEach((ud) -> {
            res.add(userRepository.findOne(ud.getUserId()));
        });
        return res;
    }

    @Secured({"ROLE_LIBRARIAN","ROLE_DMIN"})
    public Appuser addUser(Appuser au) {
        logger.debug("Adding users %s with id %s", au.getUsername(), au.getUserId());
        au = userRepository.save(au);
        return au;
    }

    @Secured({"ROLE_LIBRARIAN","ROLE_DMIN"})
    public void delUser(Long id) {
        Appuser u = userRepository.findOne(id);
        if (u != null) {
            logger.debug("Deleting users %s with id %s", u.getUsername(), u.getUserId());
            List<Ugroup> gl = u.getUgroupList();
            gl.clear();
            detailsRepository.delete(id);
            userRepository.delete(id);
        }
    }

    @Secured({"ROLE_LIBRARIAN","ROLE_DMIN"})
    public Appuser updateUser(Appuser appuser) {
        appuser = userRepository.save(appuser);
        return appuser;
    }
    
    public Appuser authUser(String login, String password) {
        Appuser appuser = userRepository.findByUsername(login);
        if (appuser != null) {
            if( ! appuser.getPasswdHash().equalsIgnoreCase(digest(password))){
                appuser = null;
                logger.debug("Invalid password");
            }
            logger.debug("Login ok");
        }else{
            logger.debug("User not found");            
        }
        return appuser;
    }

    public static String digest(String original) {
        String res = "";
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(original.getBytes());
            byte[] digest = md.digest();
            res = new String(Hex.encode(digest));
        } catch (NoSuchAlgorithmException ex) {
           logger.error("Can not create SHA-256 digester");
        }
        return res;
    }
}
