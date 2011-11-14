package org.infinispan.labs.lab1;

import javax.inject.Inject;

import org.infinispan.labs.lab1.service.TicketService;

public class TicketPopulator {

   private final TicketService tickets;

   @Inject
   public TicketPopulator(TicketService tickets) {
      this.tickets = tickets;
   }
   
   public void populate() {
	   tickets.createEvent("Devoxx", 8);
   }
   
}
