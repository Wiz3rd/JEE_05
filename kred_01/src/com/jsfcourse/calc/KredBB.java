package com.jsfcourse.calc;

import javax.inject.Inject;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

@Named
@RequestScoped
//@SessionScoped
public class KredBB {
	private String x;
	private String y;
	private String z;
	private Double result;

	@Inject
	FacesContext ctx;

	public String getx() {
		return x;
	}

	public void setx(String x) {
		this.x = x;
	}

	public String gety() {
		return y;
	}

	public void sety(String y) {
		this.y = y;
	}

	public String getz() {
		return z;
	}

	public void setz(String z) {
		this.z = z;
	}

	public Double getResult() {
		return result;
	}

	public void setResult(Double result) {
		this.result = result;
	}

	public boolean doTheMath() {
		try {
			double x = Double.parseDouble(this.x);
			double y = Double.parseDouble(this.y);
			double z = Double.parseDouble(this.z);

			result = (x / (y * 12)) * ((100 + z) / 100);

			ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Operacja wykonana poprawnie", null));
			return true;
		} catch (Exception e) {
			ctx.addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Błąd pody przetwarzania parametrów", null));
			return false;
		}
	}

	// Go to "showresult" if ok
	public String calc() {
		if (doTheMath()) {
			return "showresult";
		}
		return null;
	}

	// Put result in messages on AJAX call
	public String calc_AJAX() {
		if (doTheMath()) {
			ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Wynik: " + result, null));
		}
		return null;
	}

	public String info() {
		return "info";
	}
}
