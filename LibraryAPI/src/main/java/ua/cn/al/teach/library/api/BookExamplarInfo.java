package ua.cn.al.teach.library.api;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author al
 */
@XmlRootElement 
public class BookExamplarInfo {
    @XmlElement(required=false)
    public Long exampl_id;
    public Integer count;
    public Integer available;
}
