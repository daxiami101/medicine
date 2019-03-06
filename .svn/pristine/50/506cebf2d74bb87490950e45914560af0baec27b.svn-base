/*
 * Date: 2013-7-25
 * author: Peream  (peream@gmail.com)
 *
 */
package cn.com.taiji.sample.manager.comm;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import cn.com.taiji.common.manager.AbstractManager;

/**
 * 总线、服务名的配置信息统一配置在此类中
 * 
 * @author Peream <br>
 *         Create Time：2013-7-25 下午1:47:50<br>
 *         <a href="mailto:peream@gmail.com">peream@gmail.com</a>
 * @since 1.0
 * @version 1.0
 */
@Service
public class CommParamConfig extends AbstractManager
{
	@Value("#{commProperties.busUrl}")
	private String busUrl;
	@Value("#{commProperties.serviceName}")
	private String serviceName;
	@Value("#{commProperties.mycommServiceName}")
	private String mycommServiceName;
	@Value("#{commProperties.enableSso}")
	private boolean enableSso;
	@Value("#{commProperties.enableLocalLogin}")
	private boolean enableLocalLogin;

	public String getBusUrl()
	{
		return busUrl;
	}

	public String getServiceName()
	{
		return serviceName;
	}

	public void setBusUrl(String busUrl)
	{
		this.busUrl = busUrl;
	}

	public void setServiceName(String serviceName)
	{
		this.serviceName = serviceName;
	}

	public boolean isEnableSso()
	{
		return enableSso;
	}

	public void setEnableSso(boolean enableSso)
	{
		this.enableSso = enableSso;
	}

	public String getMycommServiceName()
	{
		return mycommServiceName;
	}

	public void setMycommServiceName(String mycommServiceName)
	{
		this.mycommServiceName = mycommServiceName;
	}

	public boolean isEnableLocalLogin()
	{
		return enableLocalLogin;
	}

	public void setEnableLocalLogin(boolean enableLocalLogin)
	{
		this.enableLocalLogin = enableLocalLogin;
	}

}
