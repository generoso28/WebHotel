/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projetoweb.servico;

//import com.mycompany.projetoweb.modelo.servico.WebConstantes;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
//import jakarta.servlet.annotation.WebServlet;
//import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
//import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *
 * @author tulio
 */

//@WebServlet(WebConstantes.BASE_PATH + "/AuthFilter")
@WebFilter(urlPatterns = { "/*" }) 
/*Um filtro em Java EE é uma classe Java que pode ser usada para interceptar solicitações a um aplicativo antes que elas cheguem a 
um servlet ou JSP. Isso é útil para verificar se um usuário está logado antes de permitir acesso a páginas restritas.*/
public class AuthFilter implements Filter {

    public AuthFilter() {
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // Configuração de inicialização do filtro, se necessário.
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
       HttpServletResponse httpResponse = (HttpServletResponse) response;
          // Obter o caminho da requisição
        String path = httpRequest.getRequestURI().substring(httpRequest.getContextPath().length());
  
      // String path = httpRequest.getServletPath();
          System.out.println("PATH:"+path);
        // Verifica se "user" está na sessão
       // System.out.println("USER: "+httpRequest.getSession().getAttribute("user"));
        if (path.startsWith("/public/") || path.equalsIgnoreCase("/login.jsp") 
                || path.equalsIgnoreCase("/com/mycompany/projetoweb/controlador/LoginControlador")
                || path.equalsIgnoreCase("/com/mycompany/projetoweb/controlador/LoGounttrolador")
                || path.equalsIgnoreCase("/index.jsp") || path.equalsIgnoreCase("/Menu.jsp")
                || path.contains("/css/") || path.contains("/js/") || path.contains("/images/")) {
            chain.doFilter(request, response); // Continuar sem bloquear essas requisições
        } 
      //   if (path.equals("/login.jsp")) {
      //     chain.doFilter(request, response); // Continua com a cadeia de filtros sem bloquear
      // } 
         else {
        if (httpRequest.getSession().getAttribute("user") == null) {
            httpResponse.sendRedirect("/projetoWEB/login.jsp"); // Redirecionar para a página de login
           //   RequestDispatcher dispatcher = request.getRequestDispatcher("/login.jsp");
           // dispatcher.forward(request, response);
        } else {
            chain.doFilter(request, response); // Usuário está logado, continue com a solicitação.
        }
         }
    }

    @Override
    public void destroy() {
        // Recursos de limpeza do filtro, se necessário.
    }
}


/*

if (path.startsWith("/assets/")) { // Exemplo: diretório de recursos estáticos
            chain.doFilter(request, response);
        } 

 public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String path = httpRequest.getServletPath();

        // Permitir acesso à página pública específica
        if (path.equals("/user/publicPage.jsp")) {
            chain.doFilter(request, response); // Continua com a cadeia de filtros sem bloquear
        } else {
            // Restante da lógica do filtro
            if (httpRequest.getSession().getAttribute("user") == null) {
                ((HttpServletResponse) response).sendRedirect("login.jsp"); // Redireciona para a página de login
            } else {
                chain.doFilter(request, response); // O usuário está logado, permitir acesso
            }
        }


xml:

   <filter>
   
      <filter-name>AuthFilter</filter-name>
    <filter-class>com.mycompany.projetoweb.modelo.controlador.AuthFilter</filter-class>
</filter>
    <filter-mapping>
        <filter-name>AuthFilter</filter-name>
        <url-pattern>/CadastroFuncionario.jsp</url-pattern>  Ajuste o padrão para suas URLs protegidas -->
        </filter-mapping> 
*/