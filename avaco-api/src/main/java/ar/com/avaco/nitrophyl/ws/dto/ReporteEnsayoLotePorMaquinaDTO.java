package ar.com.avaco.nitrophyl.ws.dto;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import ar.com.avaco.utils.DateUtils;
import ar.com.avaco.ws.rest.dto.DTOEntity;

public class ReporteEnsayoLotePorMaquinaDTO extends DTOEntity<Long> {

	private Long row;

	private Long idLote;

	private String nroLote;

	private String fecha;

	private String observaciones;

	private Long idFormula;

	private String nombreFormula;

	private String estadoEnsayo;

	public List<ReporteResultadoEnsayoDTO> resultados = new ArrayList<ReporteResultadoEnsayoDTO>();

	public Long getIdLote() {
		return idLote;
	}

	public void setIdLote(Long idLote) {
		this.idLote = idLote;
	}

	public String getNroLote() {
		return nroLote;
	}

	public void setNroLote(String nroLote) {
		this.nroLote = nroLote;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public Long getIdFormula() {
		return idFormula;
	}

	public void setIdFormula(Long idFormula) {
		this.idFormula = idFormula;
	}

	public String getNombreFormula() {
		return nombreFormula;
	}

	public void setNombreFormula(String nombreFormula) {
		this.nombreFormula = nombreFormula;
	}

	public List<ReporteResultadoEnsayoDTO> getResultados() {
		return resultados;
	}

	public void setResultados(List<ReporteResultadoEnsayoDTO> resultados) {
		this.resultados = resultados;
	}

	@Override
	public void setId(Long id) {
		row = id;
	}

	@Override
	public Long getId() {
		return row;
	}

	public Long getRow() {
		return row;
	}

	public void setRow(Long row) {
		this.row = row;
	}

	public String getEstadoEnsayo() {
		return estadoEnsayo;
	}

	public void setEstadoEnsayo(String estadoEnsayo) {
		this.estadoEnsayo = estadoEnsayo;
	}

	public static Comparator<ReporteEnsayoLotePorMaquinaDTO> getComparator(String campo, Boolean asc) {

		Comparator<ReporteEnsayoLotePorMaquinaDTO> cp = null;

		switch (campo) {
		case "lo.fecha":
			if (asc)
				cp = getFechaComparatorAsc();
			else
				cp = getFechaComparatorDesc();
			break;
		case "f.nombre":
			if (asc)
				cp = getFormulaComparatorAsc();
			else
				cp = getFormulaComparatorDesc();
			break;
		case "lo.nro_lote":
			if (asc)
				cp = getNroLoteComparatorAsc();
			else
				cp = getNroLoteComparatorDesc();
			break;
		default:
			cp = getFechaComparatorDesc();
			break;
		}
		return cp;
	}

	private static Comparator<ReporteEnsayoLotePorMaquinaDTO> getFechaComparatorAsc() {
		return new Comparator<ReporteEnsayoLotePorMaquinaDTO>() {
			@Override
			public int compare(ReporteEnsayoLotePorMaquinaDTO o1, ReporteEnsayoLotePorMaquinaDTO o2) {
				Date f1 = DateUtils.toDate(o1.getFecha(), DateUtils.dd_MM_yyyy);
				Date f2 = DateUtils.toDate(o2.getFecha(), DateUtils.dd_MM_yyyy);
				return f1.compareTo(f2);
			}
		};
	}

	private static Comparator<ReporteEnsayoLotePorMaquinaDTO> getFechaComparatorDesc() {
		return new Comparator<ReporteEnsayoLotePorMaquinaDTO>() {
			@Override
			public int compare(ReporteEnsayoLotePorMaquinaDTO o1, ReporteEnsayoLotePorMaquinaDTO o2) {
				Date f1 = DateUtils.toDate(o1.getFecha(), DateUtils.dd_MM_yyyy);
				Date f2 = DateUtils.toDate(o2.getFecha(), DateUtils.dd_MM_yyyy);
				return f2.compareTo(f1);
			}
		};
	}

	private static Comparator<ReporteEnsayoLotePorMaquinaDTO> getNroLoteComparatorAsc() {
		return new Comparator<ReporteEnsayoLotePorMaquinaDTO>() {
			@Override
			public int compare(ReporteEnsayoLotePorMaquinaDTO o1, ReporteEnsayoLotePorMaquinaDTO o2) {
				int letraComparacion = Character.compare(o1.getNroLote().charAt(0), o2.getNroLote().charAt(0));
				if (letraComparacion != 0) {
					return letraComparacion;
				}
				// Comparar por los nmeros (convertidos a enteros)
				int num1 = Integer.parseInt(o1.getNroLote().substring(1));
				int num2 = Integer.parseInt(o2.getNroLote().substring(1));
				return Integer.compare(num1, num2);
			}
		};
	}

	private static Comparator<ReporteEnsayoLotePorMaquinaDTO> getNroLoteComparatorDesc() {
		return new Comparator<ReporteEnsayoLotePorMaquinaDTO>() {
			@Override
			public int compare(ReporteEnsayoLotePorMaquinaDTO o1, ReporteEnsayoLotePorMaquinaDTO o2) {
				int letraComparacion = Character.compare(o2.getNroLote().charAt(0), o1.getNroLote().charAt(0));
				if (letraComparacion != 0) {
					return letraComparacion;
				}
				// Comparar por los nmeros (convertidos a enteros)
				int num1 = Integer.parseInt(o1.getNroLote().substring(1));
				int num2 = Integer.parseInt(o2.getNroLote().substring(1));
				return Integer.compare(num2, num1);
			}
		};
	}

	private static Comparator<ReporteEnsayoLotePorMaquinaDTO> getFormulaComparatorAsc() {
		return new Comparator<ReporteEnsayoLotePorMaquinaDTO>() {
			@Override
			public int compare(ReporteEnsayoLotePorMaquinaDTO o1, ReporteEnsayoLotePorMaquinaDTO o2) {
				return o1.getNombreFormula().compareTo(o2.getNombreFormula());
			}
		};
	}

	private static Comparator<ReporteEnsayoLotePorMaquinaDTO> getFormulaComparatorDesc() {
		return new Comparator<ReporteEnsayoLotePorMaquinaDTO>() {
			@Override
			public int compare(ReporteEnsayoLotePorMaquinaDTO o1, ReporteEnsayoLotePorMaquinaDTO o2) {
				return o2.getNombreFormula().compareTo(o1.getNombreFormula());
			}
		};
	}
}
