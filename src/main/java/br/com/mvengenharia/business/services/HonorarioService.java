package br.com.mvengenharia.business.services;

import java.math.BigDecimal;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.mvengenharia.business.entities.Honorario;
import br.com.mvengenharia.business.entities.Inspecao;
import br.com.mvengenharia.business.entities.repositories.HonorarioRepository;

@Service
@Transactional
public class HonorarioService {

	@Autowired
	private HonorarioRepository honorarioRepository;

	public HonorarioService() {
		super();
	}

	public Iterable<Honorario> findAll() {
		return this.honorarioRepository.findAll();
	}

	public void addOrUpdate(final Honorario honorario) {
		this.honorarioRepository.save(honorario);
	}

	public void remove(final Long id) {
		this.honorarioRepository.delete(id);
	}

	public void calculaHonorario(Inspecao inspecao) {
		switch (inspecao.getCliente().getDescCliente()) {
		case "SULAMERICA": {
			switch(inspecao.getTipoInspecao().getDescTipoInspecao()){
			case "Compreensivo":{
				if (inspecao.getRamo().getDescRamo().contentEquals("Empresarial - Industrial"))
				{
					if(inspecao.getValorTotalRisco().compareTo(new BigDecimal(20000)) > 0)
					{
						
					}
				}
				break;
			}
			case "Massificado":{
				break;
			}
			}
			break;
		}

		case "MORAES VELLEDA": {
			break;
		}

		case "ALFA": {
			break;
		}

		case "AXA": {
			break;
		}

		default: {
		}
		}

	}

}
