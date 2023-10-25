package com.bookmyshow.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookmyshow.dao.CinemaHallDao;
import com.bookmyshow.dao.ShowSeatDao;
import com.bookmyshow.exception.AlreadyBookedException;
import com.bookmyshow.exception.BookingCloseException;
import com.bookmyshow.model.CinemaHall;
import com.bookmyshow.model.Message;

import com.bookmyshow.model.ShowSeat;


@Service
public class ShowSeatService {

	@Autowired
	ShowSeatDao dao;
	@Autowired
	CinemaHallDao hallDao;
	@Autowired
	CinemaHall cinemaHall;
	@Autowired
	Message message;
	
	
	public ShowSeat reserved(int seatNo)
	{
		ShowSeat seat2=dao.getByseatNo(seatNo);
		if(cinemaHall.getRemainingSeats()==0) {
			try {
				throw new BookingCloseException("Booking closed");
			}
			catch(BookingCloseException e){
				message.setMsg(e.getMessage());
				return message;
			}
		}
		else
		{
			if(seat2==null)
			{
				ShowSeat seat =new ShowSeat();
				seat.setSeatNo(seatNo);
				seat.setReserved("true");
				cinemaHall.setRemainingSeats(cinemaHall.getRemainingSeats()-1);
				return dao.save(seat);
				
			}
			else
			{
				if(seat2.isReserved().equals("true"))
				{
					try {
						throw new AlreadyBookedException("This Seat No is Already Booked");
					}
					catch(AlreadyBookedException e)
					{
						message.setMsg(e.getMessage());
						return message;
					}
					
				}
				else
				{
					seat2.setReserved("true");
					cinemaHall.setRemainingSeats(cinemaHall.getRemainingSeats()-1);
					hallDao.save(cinemaHall);
					return dao.save(seat2);
				}
				
			}
		
		}
		
	}
	
	
}