package ar.com.avaco.ws.rest.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ar.com.avaco.nitrophyl.ws.dto.ComboDTO;
import ar.com.avaco.nitrophyl.ws.dto.MoldeClienteDTO;
import ar.com.avaco.nitrophyl.ws.dto.MoldeDTO;
import ar.com.avaco.nitrophyl.ws.dto.MoldeDimensionListadoDTO;
import ar.com.avaco.nitrophyl.ws.dto.MoldeFilterDTO;
import ar.com.avaco.nitrophyl.ws.dto.MoldeFotoDTO;
import ar.com.avaco.nitrophyl.ws.dto.MoldeFotoListadoDTO;
import ar.com.avaco.nitrophyl.ws.dto.MoldeListadoDTO;
import ar.com.avaco.nitrophyl.ws.dto.MoldeObservacionDTO;
import ar.com.avaco.nitrophyl.ws.dto.MoldePlanoDTO;
import ar.com.avaco.nitrophyl.ws.dto.MoldePlanoListadoDTO;
import ar.com.avaco.nitrophyl.ws.dto.MoldeRegistroDTO;
import ar.com.avaco.nitrophyl.ws.dto.PageDTO;
import ar.com.avaco.nitrophyl.ws.service.MoldeEPService;
import ar.com.avaco.ws.rest.dto.JSONResponse;

@RestController
public class MoldeRestController extends AbstractAuditableDTORestController<MoldeDTO, Long, MoldeEPService> {

	@Resource(name = "moldeEPService")
	public void setService(MoldeEPService moldeEPService) {
		super.service = moldeEPService;
	}

	/* EP Molde */

	@RequestMapping(value = "/molde", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JSONResponse> list(MoldeFilterDTO filter) throws Exception {
		PageDTO<MoldeListadoDTO> page = this.service.list(filter);
		JSONResponse response = new JSONResponse();
		response.setData(page);
		response.setStatus(JSONResponse.OK);
		return new ResponseEntity<JSONResponse>(response, HttpStatus.OK);
	}

	@RequestMapping(value = "/molde/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JSONResponse> getMolde(@PathVariable("id") Long id) throws Exception {
		MoldeDTO moldeDto = this.service.get(id);
		JSONResponse response = new JSONResponse();
		response.setData(moldeDto);
		response.setStatus(JSONResponse.OK);
		return new ResponseEntity<JSONResponse>(response, HttpStatus.OK);
	}

	@RequestMapping(value = "/molde", method = RequestMethod.POST)
	public ResponseEntity<JSONResponse> addMolde(@RequestBody MoldeDTO moldeDTO) throws Exception {
		moldeDTO.setId(null);
		try {
			MoldeDTO saved = this.service.save(moldeDTO);
			JSONResponse response = new JSONResponse();
			response.setData(saved);
			response.setStatus(JSONResponse.OK);
			return new ResponseEntity<JSONResponse>(response, HttpStatus.OK);
		} catch (DataIntegrityViolationException e) {
			throw new IllegalArgumentException(
					"Violacion de integridad, verifique que no exista un Molde con el mismo Codigo", e);
		}
	}

	@RequestMapping(value = "/molde/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JSONResponse> updateMolde(@PathVariable("id") Long id, @RequestBody MoldeDTO moldeDTO)
			throws Exception {
		moldeDTO.setId(id);
		MoldeDTO moldeDto = this.service.update(moldeDTO);
		JSONResponse response = new JSONResponse();
		response.setData(moldeDto);
		response.setStatus(JSONResponse.OK);
		return new ResponseEntity<JSONResponse>(response, HttpStatus.OK);
	}

	/* EP MoldeDimension */
	@RequestMapping(value = "/molde/dimensiones/{idMolde}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JSONResponse> getMoldeDimensiones(@PathVariable("idMolde") Long idMolde) throws Exception {
		List<MoldeDimensionListadoDTO> moldeDimensionListadoDTOs = this.service.getMoldesDimension(idMolde);
		JSONResponse response = new JSONResponse();
		response.setData(moldeDimensionListadoDTOs);
		response.setStatus(JSONResponse.OK);
		return new ResponseEntity<JSONResponse>(response, HttpStatus.OK);
	}

	@RequestMapping(value = "/molde/dimensiones/{idMolde}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JSONResponse> updateMoldeDimensiones(@PathVariable("idMolde") Long idMolde,
			@RequestBody List<MoldeDimensionListadoDTO> moldeDimensionListadoDTOs) throws Exception {
		List<MoldeDimensionListadoDTO> result = this.service.updateMoldeDimensiones(idMolde, moldeDimensionListadoDTOs);
		JSONResponse response = new JSONResponse();
		response.setData(result);
		response.setStatus(JSONResponse.OK);
		return new ResponseEntity<JSONResponse>(response, HttpStatus.OK);
	}

	@RequestMapping(value = "/molde/registro/{idMolde}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JSONResponse> getMoldeRegistro(@PathVariable("idMolde") Long idMolde) throws Exception {
		List<MoldeRegistroDTO> moldeRegistroDTOs = this.service.getMoldesRegistro(idMolde);
		JSONResponse response = new JSONResponse();
		response.setData(moldeRegistroDTOs);
		response.setStatus(JSONResponse.OK);
		return new ResponseEntity<JSONResponse>(response, HttpStatus.OK);
	}

	@RequestMapping(value = "/molde/registro/{idMolde}", method = RequestMethod.POST)
	public ResponseEntity<JSONResponse> addMoldeRegistro(@RequestBody MoldeRegistroDTO moldeRegistroDTO)
			throws Exception {
		MoldeRegistroDTO saved = this.service.saveMoldeRegistro(moldeRegistroDTO);
		JSONResponse response = new JSONResponse();
		response.setData(saved);
		response.setStatus(JSONResponse.OK);
		return new ResponseEntity<JSONResponse>(response, HttpStatus.OK);
	}

	@RequestMapping(value = "/molde/plano/{idMolde}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JSONResponse> getMoldePlano(@PathVariable("idMolde") Long idMolde) throws Exception {
		List<MoldePlanoListadoDTO> moldePlanoDTOs = this.service.getMoldesPlano(idMolde);
		JSONResponse response = new JSONResponse();
		response.setData(moldePlanoDTOs);
		response.setStatus(JSONResponse.OK);
		return new ResponseEntity<JSONResponse>(response, HttpStatus.OK);
	}

	@RequestMapping(value = "/molde/plano/", method = RequestMethod.POST)
	public ResponseEntity<JSONResponse> addMoldePlano(@RequestBody MoldePlanoDTO moldePlanoDTO) throws Exception {
		MoldePlanoDTO saved = this.service.addMoldePlano(moldePlanoDTO);
		JSONResponse response = new JSONResponse();
		response.setData(saved);
		response.setStatus(JSONResponse.OK);
		return new ResponseEntity<JSONResponse>(response, HttpStatus.OK);
	}

	@RequestMapping(value = "/molde/plano/descargar/{idMoldePlano}", method = RequestMethod.POST)
	public ResponseEntity<JSONResponse> downloadMoldePlano(@PathVariable("idMoldePlano") Long idMoldePlano)
			throws Exception {
		MoldePlanoDTO saved = this.service.downloadMoldePlano(idMoldePlano);
		JSONResponse response = new JSONResponse();
		response.setData(saved);
		response.setStatus(JSONResponse.OK);
		return new ResponseEntity<JSONResponse>(response, HttpStatus.OK);
	}

	@RequestMapping(value = "/molde/foto/{idMolde}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JSONResponse> getMoldeFoto(@PathVariable("idMolde") Long idMolde) throws Exception {
		List<MoldeFotoListadoDTO> moldeFotoDTOs = this.service.getMoldesFoto(idMolde);
		JSONResponse response = new JSONResponse();
		response.setData(moldeFotoDTOs);
		response.setStatus(JSONResponse.OK);
		return new ResponseEntity<JSONResponse>(response, HttpStatus.OK);
	}

	@RequestMapping(value = "/molde/foto/", method = RequestMethod.POST)
	public ResponseEntity<JSONResponse> addMoldeFoto(@RequestBody MoldeFotoDTO moldeFotoDTO) throws Exception {
		MoldeFotoDTO saved = this.service.addMoldeFoto(moldeFotoDTO);
		JSONResponse response = new JSONResponse();
		response.setData(saved);
		response.setStatus(JSONResponse.OK);
		return new ResponseEntity<JSONResponse>(response, HttpStatus.OK);
	}

	@RequestMapping(value = "/molde/foto/descargar/{idMoldeFoto}", method = RequestMethod.POST)
	public ResponseEntity<JSONResponse> downloadMoldeFoto(@PathVariable("idMoldeFoto") Long idMoldeFoto)
			throws Exception {
		MoldeFotoDTO saved = this.service.downloadMoldeFoto(idMoldeFoto);
		JSONResponse response = new JSONResponse();
		response.setData(saved);
		response.setStatus(JSONResponse.OK);
		return new ResponseEntity<JSONResponse>(response, HttpStatus.OK);
	}

	@RequestMapping(value = "/molde/clientes/{idMolde}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JSONResponse> getMoldeClientes(@PathVariable("idMolde") Long idMolde) throws Exception {
		List<MoldeClienteDTO> clienteMoldeDTOs = this.service.getMoldeClientes(idMolde);
		JSONResponse response = new JSONResponse();
		response.setData(clienteMoldeDTOs);
		response.setStatus(JSONResponse.OK);
		return new ResponseEntity<JSONResponse>(response, HttpStatus.OK);
	}

	@RequestMapping(value = "/molde/clientes/{idMolde}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JSONResponse> updateMoldeClientes(@PathVariable("idMolde") Long idMolde,
			@RequestBody List<MoldeClienteDTO> moldeClientesListadoDTOs) throws Exception {
		List<MoldeClienteDTO> result = this.service.updateMoldeClientes(idMolde, moldeClientesListadoDTOs);
		JSONResponse response = new JSONResponse();
		response.setData(result);
		response.setStatus(JSONResponse.OK);
		return new ResponseEntity<JSONResponse>(response, HttpStatus.OK);
	}

	/* EP Molde Observaciones */
	@RequestMapping(value = "/molde/observacion/{idMolde}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JSONResponse> getObservaciones(@PathVariable("idMolde") Long idMolde) throws Exception {
		List<MoldeObservacionDTO> observaciones = this.service.getMoldeObservaciones(idMolde);
		JSONResponse response = new JSONResponse();
		response.setData(observaciones);
		response.setStatus(JSONResponse.OK);
		return new ResponseEntity<JSONResponse>(response, HttpStatus.OK);
	}

	@RequestMapping(value = "/molde/observacion/", method = RequestMethod.POST)
	public ResponseEntity<JSONResponse> addMoldeFoto(@RequestBody MoldeObservacionDTO dto) throws Exception {
		MoldeObservacionDTO saved = this.service.addMoldeObservacion(dto);
		JSONResponse response = new JSONResponse();
		response.setData(saved);
		response.setStatus(JSONResponse.OK);
		return new ResponseEntity<JSONResponse>(response, HttpStatus.OK);
	}

	@RequestMapping(value = "/molde/combo", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JSONResponse> getCombo(MoldeFilterDTO filter) throws Exception {
		filter.setAsc(true);
		filter.setIdx("nombre");
		filter.setFirst(1);
		filter.setRows(50);
		PageDTO<MoldeListadoDTO> page = this.service.list(filter);
		List<ComboDTO> combo = new ArrayList<ComboDTO>();
		if (page.getPage() != null && !page.getPage().isEmpty())
			page.getPage().stream()
					.forEach(molde -> combo.add(new ComboDTO(molde.getCodigo(), molde.getId().toString())));
		JSONResponse response = new JSONResponse();
		response.setData(combo);
		response.setStatus(JSONResponse.OK);
		return new ResponseEntity<JSONResponse>(response, HttpStatus.OK);
	}

}