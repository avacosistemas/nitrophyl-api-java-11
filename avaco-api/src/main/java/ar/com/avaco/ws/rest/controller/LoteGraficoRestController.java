package ar.com.avaco.ws.rest.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ar.com.avaco.commons.exception.BusinessException;
import ar.com.avaco.nitrophyl.ws.dto.ArchivoDTO;
import ar.com.avaco.nitrophyl.ws.dto.LoteGraficoDTO;
import ar.com.avaco.nitrophyl.ws.dto.LoteGraficoSinArchivoDTO;
import ar.com.avaco.nitrophyl.ws.service.LoteGraficoEPService;
import ar.com.avaco.ws.rest.dto.JSONResponse;

@RestController
public class LoteGraficoRestController extends AbstractDTORestController<LoteGraficoDTO, Long, LoteGraficoEPService> {

	@Resource(name = "loteGraficoEPService")
	public void setService(LoteGraficoEPService loteGraficoEPService) {
		super.service = loteGraficoEPService;
	}

	@RequestMapping(value = "/lote/grafico", method = RequestMethod.POST)
	public ResponseEntity<JSONResponse> addGrafico(@RequestBody LoteGraficoDTO loteGraficoDTO) throws Exception {
		LoteGraficoDTO saved = this.service.save(loteGraficoDTO);
		JSONResponse response = new JSONResponse();
		response.setData(saved);
		response.setStatus(JSONResponse.OK);
		return new ResponseEntity<JSONResponse>(response, HttpStatus.OK);
	}

	@RequestMapping(value = "/lote/grafico", method = RequestMethod.GET)
	public ResponseEntity<JSONResponse> getGrafico(@RequestParam Long idLoteGrafico) throws Exception {
		JSONResponse response = new JSONResponse();
		response.setStatus(JSONResponse.OK);
		LoteGraficoDTO loteGraficoDTO = this.service.get(idLoteGrafico);
		ArchivoDTO archivo = new ArchivoDTO();
		archivo.setArchivo(loteGraficoDTO.getArchivo());
		archivo.setNombre("Grafico-Lote-" + loteGraficoDTO.getLote() + "-" + loteGraficoDTO.getMaquina() + ".pdf");
		response.setData(archivo);
		return new ResponseEntity<JSONResponse>(response, HttpStatus.OK);
	}

	@RequestMapping(value = "/lote/graficos", method = RequestMethod.GET)
	public ResponseEntity<JSONResponse> getGraficos(@RequestParam Long idLote) throws Exception {
		JSONResponse response = new JSONResponse();
		response.setStatus(JSONResponse.OK);
		List<LoteGraficoSinArchivoDTO> graficos = this.service.listByIdLote(idLote);

		response.setData(graficos);
		return new ResponseEntity<JSONResponse>(response, HttpStatus.OK);
	}

	@Override
	@RequestMapping(value = "/lote/graficos/{idGraficoprueba}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JSONResponse> delete(@PathVariable Long idGraficoprueba) throws BusinessException {
		return super.delete(idGraficoprueba);
	}

}