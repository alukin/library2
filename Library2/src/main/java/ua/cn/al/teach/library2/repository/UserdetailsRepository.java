/*
 * 
 * 
 */
package ua.cn.al.teach.library2.repository;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import ua.cn.al.teach.library2.jpa.Userdetails;

/**
 *
 * @author al
 */
public interface UserdetailsRepository extends CrudRepository<Userdetails, Long> {
    public List<Userdetails> findByFirstNameAndLastName(String firstName, String LastName);
}
