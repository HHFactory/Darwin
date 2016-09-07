package com.hhfactory.service.implement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hhfactory.entity.MenuEntity;
import com.hhfactory.repository.MenuRepository;
import com.hhfactory.service.MenuService;

import lombok.NonNull;

/**
 * メニュー系処理クラス
 *
 */
@Service
@Transactional
public class MenuServiceImpl implements MenuService {
	@Autowired
	private MenuRepository menuRepository;

	/**
	 * 指定されたIDからメニュー情報を取得する<br>
	 * 
	 * @param id
	 *            [Long]:取得対象ID
	 * @return 対象メニュー情報
	 */
	@Override
	@Transactional(readOnly = true)
	public MenuEntity findMenuById(Long id) {
		return menuRepository.findOne(id);
	}

	/**
	 * メニュー情報を登録する
	 * 
	 * @param insertTarget
	 *            [MenuEntity]:登録対象MenuEntity
	 * @return 登録結果MenuEntity
	 */
	@Override
	public MenuEntity createMenu(MenuEntity insertTarget) {
		return menuRepository.save(insertTarget);
	}

	/**
	 * メニュー情報を削除する
	 * 
	 * @param id:削除対象データID
	 * 
	 */
	@Override
	public void deleteMenu(Long id) {
		menuRepository.delete(id);
	}

	/**
	 * メニュー情報を更新する
	 * 
	 * @param alterEntity:更新内容MenuEntity
	 * 
	 */
	@Override
	@Transactional
	public void updateMenu(Long id, MenuEntity alterEntity) {
		menuRepository.save(alterEntity);
	}

	/**
	 * フードタイプからメニュー情報リストを取得する<br>
	 * 
	 * @param targetFoodType
	 *            [Integer]:検索対象フードタイプコード値
	 * @return 検索結果リスト
	 */
	@Override
	public List<MenuEntity> findMenusByFoodType(@NonNull Integer targetFoodType) {
		return menuRepository.findByFoodType(targetFoodType);
	}
}
