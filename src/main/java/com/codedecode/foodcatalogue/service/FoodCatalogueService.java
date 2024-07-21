package com.codedecode.foodcatalogue.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.codedecode.foodcatalogue.dto.FoodCataloguePage;
import com.codedecode.foodcatalogue.dto.FoodItemDto;
import com.codedecode.foodcatalogue.dto.RestaurantDto;
import com.codedecode.foodcatalogue.entity.FoodItem;
import com.codedecode.foodcatalogue.mapper.FoodItemMapper;
import com.codedecode.foodcatalogue.repo.FoodItemRepo;

@Service
public class FoodCatalogueService {
	
	public static final String RESTAURANT_SERVICE_URL="http://RESTAURANT-SERVICE/restaurant/fetchRestaurantById/";
//mongodb://localhost:27017
	@Autowired
	FoodItemRepo foodItemRepo;
	
	@Autowired
	RestTemplate restTemplate;
	
	public FoodItemDto addFoodItem(FoodItemDto foodItemDto) {
		return FoodItemMapper.INSTANCE
				.mapFoodItemToFoodItemDto(foodItemRepo.save(FoodItemMapper.INSTANCE.mapFoodItemDtoToFoodItem(foodItemDto)));
	}
	
	public FoodCataloguePage fetchFoodCataloguePageDetails(Integer restaurantId) {
		
		List<FoodItem> foodItemList = fetchFoodItemList(restaurantId);
		RestaurantDto restaurant = fetchRestaurantFromRestaurantMS(restaurantId);
		return createFoodCataloguePage(foodItemList, restaurant);
	}
	
	private List<FoodItem> fetchFoodItemList(Integer restaurantId) {
		
		return foodItemRepo.findByRestaurantId(restaurantId);
	}
	
	private RestaurantDto fetchRestaurantFromRestaurantMS(Integer restaurantId) {
		return restTemplate.getForObject(RESTAURANT_SERVICE_URL+restaurantId, RestaurantDto.class);
	}

	private FoodCataloguePage createFoodCataloguePage(List<FoodItem> foodItemsList, RestaurantDto restaurant) {
		return new FoodCataloguePage(foodItemsList, restaurant);
	}
	
}
