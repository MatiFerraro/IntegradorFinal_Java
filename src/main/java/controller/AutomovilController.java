package controller;

import DTOs.AutomovilDTO;
import services.AutomovilService;
import services.imp.AutomovilServiceImp;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class AutomovilController extends HttpServlet {

    AutomovilService automovilService = new AutomovilServiceImp();

    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        AutomovilDTO automovilDTO = new AutomovilDTO();

        automovilDTO.setIdVariante(Integer.parseInt(req.getParameter("idVariante")));
        automovilDTO.setPrecioBase(Float.parseFloat(req.getParameter("precioBase")));
        automovilDTO.setPrecioFinal(Float.parseFloat(req.getParameter("precioFinal")));

        try {
            automovilService.fabricarAuto(automovilDTO);
        }
        catch (Exception ex) {
            throw new ServletException("Servlet Error: Error en insert " + ex.getCause());
        }

    }

    public void doDelete(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        try {
            automovilService.deleteService(Integer.parseInt(req.getParameter("id")));
        }
        catch (Exception ex) {
            throw new ServletException("Servlet Error: Error en delete " + ex.getCause());
        }

    }

    public void doPut(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        AutomovilDTO automovilDTO = new AutomovilDTO();

        automovilDTO.setIdVariante(Integer.parseInt(req.getParameter("idVariante")));
        automovilDTO.setPrecioBase(Float.parseFloat(req.getParameter("precioBase")));
        automovilDTO.setPrecioFinal(Float.parseFloat(req.getParameter("precioFinal")));

        try {
            automovilService.updateService(automovilDTO);
        }
        catch (Exception ex) {
            throw new ServletException("Servlet Error: Error en update " + ex.getCause());
        }

    }

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        try {
            automovilService.consultarAuto(Integer.parseInt(req.getParameter("id")));
        }
        catch (Exception ex) {
            throw new ServletException("Servlet Error: Error en consultar " + ex.getCause());
        }

    }

    public void doGetPrecio(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        try {
            automovilService.consultarAuto_Precio(Integer.parseInt(req.getParameter("id")));
        }
        catch (Exception ex) {
            throw new ServletException("Servlet Error: Error en consutlar precio " + ex.getCause());
        }

    }

    public void doGetLista(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        try {
            automovilService.consultarAutos();
        }
        catch (Exception ex) {
            throw new ServletException("Servlet Error: Error en consutlar lista " + ex.getCause());
        }

    }

}
