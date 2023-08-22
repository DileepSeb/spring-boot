package org.jsp.springbootapp.service;

import java.util.List;
import java.util.Optional;

import org.jsp.springbootapp.dao.UserDao;
import org.jsp.springbootapp.dto.ResponseStructure;
import org.jsp.springbootapp.dto.User;
import org.jsp.springbootapp.exception.IdNotFoundException;
import org.jsp.springbootapp.exception.InvalidCredentialsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class UserService {
     @Autowired
	private UserDao dao;
     
     
     
 	public ResponseEntity<ResponseStructure<User>> saveUser( @RequestBody User u) {
 		ResponseStructure<User> structure=new ResponseStructure<>();
 		structure.setData(dao.saveUser(u));
 		structure.setMessage("user saved with id:"+u.getId());
 		structure.setStatusCode(HttpStatus.CREATED.value());
 		return new ResponseEntity<ResponseStructure<User>>(structure, HttpStatus.CREATED);
 		
 	}
 	
 		
 	public ResponseEntity<ResponseStructure<User>> updateUser( @RequestBody User u) {
 		ResponseStructure<User> structure=new ResponseStructure<>();
 		structure.setData(dao.updateUser(u));
 		structure.setMessage("user updated");
 		structure.setStatusCode(HttpStatus.ACCEPTED.value());
 		return new ResponseEntity<ResponseStructure<User>>(structure, HttpStatus.ACCEPTED);
 		
 	}
 	
 	public ResponseEntity<ResponseStructure<User>> findById(@PathVariable int id) {
 		ResponseStructure<User> structure=new ResponseStructure<>();
 		Optional<User> recUser=dao.findById(id);
 		if(recUser.isPresent()) {
 			structure.setMessage("user found");
 			structure.setData(recUser.get());
 			structure.setStatusCode(HttpStatus.OK.value());
 			return new ResponseEntity<ResponseStructure<User>>(structure, HttpStatus.OK);
 		}
 		throw new IdNotFoundException();
 					
 		}
 
 	
 	public ResponseEntity<ResponseStructure<String>> deleteUser(@PathVariable int id) {
 		ResponseStructure<String> structure=new ResponseStructure<>();
 		
 		Optional<User> recUser=dao.findById(id);
 		if(recUser.isPresent()) {
 			structure.setMessage("user deleted");
 			structure.setData("user found");
 			structure.setStatusCode(HttpStatus.OK.value());
 			dao.deleteUser(id);
 			return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.OK);
 			
 		}
 		structure.setMessage("user not deleted");
 		structure.setData("user not found");
 		structure.setStatusCode(HttpStatus.NOT_FOUND.value());
 		return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.NOT_FOUND);
 	}
 	public ResponseEntity<ResponseStructure<List<User>>> findAll(){
 		ResponseStructure<List<User>> structure=new ResponseStructure<> ();
 		structure.setData(dao.findAll());
 		structure.setMessage("List of all users");
 		structure.setStatusCode(HttpStatus.OK.value());
 		return new ResponseEntity<ResponseStructure<List<User>>>(structure,HttpStatus.OK);
 		
 	}
 	public ResponseEntity<ResponseStructure<User>> verifyUser(long phone,String password){
 		ResponseStructure<User> structure=new ResponseStructure<>();
 		Optional<User> recUser=dao.verifyUser(phone, password);
 		if(recUser.isPresent()) {
 			structure.setData(recUser.get());
 			structure.setMessage("user is verified sucessfully");
 			structure.setStatusCode(HttpStatus.OK.value());
 			return  new ResponseEntity<ResponseStructure<User>>(structure,HttpStatus.OK);
 		}
 		throw new InvalidCredentialsException();
 	}
 	
 	public ResponseEntity<ResponseStructure<User>> verifyUser(String email,String password){
 		ResponseStructure<User> structure=new ResponseStructure<>();
 		Optional<User> recUser=dao.verifyUser(email, password);
 		if(recUser.isPresent()) {
 			structure.setData(recUser.get());
 			structure.setMessage("user is verified sucessfully");
 			structure.setStatusCode(HttpStatus.OK.value());
 			return  new ResponseEntity<ResponseStructure<User>>(structure,HttpStatus.OK);
 		}
 		throw new InvalidCredentialsException();
 	}
}
