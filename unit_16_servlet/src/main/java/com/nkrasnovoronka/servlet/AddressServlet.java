package com.nkrasnovoronka.servlet;

import com.nkrasnovoronka.model.IpAddress;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
public class AddressServlet extends HttpServlet {

    private IpAddress ipAddress;

    @Override
    public void init() {
        System.out.println(getServletName() + " initialized");
        ipAddress = new IpAddress();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String remoteHost = req.getRemoteHost();
        String userAgent = req.getHeader("User-Agent");
        ipAddress.addIpAndUserAgent(remoteHost, userAgent);

        PrintWriter responseBody = resp.getWriter();
        resp.setContentType("text/html");
        responseBody.println("<table style=\"width:100%\">");
        responseBody.println("" +
                "<tr>\n" +
                "    <th>Ip address</th>\n" +
                "    <th>User-Agent</th>\n" +
                " </tr>");


        for(Map.Entry<String, String> entry : ipAddress.getIpAddresses().entrySet()){
            if(entry.getKey().equals(remoteHost) && entry.getValue().equals(userAgent)){
                responseBody.println(String.format(
                                "<tr>\n" +
                                "    <td><b>%s</b></td>\n" +
                                "    <td><b>%s</b></td>\n" +
                                "</tr>", entry.getKey(), entry.getValue()));
            }else {
                responseBody.println(String.format(
                                "<tr>\n" +
                                "    <td>%s</td>\n" +
                                "    <td>%s</td>\n" +
                                "</tr>", entry.getKey(), entry.getValue()));
            }
        }
        responseBody.println("</table>");
    }


    @Override
    public void destroy() {
        System.out.println(getServletName() + " destroyed");
    }
}