package uk.co.redkiteweb.dccweb.data.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import uk.co.redkiteweb.dccweb.data.model.Train;

/**
 * Created by shawn on 24/06/16.
 */
@Repository
public interface TrainRepository extends CrudRepository<Train, Integer> {
}
