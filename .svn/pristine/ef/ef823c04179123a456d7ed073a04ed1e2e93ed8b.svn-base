/*
 * Date: 2013-7-22
 * author: Peream  (peream@gmail.com)
 *
 */
package cn.com.taiji.sample.manager.comm;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.taiji.mycomm.manager.comm.AbstractServiceManager;
import cn.com.taiji.mycomm.model.comm.protocol.mycomm.MycommSampleRequest;
import cn.com.taiji.mycomm.model.comm.protocol.mycomm.MycommSampleResponse;

/**
 * 用于请求其他系统（MYCOMM）的服务，需要请求多个系统就建立多了ServiceManager<BR>
 * 此处的serviceName指的是目标系统的服务名
 * 
 * @author Peream <br>
 *         Create Time：2013-7-22 下午3:58:21<br>
 *         <a href="mailto:peream@gmail.com">peream@gmail.com</a>
 * @since 1.0
 * @version 1.0
 */
@Service
public class MycommServiceManagerImpl extends AbstractServiceManager implements MycommServiceManager
{
	@Autowired
	public MycommServiceManagerImpl(CommParamConfig config)
	{
		super(config.getBusUrl(), config.getMycommServiceName());
	}

	@Override
	public MycommSampleResponse sampleRequest(MycommSampleRequest request) throws IOException
	{
		return requestHttpPost(MycommSampleResponse.class, getUrl(), request);
	}

}
