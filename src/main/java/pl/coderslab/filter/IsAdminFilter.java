package pl.coderslab.filter;


import pl.coderslab.entity.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@WebFilter(filterName = "IsAdminFilter", urlPatterns = {"/admin/*"})
public class IsAdminFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;

        HttpSession session = request.getSession();

        String url = request.getRequestURI();
        User user = (User) session.getAttribute("user");

        if(user == null){
            response.sendRedirect(request.getContextPath()+"/login");
            return;
        }

        if( !user.getIsAdmin() ){
        // nie daje zezwolenie
            response.sendRedirect(request.getContextPath()+"/");
            return;
        }

        //daje zezwolenia

        chain.doFilter(req, resp);
    }

    @Override
    public void destroy() {

    }
}





/*
@WebFilter(filterName = "AuthFilter", urlPatterns = {"*"})
public class AuthFilter implements Filter {


    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;

        HttpSession session = request.getSession();


        String url = request.getRequestURI();

        if( (url.equals("/abc") )) {
            if(session.getAttribute("user") == null){
                response.sendRedirect(request.getContextPath()+"/login");
                return;
            }
        }

//        if( !(url.equals("/login") || url.equals("/home")) ){
//            if(session.getAttribute("user") == null){
//                response.sendRedirect(request.getContextPath()+"/login");
//                return;
//            }
//        }

        chain.doFilter(req, resp);
    }

 */
