/*
 * Date: 2013年9月6日
 * author: Peream  (peream@gmail.com)
 *
 */
package cn.com.taiji.sample.manager.acl;

import cn.com.taiji.common.manager.pub.AbstractHelper;
import cn.com.taiji.mycomm.model.comm.protocol.sso.CommonUserInfo;
import cn.com.taiji.mycomm.model.comm.protocol.sso.SsoGender;
import cn.com.taiji.sample.entity.User;

/**
 * 
 * @author Peream <br>
 *         Create Time：2013年9月6日 下午5:21:05<br>
 *         <a href="mailto:peream@gmail.com">peream@gmail.com</a>
 * @since 1.0
 * @version 1.0
 */
public class SsoHelper extends AbstractHelper
{
	public static void protocol2User(CommonUserInfo info, User dest)
	{
		dest.setEmail(info.getEmail());
		dest.setFax(info.getFax());
		dest.setLoginName(info.getAppLoginName());
		dest.setMale(info.getGender() == SsoGender.MALE);
		String mobile = info.getMobile();
		if (!hasText(mobile)) mobile = "13800138000";
		dest.setMobile(mobile);
		dest.setName(info.getName());
		// dest.setStatus(UserStatus.NORMAL);
		dest.setTel(info.getTel());
	}

	public static void user2Protocol(User user, CommonUserInfo dest)
	{
		dest.setEmail(user.getEmail());
		dest.setFax(user.getFax());
		dest.setAppLoginName(user.getLoginName());
		dest.setGender(user.getMale() ? SsoGender.MALE : SsoGender.FEMALE);
		dest.setMobile(user.getMobile());
		dest.setName(user.getName());
		dest.setTel(user.getTel());
	}
}
