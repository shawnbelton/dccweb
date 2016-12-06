package uk.co.redkiteweb.dccweb.data.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import uk.co.redkiteweb.dccweb.data.model.MacroStep;

/**
 * Created by shawn on 02/12/16.
 */
@Repository
public interface MacroStepRepository extends CrudRepository<MacroStep, Integer> {
}
