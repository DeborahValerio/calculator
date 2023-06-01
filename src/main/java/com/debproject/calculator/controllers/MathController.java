package com.debproject.calculator.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.debproject.calculator.controllers.exceptions.UnsupportedMathOperationException;

@RestController
public class MathController {

	@GetMapping(value = "/sum/{numberOne}/{numberTwo}")
	public Double sum(@PathVariable(value = "numberOne") String numberOne, @PathVariable(value = "numberTwo") String numberTwo) throws Exception {
		
		if(!isNumeric(numberOne) || !isNumeric(numberTwo)) {
			throw new UnsupportedMathOperationException("Please set a numeric value");
		}
	
		return convertToDouble(numberOne) + convertToDouble(numberTwo);
	}
	
	@GetMapping(value = "/sub/{numberOne}/{numberTwo}")
	public Double sub(@PathVariable(value = "numberOne") String numberOne, @PathVariable(value = "numberTwo") String numberTwo) throws Exception {
		
		if(!isNumeric(numberOne) || !isNumeric(numberTwo)) {
			throw new UnsupportedMathOperationException("Please set a numeric value");
		}
	
		return convertToDouble(numberOne) - convertToDouble(numberTwo);
	}
	
	@GetMapping(value = "/multi/{numberOne}/{numberTwo}")
	public Double multi(@PathVariable(value = "numberOne") String numberOne, @PathVariable(value = "numberTwo") String numberTwo) throws Exception {
		
		if(!isNumeric(numberOne) || !isNumeric(numberTwo)) {
			throw new UnsupportedMathOperationException("Please set a numeric value");
		}
	
		return convertToDouble(numberOne) * convertToDouble(numberTwo);
	}
	
	@GetMapping(value = "/div/{numberOne}/{numberTwo}")
	public Double div(@PathVariable(value = "numberOne") String numberOne, @PathVariable(value = "numberTwo") String numberTwo) throws Exception {
		
		if(!isNumeric(numberOne) || !isNumeric(numberTwo)) {
			throw new UnsupportedMathOperationException("Please set a numeric value");
		}
		
		if(convertToDouble(numberTwo) == 0.0) {
			throw new UnsupportedMathOperationException("Division by 0 does not exist");
		}
	
		return convertToDouble(numberOne) / convertToDouble(numberTwo);
	}
	
	@GetMapping(value = "/avg/{numberOne}/{numberTwo}")
	public Double avg(@PathVariable(value = "numberOne") String numberOne, @PathVariable(value = "numberTwo") String numberTwo) throws Exception {
		
		if(!isNumeric(numberOne) || !isNumeric(numberTwo)) {
			throw new UnsupportedMathOperationException("Please set a numeric value");
		}
	
		return (convertToDouble(numberOne) + convertToDouble(numberTwo)) / 2.0;
	}
	
	@GetMapping(value = "/sqrt/{numberOne}")
	public Double sqrt(@PathVariable(value = "numberOne") String numberOne) throws Exception {
		
		if(!isNumeric(numberOne)) {
			throw new UnsupportedMathOperationException("Please set a numeric value");
		}
		
		if(convertToDouble(numberOne) < 0.0) {
			throw new UnsupportedMathOperationException("Please set a positive value");
		}
	
		return Math.sqrt(convertToDouble(numberOne));
	}

	private Double convertToDouble(String strNumber) {
		if(strNumber == null) return 0D;
		String number = strNumber.replaceAll(",", ".");
		if(isNumeric(number)) return Double.parseDouble(number);
		return null;
	}

	private boolean isNumeric(String strNumber) {
		if(strNumber == null) return false;
		String number = strNumber.replaceAll(",", ".");
		return number.matches("[-+]?[0-9]*\\.?[0-9]+");
	}
	
}
