package uk.co.redkiteweb.dccweb.data.repositories;

import org.springframework.data.repository.CrudRepository;
import uk.co.redkiteweb.dccweb.data.model.DecoderFunction;

import java.util.List;

/**
 * Created by shawn on 25/10/16.
 */
public interface DecoderFunctionRepository extends CrudRepository<DecoderFunction, Integer> {

    List<DecoderFunction> findAllByDecoderId(final Integer decoderId);

}
