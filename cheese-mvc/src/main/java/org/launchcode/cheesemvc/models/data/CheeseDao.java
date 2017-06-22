package org.launchcode.cheesemvc.models.data;

import org.launchcode.cheesemvc.models.Cheese;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

/**
 * Created by lynnstrauss on 6/21/17.
 */

@Repository
@Transactional
public interface CheeseDao extends CrudRepository<Cheese, Integer> {

}
