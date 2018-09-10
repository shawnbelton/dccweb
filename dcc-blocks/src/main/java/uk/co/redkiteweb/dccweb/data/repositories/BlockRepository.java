package uk.co.redkiteweb.dccweb.data.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import uk.co.redkiteweb.dccweb.data.model.Block;

/**
 * Created by shawn on 03/04/17.
 */
@Repository
public interface BlockRepository extends CrudRepository<Block, String> {
}
