package com.wk.ssp.utils.log;
/**
 * @description: Log级别控制
 */
public enum LogLevel {

	INFO {
		@Override
		public String getName() {
			return "INFO";
		}
	},

	BUSERROR {
		@Override
		public String getName() {
			return "BUSERROR";
		}
	},

	SYSERROR {
		@Override
		public String getName() {
			return "SYSERROR";
		}
	},
	RBI {
		@Override
		public String getName() {
			return "RBI";
		}
	};

	public abstract String getName();

	public int getValue() {
		return ordinal();
	}
}
