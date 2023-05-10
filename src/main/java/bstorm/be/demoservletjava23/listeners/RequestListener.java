package bstorm.be.demoservletjava23.listeners;

import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletRequestEvent;
import jakarta.servlet.ServletRequestListener;
import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpServletRequest;

import java.util.logging.Level;
import java.util.logging.Logger;

@WebListener
public class RequestListener implements ServletRequestListener {

    private static final Logger LOG = Logger.getLogger(RequestListener.class.getName());

    @Override
    public void requestInitialized(ServletRequestEvent sre){
        HttpServletRequest request = (HttpServletRequest) sre.getServletRequest();
        request.getSession(true).setAttribute("startTime",System.currentTimeMillis());
    }

    @Override
    public void requestDestroyed(ServletRequestEvent sre) {
        HttpServletRequest request = (HttpServletRequest) sre.getServletRequest();
        long startTime = (Long) request.getSession(true).getAttribute("startTime");
        LOG.log(Level.INFO,"Request takes {0} milliseconds.",System.currentTimeMillis() - startTime);
    }
}
