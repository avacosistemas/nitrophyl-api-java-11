package ar.com.avaco.nitrophyl.domain.entities.pieza;

import ar.com.avaco.nitrophyl.domain.entities.AuditableEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = false)
public class Deposito extends AuditableEntity<Long> {

	private static final long serialVersionUID = -2766411820210875781L;

	private Long id;
	
	private String nombre;
	
}
