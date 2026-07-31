package com.classic.craftorder.infraestructura.configuracion;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.servlet.HandlerInterceptor;

public class SesionInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession(false);
        String rol = session != null ? (String) session.getAttribute("usuarioRol") : null;
        String uri = request.getRequestURI();

        if (rol == null) {
            response.sendRedirect("/login");
            return false;
        }

        if (uri.startsWith("/admin") && !"ADMIN".equals(rol)) {
            response.sendRedirect("/login");
            return false;
        }

        if (uri.startsWith("/artesano") && !"ARTESANO".equals(rol)) {
            response.sendRedirect("/login");
            return false;
        }

        return true;
    }
}
