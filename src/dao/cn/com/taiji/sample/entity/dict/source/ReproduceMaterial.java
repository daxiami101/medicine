package cn.com.taiji.sample.entity.dict.source;

public enum ReproduceMaterial {
	SEED("种子", "01"  ){},
	SPROUT ("种苗","02"){},	
	OTHER ("其他","03"){},	
	
	;
	private String value;//
	private String code;//

	private ReproduceMaterial(String value,String code) {
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
