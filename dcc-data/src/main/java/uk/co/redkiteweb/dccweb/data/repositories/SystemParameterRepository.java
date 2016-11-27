package uk.co.redkiteweb.dccweb.data.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import uk.co.redkiteweb.dccweb.data.model.SystemParameter;

/**
 * Created by shawn on 25/11/16.
 */
@Repository
public interface SystemParameterRepository extends CrudRepository<SystemParameter, Integer> {

    SystemParameter findByName(final String name);

}
