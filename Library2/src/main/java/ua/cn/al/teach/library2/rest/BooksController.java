/*
 * 
 * 
 */
package ua.cn.al.teach.library2.rest;

import java.util.Date;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ua.cn.al.teach.library.api.BookReply;
import ua.cn.al.teach.library.api.UserListReply;
import ua.cn.al.teach.library2.jpa.Book;
import ua.cn.al.teach.library2.services.BookMapper;
import ua.cn.al.teach.library2.services.BookService;

/**
 *
 * @author al
 */
@RestController
public class BooksController {
    
    @Autowired
    BookService bookService;
    @Autowired BookMapper bookMapper;
    
    @RequestMapping(path="/books/all",  method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public BookReply getAllBooks(){
        BookReply reply = new BookReply();
        for(Book b: bookService.getAll()){
           reply.books.add(bookMapper.fromInternal(b));            
        }
        return reply;
    }
   
}
