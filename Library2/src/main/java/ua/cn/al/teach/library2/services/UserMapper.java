package ua.cn.al.teach.library2.services;
;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.cn.al.teach.library.api.LibUser;
import ua.cn.al.teach.library2.jpa.Appuser;
import ua.cn.al.teach.library2.jpa.Ugroup;
import ua.cn.al.teach.library2.jpa.Userdetails;
import ua.cn.al.teach.library2.repository.UgroupRepository;
import ua.cn.al.teach.library2.repository.UserRepository;
import ua.cn.al.teach.library2.repository.UserdetailsRepository;
import ua.cn.al.teach.library2.utils.EntityIdGenerator;

/**
 *
 * @author al
 */
@Component

public class UserMapper {
private static final Logger logger =  LoggerFactory.getLogger(UserMapper.class);

    public static final Long LIBRARIANS_GROUP_ID = 1L;
    @Autowired
    UserRepository userRepository;
    @Autowired
    UserdetailsRepository detailsRepository;
    @Autowired
    UgroupRepository groupRepository;
    
/**
 * Maps internal JPA model to external REST model
 * @param u innternal user model
 * @return external REST user model
 */
    public LibUser fromInternal(Appuser u) {
        LibUser lu = null;
        if (u != null) {
            lu = new LibUser();
            Userdetails ud = u.getUserdetails();
            lu.isLibrarian = u.getUserId() < 100;
            lu.login = u.getUsername();
            lu.user_id = u.getUserId();
            if (ud != null) {
                lu.firstName = u.getUserdetails().getFirstName();
                lu.lastName = u.getUserdetails().getLastName();
            }
        }
        return lu;
    }
/**
 * Creates new Appuser and Userdeatils with good Id
 * @return newly created Appuser with required fields set
 */
    private Appuser newUser() {
        //TODO: get logged user from security context
        String createdBy = "REST";
        Appuser au = new Appuser();
        Userdetails ud = new Userdetails();
        boolean idOK = false;
        Long id = 0L;
        while (!idOK) {
            id = EntityIdGenerator.random();
            idOK = !userRepository.exists(id);
        }
        //notNull
        ud.setNotes("none");
        au.setPasswdHash("*");
        au.setCreatedBy(createdBy);
        au.setUserId(id);
        ud.setUserId(id);
        au.setUserdetails(ud);
        return au;
    }
    
/**
 * Maps extrernal REST model to internal Appuser;
 * If user does not exists in DB then creates new. If user already exists
 * then fetches user from DB and sets all fields from external REST model
 * @param lu REST model
 * @return internal Appuser with all required fields set
 */
    public Appuser toInternal(LibUser lu) {
        Appuser au = null;
        //first, check if it exists
        if (lu.user_id != null) {
            au = userRepository.findOne(lu.user_id);
        }
        if (au == null) { //not found, create new
            logger.debug("Creating new user");
            au = newUser();
        }
        logger.debug("Updating existing user");
        au.setUsername(lu.login);
        au.getUserdetails().setFirstName(lu.firstName);
        au.getUserdetails().setLastName(lu.lastName);
        au.setEmail(lu.email);
        if (lu.isLibrarian) {
            Ugroup g = groupRepository.findOne(LIBRARIANS_GROUP_ID);
            g.getAppuserList().add(au);
            au.getUgroupList().add(g);
        }
        return au;
    }
}
