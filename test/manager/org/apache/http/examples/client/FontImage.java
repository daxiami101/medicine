/*
 * ====================================================================
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 * ====================================================================
 *
 * This software consists of voluntary contributions made by many
 * individuals on behalf of the Apache Software Foundation.  For more
 * information on the Apache Software Foundation, please see
 * <http://www.apache.org/>.
 *
 */
package org.apache.http.examples.client;
import java.awt.Color;  
import java.awt.Font;  
import java.awt.FontMetrics;  
import java.awt.Graphics;  
import java.awt.Rectangle;  
import java.awt.image.BufferedImage;  
import java.io.File;  
  
import javax.imageio.ImageIO;  
  
public class FontImage {  
    public static void main(String[] args) throws Exception { 
//    	枸杞，白芍，地黄，山药，黄芩，当归，党参，金银花，蒲公英，连翘
//    	String  medicineName="冬虫夏草";
        String[]medicineNames={"枸杞","白芍","地黄","山药","黄芩","当归","党参","金银花","蒲公英","连翘"};
        for(String medicineName:medicineNames){
        	int fontSize=30;
        	int fontNum=medicineName.length();
            createImage(medicineName, new Font("微软雅黑", Font.PLAIN, fontSize), 
            		new File("C:/Users/dn/Desktop/二维码测试/二维码样例文件2/"+medicineName+".png"), fontSize*fontNum, 64);
            
            String logoFilePath="C:/Users/dn/Desktop/二维码测试/二维码样例文件2/"+medicineName+".png";
        	String saveFilePath="C:/Users/dn/Desktop/二维码测试/二维码样例文件2/"+medicineName+".png";
            ZXingCode.getLogoQRCode("https://www.baidu.com/",logoFilePath,saveFilePath, medicineName);
        }
    }  
  
    // 根据str,font的样式以及输出文件目录  
    public static void createImage(String str, Font font, File outFile,  
            Integer width, Integer height) throws Exception {  
        // 创建图片  
        BufferedImage image = new BufferedImage(width, height,  
                BufferedImage.TYPE_INT_BGR);  
        Graphics g = image.getGraphics();  
        g.setClip(0, 0, width, height);  
        g.setColor(Color.white);  
        g.fillRect(0, 0, width, height);// 先用黑色填充整张图片,也就是背景  
        g.setColor(Color.black);// 在换成黑色  
        g.setFont(font);// 设置画笔字体  
        /** 用于获得垂直居中y */  
        Rectangle clip = g.getClipBounds();  
        FontMetrics fm = g.getFontMetrics(font);  
        int ascent = fm.getAscent();  
        int descent = fm.getDescent();  
        int y = (clip.height - (ascent + descent)) / 2 + ascent;  
        for (int i = 0; i < 6; i++) {// 256 340 0 680  
            g.drawString(str, i * 680, y);// 画出字符串  
        }  
        g.dispose();  
        ImageIO.write(image, "png", outFile);// 输出png图片  
    }  
}  