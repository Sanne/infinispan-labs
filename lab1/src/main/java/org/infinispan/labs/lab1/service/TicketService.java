package org.infinispan.labs.lab1.service;

import java.util.List;

import org.infinispan.labs.lab1.model.Ticket;

public interface TicketService {

	public List<Ticket> getReservedTickets();

	public void clearAllocations();

	public void bookTicket(String id);

	public String getNodeId();

	public String getOwners(String id);

	/**
	 * @param eventName
	 * @param numberOfTickets
	 */
	public void createEvent(String eventName, int numberOfTickets);

	/**
	 * @param name
	 * @param ticketId
	 * @param event
	 */
	void reserveTicket(String name, String ticketId, String event);

	/**
	 * @return
	 */
	public List<Ticket> getAvailableTickets();

}
