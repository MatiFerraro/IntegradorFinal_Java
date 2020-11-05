package controller;

import DTOs.AdicionalDTO;
import services.AdicionalService;
import services.imp.AdicionalServiceImp;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class AdicionalController extends HttpServlet {

    AdicionalService adicionalService = new AdicionalServiceImp();

    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        AdicionalDTO adicionalDTO = new AdicionalDTO();

        adicionalDTO.setDescripcion(req.getParameter("descripcion"));
        adicionalDTO.setPrecioAdicional(Float.parseFloat(req.getParameter("precio")));

        try {
            adicionalService.agregarAdicional(adicionalDTO);
        } catch (Exception ex) {
            throw new ServletException("Servlet Error: Error en insert" + ex.getCause());
        }

        res.setContentType("text/html");
        PrintWriter pw = res.getWriter();
        pw.println("<html><head>");
        pw.println("<TITLE>Servlet Curso Java Método Post</TITLE>");
        pw.println("</head><body>");
        pw.println("<p>Insertar un Adicional</p>");
        pw.println(req.getParameter("descripcion"));
        pw.println(req.getParameter("precio"));
        pw.println("</body>");
        pw.close();

    }

    public void doDelete(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        try {
            adicionalService.deleteService(Integer.parseInt(req.getParameter("id")));
        }
        catch (Exception ex) {
            throw new ServletException("Servlet Error: Error en delete" + ex.getCause());
        }

        res.setContentType("text/html");
        PrintWriter pw = res.getWriter();
        pw.println("<html><head>");
        pw.println("<TITLE>Servlet Curso Java Metodo GET</TITLE>");
        pw.println("</head><body>");
        pw.println("<p>Eliminar Adicional</p>");
        pw.println("</body>");
        pw.close();

    }

    public void doPut(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        AdicionalDTO adicionalDTO = new AdicionalDTO();

        adicionalDTO.setDescripcion(req.getParameter("descripcion"));
        adicionalDTO.setPrecioAdicional(Float.parseFloat(req.getParameter("precio")));

        try {
            adicionalService.updateService(adicionalDTO);
        }
        catch (Exception ex) {
            throw new ServletException("Servlet Error: Error en update" + ex.getCause());
        }

        res.setContentType("text/html");
        PrintWriter pw = res.getWriter();
        pw.println("<html><head>");
        pw.println("<TITLE>Servlet Curso Java Metodo GET</TITLE>");
        pw.println("</head><body>");
        pw.println("<p>Actualizar Adicional</p>");
        pw.println("</body>");
        pw.close();

    }

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        try {
            adicionalService.consultarAdicional(Integer.parseInt(req.getParameter("id")));
        }
        catch (Exception ex) {
            throw new ServletException("Servlet Error: Error en consutlar" + ex.getCause());
        }

        res.setContentType("text/html");
        PrintWriter pw = res.getWriter();
        pw.println("<html><head>");
        pw.println("<TITLE>Servlet Curso Java Metodo GET</TITLE>");
        pw.println("</head><body>");
        pw.println("<p>Consultar un Adicional</p>");
        pw.println("</body>");
        pw.close();

    }

}
