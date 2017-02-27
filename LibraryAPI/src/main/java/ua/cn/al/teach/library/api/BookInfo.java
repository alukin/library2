/*
 * 
 * 
 */
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
public class BookInfo {
    @XmlElement(required=false)
    public Long book_id;
    @XmlElement(required=true)
    public String title;
    @XmlElement(required=true)
    public List<String> authors = new ArrayList<>();
    @XmlElement(required=true)
    public List<BookExamplarInfo> examplars = new ArrayList<>();    
    
}
