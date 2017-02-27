/*
 * 
 * 
 */
package ua.cn.al.teach.library2.jpa;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author al
 */
@Entity
@Table(name = "author")
@NamedQueries({
    @NamedQuery(name = "Author.findAll", query = "SELECT a FROM Author a")})
public class Author implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "author_id")
    private Long authorId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "name")
    private String name;
    @Size(max = 200)
    @Column(name = "info")
    private String info;
    @JoinTable(name = "book_author", joinColumns = {
        @JoinColumn(name = "author_id", referencedColumnName = "author_id")}, inverseJoinColumns = {
        @JoinColumn(name = "book_id", referencedColumnName = "book_id")})
    @ManyToMany
    private List<Book> bookList = new ArrayList<>();

    public Author() {
    }

    public Author(Long authorId) {
        this.authorId = authorId;
    }

    public Author(Long authorId, String name) {
        this.authorId = authorId;
        this.name = name;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public List<Book> getBookList() {
        return bookList;
    }

    public void setBookList(List<Book> bookList) {
        this.bookList = bookList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (authorId != null ? authorId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Author)) {
            return false;
        }
        Author other = (Author) object;
        if ((this.authorId == null && other.authorId != null) || (this.authorId != null && !this.authorId.equals(other.authorId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ua.cn.al.teach.library2.jpa.Author[ authorId=" + authorId + " ]";
    }
    
}
