package com.moose.recipe_app.bootstrap;

import com.moose.recipe_app.domain.*;
import com.moose.recipe_app.repositories.CategoryRepository;
import com.moose.recipe_app.repositories.RecipeRepository;
import com.moose.recipe_app.repositories.UnitOfMeasureRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by Ivan Garrido on 2020-12-29.
 * <p>
 * Package: com.moose.recipe_app.bootstrap
 */

@Slf4j
@Component
public class RecipeBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private final CategoryRepository categoryRepository;
    private final RecipeRepository recipeRepository;
    private final UnitOfMeasureRepository unitOfMeasureRepository;

    public RecipeBootstrap(CategoryRepository categoryRepository, RecipeRepository recipeRepository, UnitOfMeasureRepository unitOfMeasureRepository) {
        this.categoryRepository = categoryRepository;
        this.recipeRepository = recipeRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        recipeRepository.saveAll(getRecipes());
    }

    private List<Recipe> getRecipes() {

        List<Recipe> recipes = new ArrayList<>(2);

        Optional<UnitOfMeasure> eachUomOptional = unitOfMeasureRepository.findByDescription("Each");
        if (!eachUomOptional.isPresent()) {
            log.info("unit of measure not defined is Each");
            throw new RuntimeException("Expected Unit of measure not found");
        }


        Optional<UnitOfMeasure> tableSpoonUomOptional = unitOfMeasureRepository.findByDescription("Tablespoon");

        if (!tableSpoonUomOptional.isPresent()) {
            log.info("unit of measure not defined is Table spoon");
            throw new RuntimeException("Expected Unit of measure Tablespoon not found");
        }

        Optional<UnitOfMeasure> teaSpoonUomOptional = unitOfMeasureRepository.findByDescription("Teaspoon");
        if (!teaSpoonUomOptional.isPresent()) {
            throw new RuntimeException("Expected Unit of measure Teaspoon not found");
        }

        Optional<UnitOfMeasure> dashUomOptional = unitOfMeasureRepository.findByDescription("Dash");
        if (!dashUomOptional.isPresent()) {
            throw new RuntimeException("Expected Unit of measure Dash not found");
        }
        Optional<UnitOfMeasure> pintUomOptional = unitOfMeasureRepository.findByDescription("Pint");
        if (!pintUomOptional.isPresent()) {
            throw new RuntimeException("Expected Unit of measure Pint not found");
        }
        Optional<UnitOfMeasure> cupsUomOptional = unitOfMeasureRepository.findByDescription("Cup");
        if (!cupsUomOptional.isPresent()) {
            throw new RuntimeException("Expected Unit of measure cup not found");
        }

        UnitOfMeasure eachUom = eachUomOptional.get();
        UnitOfMeasure tableSpoon = tableSpoonUomOptional.get();
        UnitOfMeasure teaSpoon = teaSpoonUomOptional.get();
        UnitOfMeasure dash = dashUomOptional.get();
        UnitOfMeasure pint = pintUomOptional.get();
        UnitOfMeasure cups = cupsUomOptional.get();


        Optional<Category> americanCategoryOptional = categoryRepository.findByDescription("American");

        if(!americanCategoryOptional.isPresent()){
            throw new RuntimeException("Expected Category American Not found");
        }
        Optional<Category> mexicanCategoryOptional = categoryRepository.findByDescription("Mexican");

        if(!mexicanCategoryOptional.isPresent()){
            throw new RuntimeException("Expected Category Mexican Not found");
        }


        Category americanCategory = americanCategoryOptional.get();
        Category mexicanCategory = mexicanCategoryOptional.get();

        Recipe guacaRecipe = new Recipe();

        guacaRecipe.setDescription("Perfect Guaca");
        guacaRecipe.setPrepTime(10);
        guacaRecipe.setCookTime(0);
        guacaRecipe.setDifficulty(Difficulty.EASY);
        guacaRecipe.setDirections("1 Cut the avocado, remove flesh: Cut the avocados in half. Remove the pit. Score the inside of the avocado with a blunt knife and scoop out the flesh with a spoon. (See How to Cut and Peel an Avocado.) Place in a bowl.\n" +
                        "2 Mash with a fork: Using a fork, roughly mash the avocado. (Don't overdo it! The guacamole should be a little chunky.) \n3 Add salt, lime juice, and the rest: Sprinkle with salt and lime (or lemon) juice. The acid in the lime juice will provide "+
                        "some balance to the richness of the avocado and will help delay the avocados from turning brown.\n" +
                        "Add the chopped onion, cilantro, black pepper, and chiles. Chili peppers vary individually in their hotness. So, start with a" +
                        " half of one chili pepper and add to the guacamole to your desired degree of hotness. Remember that much of this is done to taste because of the variability in the fresh ingredients. Start with this recipe and adjust to your taste.\nChilling tomatoes hurts their flavor, so if you want to add chopped tomato to your guacamole, add it just before serving.\n" +
                        "4 Serve: Serve immediately, or if making a few hours ahead, place plastic wrap on the surface of the guacamole and press down to cover it and to prevent air reaching it. " +
                        "(The oxygen in the air causes oxidation which will turn the guacamole brown.) Refrigerate until ready to serve.");

        Notes guacNotes = new Notes();
        guacNotes.setRecipeNotes(
                "Simple Guacamole: The simplest version of guacamole is just mashed avocados with salt. Don’t let the lack of availability of other ingredients stop you from making guacamole.\n" +
                        "Quick guacamole: For a very quick guacamole just take a 1/4 cup of salsa and mix it in with your mashed avocados.\n" +
                        "Don’t have enough avocados? To extend a limited supply of avocados, add either sour cream or cottage cheese to your guacamole dip. Purists may be horrified, but so what? It tastes great.");

        guacNotes.setRecipe(guacaRecipe);
        guacaRecipe.setNotes(guacNotes);

        guacaRecipe.getIngredients().add(new Ingredient("ripe avocados", new BigDecimal(2), eachUom, guacaRecipe));
        guacaRecipe.getIngredients().add(new Ingredient("Kosher salt", new BigDecimal(".5"), teaSpoon, guacaRecipe));
        guacaRecipe.getIngredients().add(new Ingredient("fresh lime juice or lemon juice", new BigDecimal(2),tableSpoon, guacaRecipe));
        guacaRecipe.getIngredients().add(new Ingredient("minced red onion or thinly sliced green onion", new BigDecimal(2), tableSpoon, guacaRecipe));
        guacaRecipe.getIngredients().add(new Ingredient("serrano chiles, steams and seeds removed minced", new BigDecimal(2), eachUom, guacaRecipe));
        guacaRecipe.getIngredients().add(new Ingredient( "Cilantro", new BigDecimal(2), tableSpoon, guacaRecipe));
        guacaRecipe.getIngredients().add(new Ingredient("Freshly grated blck pepper", new BigDecimal(2), dash, guacaRecipe));
        guacaRecipe.getIngredients().add(new Ingredient("ripe tomato, seeds and pulp removed, chopped", new BigDecimal("0.5"),eachUom, guacaRecipe));

        guacaRecipe.getCategories().add(mexicanCategory);
        guacaRecipe.getCategories().add(americanCategory);

        recipes.add(guacaRecipe);



        //Tacos recipe
        Recipe tacos = new Recipe();
        tacos.setDescription("Spicy grilled Chicken Taco");
        tacos.setCookTime(9);
        tacos.setPrepTime(20);
        tacos.setDifficulty(Difficulty.MODERATE);

        tacos.setDirections(" bla bla bla directions of creating tacos");

        Notes tacoNotes = new Notes();
        tacoNotes.setRecipeNotes("Recipe notes for tacos");

        tacoNotes.setRecipe(tacos);
        tacos.setNotes(tacoNotes);

        tacos.getIngredients().add(new Ingredient("Ancho Chili Powder", new BigDecimal(2), tableSpoon, tacos));
        tacos.getIngredients().add(new Ingredient("dried oregano", new BigDecimal(1), teaSpoon, tacos));

        tacos.getCategories().add(americanCategory);
        tacos.getCategories().add(mexicanCategory);

        recipes.add(tacos);




        return recipes;
    }


}
