package com.india.healthcare.analysis.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.india.healthcare.analysis.dto.AllStatesDetailsDTO;
import com.india.healthcare.analysis.dto.TopOverallDTO;
import com.india.healthcare.analysis.service.StatewiseDetailsService;

@RestController
public class DashboardController {    
    private final StatewiseDetailsService statewiseDetailsService;

    @Inject
    public DashboardController(final StatewiseDetailsService statewiseDetailsService) {
    	this.statewiseDetailsService = statewiseDetailsService;
    }
    
    @RequestMapping(value="/dashboard")
    public ModelAndView displayDashboard(HttpServletRequest request, HttpServletResponse repsonse) {
    	ModelAndView modelAndView = new ModelAndView("static/mainpage.html");
		return modelAndView;
    }
    
    @RequestMapping(value="/statewise",method = RequestMethod.GET)
    public AllStatesDetailsDTO viewDetails() {
    	return statewiseDetailsService.getAllStatesDetails();
    }
    
    @RequestMapping(value="/topfiveoverall", method = RequestMethod.GET)
    public TopOverallDTO getTopOverall() {
    	return statewiseDetailsService.getTopFiveOverallStatesDetails();
    }
    
}
