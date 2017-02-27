/*
 * 
 * 
 */
package ua.cn.al.teach.library.api;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author al
 */
@XmlRootElement
public class LibUser {
    @XmlElement(required=false)
    public Long user_id;
    @XmlElement(required=true)
    public String login;
    @XmlElement(required=true)
    public String firstName;
    @XmlElement(required=true)
    public String lastName;
    @XmlElement(required=true)
    public Boolean isLibrarian;
    @XmlElement(required=true)
    public String email;    
}
