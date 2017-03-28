package uk.co.redkiteweb.dccweb.data.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import uk.co.redkiteweb.dccweb.data.model.AccessoryDecoder;

import java.util.List;

/**
 * Created by shawn on 26/01/17.
 */
@Repository
public interface AccessoryDecoderRepository extends CrudRepository<AccessoryDecoder, Integer> {
    List<AccessoryDecoder> findAccessoryDecodersByAddress(final Integer address);
}
