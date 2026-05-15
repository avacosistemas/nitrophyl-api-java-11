package ar.com.avaco.commons.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import ar.com.avaco.commons.domain.FieldDef;
import ar.com.avaco.commons.domain.FormDef;

@Transactional
@Service("formDefService")
public class FormDefServiceImpl implements FormDefService {

	private List<FormDef> formDef;
	
	@Override
	public List<FieldDef> getFieldsDef(String formKey) {			
			FormDef formDef = this.getFormDef(formKey);
			
			if (formDef != null) {
				return formDef.getFields();
			}
			return null;
	}
	
	@Override
	public void saveFieldsDef(List<FormDef> formDef) {
		this.formDef = formDef;
	}

	@Override
	public FormDef getFormDef(String formKey) {
		return this.formDef.stream().filter(f -> f.getKey().equals(formKey)).findFirst().get();
	}	
}
