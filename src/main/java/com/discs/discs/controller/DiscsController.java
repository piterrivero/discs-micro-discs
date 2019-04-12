package com.discs.discs.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.discs.discs.ErrorCodes;
import com.discs.discs.dao.DiscRepository;
import com.discs.discs.dao.SequenceDao;
import com.discs.discs.model.Disc;
import com.discs.discs.model.response.DiscsGeneralResponse;


@CrossOrigin
@RestController
public class DiscsController extends ErrorCodes {

	private static final String DISC_SEQ_KEY = "disc";
	
	@Autowired
	Environment environment;
	@Autowired
	DiscRepository discRepository;
	@Autowired
	SequenceDao sequenceDao;
	
	@GetMapping("/discs/test")
	public String test(){
	    String resp = "The Discs Service is works on the port: " + environment.getProperty("local.server.port");
	    return resp;
	}
	
	@RequestMapping(value = "/discs/list")
	public DiscsGeneralResponse list(@RequestParam String order){
		List<Disc> discList = null;
		
		if (order != null && !order.equals("") && order.toUpperCase().equals("ASC")) {
			discList = discRepository.findAll(new Sort(Sort.Direction.ASC, "title"));
		}else if (order != null && !order.equals("") && order.toUpperCase().equals("DESC")) {
			discList = discRepository.findAll(new Sort(Sort.Direction.DESC, "title"));
		}
		
		DiscsGeneralResponse discsGeneralResponse = new DiscsGeneralResponse();
		discsGeneralResponse.setErrorCode(0);
		discsGeneralResponse.setErrorMsg("OK");
		discsGeneralResponse.setResponsePort(environment.getProperty("local.server.port"));
		discsGeneralResponse.setDiscsList(discList);
		
		return discsGeneralResponse;
	}
	
	@RequestMapping(value = "/discs/find")
	public DiscsGeneralResponse findById(@RequestParam String id){
		
		DiscsGeneralResponse discsGeneralResponse = new DiscsGeneralResponse();
		
		Disc disc = discRepository.findById(Long.parseLong(id));
		
		if (disc == null) {
			discsGeneralResponse.setErrorCode(RECORD_NOT_FOUND);
			discsGeneralResponse.setErrorMsg("Error: Disc not found");
		} else {
			discsGeneralResponse.setErrorCode(0);
			discsGeneralResponse.setErrorMsg("OK");
			discsGeneralResponse.setResponsePort(environment.getProperty("local.server.port"));
			discsGeneralResponse.setDisc(disc);
		}
		
	    return discsGeneralResponse;
	}
	
	@RequestMapping(value = "/discs/add", method = RequestMethod.POST)
	public DiscsGeneralResponse add(@RequestBody(required=true) Disc disc){
		disc.setIdDisc(sequenceDao.getNextSequenceId(DISC_SEQ_KEY));
		discRepository.save(disc);
		
		DiscsGeneralResponse discsGeneralResponse = new DiscsGeneralResponse();
		discsGeneralResponse.setErrorCode(0);
		discsGeneralResponse.setErrorMsg("OK");
		discsGeneralResponse.setResponsePort(environment.getProperty("local.server.port"));
		
		return discsGeneralResponse;
	}
	
	@RequestMapping(value = "/discs/edit", method = RequestMethod.POST)
	public DiscsGeneralResponse edit(@RequestBody(required=true) Disc disc){
		
		DiscsGeneralResponse discsGeneralResponse = new DiscsGeneralResponse();
		
		if (disc.getIdDisc() == 0) {
			discsGeneralResponse.setErrorCode(REQUIRED_FIELD);
			discsGeneralResponse.setErrorMsg("Error: the field discId is required");
			discsGeneralResponse.setResponsePort(environment.getProperty("local.server.port"));
		}else {
			DiscsGeneralResponse dgrTemp = findById(String.valueOf(disc.getIdDisc()));
			if (dgrTemp.getErrorCode() == RECORD_NOT_FOUND){
				discsGeneralResponse.setErrorCode(RECORD_NOT_FOUND);
				discsGeneralResponse.setErrorMsg("Error: Disc not found");
				discsGeneralResponse.setResponsePort(environment.getProperty("local.server.port"));
			}else {
				discsGeneralResponse.setErrorCode(0);
				discsGeneralResponse.setErrorMsg("OK");
				discsGeneralResponse.setResponsePort(environment.getProperty("local.server.port"));
				discRepository.save(disc);
			}
		}
		
		return discsGeneralResponse;
	}
	
	@RequestMapping(value = "/discs/delete")
	public DiscsGeneralResponse delete(@RequestParam String id){
		
		DiscsGeneralResponse discsGeneralResponse = new DiscsGeneralResponse();
		
		Disc disc = discRepository.findById(Long.parseLong(id));
		
		if (disc == null){
			discsGeneralResponse.setErrorCode(RECORD_NOT_FOUND);
			discsGeneralResponse.setErrorMsg("Error: Disc not found");
			discsGeneralResponse.setResponsePort(environment.getProperty("local.server.port"));
		}else {
			discRepository.delete(disc);
			discsGeneralResponse.setErrorCode(0);
			discsGeneralResponse.setErrorMsg("OK");
			discsGeneralResponse.setResponsePort(environment.getProperty("local.server.port"));
		}
		return discsGeneralResponse;
	}
}
