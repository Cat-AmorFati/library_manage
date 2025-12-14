package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

@WebServlet("/CodeServlet")
public class CodeServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException, ServletException {

        // 生成验证码（4位随机数）
        String code = String.valueOf((int) ((Math.random() * 9 + 1) * 1000));
        req.getSession().setAttribute("checkcode", code);

        // 生成图片
        int width = 80, height = 32;
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics g = image.getGraphics();

        g.setColor(Color.WHITE);
        g.fillRect(0, 0, width, height);

        g.setFont(new Font("Arial", Font.BOLD, 22));
        g.setColor(Color.BLACK);
        g.drawString(code, 15, 25);

        // 输出图片
        resp.setContentType("image/png");
        ImageIO.write(image, "png", resp.getOutputStream());
    }
}
