/*
 * 
 * 
 */
package ua.cn.al.teach.library2.repository;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import ua.cn.al.teach.library2.jpa.Ugroup;

/**
 *
 * @author al
 */
public interface UgroupRepository extends CrudRepository<Ugroup, Long>{
    @Override
    public List<Ugroup> findAll();
}
