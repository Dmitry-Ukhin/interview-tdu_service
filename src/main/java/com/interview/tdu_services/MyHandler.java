package com.interview.tdu_services;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.AbstractHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

public class MyHandler extends AbstractHandler{
    private final ObjectMapper objectMapper = new ObjectMapper();

    public MyHandler() {
    }

    @Override
    public void handle(String s, Request request, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException, ServletException {
        Map<Integer, String> statusCells = Application.getBuffer().getStatusCells();

        httpServletResponse.setContentType("application/json");
        httpServletResponse.setStatus(HttpServletResponse.SC_OK);

        objectMapper.writeValue(httpServletResponse.getWriter(), statusCells);
    }
}
