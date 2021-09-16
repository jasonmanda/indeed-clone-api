package com.dash.it.solution.viewmodel;
public class BaseQuery<T>
{
    private boolean validate;
    private int code=200;
    private T data;
    private String message;
	private String erreur_mssg;
	
	public boolean isValidate() {
		return validate;
	}
	public void setValidate(boolean validate) {
		this.validate = validate;
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getErreur_mssg() {
		return erreur_mssg;
	}
	public void setErreur_mssg(String erreur_mssg) {
		this.erreur_mssg = erreur_mssg;
	}
	
}