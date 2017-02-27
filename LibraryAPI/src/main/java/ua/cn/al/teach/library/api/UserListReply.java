package ua.cn.al.teach.library.api;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author al
 */
@XmlRootElement
public class UserListReply extends GenericReply{
    @XmlElement(required=true)
    public List<LibUser> users = new ArrayList<>();
}
