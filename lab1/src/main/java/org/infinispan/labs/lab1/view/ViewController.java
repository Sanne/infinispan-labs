/*
 * JBoss, Home of Professional Open Source
 * Copyright 2011 Red Hat Inc. and/or its affiliates and other
 * contributors as indicated by the @author tags. All rights reserved.
 * See the copyright.txt in the distribution for a full listing of
 * individual contributors.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */
package org.infinispan.labs.lab1.view;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;

import org.infinispan.labs.lab1.model.Ticket;
import org.infinispan.labs.lab1.model.TicketAllocation;
import org.infinispan.labs.lab1.service.TicketService;

@Named
@RequestScoped
public class ViewController {

	@Inject
	private TicketService service;

	// Variables populated by JSF when form is submitted
	private String name;
	private String ticketId;
	private String id;
	private TicketAllocation allocatedTicket;
	private String event;

	@Produces
	@Named
	public List<Ticket> getReservedTickets() {
		return service.getReservedTickets();
	}
	
	@Produces
	@Named
	public List<Ticket> getAvailableTickets() {
		return service.getAvailableTickets();
	}

	@Produces
	@Named
	public int getNumberOfReservedTickets() {
		return getReservedTickets().size();
	}

	public void book(String id) {
		service.bookTicket( id );
	}

	public void reserve() {
		service.reserveTicket( name, ticketId, event );
	}

	public void clearAllocations() {
		service.clearAllocations();
	}

	public String getNodeId() {
		return service.getNodeId();
	}

	public String getOwners(String key) {
		return service.getOwners( key );
	}

	public void query() {
		// this.allocatedTicket = service.getTicketAllocation(id);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTicketId() {
		return ticketId;
	}
	
	public String getEvent() {
		return event;
	}

	public void setEvent(String event) {
		this.event = event;
	}

	public void setTicketId(String ticketId) {
		this.ticketId = ticketId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public TicketAllocation getAllocatedTicket() {
		return allocatedTicket;
	}

}
