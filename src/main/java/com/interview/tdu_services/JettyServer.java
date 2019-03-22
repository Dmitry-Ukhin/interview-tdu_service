package com.interview.tdu_services;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.AbstractHandler;

public class JettyServer {
    private final Server server;
    private final AbstractHandler handler;

    public JettyServer(int port, AbstractHandler handler){
        this.server = new Server(port);
        this.handler = handler;
    }

    public void start() throws Exception{
        server.setHandler(handler);
        server.start();
        server.join();
    }
}
