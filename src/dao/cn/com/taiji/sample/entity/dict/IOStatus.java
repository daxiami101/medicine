package cn.com.taiji.sample.entity.dict;

public enum IOStatus {
	DOING("1", "上传进行中") {},
	SUCCESS("2", "上传成功") {},
	FAILED("3", "上传失败") {},
	SUCERROR("4","成功但含有错误记录"){},
	FILEERROR("5","上传文件错误"){},
	FILEEMPTY("6","上传文件为空"){},
	COMPLETE("7", "正在保存数据") {},
	HUGE("8","ZIP包过大，请不超过5M"){},
	UPING("9","照片正在入库"){},
	TEMPLATEERROR("10","上传模板不正确"){},
	FILETYPERROR("11","文件类型错误"){},
	FILEINCLERROR("12","文件中含有错误数据"){},
	FILEREADING("13","文件读取中"){}
	;      
	private String code;
	private String value;

	public static IOStatus fromCode(String code)
	{
		for (IOStatus type : IOStatus.values())
		{
			if (type.getCode().equals(code)) return type;
		}
		return null;
	}

	private IOStatus(String code, String value)
	{
		this.code = code;
		this.value = value;
	}

	public String getCode()
	{
		return code;
	}

	public String getValue()
	{
		return value;
	}
}

