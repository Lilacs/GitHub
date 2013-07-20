package org.lilacs.action.common;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import java.util.Random;

import org.lilacs.util.BaseAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;


import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

@Controller
@Scope("prototype")
@Transactional
public class CheckCode extends BaseAware {
	private InputStream image;

	public String execute() {
		// 创建一块画布(在内存中的映像对象),前两个参数是图像的大小,第三个参数是图片的颜色样式
		BufferedImage bi = new BufferedImage(85, 30, BufferedImage.TYPE_INT_RGB);
		// 获得画笔
		Graphics g = bi.getGraphics();
		// 给画笔上颜色(随机的颜色)
		Random r = new Random();

		g.setColor(new Color(r.nextInt(255), r.nextInt(255), r.nextInt(255)));
		// 绘制一个背景色
		g.fillRect(0, 0, 85, 30);
		// 往里面写上验证码
		String checkcode = getRandom() + getRandom() + getRandom()
				+ getRandom();
		session.put("checkcode", checkcode);
		// 给要写上验证码设置颜色
		g.setColor(new Color(r.nextInt(255), r.nextInt(255), r.nextInt(255)));
		// 设置字体(字体,字体样式,字体大小)
		g.setFont(new Font(null, Font.ITALIC, 27));
		// 设置字体的位置
		g.drawString(checkcode, 5, 25);
		// 减小图片数字的辨识度
		for (int i = 0; i < 5; i++) {
			g.setColor(new Color(r.nextInt(255), r.nextInt(255), r.nextInt(255)));
			g.drawLine(r.nextInt(60), r.nextInt(20), r.nextInt(60),
					r.nextInt(20));
		}
		try {
			image = getInputStream(bi);
			return "success";
		} catch (IOException e) {
			e.printStackTrace();
			return "fail";
		}

	}

	public String getRandom() {
		while (true) {
			Random r = new Random();
			int i = r.nextInt(122);
			if (i > 48 && i < 57 || i > 65 && i < 90 || i > 97 && i < 122) {
				String a = (char) i + "";
				return a;
			}
		}
	}

	public static InputStream getInputStream(BufferedImage image)
			throws IOException {
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(bos);
		encoder.encode(image);// 将图片压缩处理
		byte[] imageBts = bos.toByteArray();
		InputStream in = new ByteArrayInputStream(imageBts);
		return in;
	}

	public InputStream getImage() {
		return image;
	}

	public void setImage(InputStream image) {
		this.image = image;
	}

}
