package uk.co.redkiteweb.dccweb.data.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import uk.co.redkiteweb.dccweb.data.model.CV;

/**
 * Created by shawn on 29/07/16.
 */
@Repository
public interface CVRepository extends CrudRepository<CV, Integer> {
}
