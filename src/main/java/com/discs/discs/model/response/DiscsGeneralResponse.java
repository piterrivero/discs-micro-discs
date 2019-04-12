package com.discs.discs.model.response;

import java.util.List;

import com.discs.discs.model.Disc;

public class DiscsGeneralResponse {

	private Disc disc;
	private List<Disc> discsList;
	private int errorCode;
	private String errorMsg;
	private String responsePort;
	
	public DiscsGeneralResponse() {
	}
	
	public Disc getDisc() {
		return disc;
	}
	public void setDisc(Disc disc) {
		this.disc = disc;
	}
	public List<Disc> getDiscsList() {
		return discsList;
	}
	public void setDiscsList(List<Disc> discsList) {
		this.discsList = discsList;
	}
	public int getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}
	public String getErrorMsg() {
		return errorMsg;
	}
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
	public String getResponsePort() {
		return responsePort;
	}
	public void setResponsePort(String responsePort) {
		this.responsePort = responsePort;
	}
}
