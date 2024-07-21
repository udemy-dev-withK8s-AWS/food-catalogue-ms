package com.codedecode.foodcatalogue.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.codedecode.foodcatalogue.dto.FoodItemDto;
import com.codedecode.foodcatalogue.entity.FoodItem;
import com.codedecode.foodcatalogue.mapper.FoodItemMapper;
import com.codedecode.foodcatalogue.repo.FoodItemRepo;

@Service
public class FoodItemService {

	@Autowired
	FoodItemRepo foodItemRepo;
	
	public FoodItemDto addFoodItem(FoodItemDto foodItemDto) {
		return FoodItemMapper.INSTANCE.mapFoodItemToFoodItemDto(foodItemRepo.save(FoodItemMapper.INSTANCE.mapFoodItemDtoToFoodItem(foodItemDto)));
	}
	
	public List<FoodItemDto> findAllFoodItems(){
		return foodItemRepo.findAll().stream()
				.map(foodItem -> FoodItemMapper.INSTANCE.mapFoodItemToFoodItemDto(foodItem))
				.collect(Collectors.toList());
	}
	
	public ResponseEntity<FoodItemDto> findFoodItemById(Integer id){
		Optional<FoodItem> restaurant = foodItemRepo.findById(id);
		
		if(restaurant.isPresent()) {
			return new ResponseEntity<>(FoodItemMapper.INSTANCE.mapFoodItemToFoodItemDto(foodItemRepo.findById(id).get()),HttpStatus.OK);
		}
		
		return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
//		return FoodItemMapper.INSTANCE.mapRestaurantToFoodItemDto(foodItemRepo.findById(id).orElseThrow(() -> new NoSuchElementException("Restaurant Not Found!!")));
	}


}
