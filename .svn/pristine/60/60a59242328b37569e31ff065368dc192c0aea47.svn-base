package com.gionee.ssp.common;

/**
 * @author dingyw
 *
 * 2017年9月27日
 */
public class CommonConstant {
	/**
	 * @author dingyw
	 *
	 * 2017年10月10日
	 */
	public enum SYS_CODE {
		FIRM_NAME("firm", "金立厂商"), 
		WK_NAME("wk", "玩咖"), 
		BAIDU_NAME("baidu", "百度"), 
		TOUTIAO_NAME("toutiao", "今日头条"),
		ZAKER_NAME("zaker", "zaker"),
		LINGJI_NAME("lingji", "灵集"),
		INMOBI_NAME("inmobi", "inmobi");

		String value;
		String desc;

		SYS_CODE(String value, String desc) {
			this.value = value;
			this.desc = desc;
		}

		@Override
		public String toString() {
			return this.value;
		}

		public String getValue() {
			return this.value;
		}

		public String getDesc() {
			return this.desc;
		}

	}
	/**根据大数据提供的excel整理出来 by yangyijun提供该excel
	 * @author dingyw
	 *
	 * 2017年9月30日
	 */
	public enum DMP_LABLE_PREFIX {
		GENDER_LABEL("101", "性别标签"), 
		AGE_LABLE("102", "年龄标签"),
		HOBBY_201("201", "兴趣标签201"),
		HOBBY_202("202", "兴趣标签202"),
		HOBBY_203("203", "兴趣标签203");

		String value;
		String desc;

		DMP_LABLE_PREFIX(String value, String desc) {
			this.value = value;
			this.desc = desc;
		}

		@Override
		public String toString() {
			return this.value;
		}

		public String getValue() {
			return this.value;
		}

		public String getDesc() {
			return this.desc;
		}

	}
	/**根据大数据提供的excel整理出来 by yangyijun提供该excel
	 * @author dingyw
	 *
	 * 2017年9月30日
	 */
	public enum IS_PUSH {
		FALSE(0, "不是直投广告"), 
		TRUE(1, "是直投广告");

		int value;
		String desc;

		IS_PUSH(int value, String desc) {
			this.value = value;
			this.desc = desc;
		}


		public int getValue() {
			return this.value;
		}

		public String getDesc() {
			return this.desc;
		}

	}
	
	/**注意:SDK_AD_TYPE和AD_TYPE的开屏和插屏相反,已系统某行代码作为区分
	 * @author dingyw
	 *
	 * 2017年10月10日
	 */
	public enum SDK_AD_TYPE {
		BANNER(1, "横幅"), 
		SPLASH_SCREEN(2, "开屏"),
		INSERT_SCREEN(3, "插屏"),
		NATIVE(4, "原生广告");

		int value;
		String desc;

		SDK_AD_TYPE(int value, String desc) {
			this.value = value;
			this.desc = desc;
		}

		public int getValue() {
			return this.value;
		}

		public String getDesc() {
			return this.desc;
		}

	}
	/**注意:SDK_AD_TYPE和AD_TYPE的开屏和插屏相反,已系统某行代码作为区分
	 * 这里跟dsp是一致的
	 * @author dingyw
	 *
	 * 2017年10月10日
	 */
	public enum AD_TYPE {
		BANNER(1, "横幅"), 
		INSERT_SCREEN(2, "插屏"),
		SPLASH_SCREEN(3, "开屏"),
		NATIVE(4, "原生广告");

		int value;
		String desc;

		AD_TYPE(int value, String desc) {
			this.value = value;
			this.desc = desc;
		}

		public int getValue() {
			return this.value;
		}

		public String getDesc() {
			return this.desc;
		}

	}

    
    /**物料类型 1：信息流组图，2：信息流小图，3：信息流大图, 4:图片, 5:图文
	 * @author dingyw
	 *
	 * 2017年10月9日
	 */
	public enum MATERIAL_TYPE {
		NATIVE_GROUP(1, "信息流组图"), 
		NATIVE_SMALL(2, "信息流小图"),
		NATIVE_BIG(3, "信息流大图"),
		ONLY_IMG(4, "图片"),
		IMG_TEXT(5, "图文");

		int value;
		String desc;

		MATERIAL_TYPE(int value, String desc) {
			this.value = value;
			this.desc = desc;
		}

		public int getValue() {
			return this.value;
		}

		public String getDesc() {
			return this.desc;
		}

	}
	
	 /**SDK交互类型
		 * @author dingyw
		 *
		 * 2017年10月9日
		 */
	public enum SDKInteractionType {
			NO_ACTION(0, "无动作"), 
			WEB(1, "网页打开"),
			DOWNLOAD(2, "直接下载"),
			RESERVED(3, "保留"),
			APP_WAKE(4, "应用唤醒"),
			CARD(5, "卡片打开"),
			JUMP_APPSTORE_GAMEHALL(6, "跳转游戏大厅");

			int value;
			String desc;

			SDKInteractionType(int value, String desc) {
				this.value = value;
				this.desc = desc;
			}

			public int getValue() {
				return this.value;
			}

			public String getDesc() {
				return this.desc;
			}

		}
		/**ad交互类型
		 * @author dingyw
		 *
		 * 2017年10月9日
		 */
		public enum AdInteractionType {
			ALL(0, "不限制"), 
			WEB(1, "网页打开"),
			DOWNLOAD(2, "直接下载"),
			NO_USE(3, "保留"),
			APP_WAKE(4, "应用唤醒");

			int value;
			String desc;

			AdInteractionType(int value, String desc) {
				this.value = value;
				this.desc = desc;
			}

			public int getValue() {
				return this.value;
			}

			public String getDesc() {
				return this.desc;
			}
		}
		/**ad交互类型
		 * @author dingyw
		 *
		 * 2017年10月9日
		 */
		public enum is_silent_install {
			FALSE(0, "不静默安装"), 
			TRUE(1, "静默安装");

			int value;
			String desc;

			is_silent_install(int value, String desc) {
				this.value = value;
				this.desc = desc;
			}

			public int getValue() {
				return this.value;
			}

			public String getDesc() {
				return this.desc;
			}
		}
		
		/**创意类型 
		 * 0：无创意类型，1：纯文字创意，2：纯图片创意， 3:图文混合创意，4：H5创意。5:信息流广告
		 * @author dingyw
		 *
		 * 2017年10月9日
		 */
		public enum CreativeType {
			NO_TYPE(0, "无创意类型"), 
			TEXT(1, "纯文字创意"),
			IMG(2, "纯图片创意"),
			MIX(3, "图文混合创意"),
			HTML(4, "H5创意"),
			NATIVE(5, "信息流广告");

			int value;
			String desc;

			CreativeType(int value, String desc) {
				this.value = value;
				this.desc = desc;
			}

			public int getValue() {
				return this.value;
			}

			public String getDesc() {
				return this.desc;
			}
		}
		/**点击监播发送时机 
		 * 1：页面开始加载时发，2：页面加载完成时发，3：点击就发，4：开始下载时发 
		 * @author dingyw
		 * 2017年10月9日
		 */
		public enum TRACKER_TIMING_TYPE {
			PAGE_LOAD_START(1, "页面开始加载时发"), 
			PAGE_LOAD_FINISH(2, "页面加载完成时发"),
			CLICK(3, "点击就发"),
			DOWNLOAD_START(4, "开始下载时发");

			int value;
			String desc;

			TRACKER_TIMING_TYPE(int value, String desc) {
				this.value = value;
				this.desc = desc;
			}

			public int getValue() {
				return this.value;
			}

			public String getDesc() {
				return this.desc;
			}
		}

		public enum IS_TRUE {
			FALSE(0, "false"), 
			TRUE(1, "true");

			int value;
			String desc;

			IS_TRUE(int value, String desc) {
				this.value = value;
				this.desc = desc;
			}

			public int getValue() {
				return this.value;
			}

			public String getDesc() {
				return this.desc;
			}
		}
		
		/** 横幅请求类型.0:纯图片，1：图文 **/
		
		public enum BANNER_MATERIAL_TYPE {
			ONLY_IMG(0, "纯图片"), 
			IMG_TEXT(1, "图文");

			int value;
			String desc;

			BANNER_MATERIAL_TYPE(int value, String desc) {
				this.value = value;
				this.desc = desc;
			}

			public int getValue() {
				return this.value;
			}

			public String getDesc() {
				return this.desc;
			}
		}
		
		/** 原生广告类型 1：组图；2：小图；3：大图 **/
		
		public enum NATIVE_MATERIAL_TYPE {
			GROUP_IMG(1, "组图"), 
			SMALL_IMG(2, "小图"),
			BIG_IMG(3, "大图");

			int value;
			String desc;

			NATIVE_MATERIAL_TYPE(int value, String desc) {
				this.value = value;
				this.desc = desc;
			}

			public int getValue() {
				return this.value;
			}

			public String getDesc() {
				return this.desc;
			}
		}
		public enum ConnectionType {
			ALL(0, "所有"), 
			CARRIER_TYPE(1, "2G/3G/4G"),
			WIFI(2, "wifi");

			int value;
			String desc;

			ConnectionType(int value, String desc) {
				this.value = value;
				this.desc = desc;
			}

			public int getValue() {
				return this.value;
			}

			public String getDesc() {
				return this.desc;
			}
		}
		
		public enum SDK_RSP_CODE {
			NO_ERROR(0, "响应无错误"), 
			INTERNAL_ERROR(1, "系统内部错误"),
			PARAM_ERROR(2, "参数错误"),
			API_UNSUPPORTED(3, "不支持的API"),
			ABOLITION_API(4, "废除的API"),
			VERIFICATION_FAILURE(5, "验证失效"),
			NO_CONTENT(6, "无内容"),
			ANTI_CHEATING(7, "防刷策略，IP被封"),
			LOWER_SDK_VERSION(8, "SDK版本过低");

			int value;
			String desc;

			SDK_RSP_CODE(int value, String desc) {
				this.value = value;
				this.desc = desc;
			}

			public int getValue() {
				return this.value;
			}

			public String getDesc() {
				return this.desc;
			}
		}
		
}
