package com.hhfactory.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.collections.CollectionUtils;
import org.modelmapper.AbstractConverter;
import org.modelmapper.Converter;
import org.modelmapper.PropertyMap;
import org.springframework.stereotype.Component;

import com.hhfactory.dto.MenuDto;
import com.hhfactory.entity.MenuEntity;

/**
 * 
 * MenuDtoへのマッピング定義クラス
 *
 */
@Component
public class ToMenuDtoMapperConfig {

	/**
	 * 
	 * @return
	 */
	public PropertyMap<MenuEntity, MenuDto> menuEntityToDtoMap() {
		return new PropertyMap<MenuEntity, MenuDto>() {
			@Override
			protected void configure() {
				map().setImageUrl(source.getImage().getImgUrl());
				map().setRestaurantId(source.getRestaurant().getId());
				using(commentIdListConverter).map(source).setMenuCommentIdList(null);
			}
		};
	}

	/**
	 * 
	 */
	private Converter<MenuEntity, List<Long>> commentIdListConverter = new AbstractConverter<MenuEntity, List<Long>>() {
		@Override
		protected List<Long> convert(MenuEntity source) {
			if ( CollectionUtils.isNotEmpty(source.getComments()) ) {
				return source.getComments().stream().map(menuComment -> menuComment.getId())
	                    .collect(Collectors.toList());
			}
			return null;
		}
	};

}
