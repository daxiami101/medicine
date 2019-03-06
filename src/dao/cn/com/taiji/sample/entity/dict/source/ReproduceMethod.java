package cn.com.taiji.sample.entity.dict.source;

public enum ReproduceMethod {
	SEX("有性繁殖", "01"  ){},
	NON_SEX ("无性繁殖","02"){},	
	SEED ("种子直播","03"){},	
	SPROUT ("育苗移栽","04"){},	
	SPLIT ("分割繁殖","05"){},	
	SUPRESS ("压条繁殖","06"){},	
	INSERT ("扦插繁殖","07"){},	
	COMBINE ("嫁接繁殖","08"){},	
	CELL ("组培繁殖","09"){},	
	
	;
	private String value;//
	private String code;//

	private ReproduceMethod(String value,String code) {
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
