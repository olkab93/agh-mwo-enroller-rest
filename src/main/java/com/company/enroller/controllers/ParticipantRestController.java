package com.company.enroller.controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.company.enroller.model.Participant;
import com.company.enroller.persistence.ParticipantService;

@RestController
@RequestMapping("/participants")
public class ParticipantRestController {

	@Autowired
	ParticipantService participantService;

	@RequestMapping(value = "", method = RequestMethod.GET)
	public ResponseEntity<?> getParticipants() {
		Collection<Participant> participants = participantService.getAll();
		return new ResponseEntity<Collection<Participant>>(participants, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getMeeting(@PathVariable("id") String login) {
	     Participant participant = participantService.findByLogin(login);
	     if (participant == null) {
	         return new ResponseEntity(HttpStatus.NOT_FOUND);
	     }
	     return new ResponseEntity<Participant>(participant, HttpStatus.OK);
	 }
	
	@RequestMapping(value = "", method = RequestMethod.POST)
	 public ResponseEntity<?> addParticipant(@RequestBody Participant participant) {
		if (participantService.findByLogin(participant.getLogin()) != null ) {
			return new ResponseEntity("Participant with login: " + participant.getLogin() +  " already exists!",
					HttpStatus.CONFLICT);
		}
		
		participantService.addParticipant(participant);
		
		return new ResponseEntity<Participant>(participant, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> removeParticipant(@PathVariable("id") String login) {
		 Participant participant = participantService.findByLogin(login);
		if (participant == null ) {
			return new ResponseEntity("Participant with login: " + login +  " doesn't exist!",
					HttpStatus.NOT_FOUND);
		}
		
		participantService.removeParticipant(participant);
		
		return new ResponseEntity<Participant>(participant, HttpStatus.OK);
    	
    }
	
	@RequestMapping(value = "/{id}", method = RequestMethod.POST)
	public ResponseEntity<?> modifyParticipant(@PathVariable("id") String login, @RequestBody Participant newParticipant) {
		Participant participant = participantService.findByLogin(login);
		if (participant == null ) {
			return new ResponseEntity("Participant with login: " + login +  " doesn't exist!",
					HttpStatus.NOT_FOUND);
		}
		
		participant.setPassword(newParticipant.getPassword());
		participantService.modifyParticipant(participant);
		
		return new ResponseEntity<Participant>(participant, HttpStatus.OK);
	}
	
	

}
