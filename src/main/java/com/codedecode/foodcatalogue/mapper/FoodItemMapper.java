package com.codedecode.foodcatalogue.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.codedecode.foodcatalogue.dto.FoodItemDto;
import com.codedecode.foodcatalogue.entity.FoodItem;

@Mapper
public interface FoodItemMapper {
	
	FoodItemMapper INSTANCE = Mappers.getMapper(FoodItemMapper.class);
	
	FoodItem mapFoodItemDtoToFoodItem(FoodItemDto foodItemDto);
	
	FoodItemDto mapFoodItemToFoodItemDto(FoodItem foodItem);

}
