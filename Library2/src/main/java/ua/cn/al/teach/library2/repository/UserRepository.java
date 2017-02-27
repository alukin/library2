/*
 * 
 * 
 */
package ua.cn.al.teach.library2.repository;

/**
 *
 * @author al
 */

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import ua.cn.al.teach.library2.jpa.Appuser;

public interface UserRepository extends CrudRepository<Appuser, Long> {
    @Override
    public List<Appuser> findAll();
}
