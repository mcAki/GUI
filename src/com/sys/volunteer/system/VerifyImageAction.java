package com.sys.volunteer.system;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Random;

import javax.imageio.ImageIO;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.sys.volunteer.baseaction.BaseAction;
import com.sys.volunteer.common.Const;

@Controller
@Scope("prototype")
public class VerifyImageAction extends BaseAction{

	private String[] fontsList = {"Arial",
			"Times New Roman",
			"Courier",
			"MS Serif",
			"Tahoma",
			"Verdana",
			"Comic Sans",
			"MS Sans Serif",
			"Impact",
			"Lucida Console",
			"Constantia",
			"Modem",
			"Monotype Corsiva",
			"Arial Bold"};
	
	private String randChar = "abcdefghjkmnpqstuvwxyzABCDEFGHJKLMNPQRSTUVWXYZ23456789";
	
	private String verifyCode;
	
	private Random random;
	
	private int codeLength;
	
	private int fontTop;
	private int fontLeft;
	private int fontSpace;
	
	private int width;	

	private int height;
	
    //在内存中创建图象
	public String getVerifyCode() {
		return verifyCode;
	}

	public void setVerifyCode(String verifyCode) {
		this.verifyCode = verifyCode;
	}
	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public Color getRndColor(){		

		return new Color(
				random.nextInt(190),
				random.nextInt(190),
				random.nextInt(190));
	}
	
	@Override
	public String execute() throws Exception {
		codeLength =5;
	    width = 100;
	    height = 28;	
		
	    BufferedImage image = new BufferedImage(width, height,BufferedImage.TYPE_INT_BGR );
	    //获取图形上下文

	    Graphics g = image.getGraphics();
	    //设定背景色

	    g.setColor(Color.white);
	    g.fillRect(0, 0, width, height);
	    //画边框

	    g.setColor(Color.black);
	    g.drawRect(0, 0, width - 1, height - 1);
	    //取随机产生的认证码(4位数字)

	    //Math.random()方法来产生一个随机数，这个产生的随机数是0-1之间的一个double，我们可以把他乘以一定的数，比如说乘以100，他就是个100以内的随机

	    random = new Random();
	    
	    String rand = String.valueOf(Math.random()*10000);
	    
		fontTop=18;
		fontLeft=10;
		fontSpace=17;
	
	    String tmpChar;
	    String Code="";
	    
	    //随机位置便宜  震动频率
	    int rdnXSeed = 1;
	    int rdnYSeed = 1;
	    
	    
	        
	    for(int i=0;i<codeLength;i++)
	    {
	    	tmpChar = randChar.charAt(random.nextInt(randChar.length())) + "";
	    	Code += tmpChar;
	    	
			g.setColor(getRndColor());
			String fontName = fontsList[random.nextInt(fontsList.length-1)];
			
			g.setFont(new Font(fontName, Font.PLAIN, 20));
			g.drawString(tmpChar, 
					fontLeft + (random.nextInt(rdnXSeed<<2)-rdnXSeed) + (fontSpace*i), 
					fontTop + (random.nextInt(rdnXSeed<<2)-rdnXSeed));	       
	    }
	      
	    //将认证码存入SESSION
	    getSession().put(Const.VERIFY_IMAGE_SESSION_KEY, Code.toLowerCase());
	    
	    System.out.println(Code);
	    //将认证码显示到图象中

	    int x,y,x2,y2;
	    
	    //随机产生干扰点,使图象中的认证码不易被其它程序探测到
	    for (int iIndex = 0; iIndex < 10; iIndex++) {
	        x = random.nextInt(width);
	        y = random.nextInt(height);
	        g.drawLine(x, y, x, y); 
	    }
	    //随机产生干扰线,使图象中的认证码不易被其它程序探测到
	    for (int iIndex = 0; iIndex < 2; iIndex++) {
	    	g.setColor(getRndColor());
	        x = random.nextInt(width);
	        y = random.nextInt(height);
	        x2 = random.nextInt(width);
	        y2 = random.nextInt(height);	        
	        g.drawLine(x, y, x2, y2);
	    }	    
	    
	    //图象生效

	    g.dispose();
	    //输出图象到页面

	    //以JPEG形式写入 当前HTTPSelvletResponse的流
	    ImageIO.write(image, "JPEG", getHttpServletResponse().getOutputStream());
	    
		return null;
	}
}
