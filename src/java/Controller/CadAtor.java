/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;
import model.Ator;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

/**
 *
 * @author Megas-XRL8
 */
@WebServlet(name = "CadAtor", urlPatterns = {"/CadAtor"})
@MultipartConfig
public class CadAtor extends HttpServlet {

    private String statusSQL;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        
        String sHtml = "";
        
        HttpSession session = request.getSession(true);
        if(String.valueOf(session.getAttribute("nome")) == null)
                response.sendRedirect("./login.html");
        
        statusSQL = "Entrou no servelet";
        
        Ator atr = new Ator();
        atr.nomeAtor = request.getParameter("nome");
        int age = 0;
        String idade = request.getParameter("idade");
        age = Integer.parseInt(idade);
        atr.idade = age;
        
       Part part = request.getPart("atorCapa");
        if(part != null){
        InputStream atorCapa = part.getInputStream();
        atr.capatamanho = part.getSize();
        atr.capa = atorCapa;
        }
      
      sHtml = "" + atr.nomeAtor;
        if(request.getParameter("oper") != null){
        
        }
        atr.IncluirAtor();
        try ( PrintWriter out = response.getWriter()) {
            response.sendRedirect("./loadingAtor.html");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
