package com.example.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {

	@Autowired
	private UserRepository repository;

	@GetMapping
	public Iterable<User> findAll() {
		return repository.findAll();
	}

	@GetMapping("/name/{userName}")
	public List<User> findByName(@PathVariable String userName) throws Exception {
		if ("patil".equals(userName)) {
			throw new Exception("User not found: " + userName);
		}
		return repository.findByName(userName);
	}

	@GetMapping("/{id}")
	public User findOne(@PathVariable Long id) throws Exception {
		return repository.findById(id).orElseThrow(Exception::new);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public User create(@RequestBody User user) {
		return repository.save(user);
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) throws Exception {
		repository.findById(id).orElseThrow(Exception::new);
		repository.deleteById(id);
	}
}