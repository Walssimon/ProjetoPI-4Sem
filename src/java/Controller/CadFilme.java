/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;
import model.Filme;
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
import model.GeneroFilme;


@WebServlet(name = "CadFilme", urlPatterns = {"/CadFilme"})
@MultipartConfig
public class CadFilme extends HttpServlet {
    
    private String statusSQL;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            response.setContentType("text/html;charset=UTF-8");
        
        
        String sHTML = "";
        String Modal = "";

        HttpSession session = request.getSession(true);
        if (String.valueOf(session.getAttribute("nome")) == null) 
            response.sendRedirect("./login.html");
        
        statusSQL = "Entrou no serverlet";
      
        Filme film = new Filme(); // Instancia o objeto Usuario
        request.setCharacterEncoding("UTF-8");  
        String filmeid = request.getParameter("id");
        if (filmeid != null)
            film.id = Integer.parseInt(filmeid);
        film.nome = request.getParameter("nome");
        film.sinopse = request.getParameter("sinopse");
        film.duracao = request.getParameter("duracao");
        film.dataLancamento = request.getParameter("data");
        String classificacaoStr = request.getParameter("classificacao");
        int classificacao = 0;
        String avaliacaoStr = request.getParameter("avaliacao");
        double avaliacao = 0.0;
        String genreoStr = request.getParameter("categoria");
        int categoria;
    
         if (classificacaoStr != null && !classificacaoStr.trim().isEmpty()) {
            // Verificando a classificação para converter em um número
            if (classificacaoStr.equals("12") || classificacaoStr.equals("+12")) {
                classificacao = 12;
            } if (classificacaoStr.equals("10") || classificacaoStr.equals("+10")) {
                classificacao = 10;
            }else if (classificacaoStr.equals("14") || classificacaoStr.equals("+14")) {
                classificacao = 14;
            } else if (classificacaoStr.equals("16") || classificacaoStr.equals("+16")) {
                classificacao = 16;
            } else if (classificacaoStr.equals("18") || classificacaoStr.equals("+18")) {
                classificacao = 18;
            } else if (classificacaoStr.equalsIgnoreCase("Livre")) {
                classificacao = 0; // Valor para classificação "Livre"
            } else {
                classificacao = 0; // V
                //valor padrão, caso o valor não corresponda
            }
            film.classificacao = classificacao;
        }
         if (avaliacaoStr != null && !avaliacaoStr.trim().isEmpty()) {
            try {
                avaliacao = Double.parseDouble(avaliacaoStr);
                film.avaliacao = avaliacao;
            } catch (NumberFormatException e) {
                // Tratar erro de conversão de número
                e.printStackTrace();  // Log de erro ou definir valor padrão
                film.avaliacao = 0.0;
            }
        }
         
          GeneroFilme genf = new GeneroFilme();
          if(genreoStr != null && !genreoStr.trim().isEmpty()){
             if (genreoStr.equals("Ação")) {
                genf.idGenero = 1;
            }else if(genreoStr.equals("Comédia")){
                genf.idGenero = 2;
            }else if(genreoStr.equals("Românce")){
                genf.idGenero = 3;
            }else if(genreoStr.equals("Aventura")){
                genf.idGenero = 4;
            }else if(genreoStr.equals("Animação")){
                genf.idGenero = 5;
            }else if(genreoStr.equals("Ficção")){
                genf.idGenero = 6;
            }else if(genreoStr.equals("Drama")){
                genf.idGenero = 7;
            }else if(genreoStr.equals("Terror")){
                genf.idGenero = 8;
            }else if(genreoStr.equals("Fantasia")){
                genf.idGenero = 9;
            }else if(genreoStr.equals("Suspense")){
                genf.idGenero = 10;
            }else if(genreoStr.equals("Mistério")){
                genf.idGenero = 11;
            }else if(genreoStr.equals("Documentário")){
                genf.idGenero = 12;
            }else if(genreoStr.equals("Musical")){
                genf.idGenero = 13;
            } else if(genreoStr.equals("Histórico")){
                genf.idGenero = 14;
            } else if(genreoStr.equals("Família")){
                genf.idGenero = 15;
            }    
            
            
    }
     
/* Código para trazer a requisição do arquivo e colocar na objeto user */
        Part part = request.getPart("arquivoCapa");
        if(part != null){
        InputStream arquivoCapa = part.getInputStream();
        film.capatamanho = part.getSize();
        film.capa = arquivoCapa; }
        
        Part part2 = request.getPart("arquivoBanner");
        if(part2 != null){
        InputStream arquivoBanner = part2.getInputStream();
        film.bannertamanho = part2.getSize();
        film.banner = arquivoBanner; }
/* Código para trazer a requisição do arquivo e colocar na objeto user */
        if (request.getParameter("oper") != null) {
            film.incluirFilme();
            genf.idFilme = genf.buscarUltimoFilme();
            genf.incluir();
            statusSQL = film.nome + film.sinopse + film.bannerimagemBase64 + film.banner + film.banner + film.bannerimagemBase64 + film.capaimagemBase64 + film.banner + film.capatamanho;
            if (film.statusSQL == null) {
                statusSQL = "Registro Alterado com Sucesso !";
            }}
        
        
        if (request.getParameter("deletar") != null) {
            
            session.invalidate();
            statusSQL = "Você deletou seu usuário, sua sessão foi fechada!";}
        
        if (request.getParameter("alterar") != null) {
            film.alterar();
            genf.idFilme = film.id;
            genf.alterar();
            if (film.statusSQL == null) {
                statusSQL = "Registro Alterado com Sucesso !"+genf.idFilme + film.id ;
            }}

        
        
        
        try (PrintWriter out = response.getWriter()) {
            response.sendRedirect("./loadingFilm.html");

            //out.println("<!DOCTYPE html>");
            //out.println("<html>");
            //out.println("<head>");
            //out.println("<title>Servlet Upload</title>");
            //out.println("</head>");
            //out.println("<body>");
            //out.println("<h1>Servlet Upload at " + statusSQL + "\n" +film.statusSQL + "</h1>");
           // out.println("</body>");
            //out.println("</html>");
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
