package com.manager.demo1.contoller;

import java.util.HashMap;


import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.manager.demo1.exception.ResourceNotFoundException;
import com.manager.demo1.repository.Memberrepository;

import com.manager.demo1.modal.Member;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1")
public class Membercontroller {
	@Autowired
	private Memberrepository memberrepository;

	// get all Members
	@GetMapping("/dramas")
	public List<Member> getDramas() {
		return memberrepository.findAll();
	}

	// we are creating a student posting details
	@PostMapping("/dramas")
	public Member createDrama(@RequestBody Member member) {
		System.out.println("this data is being posted");
		return memberrepository.save(member);
	}

	@DeleteMapping("dramas/{id}")
	public ResponseEntity<Map<String, Boolean>> deleteDrama(@PathVariable Long id) {
		Member member = memberrepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Drama not exist with id :" + id));

		memberrepository.delete(member);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}

	// get website by id rest api
	@GetMapping("/dramas/{id}")
	public ResponseEntity<Member> getDramaById(@PathVariable Long id) {
		System.out.println("this is test");
		Member member = memberrepository.findById(id)
				
				.orElseThrow(() -> new ResourceNotFoundException("drama not exist with id :" + id));
		
		return ResponseEntity.ok(member);
	}

	// // update website rest api

	@PutMapping("/dramas/{id}")
	public ResponseEntity<Member> updateDrama(@PathVariable Long id, @RequestBody Member dramaDetails) {

		Member upStudent = memberrepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("drama not exist with id :" + id));

		upStudent.setName(dramaDetails.getName());
		upStudent.setDescription(dramaDetails.getDescription());
		

		Member updateStudent = memberrepository.save(upStudent);
		return ResponseEntity.ok(updateStudent);
	}

}
