package com.gionee.ssp.conf.gameAndStore;

/**
 * 
 * @ClassName GameAppSettings
 * @Desc {应用商店游戏大厅配置}
 * @author zhengk
 * @date Mar 13, 2017
 */
public class GameAppSettings {

	// 确认应用商店和游戏大厅的包名
	public enum CHANNEL {

		GAME_HALL(2, "gn.com.android.gamehall"), //
		APP_STORE(3, "com.gionee.aora.market");

		public final int code;
		public final String bundle;

		CHANNEL(int code, String bundle) {
			this.code = code;
			this.bundle = bundle;
		}

		public static CHANNEL valueOf(int code) {

			switch (code) {
			case 2:
				return GAME_HALL;
			case 3:
				return APP_STORE;
			default:
				return null;
			}
		}
	}

	public enum ES {

		GAME_HALL("game_hall_index", "app"), //
		APP_STORE("app_store_index", "app");

		public final String index;
		public final String type;

		ES(String index, String type) {
			this.index = index;
			this.type = type;
		}
	}

}
