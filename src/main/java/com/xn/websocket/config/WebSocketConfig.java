package com.xn.websocket.config;

import javax.servlet.http.HttpSession;
import javax.websocket.HandshakeResponse;
import javax.websocket.server.HandshakeRequest;
import javax.websocket.server.ServerEndpointConfig;

import org.apache.catalina.session.StandardSessionFacade;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

@Configuration
public class WebSocketConfig extends ServerEndpointConfig.Configurator{
	
	
	 private static final String HttpSession = null;
	    /* 修改握手,就是在握手协议建立之前修改其中携带的内容 */
	    @Override
	    public void modifyHandshake(ServerEndpointConfig sec, HandshakeRequest request, HandshakeResponse response) {
	        /*如果没有监听器,那么这里获取到的HttpSession是null*/
	        StandardSessionFacade ssf = (StandardSessionFacade) request.getHttpSession();
	        if (ssf != null) {
	            HttpSession session = (HttpSession) request.getHttpSession();
	            sec.getUserProperties().put("sessionid", session);
	        }
	        sec.getUserProperties().put("name", "小强");
	        super.modifyHandshake(sec, request, response);
	    }
	
	
 
    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        
        return new ServerEndpointExporter();
    }
}
