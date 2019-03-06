package cn.com.taiji.sample.entity.dict.source;

public enum BuySell {
	SELF_PRODUCE("自产", "01"  ){},
	PURCHASE ("外购","02"){},	
	;
	private String value;//
	private String code;//

	private BuySell(String value,String code) {
		this.code = code;
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
}
