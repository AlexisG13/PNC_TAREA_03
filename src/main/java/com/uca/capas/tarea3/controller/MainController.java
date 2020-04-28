package com.uca.capas.tarea3.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class MainController {

    @RequestMapping("/ingresar")
    public String index() {
	return "commons/ingresar";
    }

    @RequestMapping("/alumnoGuardado")
    public ModelAndView guardarAlumno(@RequestParam String nombres, @RequestParam String apellidos,
	    @RequestParam String fechaNacimiento, @RequestParam String lugarNacimiento, @RequestParam String colegio,
	    @RequestParam String telFijo, @RequestParam String telMovil) throws ParseException {

	ModelAndView mav = new ModelAndView();
	ArrayList<String> errores = new ArrayList<String>();
	if (nombres.length() < 1 || nombres.length() > 25) {
	    errores.add("Los nombres deben de ser como minimo de 1 caracter o maximo de 25");
	}
	if (apellidos.length() < 1 || apellidos.length() > 25) {
	    errores.add("Los apellidos deben de ser como minimo de 1 caracter o maximo de 25");
	}
	Date fechaAux = new SimpleDateFormat("yyyy-mm-dd").parse(fechaNacimiento);
	Calendar calendar = new GregorianCalendar();
	calendar.setTime(fechaAux);
	int annio = calendar.get(Calendar.YEAR);
	if (annio < 2003) {
	    errores.add("La fecha no puede ser menor al 1 de enero de 2003");
	}
	if (lugarNacimiento.length() < 1 || lugarNacimiento.length() > 25) {
	    errores.add("El lugar de nacimiento debe de ser como minimo de 1 caracter o maximo de 25");
	}
	if (colegio.length() < 1 || colegio.length() > 100) {
	    errores.add("El colegio o instituto debe de ser como minimo de 1 caracter o maximo de 25");
	}
	if (String.valueOf(telFijo).length() != 8) {
	    errores.add("El telefono fijo debe de ser de 8 numeros exactamente");
	}
	if (String.valueOf(telMovil).length() != 8) {
	    errores.add("El telefono movil debe de ser de 8 numeros exactamente");
	}
	if (errores.size() != 0) {
	    mav.addObject("errores", errores);
	    mav.setViewName("commons/errores");
	    return mav;
	}
	mav.setViewName("commons/alumnoGuardado");
	return mav;
    }

}
