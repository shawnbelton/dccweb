package uk.co.redkiteweb.dccweb.data.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import uk.co.redkiteweb.dccweb.data.model.Macro;

/**
 * Created by shawn on 02/12/16.
 */
@Repository
public interface MacroRepository extends CrudRepository<Macro, Integer> {

    Macro findByName(final String name);

}
