package pl.mcapps;

import javax.management.RuntimeErrorException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;

@ControllerAdvice
public class ExceptionController {


	@ExceptionHandler(Exception.class)
	public String handleException() {
		return "excView";
			
	}
}




///*
//public String runtimeExc() {
//		throw new RuntimeException();
//		
//	}
//	@ExceptionHandler(Exception.class)
//	public String handleException() {
//		return "handleexception";
//			
//	}
//*/