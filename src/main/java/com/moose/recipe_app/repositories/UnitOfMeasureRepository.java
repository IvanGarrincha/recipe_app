package com.moose.recipe_app.repositories;

import com.moose.recipe_app.domain.UnitOfMeasure;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Ivan Garrido on 2020-12-29.
 * <p>
 * Package: com.moose.recipe_app.repositories
 */

public interface UnitOfMeasureRepository extends CrudRepository<UnitOfMeasure, Long> {

}
