package com.packtpub.service;

import java.util.Arrays;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.packtpub.model.Ticket;
import com.packtpub.model.User;

@Service
public class TicketServiceImpl implements TicketService {
	
	@Autowired
	UserService userService;

	// Dummy tickets
	public static List<Ticket> tickets;

	public TicketServiceImpl() {
		tickets = new LinkedList<>();
		
		/*
		tickets.add(new Ticket(101, new Date(), "Login is not working", 5, 1));
		tickets.add(new Ticket(101, new Date(), "Submit button is not working", 5, 1));
		tickets.add(new Ticket(102, new Date(), "Registration is not working", 5, 1));
		*/
	}

	@Override
	public List<Ticket> getAllTickets() {
		return tickets;
	}

	@Override
	public List<Ticket> getMyTickets(Integer creatorid) {		
		
		return tickets.stream()
				.filter(x -> x.getCreatorid().intValue() == creatorid.intValue())				
				.collect(Collectors.toList())
				;
	}

	@Override
	public Ticket getTicket(Integer creatorid, Integer ticketid) {
		
		return tickets.stream()
				.filter(x -> x.getCreatorid().intValue()  == creatorid.intValue() && x.getTicketid().intValue() == ticketid.intValue())
				.findAny()
				.orElse(null);
	}
	
	@Override
	public Ticket getTicket(Integer ticketid) {
		
		return tickets.stream()
				.filter(x -> x.getTicketid().intValue() == ticketid.intValue())
				.findAny()
				.orElse(null);
	}

	@Override
	public void addTicket(Integer creatorid, String content, Integer severity, Integer status) {
		Ticket ticket = new Ticket(creatorid, new Date(), content, severity, status);
		
		tickets.add(ticket);
	}
	
	
	@Override
	public void updateTicket(Integer ticketid, String content, Integer severity, Integer status) {
		
		Ticket ticket = getTicket(ticketid);
		
		if(ticket == null){
			throw new RuntimeException("Ticket Not Available");
		}
		
		ticket.setContent(content);
		ticket.setSeverity(severity);
		ticket.setStatus(status);	
	}

	@Override
	public void deleteTickets(User user, String ticketids) {
		
		List<String> ticketObjList = Arrays.asList(ticketids.split(","));
		
		if(user.getUsertype() == 2 && ticketObjList.size() > 3){
			throw new RuntimeException("CSR can't delete more than 3 tickets");
		}
		
		List<Integer> intList =
			ticketObjList.stream()
			.map(Integer::valueOf)
			.collect(Collectors.toList())
        ;					
		
		tickets.removeIf(x -> intList.contains(x.getTicketid()));
	}
	
	@Override
	public void deleteMyTicket(Integer userid, Integer ticketid) {		
		tickets.removeIf(x -> x.getTicketid().intValue() == ticketid.intValue() && x.getCreatorid().intValue() == userid.intValue());
	}
}