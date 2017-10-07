package com.san.utils;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Code extends HttpServlet{
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		//PrintWriter out=resp.getWriter();
		//在内存中加载一张图片
		int width=60;
		int height=35;
		String suiji="";
		BufferedImage img=new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		//获得画笔
		Graphics g=img.getGraphics();
		g.setColor(Color.BLUE);
		g.drawRect(0, 0, width, height);
		g.setColor(Color.PINK);
		g.fillRect(1, 1, width-2, height-2);
		g.setColor(Color.BLACK);
		g.setFont(new Font("宋体",Font.BOLD,15));
		//产生随机数
		Random rd=new Random();
		int x=10;
		for(int i=0;i<4;i++){
			String m=rd.nextInt(10)+"";
			g.drawString(m, x, 23);
			x+=10;
			suiji+=m;
		}
		HttpSession session = req.getSession();
		session.setAttribute("suiName",suiji);
		//产生干扰线
		for(int i=0;i<8;i++){
			g.drawLine(rd.nextInt(width), rd.nextInt(height), rd.nextInt(width), rd.nextInt(height));
		}
		//输出
		ServletOutputStream pw = resp.getOutputStream();
		ImageIO.write(img, "jpg", pw);
	}
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req,resp);
	}
	
}
