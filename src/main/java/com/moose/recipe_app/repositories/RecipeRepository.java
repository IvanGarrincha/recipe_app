package com.moose.recipe_app.repositories;

import com.moose.recipe_app.domain.Recipe;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Ivan Garrido on 2020-12-29.
 * <p>
 * Package: com.moose.recipe_app.repositories
 */

public interface RecipeRepository extends CrudRepository<Recipe, Long> {
}
