package com.bookmyshow.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bookmyshow.model.CinemaHall;
import com.bookmyshow.service.CinemaHallService;

@RestController
@RequestMapping("CinemaHall")
public class CinemaHallController {

	@Autowired
	CinemaHallService hallService;
	@GetMapping("getSeat")
	public ResponseEntity<CinemaHall> getSeat(String name,int ShowNo,HttpStatus code)
	{
		return new ResponseEntity<>(hallService.getSeat(name, ShowNo),code.OK);
	}
}