
package ar.com.avaco.nitrophyl.ws.service;

import java.util.List;

import ar.com.avaco.commons.exception.BusinessException;
import ar.com.avaco.commons.exception.ErrorValidationException;
import ar.com.avaco.nitrophyl.ws.dto.MoldeBocaListadoDTO;
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
import ar.com.avaco.ws.rest.service.CRUDEPService;

public interface MoldeEPService extends CRUDEPService<Long, MoldeDTO> {

	List<MoldeDTO> listMoldes();

	List<MoldeListadoDTO> listado();

	List<MoldeBocaListadoDTO> getMoldesBoca(Long idMolde);

	List<MoldeBocaListadoDTO> updateMoldesBoca(Long idMolde, List<MoldeBocaListadoDTO> moldeBocaListadoDTO);

	List<MoldeDimensionListadoDTO> getMoldesDimension(Long idMolde);

	List<MoldeDimensionListadoDTO> updateMoldeDimensiones(Long idMolde,
			List<MoldeDimensionListadoDTO> moldeDimensionListadoDTOs);

	List<MoldeRegistroDTO> getMoldesRegistro(Long idMolde);

	MoldeRegistroDTO saveMoldeRegistro(MoldeRegistroDTO moldeRegistroDTO);

	List<MoldePlanoListadoDTO> getMoldesPlano(Long idMolde);
	
	MoldePlanoDTO addMoldePlano(MoldePlanoDTO moldePlanoDTO) throws ErrorValidationException, BusinessException;

	List<MoldeFotoListadoDTO> getMoldesFoto(Long idMolde);

	MoldeFotoDTO addMoldeFoto(MoldeFotoDTO moldeFotoDTO) throws ErrorValidationException, BusinessException;

	MoldePlanoDTO downloadMoldePlano(Long idMoldePlano);

	MoldeFotoDTO downloadMoldeFoto(Long idMoldeFoto);

	List<MoldeClienteDTO> getMoldeClientes(Long idMolde);

	List<MoldeClienteDTO> updateMoldeClientes(Long idMolde, List<MoldeClienteDTO> moldeClientesListadoDTOs);

	List<MoldeObservacionDTO> getMoldeObservaciones(Long idMolde);

	MoldeObservacionDTO addMoldeObservacion(MoldeObservacionDTO dto);

	PageDTO<MoldeListadoDTO> list(MoldeFilterDTO filter);

}
