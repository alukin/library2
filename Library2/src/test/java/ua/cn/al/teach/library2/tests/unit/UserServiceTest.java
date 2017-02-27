package ua.cn.al.teach.library2.tests.unit;


import java.util.List;
import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;
import ua.cn.al.teach.library2.jpa.Appuser;
import ua.cn.al.teach.library2.jpa.Userdetails;
import ua.cn.al.teach.library2.services.UserService;
import ua.cn.al.teach.library2.utils.EntityIdGenerator;

/**
 *
 * @author al
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)

public class UserServiceTest {
    @Autowired
    private UserService userServie;
    
    @Test
    public void findUserTest() throws Exception {
        int count = userServie.getAllUsers().size();
        assert(count>=6);
    } 
    
    @Test
    public void findByNameTest(){
        //List<Appuser> ul = userServie.findUserByName("Ivan", "Ivanov");
        List<Appuser> ul = userServie.findUserByName("Ivan", "Ivanow");
        int count = ul.size();
        assertEquals("Can not find pre-defined user",1, count);        
    }
    
    @Test 
    public void addUserTest(){
        Long user_id = EntityIdGenerator.random();
        Appuser au = new Appuser(user_id);
        Userdetails ud = new Userdetails(user_id);
        au.setCreatedBy("test");
        au.setUsername("test_username");
        au.setEmail("ttt@test.com");
        au.setPasswdHash("none");        
        ud.setFirstName("TestUserFirst");
        ud.setLastName("TestUserLast");
        //this one is not optional in JPA, fix it!
        ud.setNotes("none");
        au.setUserdetails(ud);
        userServie.addUser(au);
        Appuser u = userServie.getUserById(user_id);
        assertNotNull("New user not found!", u); 
        userServie.delUser(user_id);
        u = userServie.getUserById(user_id);
        assertNull("Can not delete user!", u); 
        
    }
}
