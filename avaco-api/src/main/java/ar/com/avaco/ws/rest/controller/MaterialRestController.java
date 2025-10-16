package ar.com.avaco.ws.rest.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ar.com.avaco.nitrophyl.ws.dto.MaterialDTO;
import ar.com.avaco.nitrophyl.ws.dto.MaterialFilterDTO;
import ar.com.avaco.nitrophyl.ws.service.MaterialEPService;
import ar.com.avaco.nitrophyl.ws.service.filter.MaterialFilter;
import ar.com.avaco.ws.rest.dto.JSONResponse;

@RestController
public class MaterialRestController extends AbstractDTORestController<MaterialDTO, Long, MaterialEPService> {

	
	@RequestMapping(value = "/material", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JSONResponse> list(MaterialFilterDTO mfdto) {
		List<MaterialDTO> listFilter = super.service.listFilter(new MaterialFilter(mfdto));
		JSONResponse response = new JSONResponse();
		response.setData(listFilter);
		response.setStatus(JSONResponse.OK);
		return new ResponseEntity<JSONResponse>(response, HttpStatus.OK);
	}
	
	@Resource(name = "materialEPService")
	public void setService(MaterialEPService materialEPService) {
		super.service = materialEPService;
	}

}