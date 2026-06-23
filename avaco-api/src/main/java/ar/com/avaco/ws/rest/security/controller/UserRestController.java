package ar.com.avaco.ws.rest.security.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ar.com.avaco.commons.exception.BusinessException;
import ar.com.avaco.nitrophyl.ws.dto.ComboDTO;
import ar.com.avaco.ws.rest.controller.AbsctractRestController;
import ar.com.avaco.ws.rest.dto.JSONResponse;
import ar.com.avaco.ws.rest.security.dto.User;
import ar.com.avaco.ws.rest.security.service.UserService;
import ar.com.avaco.ws.rest.security.util.JwtTokenUtil;

/**
 * @author beto
 *
 */
@RestController
public class UserRestController extends AbsctractRestController<User, Long, UserService> {

	private JwtTokenUtil jwtTokenUtil;

	private UserDetailsService userDetailsService;

	@Value("${jwt.header}")
	private String tokenHeader;

	// -------------------Retrieve All
	// permisos--------------------------------------------------------

	@RequestMapping(value = "/users/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JSONResponse> list() {
		return super.list();
	}

	// -------------------Retrieve single
	// Pages--------------------------------------------------------
	@RequestMapping(value = "/users/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JSONResponse> get(@PathVariable("id") Long id) throws BusinessException {
		return super.get(id);
	}

	// -------------------Create a
	// Page--------------------------------------------------------
	@RequestMapping(value = "/users/", method = RequestMethod.POST)
	public ResponseEntity<JSONResponse> create(@RequestBody User user) throws BusinessException {
		User result = this.service.saveUser(user);
		return new ResponseEntity<JSONResponse>(getResponseOK(result), HttpStatus.CREATED);
	}

	// ------------------- Update a Page
	// --------------------------------------------------------
	@RequestMapping(value = "/users/{id}", method = RequestMethod.PUT)
	public ResponseEntity<JSONResponse> update(@PathVariable("id") Long id, @RequestBody User user)
			throws BusinessException {
		return super.update(id, user);
	}

	@RequestMapping(value = "/users/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<JSONResponse> delete(@PathVariable("id") Long id) throws BusinessException {
		return super.delete(id);
	}

	@Resource(name = "userService")
	public void setUserService(UserService userService) {
		super.service = userService;
	}

	@RequestMapping(value = "/users/update/validation/", method = RequestMethod.POST)
	public ResponseEntity<JSONResponse> updateValidation(@RequestBody User user) throws Exception {
		return super.executeProcess("update-validation", Void -> {
			this.service.updateValidation(user);
			return null;
		});
	}

	@RequestMapping(value = "/users/combo", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JSONResponse> listombo(@RequestParam(required = false) String nombre) {
		nombre = nombre != null ? nombre : "";
		List<User> nombres = this.service.listPattern("nombre", nombre);
		List<User> apellidos = this.service.listPattern("apellido", nombre);

		List<User> usuarios = Stream.concat(nombres.stream(), apellidos.stream())
				.collect(Collectors.toMap(User::getId, Function.identity(), (u1, u2) -> u1)).values().stream()
				.collect(Collectors.toList());

		List<ComboDTO> combo = new ArrayList<ComboDTO>();

		usuarios.forEach(usuario -> combo.add(new ComboDTO(usuario.getNombreApellido(), usuario.getId().toString())));
		JSONResponse response = new JSONResponse();
		response.setData(combo);
		response.setStatus(JSONResponse.OK);
		return new ResponseEntity<JSONResponse>(response, HttpStatus.OK);
	}

}