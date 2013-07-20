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
		// ����һ�黭��(���ڴ��е�ӳ�����),ǰ����������ͼ��Ĵ�С,������������ͼƬ����ɫ��ʽ
		BufferedImage bi = new BufferedImage(85, 30, BufferedImage.TYPE_INT_RGB);
		// ��û���
		Graphics g = bi.getGraphics();
		// ����������ɫ(�������ɫ)
		Random r = new Random();

		g.setColor(new Color(r.nextInt(255), r.nextInt(255), r.nextInt(255)));
		// ����һ������ɫ
		g.fillRect(0, 0, 85, 30);
		// ������д����֤��
		String checkcode = getRandom() + getRandom() + getRandom()
				+ getRandom();
		session.put("checkcode", checkcode);
		// ��Ҫд����֤��������ɫ
		g.setColor(new Color(r.nextInt(255), r.nextInt(255), r.nextInt(255)));
		// ��������(����,������ʽ,�����С)
		g.setFont(new Font(null, Font.ITALIC, 27));
		// ���������λ��
		g.drawString(checkcode, 5, 25);
		// ��СͼƬ���ֵı�ʶ��
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
		encoder.encode(image);// ��ͼƬѹ������
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
