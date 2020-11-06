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
        }
        catch (Exception ex) {
            throw new ServletException("Servlet Error: Error en insert " + ex.getCause());
        }

    }

    public void doDelete(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        try {
            adicionalService.deleteService(Integer.parseInt(req.getParameter("id")));
        }
        catch (Exception ex) {
            throw new ServletException("Servlet Error: Error en delete " + ex.getCause());
        }

    }

    public void doPut(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        AdicionalDTO adicionalDTO = new AdicionalDTO();

        adicionalDTO.setDescripcion(req.getParameter("descripcion"));
        adicionalDTO.setPrecioAdicional(Float.parseFloat(req.getParameter("precio")));

        try {
            adicionalService.updateService(adicionalDTO);
        }
        catch (Exception ex) {
            throw new ServletException("Servlet Error: Error en update " + ex.getCause());
        }

    }

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        try {
            adicionalService.consultarAdicional(Integer.parseInt(req.getParameter("id")));
        }
        catch (Exception ex) {
            throw new ServletException("Servlet Error: Error en consutlar " + ex.getCause());
        }

    }

}
