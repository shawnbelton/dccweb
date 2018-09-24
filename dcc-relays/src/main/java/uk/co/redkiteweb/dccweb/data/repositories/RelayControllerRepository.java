package uk.co.redkiteweb.dccweb.data.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import uk.co.redkiteweb.dccweb.data.model.RelayController;

/**
 * Created by shawn on 31/05/17.
 */
@Repository
public interface RelayControllerRepository extends CrudRepository<RelayController, String> {
}
