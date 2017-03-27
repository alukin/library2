/*
 * 
 * 
 */
package ua.cn.al.teach.library2.auth;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ua.cn.al.teach.library2.jpa.Appuser;
import ua.cn.al.teach.library2.jpa.Ugroup;

/**
 *
 * @author al
 */
public class AuthUser implements UserDetails{
    private final Appuser user;
    Collection<UserAuthority> authorities = new ArrayList<>();
    
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }
    
    public Appuser getAppUser(){
        return user;
    }
    
    public AuthUser(Appuser user){
        this.user = user;
        List<Ugroup> groups = user.getUgroupList();
        authorities.add(new UserAuthority(AuthorityName.ROLE_USER));
        for(Ugroup g: groups){
            if(g.getGroupId()<10L){
                authorities.add(new UserAuthority(AuthorityName.ROLE_ADMIN));
            }
            if(g.getGroupId()<100L){
                authorities.add(new UserAuthority(AuthorityName.ROLE_LIBRARIAN));
            }
        }
    }
    
    @Override
    public String getPassword() {
        return user.getPasswdHash();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
       return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
    
    public Date getLastPasswordResetDate(){
        return user.getLastPasswordResetDate();
    }
}
