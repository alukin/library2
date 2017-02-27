/*
 * 
 * 
 */
package ua.cn.al.teach.library2.services;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import ua.cn.al.teach.library2.jpa.Book;

/**
 *
 * @author al
 */
@Service
public class BookService {

    public List<Book> getAll() {
        List<Book> bl = new ArrayList<>();
        return bl;
    }
    
}
