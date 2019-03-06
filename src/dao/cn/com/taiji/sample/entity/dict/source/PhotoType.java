package cn.com.taiji.sample.entity.dict.source;

public enum PhotoType {

	QRCODE("品种二维码", "01" ,"source_qrcode" ){},
	PURCHASE ("种植企业","02","source_purchase"){},	
	MAKE ("炮制","03","source_make"){},	
	EXTRACT ("提取","04","source_extract"){},	
	PREPARATION ("制剂","05","source_preparation"){},	
	CASE ("包装","06","source_case"){},	
	END_PRODUCTION ("种植企业","07","source_end_production"){},	
	
	;
	private String value;//汉字值
	private String code;//编码
	private String tableName;//接口名称
	
	private PhotoType(String value,String code,String tableName) {
		this.code = code;
		this.value = value;
		this.tableName = tableName;
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