package com.hhfactory.service;

import com.hhfactory.entity.MenuEntity;

/**
 * メニュー系処理定義インターフェース
 *
 */

public interface MenuService {
	//メニューを検索
	public MenuEntity findMenuById(Long id);
	//メニュー情報を新規登録
	public MenuEntity createMenu(MenuEntity insertTarget);
	//メニュー情報を削除
	public void deleteMenu(Long id );
	//メニュー情報を更新
	public void updateMenu(Long id, MenuEntity updateEntity);
	//指定したメニューをお気に入りに追加
	//指定したメニューをお気に入りから削除
	
}
