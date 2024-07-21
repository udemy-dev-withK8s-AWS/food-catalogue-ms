package com.codedecode.foodcatalogue.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codedecode.foodcatalogue.dto.FoodCataloguePage;
import com.codedecode.foodcatalogue.dto.FoodItemDto;
import com.codedecode.foodcatalogue.service.FoodCatalogueService;

@RestController
@RequestMapping("/foodcatalogue")
@CrossOrigin
public class FoodCatalogueController {

	@Autowired
	FoodCatalogueService foodCatalogueService;
	
	@PostMapping("/addfooditem")
	public ResponseEntity<FoodItemDto> addFoodItem(@RequestBody FoodItemDto foodItemDto) {
		return new ResponseEntity<FoodItemDto>(foodCatalogueService.addFoodItem(foodItemDto), HttpStatus.CREATED);
	}
	
	@GetMapping("fetchRestaurantAndFoodItemsById/{restaurantId}")
	public ResponseEntity<FoodCataloguePage> fetchRestaurantDetailsWithFoodMenu(@PathVariable Integer restaurantId){
		return new ResponseEntity<FoodCataloguePage>(foodCatalogueService.fetchFoodCataloguePageDetails(restaurantId), HttpStatus.OK);
	}
}
