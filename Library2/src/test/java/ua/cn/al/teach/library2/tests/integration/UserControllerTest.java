package ua.cn.al.teach.library2.tests.integration;

import static org.junit.Assert.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import ua.cn.al.teach.library.api.AddUserRequest;
import ua.cn.al.teach.library.api.LibUser;
import ua.cn.al.teach.library.api.UserListReply;

/**
 *
 * @author al
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;
        
    @Test
    public void findUserTest() throws Exception {
        this.mockMvc.perform(get("/users/byid/1"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("librarian1")));
    }
    
    @Test
    public void addUserTest() throws Exception{
        AddUserRequest rq = new AddUserRequest();
        rq.user = new LibUser();
        rq.user.firstName = "Test1First";
        rq.user.isLibrarian = true;
        rq.user.lastName ="Test1Last";
        rq.user.login ="test_user_1";
        rq.user.email ="test@test.com";
        
        ObjectMapper om = new ObjectMapper();
        String content = om.writeValueAsString(rq);

        MvcResult result = mockMvc.perform(post("/users/add")
                 .accept(MediaType.APPLICATION_JSON_UTF8)
                 .contentType(MediaType.APPLICATION_JSON_UTF8)
                 .content(content)
         )
           .andExpect(status().isOk())
         .andReturn();
         
        String reply = result.getResponse().getContentAsString();
        UserListReply ur = om.readValue(reply, UserListReply.class);
        assertEquals("Reurn code in not 0",ur.retcode.longValue(), 0L);
        if(ur.retcode==0){
            mockMvc.perform(delete("/users/del/"+ur.users.get(0).user_id)
                                  .accept(MediaType.APPLICATION_JSON_UTF8)
                           )
                    .andExpect(status().isOk());
                    
            
        }
    }
}
