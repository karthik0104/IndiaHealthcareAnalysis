package com.india.healthcare.analysis.controller;

import javax.inject.Inject;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.india.healthcare.analysis.dto.AllStatesDetailsDTO;
import com.india.healthcare.analysis.service.StatewiseDetailsService;

@RestController
public class DashboardController {    
    private final StatewiseDetailsService statewiseDetailsService;
    
    @Inject
    public DashboardController(final StatewiseDetailsService statewiseDetailsService) {
    	this.statewiseDetailsService = statewiseDetailsService;
    }
    
    @RequestMapping(value="/statewise",method = RequestMethod.GET)
    public AllStatesDetailsDTO viewDetails() {
    	return statewiseDetailsService.getAllStatesDetails();
    }
}
