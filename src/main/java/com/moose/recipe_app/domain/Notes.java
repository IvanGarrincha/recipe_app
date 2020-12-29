package com.moose.recipe_app.domain;

import javax.persistence.*;

/**
 * Created by Ivan Garrido on 2020-12-29.
 * <p>
 * Package: com.moose.recipe_app.domain
 */

@Entity
public class Notes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //No cascade here

    @OneToOne
    private Recipe recipe;

    @Lob
    private String recipeNotes;

    public Notes(){}



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    public String getRecipeNotes() {
        return recipeNotes;
    }

    public void setRecipeNotes(String recipeNotes) {
        this.recipeNotes = recipeNotes;
    }
}
