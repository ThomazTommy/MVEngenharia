package br.com.mvengenharia.business.services;

import java.math.BigDecimal;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import br.com.mvengenharia.business.entities.Honorario;
import br.com.mvengenharia.business.entities.Inspecao;
import br.com.mvengenharia.business.entities.repositories.HonorarioRepository;

@Service
@Transactional
public class HonorarioService {

	//Sulamerica
	BigDecimal valorCompreensivoIndustrialAteVinteMil = new BigDecimal(350.00);
	BigDecimal valorCompreensivoIndustrialMaiorVinteMilMenorCinquentaMil = new BigDecimal(600.00);
	BigDecimal valorCompreensivoNaoIndustrialAteVinteMil = new BigDecimal(200.00);
	BigDecimal valorCompreensivoNaoIndustrialMaiorVinteMilMenorCinquentaMil = new BigDecimal(350.00);
	BigDecimal valorCompreensivoNaoIndustrialMaiorCinquentaMilMenorCemMil = new BigDecimal(650.00);
	BigDecimal valorCompreensivoNaoIndustrialMaiorCemMil = new BigDecimal(750.00);
	BigDecimal valorMassificadoCondominioAteCemMilAteDoisBlocos = new BigDecimal(100.00);
	BigDecimal valorMassificadoCondominioAteCemMilMaisTresBlocos = new BigDecimal(145.00);
	BigDecimal valorMassificadoCondominioMaiorCemMil = new BigDecimal(200.00);
	BigDecimal valorMassificadoNaoIndustrialAteQuinzeMil = new BigDecimal(115.00);
	BigDecimal valorMassificadoNaoIndustrialMaiorQuinzeMilMenorVinteMil = new BigDecimal(230.00);
	BigDecimal valorMassificadoNaoIndustrialMaiorVinteMilMenorCinquentaMil = new BigDecimal(402.50);
	BigDecimal valorMassificadoIndustrialAteQuinzeMil = new BigDecimal(230.00);
	
	//Alfa
	BigDecimal valorAlfaResidencial = new BigDecimal(80.00);
	BigDecimal valorAlfaComercioAteDezMil = new BigDecimal(140.00);
	BigDecimal valorAlfaComercioMaisDezMil = new BigDecimal(196.00);
	BigDecimal valorAlfaIndustrialAteDezMil = new BigDecimal(280.00);
	BigDecimal valorAlfaIndustrialMaisDezMil = new BigDecimal(392.00);
	BigDecimal valorAlfaCondominioAteDoisBlocosAteDezMil = new BigDecimal(100.00);
	BigDecimal valorAlfaCondominioAteDoisBlocosMaisDezMil = new BigDecimal(120.00);
	BigDecimal valorAlfaCondominioAteCincoBlocosAteDezMil = new BigDecimal(140.00);
	BigDecimal valorAlfaCondominioAteCincoBlocosMaisDezMil = new BigDecimal(160.00);
	BigDecimal valorAlfaCondominioMaisCincoBlocos = new BigDecimal(200.00);
	
	

	@Autowired
	private HonorarioRepository honorarioRepository;

	@Autowired
	private FuncionarioService funcionarioService;
	

	@Autowired
	private InspecaoService inspecaoService;

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
			switch (inspecao.getTipoInspecao().getDescTipoInspecao()) {
			case "COMPREENSIVO": {
				if (inspecao.getRamo().getDescRamo().contentEquals("Empresarial - Industrial")) {
					if ((inspecao.getValorTotalRisco().compareTo(new BigDecimal(20000001)) >= 0)
							&& (inspecao.getValorTotalRisco().compareTo(new BigDecimal(50000000)) <= 0)) {
						this.setaHonorario(inspecao, valorCompreensivoIndustrialMaiorVinteMilMenorCinquentaMil);
					}
					if (inspecao.getValorTotalRisco().compareTo(new BigDecimal(20000000)) <= 0) {
						this.setaHonorario(inspecao, valorCompreensivoIndustrialAteVinteMil);
					}
				} else {
					if (inspecao.getValorTotalRisco().compareTo(new BigDecimal(20000000)) <= 0) {
						this.setaHonorario(inspecao, valorCompreensivoNaoIndustrialAteVinteMil);
					}
					if ((inspecao.getValorTotalRisco().compareTo(new BigDecimal(20000001)) >= 0)
							&& (inspecao.getValorTotalRisco().compareTo(new BigDecimal(50000000)) <= 0)) {
						this.setaHonorario(inspecao, valorCompreensivoNaoIndustrialMaiorVinteMilMenorCinquentaMil);
					}
					if ((inspecao.getValorTotalRisco().compareTo(new BigDecimal(50000001)) >= 0)
							&& (inspecao.getValorTotalRisco().compareTo(new BigDecimal(100000000)) <= 0)) {
						this.setaHonorario(inspecao, valorCompreensivoNaoIndustrialMaiorCinquentaMilMenorCemMil);
					}
					if (inspecao.getValorTotalRisco().compareTo(new BigDecimal(100000001)) >= 0) {

						this.setaHonorario(inspecao, valorCompreensivoNaoIndustrialMaiorCemMil);

					}

				}
				break;
			}
			case "MASSIFICADO": {
				if (inspecao.getRamo().getDescRamo().contentEquals("Empresarial - Industrial")) {
					if (inspecao.getValorTotalRisco().compareTo(new BigDecimal(15000000)) <= 0) {

						this.setaHonorario(inspecao, valorMassificadoIndustrialAteQuinzeMil);

					}
				}

				if (inspecao.getRamo().getDescRamo().contentEquals("Empresarial - Comércio e Serviços")) {
					if (inspecao.getValorTotalRisco().compareTo(new BigDecimal(15000000)) <= 0) {

						this.setaHonorario(inspecao, valorMassificadoNaoIndustrialAteQuinzeMil);

					}
					if ((inspecao.getValorTotalRisco().compareTo(new BigDecimal(15000001)) >= 0)
							&& (inspecao.getValorTotalRisco().compareTo(new BigDecimal(20000000)) <= 0)) {

						this.setaHonorario(inspecao, valorMassificadoNaoIndustrialMaiorQuinzeMilMenorVinteMil);

					}
					if ((inspecao.getValorTotalRisco().compareTo(new BigDecimal(20000001)) >= 0)
							&& (inspecao.getValorTotalRisco().compareTo(new BigDecimal(50000000)) <= 0)) {

						this.setaHonorario(inspecao, valorMassificadoNaoIndustrialMaiorVinteMilMenorCinquentaMil);

					}
				}

				if (inspecao.getRamo().getDescRamo().contentEquals("Condomínio")) {
					if (inspecao.getValorTotalRisco().compareTo(new BigDecimal(100000000)) <= 0) {
						if (inspecao.getQtdBlocos() <= 2) {
							this.setaHonorario(inspecao, valorMassificadoCondominioAteCemMilAteDoisBlocos);
						} else {
							this.setaHonorario(inspecao, valorMassificadoCondominioAteCemMilMaisTresBlocos);
						}
					}
					if (inspecao.getValorTotalRisco().compareTo(new BigDecimal(100000001)) >= 0) {
						this.setaHonorario(inspecao, valorMassificadoCondominioMaiorCemMil);
					}
				}
				break;
			}
			}
			break;
		}

		case "MORAES VELLEDA": {
			break;
		}

		case "ALFA": {
			if (inspecao.getValorTotalRisco().compareTo(new BigDecimal(10000000)) <= 0) {
			
				if (inspecao.getRamo().getDescRamo().contentEquals("Condomínio")) {
					if (inspecao.getQtdBlocos() <= 2) {
						this.setaHonorario(inspecao, valorAlfaCondominioAteDoisBlocosAteDezMil);
					}
					if ((inspecao.getQtdBlocos() >= 3) && (inspecao.getQtdBlocos() <= 5)) {
						this.setaHonorario(inspecao, valorAlfaCondominioAteCincoBlocosAteDezMil);
					}
					if (inspecao.getQtdBlocos() > 5) {
						this.setaHonorario(inspecao, valorAlfaCondominioMaisCincoBlocos);
					}
				}
				if (inspecao.getRamo().getDescRamo().contentEquals("Empresarial - Industrial")) {
					this.setaHonorario(inspecao, valorAlfaIndustrialMaisDezMil);
				}
				if (inspecao.getRamo().getDescRamo().contentEquals("Empresarial - Comércio e Serviços")) {
					this.setaHonorario(inspecao, valorAlfaComercioMaisDezMil);
				}
				if (inspecao.getRamo().getDescRamo().contentEquals("Residencial")) {
					this.setaHonorario(inspecao, valorAlfaResidencial);
				}
				
			}
			if (inspecao.getValorTotalRisco().compareTo(new BigDecimal(10000000)) > 0) {
				
				if (inspecao.getRamo().getDescRamo().contentEquals("Condomínio")) {
					if (inspecao.getQtdBlocos() <= 2) {
						this.setaHonorario(inspecao, valorAlfaCondominioAteDoisBlocosMaisDezMil);
					}
					if ((inspecao.getQtdBlocos() >= 3) && (inspecao.getQtdBlocos() <= 5)) {
						this.setaHonorario(inspecao, valorAlfaCondominioAteCincoBlocosMaisDezMil);
					}
					if (inspecao.getQtdBlocos() > 5) {
						this.setaHonorario(inspecao, valorAlfaCondominioMaisCincoBlocos);
					}
				}
				if (inspecao.getRamo().getDescRamo().contentEquals("Empresarial - Industrial")) {
					this.setaHonorario(inspecao, valorAlfaIndustrialAteDezMil);
				}
				if (inspecao.getRamo().getDescRamo().contentEquals("Empresarial - Comércio e Serviços")) {
					this.setaHonorario(inspecao, valorAlfaComercioAteDezMil);
				}
				if (inspecao.getRamo().getDescRamo().contentEquals("Residencial")) {
					this.setaHonorario(inspecao, valorAlfaResidencial);
				}
				
			}
			
			
			break;
		}

		case "AXA": {
			switch (inspecao.getTipoInspecao().getDescTipoInspecao()) {
			case "COMPREENSIVO": {
				if (inspecao.getRamo().getDescRamo().contentEquals("Empresarial - Industrial")) {
					if ((inspecao.getValorTotalRisco().compareTo(new BigDecimal(20000001)) >= 0)
							&& (inspecao.getValorTotalRisco().compareTo(new BigDecimal(50000000)) <= 0)) {
						this.setaHonorario(inspecao, valorCompreensivoIndustrialMaiorVinteMilMenorCinquentaMil);
					}
					if (inspecao.getValorTotalRisco().compareTo(new BigDecimal(20000000)) <= 0) {
						this.setaHonorario(inspecao, valorCompreensivoIndustrialAteVinteMil);
					}
				} else {
					if (inspecao.getValorTotalRisco().compareTo(new BigDecimal(20000000)) <= 0) {
						this.setaHonorario(inspecao, valorCompreensivoNaoIndustrialAteVinteMil);
					}
					if ((inspecao.getValorTotalRisco().compareTo(new BigDecimal(20000001)) >= 0)
							&& (inspecao.getValorTotalRisco().compareTo(new BigDecimal(50000000)) <= 0)) {
						this.setaHonorario(inspecao, valorCompreensivoNaoIndustrialMaiorVinteMilMenorCinquentaMil);
					}
					if ((inspecao.getValorTotalRisco().compareTo(new BigDecimal(50000001)) >= 0)
							&& (inspecao.getValorTotalRisco().compareTo(new BigDecimal(100000000)) <= 0)) {
						this.setaHonorario(inspecao, valorCompreensivoNaoIndustrialMaiorCinquentaMilMenorCemMil);
					}
					if (inspecao.getValorTotalRisco().compareTo(new BigDecimal(100000001)) >= 0) {

						this.setaHonorario(inspecao, valorCompreensivoNaoIndustrialMaiorCemMil);

					}

				}
				break;
			}
			case "MASSIFICADO": {
				if (inspecao.getRamo().getDescRamo().contentEquals("Empresarial - Industrial")) {
					if (inspecao.getValorTotalRisco().compareTo(new BigDecimal(15000000)) <= 0) {

						this.setaHonorario(inspecao, valorMassificadoIndustrialAteQuinzeMil);

					}
				}

				if (inspecao.getRamo().getDescRamo().contentEquals("Empresarial - Comércio e Serviços")) {
					if (inspecao.getValorTotalRisco().compareTo(new BigDecimal(15000000)) <= 0) {

						this.setaHonorario(inspecao, valorMassificadoNaoIndustrialAteQuinzeMil);

					}
					if ((inspecao.getValorTotalRisco().compareTo(new BigDecimal(15000001)) >= 0)
							&& (inspecao.getValorTotalRisco().compareTo(new BigDecimal(20000000)) <= 0)) {

						this.setaHonorario(inspecao, valorMassificadoNaoIndustrialMaiorQuinzeMilMenorVinteMil);

					}
					if ((inspecao.getValorTotalRisco().compareTo(new BigDecimal(20000001)) >= 0)
							&& (inspecao.getValorTotalRisco().compareTo(new BigDecimal(50000000)) <= 0)) {

						this.setaHonorario(inspecao, valorMassificadoNaoIndustrialMaiorVinteMilMenorCinquentaMil);

					}
				}

				if (inspecao.getRamo().getDescRamo().contentEquals("Condomínio")) {
					if (inspecao.getValorTotalRisco().compareTo(new BigDecimal(100000000)) <= 0) {
						if (inspecao.getQtdBlocos() <= 2) {
							this.setaHonorario(inspecao, valorMassificadoCondominioAteCemMilAteDoisBlocos);
						} else {
							this.setaHonorario(inspecao, valorMassificadoCondominioAteCemMilMaisTresBlocos);
						}
					}
					if (inspecao.getValorTotalRisco().compareTo(new BigDecimal(100000001)) >= 0) {
						this.setaHonorario(inspecao, valorMassificadoCondominioMaiorCemMil);
					}
				}
				break;
			}
			}
			break;
		}

		default: {
		}
		}

	}

	private void setaHonorario(Inspecao inspecao, BigDecimal valor) {
		inspecao = this.inspecaoService.findOne(inspecao.getIdInspecao());
		Honorario honorario = null;
		if(inspecao.getHonorario() == null)
		{
		honorario = new Honorario();
		}
		else{
			honorario = inspecao.getHonorario();
		}
		honorario.setValorHonorarioCalculado(valor);
		honorario.setFuncionarioAlterador(
				funcionarioService.findOne(SecurityContextHolder.getContext().getAuthentication().getName()));
		honorario.setFlagAlteracao(false);
		honorario.setDescCondicoes(inspecao.getCliente().getDescCliente() + " - "
				+ inspecao.getTipoInspecao().getDescTipoInspecao() + " - " + inspecao.getRamo().getDescRamo()
				+ " - Valor do Risco: R$ " + inspecao.getValorTotalRisco());
		honorario.setMotivoAlteracao("Calculado pelo Sistema");
		honorario.setInspecao(inspecao);
		inspecao.setHonorario(honorario);
		this.inspecaoService.addOrUpdate(inspecao);
	}

}
