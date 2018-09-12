package uk.co.redkiteweb.dccweb.data.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import uk.co.redkiteweb.dccweb.data.model.Loco;

/**
 * Created by shawn on 24/06/16.
 */
@Repository
public interface LocoRepository extends CrudRepository<Loco, Integer> {
}
