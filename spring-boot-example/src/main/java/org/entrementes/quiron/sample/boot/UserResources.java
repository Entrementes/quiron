package org.entrementes.quiron.sample.boot;

import static org.entrementes.quiron.model.constants.QuironHttpStatus.*;

import java.util.ArrayList;
import java.util.List;

import org.entrementes.quiron.annotation.ApiMethod;
import org.entrementes.quiron.annotation.ApiRequestParam;
import org.entrementes.quiron.annotation.ApiResource;
import org.entrementes.quiron.annotation.ApiResponse;
import org.entrementes.quiron.model.constants.QuironParamType;
import org.entrementes.quiron.sample.boot.model.User;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@ApiResource(id="users")
@RestController
@RequestMapping("/users")
public class UserResources {
	
	@ApiMethod(id="list", responses={
			@ApiResponse(code=OK)
	})
	@RequestMapping(value="/",method=RequestMethod.GET)
	@ResponseBody
	public List<User> list() {
		return new ArrayList<User>();
	}
	
	@ApiMethod(id="create", responses={
			@ApiResponse(code=CREATED, body="${create-response.json}", requestParams={@ApiRequestParam(value="${create-request.json}", type = QuironParamType.BODY)}),
			@ApiResponse(code=BAD_REQUEST, requestParams={@ApiRequestParam(value="${create-bad-request.json}", type = QuironParamType.BODY)})
	})
	@RequestMapping(value="/",method=RequestMethod.POST)
	@ResponseBody
	public User create(@RequestBody User newUser, BindingResult validationResult) {
		return new User();
	}
	
	@ApiMethod(id="read", responses={
			@ApiResponse(code=OK, body="${read-response.json}", requestParams={@ApiRequestParam(name="id", type=QuironParamType.URI, value="1")}),
			@ApiResponse(code=NOT_FOUND, requestParams={@ApiRequestParam(name="id", type=QuironParamType.URI, value="7")})	
	})
	@RequestMapping(value="/{id}",method=RequestMethod.GET)
	@ResponseBody
	public User read(@PathVariable("id") Integer id) {
		return new User();
	}
	
	@ApiMethod(id="update", responses={
			@ApiResponse(code=OK, body="${update-response.json}", requestParams={@ApiRequestParam(name="id", type=QuironParamType.URI, value="1"),
																				@ApiRequestParam(value="${update-request.json}", type = QuironParamType.BODY)
																			}),
			@ApiResponse(code=NOT_FOUND, requestParams={@ApiRequestParam(name="id", type=QuironParamType.URI, value="7"),
														@ApiRequestParam(value="${update-request.json}", type = QuironParamType.BODY)
													}),
			@ApiResponse(code=BAD_REQUEST, requestParams={ @ApiRequestParam(name="id", type=QuironParamType.URI, value="1"),
															@ApiRequestParam(value="${update-bad-request.json}", type = QuironParamType.BODY)
														})
													})
	@RequestMapping(value="/{id}",method=RequestMethod.PUT)
	@ResponseBody
	public User update(@PathVariable("id") String id, @RequestBody User updated) {
		return new User();
	}
	
	@ApiMethod(id="delete", responses={
			@ApiResponse(code=OK, body="${delete-response.json}", requestParams={@ApiRequestParam(name="id", type=QuironParamType.URI, value="1")}),
			@ApiResponse(code=NOT_FOUND, requestParams={@ApiRequestParam(name="id", type=QuironParamType.URI, value="7")})	
	})
	@RequestMapping(value="/{id}",method=RequestMethod.DELETE)
	@ResponseBody
	public User delete(@PathVariable("id") String id) {
		return new User();
	}

}
