/*
 * 
 * 
 */
package ua.cn.al.teach.library2.services;

import org.springframework.stereotype.Component;
import ua.cn.al.teach.library.api.BookInfo;
import ua.cn.al.teach.library2.jpa.Book;

/**
 *
 * @author al
 */
@Component
public class BookMapper {
    public BookInfo fromInternal(Book b){
        BookInfo bi = null;
        return bi;
    }
    public Book toInternal(BookInfo bi){
        Book b = null;
        return b;
    }
}
