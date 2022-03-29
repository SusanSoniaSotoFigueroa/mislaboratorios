
package com.emergentes;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

// SUSAN SONIA SOTO FIGUEROA
@WebServlet(name = "ControladorServlet", urlPatterns = {"/ControladorServlet"})
public class ControladorServlet extends HttpServlet {

   @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        
        {
        int contador =0;
        
        //obtener el arreglo de clientes
        Cookie cukis[] = request.getCookies();
        
        if(cukis != null){
            for(Cookie c : cukis){
                if (c.getName().equals("visitas")){
                    //antes de la asignacion se convierte un valor en cadena
                    contador = Integer.parseInt(c.getValue());
                }
            }
        }
        
        
        contador = contador + 1;
        
        //antes de la asignacion se convierte cadena en valor 
        Cookie c = new Cookie("visitas",Integer.toString(contador));
        
        c.setMaxAge(30);
        response.addCookie(c);
        // generar contenido a partir del servlet
        response.setContentType("text/html");
        
        PrintWriter out = response.getWriter();
        out.println("Visitante N "+ contador);
        }
        
        
        
        
        String op = request.getParameter("op");
        
        if(op.equals("vaciar")){
            
                       //para vaciar 
            HttpSession ses = request.getSession();
            
            ses.invalidate();
            
            response.sendRedirect("index.jsp");
            
        }
        if (op.equals("eliminar")){
            int pos = -1;
            int buscado = -1;
            int id = Integer.parseInt(request.getParameter("id"));
            //eliminar
            HttpSession ses = request.getSession();
            ArrayList<Tareas> lista = (ArrayList<Tareas>)ses.getAttribute("almacena");
            
            for(Tareas t : lista){
                pos++;
                if(t.getId() == id){
                    buscado = pos;
                }
            }
            lista.remove(buscado);
            response.sendRedirect("index.jsp");
        }      
           
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        
        //1
        int id = Integer.parseInt(request.getParameter("id"));
        String tarea = request.getParameter("tarea");
        String[] completado = request.getParameterValues("completado");
        //2
        Tareas objenc = new Tareas();
        //3
        objenc.setId(id);
        objenc.setTarea(tarea);
        objenc.setCompletado(completado);
        //4
        HttpSession ses = request.getSession();
        
        ArrayList<Tareas> lista = (ArrayList<Tareas>)ses.getAttribute("almacena");
        
        lista.add(objenc);
        
        response.sendRedirect("index.jsp");
        
        //request.setAttribute("tar", objenc);
        //5
        //request.getRequestDispatcher("salida.jsp").forward(request, response);
        
        
        

    }

}
