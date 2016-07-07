package uk.co.redkiteweb.dccweb.data.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import uk.co.redkiteweb.dccweb.data.model.Decoder;

/**
 * Created by shawn on 07/07/16.
 */
@Repository
public interface DecoderRepository extends CrudRepository<Decoder, Integer> {
}
