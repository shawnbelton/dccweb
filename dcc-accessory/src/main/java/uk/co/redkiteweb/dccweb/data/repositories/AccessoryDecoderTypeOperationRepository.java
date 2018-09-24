package uk.co.redkiteweb.dccweb.data.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import uk.co.redkiteweb.dccweb.data.model.AccessoryDecoderTypeOperation;

/**
 * Created by shawn on 21/02/17.
 */
@Repository
public interface AccessoryDecoderTypeOperationRepository extends CrudRepository<AccessoryDecoderTypeOperation, Integer> {
}
