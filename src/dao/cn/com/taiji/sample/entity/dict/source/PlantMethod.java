package cn.com.taiji.sample.entity.dict.source;

public enum PlantMethod {
	ROTATE("轮作", "01"  ){},
	INTERPLATE ("间作","02"){},	
	RELAY ("套作","03"){},	
	;
	private String value;//
	private String code;//

	private PlantMethod(String value,String code) {
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
