<%@page import="java.util.ArrayList"%>
<%@page import="com.emergentes.Tareas"%>
<%
 
    if (session.getAttribute("almacena") == null){
        ArrayList<Tareas> lisaux = new ArrayList<Tareas>();
        session.setAttribute("almacena", lisaux);
    }
    ArrayList<Tareas> almacena = (ArrayList<Tareas>)session.getAttribute("almacena");
%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Tareas Pendientes</h1>
        <form action="ContadorServlet" method="post">
            <table>
                <tr>
                    <td>Id</td>
                    <td><input type="text" name="id" value="0"></td>
                </tr>
                 <tr>
                    <td>Tarea</td>
                    <td><input type="text" name="tarea" value=""></td>
                </tr>
                 <tr>
                    <td></td>
                    <td><input type="submit" value="procesar"></td>
                </tr>
            </table> 
        </form>
        
        <a href="ContadorServlet?op=vaciar">Nuevo</a>
        
        <h2>Contenido de las tareas</h2>
        <table border="1">
            <tr>
                <th>Id</th>
                <th>Tarea</th>
                <th>Completado</th>
                <th></th>
            </tr>
            
            
             <tr>
                
                <td>1</td>
                <td>Reunion con Estudiantes de la Carrera</td>
                <td><input type="checkbox" name="completado" value=""></td>
                <td><a href="ContadorServlet?op=eliminar&id=<">Eliminar</a></td>
                
            </tr>
            
             <tr>

                <td>2</td>
                <td>Estudiar para el examen de probabilidades</td>
                <td><input type="checkbox" name="completado" value=""></td>
                <td><a href="ContadorServlet?op=eliminar&id=<">Eliminar</a></td>
                
            </tr>
            
            <tr>

                <td>3</td>
                <td>Partido de futsal</td>
                <td><input type="checkbox" name="completado" value=""></td>
                <td><a href="ContadorServlet?op=eliminar&id=<">Eliminar</a></td>
                
            </tr>
            <%
              if   (almacena != null){
                  for(Tareas t : almacena){                  
            %>
            <tr>
                <td><%= t.getId() %> </td>
                <td>  <%= t.getTarea() %> </td>
                <td>     <input type="checkbox" name="completado" >      </td>
                <td><a href="ContadorServlet?op=eliminar&id=<%= t.getId() %>">Eliminar</a></td>
            </tr>
            <%
                  }
                }
            %>
        </table>  
        
        <a href="ContadorServlet">Mostrar el contador de visitas personalizado</a>
    </body>
</html>
