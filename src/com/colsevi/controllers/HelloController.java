package com.colsevi.controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.coyote.http11.Http11AprProcessor;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.colsevi.application.ColseviDao;

@Controller
public class HelloController {

	
	
	@RequestMapping("profile")
	public ModelAndView profile(){
		ModelAndView model = new ModelAndView("Profile");
		return model;
	}


}
