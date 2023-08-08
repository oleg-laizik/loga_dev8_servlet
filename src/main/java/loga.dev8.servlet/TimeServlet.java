package loga.dev8.servlet;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

@WebServlet("/time")
public class TimeServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=UTF-8");

        String timezoneParam = request.getParameter("timezone");
        ZoneId timezone = parseTimezone(timezoneParam);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss z");
        LocalDateTime currentTime;

        if (timezone != null) {
            currentTime = LocalDateTime.now(timezone);
        } else {
            currentTime = LocalDateTime.now();
        }

        String formattedTime = currentTime.format(formatter);

        response.getWriter().println("<html><body>");
        response.getWriter().println("Current Time: " + formattedTime);
        response.getWriter().println("</body></html>");
    }

    private ZoneId parseTimezone(String timezone) {
        if (timezone != null) {
            return ZoneId.of(timezone);
        }
        return null;
    }
}