package cn.com.taiji.sample.entity.dict.source;

public enum ReproducePlace {
	NATIVE("本地", "01"  ){},
	FORIEGN ("异地","02"){},	
	;
	private String value;//
	private String code;//

	private ReproducePlace(String value,String code) {
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
