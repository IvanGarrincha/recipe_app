package com.moose.recipe_app.repositories;

import com.moose.recipe_app.domain.Category;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Ivan Garrido on 2020-12-29.
 * <p>
 * Package: com.moose.recipe_app.repositories
 */

public interface CategoryRepository extends CrudRepository<Category, Long> {


}
