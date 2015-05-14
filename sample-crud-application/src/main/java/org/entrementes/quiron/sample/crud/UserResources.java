package org.entrementes.quiron.sample.crud;

import static org.entrementes.quiron.model.constants.QuironHttpStatus.*;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.entrementes.quiron.annotation.ApiDependency;
import org.entrementes.quiron.annotation.ApiMethod;
import org.entrementes.quiron.annotation.ApiRequestParam;
import org.entrementes.quiron.annotation.ApiResource;
import org.entrementes.quiron.annotation.ApiResponse;
import org.entrementes.quiron.model.constants.QuironHttpStatus;
import org.entrementes.quiron.model.constants.QuironParamType;
import org.entrementes.quiron.sample.crud.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@ApiResource(id="users")
@RestController
@RequestMapping("/users")
public class UserResources {
	
	@ApiMethod(id="list", description="list all users.", responses={
			@ApiResponse(code=OK, description="no pagination result."),
			@ApiResponse(code=OK, description="pagination result.", requestParams={
				@ApiRequestParam(name="size", type=QuironParamType.QUERY, value="10")
			})
	})
	@RequestMapping(value="/",method=RequestMethod.GET, produces="application/json")
	@ResponseBody
	public List<User> list(@RequestParam(value="size",required=false) Integer pageSize) {
		List<User> result = new ArrayList<User>();
		if(pageSize != null){
			//HACK!
			for(int i = 1; i < Math.abs(pageSize) + 1; i++){
				result.add(new User(i,"Paged " + i ,10 + i));
			}
		}else{
			result.add(new User(1,"Huguinho",11));
			result.add(new User(2,"Zezinho",13));
			result.add(new User(3,"Luizinho",9));
			result.add(new User(4,"Donald",9));
		}
		return result;
	}
	
	@ApiMethod(id="create", description="create new user.", responses={
			@ApiResponse(code=CREATED, description="request body complete, user created.", body="${create-response.json}", requestParams={@ApiRequestParam(value="${create-request.json}", type = QuironParamType.BODY)}),
			@ApiResponse(code=BAD_REQUEST, description="missing attributes in request body, user not created.", requestParams={@ApiRequestParam(value="${create-bad-request.json}", type = QuironParamType.BODY)})
	})
	@RequestMapping(value="/",method=RequestMethod.POST, consumes="application/json", produces="application/json")
	@ResponseBody
	public ResponseEntity<User> create(@Valid @RequestBody User newUser, BindingResult validationResult) {
		if(validationResult.hasErrors()){
			return new ResponseEntity<User>(newUser,HttpStatus.BAD_REQUEST);
		}
		newUser.setId(1);
		return new ResponseEntity<User>(newUser,HttpStatus.CREATED);
	}
	
	@ApiMethod(id="read", description="find user by id.", responses={
			@ApiResponse(code=OK, description="user id is present in the database, returning user information.", body="${read-response.json}", requestParams={@ApiRequestParam(name="id", type=QuironParamType.URI, value="1")}),
			@ApiResponse(code=NOT_FOUND, description="user id is NOT present in the database, no user information found.", requestParams={@ApiRequestParam(name="id", type=QuironParamType.URI, value="7")})	
	})
	@RequestMapping(value="/{id}",method=RequestMethod.GET, produces="application/json")
	@ResponseBody
	public ResponseEntity<User> read(@PathVariable("id") Integer id) {
		if(id != 1){
			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		}else{
			return new ResponseEntity<User>(new User(1,"Ramirez",36),HttpStatus.OK);
		}
	}
	
	@ApiMethod(id="update", description="uptade user.", responses={
			@ApiResponse(code=OK, description="request body complete, user updated.", body="${update-response.json}", requestParams={@ApiRequestParam(name="id", type=QuironParamType.URI, value="1"),
																				@ApiRequestParam(value="${update-request.json}", type = QuironParamType.BODY)
																			}),
			@ApiResponse(code=NOT_FOUND, description="user id is NOT present in the database, user not updated.", requestParams={@ApiRequestParam(name="id", type=QuironParamType.URI, value="7"),
														@ApiRequestParam(value="${update-request.json}", type = QuironParamType.BODY)
													}),
			@ApiResponse(code=BAD_REQUEST, description="missing attributes in request body, user not updated.", requestParams={ @ApiRequestParam(name="id", type=QuironParamType.URI, value="1"),
															@ApiRequestParam(value="${update-bad-request.json}", type = QuironParamType.BODY)
														})
													})
	@RequestMapping(value="/{id}",method=RequestMethod.PUT, consumes="application/json", produces="application/json")
	@ResponseBody
	public ResponseEntity<User> update(@PathVariable("id") Integer id, @Valid @RequestBody User updatedUser, BindingResult validationResult) {
		if(validationResult.hasErrors()){
			return new ResponseEntity<User>(updatedUser, HttpStatus.BAD_REQUEST);
		}
		if(id != 1){
			return new ResponseEntity<User>(updatedUser, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<User>(updatedUser,HttpStatus.OK);
	}
	
	@ApiMethod(id="delete", description="delete user by id.", responses={
			@ApiResponse(code=OK, description="user id is present in the database, user deleted.", body="${delete-response.json}", requestParams={@ApiRequestParam(name="id", type=QuironParamType.URI, value="1")}),
			@ApiResponse(code=NOT_FOUND, description="user id is NOT present in the database, user not deleted.", requestParams={@ApiRequestParam(name="id", type=QuironParamType.URI, value="7")})	
	})
	@RequestMapping(value="/{id}",method=RequestMethod.DELETE, produces="application/json")
	@ResponseBody
	public ResponseEntity<User> delete(@PathVariable("id") Integer id) {
		if(id != 1){
			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<User>(new User(1,"Ramirez",36),HttpStatus.OK);
	}
	
	@ApiMethod(id="broken", description="this method is broken. Please hire a better DEV.", responses={
				@ApiResponse(code=QuironHttpStatus.OK)
	},
			dependencies={
				@ApiDependency(id="${google-search}")
	})
	@RequestMapping(value="/{id}/broken",method=RequestMethod.GET, produces="application/json")
	@ResponseBody
	@SuppressWarnings("null")
	public ResponseEntity<User> broken(){
		User nulzitos = null;
		nulzitos.setName("Ramirez");
		return new ResponseEntity<User>(nulzitos,HttpStatus.OK);
	}

}
