package alvin.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;

@WebServlet("/encoding")
public class EncodingServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf-8");
        Writer out = resp.getWriter();
        out.write("<!doctype html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <title>输入</title>\n" +
                "    <meta charset=\"utf-8\">\n" +
                "</head>\n" +
                "<body>\n" +
                "    <form action=\"encoding\" method=\"post\">\n" +
                "        <div>\n" +
                "            <label>请输入姓名\n" +
                "                <input type=\"text\" name=\"name\">\n" +
                "            </label>\n" +
                "        </div>\n" +
                "        <div>\n" +
                "            <button type=\"submit\">提交</button>\n" +
                "        </div>\n" +
                "    </form>\n" +
                "</body>\n" +
                "</html>");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");

        String name = req.getParameter("name");
        Writer out = resp.getWriter();
        out.write("<!doctype html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <title>结果</title>\n" +
                "    <meta charset=\"utf-8\">\n" +
                "</head>\n" +
                "<body>\n" +
                "    <div>Hello, " + name + "!</div>\n" +
                "</body>\n" +
                "</html>");
    }
}
