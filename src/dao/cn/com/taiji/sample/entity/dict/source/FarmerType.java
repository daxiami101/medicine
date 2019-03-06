package cn.com.taiji.sample.entity.dict.source;

public enum FarmerType {

	FARMER("普通药农", "01"  ){},
	COMPANY ("种植企业","02"){},	
	
	;
	private String value;//接口代号
	private String code;//接口名称
	
	private FarmerType(String value,String code) {
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