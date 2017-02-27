/*
 * 
 * 
 */
package ua.cn.al.teach.library2.jpa;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author al
 */
@Entity
@Table(name = "book")
@NamedQueries({
    @NamedQuery(name = "Book.findAll", query = "SELECT b FROM Book b")})
public class Book implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "book_id")
    private Long bookId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "title")
    private String title;
    @Size(max = 200)
    @Column(name = "info")
    private String info;
    @Size(max = 200)
    @Column(name = "description")
    private String description;
    @ManyToMany(mappedBy = "bookList")
    private List<Author> authorList  = new ArrayList<>();;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "book")
    private List<BookExamplar> bookExamplarList  = new ArrayList<>();;

    public Book() {
    }

    public Book(Long bookId) {
        this.bookId = bookId;
    }

    public Book(Long bookId, String title) {
        this.bookId = bookId;
        this.title = title;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Author> getAuthorList() {
        return authorList;
    }

    public void setAuthorList(List<Author> authorList) {
        this.authorList = authorList;
    }

    public List<BookExamplar> getBookExamplarList() {
        return bookExamplarList;
    }

    public void setBookExamplarList(List<BookExamplar> bookExamplarList) {
        this.bookExamplarList = bookExamplarList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (bookId != null ? bookId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Book)) {
            return false;
        }
        Book other = (Book) object;
        if ((this.bookId == null && other.bookId != null) || (this.bookId != null && !this.bookId.equals(other.bookId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ua.cn.al.teach.library2.jpa.Book[ bookId=" + bookId + " ]";
    }
    
}
