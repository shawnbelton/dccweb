package uk.co.redkiteweb.dccweb.data.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import uk.co.redkiteweb.dccweb.data.model.AccessoryDecoderType;

/**
 * Created by shawn on 26/01/17.
 */
@Repository
public interface AccessoryDecoderTypeRepository extends CrudRepository<AccessoryDecoderType, Integer> {
}
