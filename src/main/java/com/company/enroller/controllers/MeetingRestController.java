package com.company.enroller.controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.company.enroller.model.Meeting;
import com.company.enroller.persistence.MeetingService;

@RestController
@RequestMapping("/meetings")
public class MeetingRestController {
	
	@Autowired
	MeetingService meetingService;
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public ResponseEntity<?> getMeetings() {
		Collection<Meeting> meetings = meetingService.getAll();
		return new ResponseEntity<Collection<Meeting>>(meetings, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getMeeting(@PathVariable("id") Long id) {
		Meeting meeting = meetingService.findById(id);
			if (meeting == null) {
				return new ResponseEntity(HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<Meeting>(meeting, HttpStatus.OK);
	}
	
	@RequestMapping(value = "", method = RequestMethod.POST)
	public ResponseEntity<?> addMeeting(@RequestBody Meeting meeting) {
		if (meetingService.findById(meeting.getId()) != null) {
			return new ResponseEntity("Meeting with ID: " + meeting.getId() + " already exists!",
					HttpStatus.CONFLICT);				
		}
		
		meetingService.addMeeting(meeting);
		
		return new ResponseEntity<Meeting>(meeting, HttpStatus.OK);
	}
	
//	@RequestMapping(value="/{id}", method = RequestMethod.DELETE)
//	public ResponseEntity<?> removeMeeting(@PathVariable("id") Long id) {
//		Meeting meeting = meetingService.findById(id);
//		if (meeting == null) {
//			return new ResponseEntity("Meeting with ID: " + id + " doesn't exist!",
//					HttpStatus.NOT_FOUND);
//		}
//		
//		meetingService.removeMeeting(meeting);
//		
//		return new ResponseEntity<Meeting>(meeting, HttpStatus.OK);
//	}
	

}
