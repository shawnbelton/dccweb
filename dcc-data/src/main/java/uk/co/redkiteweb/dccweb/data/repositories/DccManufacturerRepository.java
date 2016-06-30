package uk.co.redkiteweb.dccweb.data.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import uk.co.redkiteweb.dccweb.data.model.DccManufacturer;

/**
 * Created by shawn on 30/06/16.
 */
@Repository
public interface DccManufacturerRepository extends CrudRepository<DccManufacturer, Integer> {



}
