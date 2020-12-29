package com.moose.recipe_app.controllers;

import com.moose.recipe_app.domain.Category;
import com.moose.recipe_app.domain.UnitOfMeasure;
import com.moose.recipe_app.repositories.CategoryRepository;
import com.moose.recipe_app.repositories.UnitOfMeasureRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

/**
 * Created by Ivan Garrido on 2020-12-29.
 * <p>
 * Package: com.moose.recipe_app.controllers
 */

@Slf4j
@Controller
public class IndexController {

    private CategoryRepository categoryRepository;
    private UnitOfMeasureRepository unitOfMeasureRepository;

    public IndexController(CategoryRepository categoryRepository, UnitOfMeasureRepository unitOfMeasureRepository) {
        this.categoryRepository = categoryRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }

    @RequestMapping({"", "/", "/index"})
    public String getIndexPage(Model model){
        Optional<Category> categoryOptional = categoryRepository.findByDescription("American");
        Optional<UnitOfMeasure> unitOfMeasureOptional = unitOfMeasureRepository.findByDescription("Teaspoon");

        log.info("Category id is: "+ categoryOptional.get().getId());
        log.info("Unit of Measure Id is: "+ unitOfMeasureOptional.get().getId());


        model.addAttribute("text", "Den här texten har jag satt upp.");
        return "index";
    }
}
