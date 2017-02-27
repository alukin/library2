/*
 * 
 * 
 */
package ua.cn.al.teach.library2.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ua.cn.al.teach.library.api.AddUserRequest;
import ua.cn.al.teach.library.api.GenericReply;
import ua.cn.al.teach.library.api.UserListReply;
import ua.cn.al.teach.library2.jpa.Appuser;
import ua.cn.al.teach.library2.services.UserMapper;
import ua.cn.al.teach.library2.services.UserService;

/**
 *
 * @author al
 */
@RestController
public class UsersController {
private static final Logger logger =  LoggerFactory.getLogger(UsersController.class);
    @Autowired         
    UserService userService;
    @Autowired
    UserMapper userMapper;    
    
    @RequestMapping(path="/users/all",  method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public UserListReply getAllUsers(){
        UserListReply reply = new UserListReply();
        for(Appuser au: userService.getAllUsers()){
           reply.users.add(userMapper.fromInternal(au));    
        }
        return reply;
    }
    @RequestMapping(path="/users/byid/{userid}",  method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public UserListReply getUserById(@PathVariable Long userid ){
        UserListReply reply = new UserListReply();
        reply.users.add(userMapper.fromInternal(userService.getUserById(userid)));
        return reply;
    }
    
    @RequestMapping(path="/users/add",  method=RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public UserListReply addUser( @RequestBody AddUserRequest req){
        UserListReply rep = new UserListReply();
        try{
           Appuser au;
           au = userService.addUser(userMapper.toInternal(req.user));
           rep.users.add(userMapper.fromInternal(au));
        }catch(Exception e){
            rep.retcode = -1;
            rep.error_message = e.getMessage();
            logger.error("Error adding user. Expetion: "+e.getMessage(),e);
        }
        return rep;
    } 
    
    @RequestMapping(path="/users/del/{userid}",  method=RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public GenericReply delUser(@PathVariable Long userid ){
            GenericReply rep = new GenericReply();
        try{
            userService.delUser(userid);
        }catch(Exception e){
            rep.retcode = -1;
            rep.error_message = e.getMessage();
            logger.error("Error adding user. Expetion: "+e.getMessage(),e);
        }
        return rep;       
    }
}
