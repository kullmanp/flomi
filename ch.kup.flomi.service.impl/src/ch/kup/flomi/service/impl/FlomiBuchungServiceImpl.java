package ch.kup.flomi.service.impl;

import java.util.List;

import aQute.bnd.annotation.component.Component;
import aQute.bnd.annotation.component.Reference;
import ch.kup.flomi.integration.FlomiRepository;
import ch.kup.flomi.service.FlomiBuchungService;

@Component
public class FlomiBuchungServiceImpl implements FlomiBuchungService {
	private FlomiRepository flomiRepository;

	@Override
	public List<Integer> getAllYears() {
		return flomiRepository.getAllYears();
	}

	@Reference
	public void setFlomiRepository(FlomiRepository flomiRepository) {
		this.flomiRepository = flomiRepository;
	}

}