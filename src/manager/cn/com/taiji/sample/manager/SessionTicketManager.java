/*
 * Date: 2013年9月4日
 * author: Peream  (peream@gmail.com)
 *
 */
package cn.com.taiji.sample.manager;

import cn.com.taiji.sample.manager.comm.sso.SampleServletSsoAuthHandler;
import cn.com.taiji.sample.manager.comm.sso.SampleSsoTopicHandler;

/**
 * 
 * @author Peream <br>
 *         Create Time：2013年9月4日 上午9:34:56<br>
 *         <a href="mailto:peream@gmail.com">peream@gmail.com</a>
 * @since 1.0
 * @version 1.0
 * @see {@link AclHandlerInterceptor} {@link SampleServletSsoAuthHandler}
 *      {@link SampleSsoTopicHandler#handleLogoutTopic(cn.com.taiji.mycomm.model.comm.protocol.sso.SsoLogoutTopic)}
 */
public interface SessionTicketManager
{
	/**
	 * 判断指定的TicketId是否有效
	 * 
	 * @param ticketId
	 * @return
	 */
	public boolean isTicketValid(String ticketId);

	/**
	 * 删除指定的ticketId
	 * 
	 * @param ticketId
	 */
	public void removeTicket(String ticketId);

	/**
	 * 将ticket加入到ticket cache中
	 * 
	 * @param ticketId
	 * @param sessionId
	 *            对应的sessionId
	 */
	public void addTicket(String ticketId, String sessionId);

	/**
	 * 清除老的ticket，用于清除那些没收到登陆退出通知，但是在cache中时间又比较长的ticket
	 */
	public void cleanOldTicket();

}
