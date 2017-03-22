/*
 * 
 * 
 */
package ua.cn.al.teach.library2.auth;

import org.springframework.security.core.GrantedAuthority;
import ua.cn.al.teach.library2.jpa.Appuser;


/**
 *
 * @author al
 */
public class UserAuthority implements GrantedAuthority{
    private String autority; 
    
    public UserAuthority(AuthorityName n) {
        autority = n.name();
    }
      
    @Override
    public String getAuthority() {
       return autority;
    }
    
}
