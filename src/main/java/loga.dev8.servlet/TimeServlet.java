package loga.dev8.servlet;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@WebServlet(value = "/time")
public class TimeServlet extends HttpServlet {

    public static final String DATE = "yyyy-MM-dd HH:mm:ss z";
    public static final String TIMEZONE = "timezone";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String requestedTimezone = request.getParameter(TIMEZONE);

        if (requestedTimezone == null || requestedTimezone.isEmpty()) {
            response.getWriter().write(getFormattedCurrentTime("UTC"));
        } else {
            String formattedTime = getFormattedTimeInTimezone(requestedTimezone);
            response.getWriter().write(formattedTime);
        }
    }

    private String getFormattedTimeInTimezone(String timezone) {
        Instant currentTime = new Date().toInstant();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE)
                .withZone(ZoneId.of(timezone));
        return formatter.format(currentTime);
    }

    private String getFormattedCurrentTime(String timezone) {
        return getFormattedTimeInTimezone(timezone);
    }
}