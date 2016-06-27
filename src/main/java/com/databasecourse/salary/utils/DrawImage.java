package com.databasecourse.salary.utils;

import java.awt.*;
import java.awt.geom.*;
import java.awt.image.*;
import java.io.*;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.imageio.ImageIO;

public class DrawImage extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public DrawImage() {
        super();
    }

    public void destroy() {
        super.destroy();
    }

    public void init() throws ServletException {
        super.init();
    }

    public Color getRandColor(int s,int e){
        Random random=new Random ();
        if(s>255) s=255;
        if(e>255) e=255;
        int r,g,b;
        r=s+random.nextInt(e-s);
        g=s+random.nextInt(e-s);
        b=s+random.nextInt(e-s);
        return new Color(r,g,b);
    }

    @Override
    public void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);

        response.setContentType("image/jpeg");
        int width=86,height=25;
        BufferedImage image=new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
        Graphics g=image.getGraphics();
        Graphics2D g2d=(Graphics2D)g;
        Random random=new Random();
        Font mfont=new Font("console",Font.BOLD,16);
        g.setColor(getRandColor(200,250));
        g.fillRect(0, 0, width, height);
        g.setFont(mfont);
        g.setColor(getRandColor(180,200));


        for(int i=0;i<100;i++){
            int x=random.nextInt(width-1);
            int y=random.nextInt(height-1);
            int x1=random.nextInt(6)+1;
            int y1=random.nextInt(12)+1;
            BasicStroke bs=new BasicStroke(2f,BasicStroke.CAP_BUTT,BasicStroke.JOIN_BEVEL);
            Line2D line=new Line2D.Double(x,y,x+x1,y+y1);
            g2d.setStroke(bs);
            g2d.draw(line);
        }

        String sRand="";
        String ctmp="";
        int itmp=0;

        for(int i=0;i<4;i++){

            String[] rBase={"0","1","2","3","4","5","6","7","8","9"};
            int x = random.nextInt(10);
            ctmp = rBase[x];

            sRand+=ctmp;
            Color color=new Color(20+random.nextInt(110),20+random.nextInt(110),random.nextInt(110));
            g.setColor(color);

            Graphics2D g2d_word=(Graphics2D)g;
            AffineTransform trans=new AffineTransform();
            trans.rotate((45) * 3.14 / 180, 15 * i + 8, 7);

            g2d_word.setTransform(trans);
            g.drawString(ctmp, 15*i+18, 10);
        }
        HttpSession session=request.getSession(false);
        session.setAttribute("randCheckCode", sRand);
        g.dispose();
        ImageIO.write(image,"jpeg",response.getOutputStream());
    }
}
