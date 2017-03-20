

function executaAjaxGetNoReload(urlChamada, divDestino, preExecute, posExecute, afterFunction) {
	$.ajax({
		type : "GET",
		url : urlChamada,
		beforeSend : preExecute,

		success : function(response) {
			// we have the response
			var x = document.getElementById(divDestino);
			x.innerHTML = response;
			afterFunction();
		
		},
		complete : function() {
			posExecute;
		},
		error : function(xhr) {
			alert("Um erro ocorreu: " + xhr.status + " - " + xhr.statusText);
		}
	});

}
function executaAjaxGet(urlChamada, divDestino, preExecute, posExecute) {
	$.ajax({
		type : "GET",
		url : urlChamada,
		beforeSend : preExecute,

		success : function(response) {
			// we have the response
			var x = document.getElementById(divDestino);
			x.innerHTML = response;			
		},
		complete : function() {
			posExecute;
			afterReload();
		},
		error : function(xhr) {
			alert("Um erro ocorreu: " + xhr.status + " - " + xhr.statusText);
		}
	});

}

function executaAjaxPost(divDestino, formOrigem, preExecute, posExecute) {
	var data = new FormData(formOrigem[0]);

	$.ajax({
		type : 'POST',
		url : formOrigem.attr("action"),
		data : data,
		processData : false,
		contentType : false,
		beforeSend : preExecute,
		success : function(response) {
			document.getElementById(divDestino).innerHTML = response;			
		},
		complete : function() {
			posExecute;
			afterReload();			
		},
		error : function(xhr) {
			alert("Um erro ocorreu: " + xhr.status + " - " + xhr.statusText);
		}
	});
	return false;
}


function executaAjaxPostNoReload(divDestino, formOrigem, preExecute, posExecute, afterFunction) {
	var data = new FormData(formOrigem[0]);

	$.ajax({
		type : 'POST',
		url : formOrigem.attr("action"),
		data : data,
		processData : false,
		contentType : false,
		beforeSend : preExecute,
		success : function(response) {
			document.getElementById(divDestino).innerHTML = response;
			afterFunction();			
		},
		complete : function() {
			posExecute;		
		},
		error : function(xhr) {
			alert("Um erro ocorreu: " + xhr.status + " - " + xhr.statusText);
		}
	});
	return false;
}

function menuOnClick(destino) {
	executaAjaxGet(destino, 'page-wrapper', '', previnePadrao());
}


function atualizaListaCidades(enderecoGet, parametroGet, destino, funcaoAntes,
		funcaoDepois) {
	executaAjaxGet(enderecoGet + parametroGet, destino, funcaoAntes,
			funcaoDepois);
}

function ajaxindicatorstart(text) {
	$('#resultLoading').css({
		'width' : '100%',
		'height' : '100%',
		'position' : 'fixed',
		'z-index' : '10000000',
		'top' : '0',
		'left' : '0',
		'right' : '0',
		'bottom' : '0',
		'margin' : 'auto'
	});

	$('#resultLoading .bg').css({
		'background' : '#000000',
		'opacity' : '0.7',
		'width' : '100%',
		'height' : '100%',
		'position' : 'absolute',
		'top' : '0'
	});

	$('#resultLoading>div:first').css({
		'width' : '250px',
		'height' : '75px',
		'text-align' : 'center',
		'position' : 'fixed',
		'top' : '0',
		'left' : '0',
		'right' : '0',
		'bottom' : '0',
		'margin' : 'auto',
		'font-size' : '16px',
		'z-index' : '10',
		'color' : '#ffffff'

	});

	$('#resultLoading .bg').height('100%');
	$('#resultLoading').fadeIn(300);
	$('body').css('cursor', 'wait');
}

function ajaxindicatorstop() {
	$('#resultLoading .bg').height('100%');
	$('#resultLoading').fadeOut(300);
	$('body').css('cursor', 'default');
}

$(document).on({
	ajaxStart : function() {
		ajaxindicatorstart('Carregando... Aguarde');
	},
	ajaxStop : function() {
		ajaxindicatorstop();
	}
});

function previnePadrao() {
	$('form.usaAjax').on({
		click : function(event) {
			// impede o comportamento default (ir para página "teste")
			event.preventDefault();
			// alert("Clicaram");
		}
	});

};

function formSubmitClick(e, destino) {
	var name = $(e).attr('name');
	if (typeof name == 'undefined')
		return;
	var value = $(e).attr('value');
	var form = $(e).parents('form').first();
	var input = $('<input type="hidden" class="temp-hidden" />').attr('name',
			name).attr('value', value);
	$(form).find('input.temp-hidden').remove();
	$(form).append(input);
	executaAjaxPost(destino, form, "", previnePadrao());
	return false;
};

function formSubmitClickNoReload(e, destino, afterFunction) {
	var name = $(e).attr('name');
	if (typeof name == 'undefined')
		return;
	var value = $(e).attr('value');
	var form = $(e).parents('form').first();
	var input = $('<input type="hidden" class="temp-hidden" />').attr('name',
			name).attr('value', value);
	$(form).find('input.temp-hidden').remove();
	$(form).append(input);
	executaAjaxPostNoReload(destino, form, "", previnePadrao(), afterFunction);
	return false;
};

function afterReload() {
	$('.multipleSelect').multiselect({
		buttonWidth : '100%',
		nonSelectedText : 'Selecione',
		allSelectedText : 'Todos...',
		includeSelectAllOption : true,
		selectAllText : "Todos"
	});
	$('.usaCalendario').datetimepicker({
		format : 'd/m/Y H:i:s'
	});
	$.datetimepicker.setLocale('pt-BR');
	$('table#sample').DataTable().ajax.reload();
}

function mascararTelefone(objeto) {
	var v = objeto.value;
	v = v.replace(/\D/g, ""); // Remove tudo o que não é dígito
	v = v.replace(/^(\d{2})(\d)/g, "($1) $2"); // Coloca parênteses em volta
	// dos dois primeiros dígitos
	v = v.replace(/(\d)(\d{4})$/, "$1-$2"); // Coloca hífen entre o quarto e o
	// quinto dígitos
	objeto.value = v;
}

function mascaraDinheiro(objeto) {
	var v = objeto.value;
	v = v.replace(/\D/g, '');
	v = v.replace(/(\d{1,2})$/, ',$1');
	v = v.replace(/(\d)(?=(\d{3})+(?!\d))/g, '$1.');
	v = v != '' ? 'R$ ' + v : '';
	objeto.value = v;
}

function mascaraCpfCnpj(objeto) {

	var v = objeto.value;
	// Remove tudo o que não é dígito
	v = v.replace(/\D/g, "")

	if (v.length <= 11) { // CPF

		// Coloca um ponto entre o terceiro e o quarto dígitos
		v = v.replace(/(\d{3})(\d)/, "$1.$2")

		// Coloca um ponto entre o terceiro e o quarto dígitos
		// de novo (para o segundo bloco de números)
		v = v.replace(/(\d{3})(\d)/, "$1.$2")

		// Coloca um hífen entre o terceiro e o quarto dígitos
		v = v.replace(/(\d{3})(\d{1,2})$/, "$1-$2")

	} else { // CNPJ

		// Coloca ponto entre o segundo e o terceiro dígitos
		v = v.replace(/^(\d{2})(\d)/, "$1.$2")

		// Coloca ponto entre o quinto e o sexto dígitos
		v = v.replace(/^(\d{2})\.(\d{3})(\d)/, "$1.$2.$3")

		// Coloca uma barra entre o oitavo e o nono dígitos
		v = v.replace(/\.(\d{3})(\d)/, ".$1/$2")

		// Coloca um hífen depois do bloco de quatro dígitos
		v = v.replace(/(\d{4})(\d)/, "$1-$2")

	}

	objeto.value = v;

}

function mascararCep(objeto) {

	var v = objeto.value;
	// Remove tudo o que não é dígito
	v = v.replace(/\D/g, "")
	// Coloca um ponto entre o terceiro e o quarto dígitos
	v = v.replace(/(\d{5})(\d)/, "$1-$2")
	objeto.value = v;

}

function somenteNumeros(e) {
	var tecla = e.charCode;
	if ((tecla > 47 && tecla < 58))
		return true;
	else {
		if (tecla == 8 || tecla == 0)
			return true;
		else
			return false;
	}
}


function menuListaInspecoesPorFuncionarioDesignado(destino, idTabela) {
	$.ajax({
		type : "GET",
		url : destino,
		beforeSend : '',
		success : function(response) {
			// we have the response
			var x = document.getElementById('page-wrapper');
			x.innerHTML = response;
			loadDataTableStatic(idTabela);			
		},
		complete : previnePadrao(),
		error : function(xhr) {
			alert("Um erro ocorreu: " + xhr.status + " - " + xhr.statusText);
		}
	});

}


function loadDataTableStatic(idTabela) {
    $('#' + idTabela).DataTable({
		language : {
			'url' : '/static/js/Portuguese.json'
		}
		
    });
}

function menuListaInspecoes(destino, urlDestino, functionLink) {
	$.ajax({
		type : "GET",
		url : destino,
		beforeSend : '',

		success : function(response) {
			// we have the response
			var x = document.getElementById('page-wrapper');
			x.innerHTML = response;
			loadDataTable(urlDestino, functionLink);			
		},
		complete : previnePadrao(),
		error : function(xhr) {
			alert("Um erro ocorreu: " + xhr.status + " - " + xhr.statusText);
		}
	});

}

function menuListaInspecoesPosInspecao(destino, urlDestino, functionLink) {
	$.ajax({
		type : "GET",
		url : destino,
		beforeSend : '',

		success : function(response) {
			// we have the response
			var x = document.getElementById('page-wrapper');
			x.innerHTML = response;
			loadDataTablePosInspecao(urlDestino, functionLink);			
		},
		complete : previnePadrao(),
		error : function(xhr) {
			alert("Um erro ocorreu: " + xhr.status + " - " + xhr.statusText);
		}
	});

}

function loadDataTable(urlDestino, functionLink) {
	var table = $('table#sample')
			.DataTable(
					{
						language : {
							'url' : '/static/js/Portuguese.json'
						},
						ajax : {
							'url' : urlDestino,
							'contentType' : 'application/json',
							'type' : 'POST',
							'data' : function(d) {
								return JSON.stringify(d);
							}
						},
						serverSide : true,						
						

						columns : [
								{
									data : 'idInspecao'
								},
								{
									data : 'dtAgendada',
									render : function(data, type, row) {
										if (row.dtAgendada)
											return new Date(row.dtAgendada)
													.toLocaleString();
										else
											return 'Não Confirmado';
									}
								},
								{
									data : 'dtSolicitacaoInspecao',
									render : function(data, type, row) {
										return new Date(
												row.dtSolicitacaoInspecao)
												.toLocaleString();
									}
								},
								{
									data : 'dtLimite',
									render : function(data, type, row) {
										return new Date(row.dtLimite)
												.toLocaleString();
									}
								},

								{
									data : 'cliente.descCliente',

								},

								{
									data : 'endereco.logradouro',

									render : function(data, type, row) {
										if (row.endereco) {
											return row.endereco.tipoLogradouro.descTipoLogradouro
													+ " "
													+ row.endereco.logradouro
													+ " "
													+ row.endereco.numero
													+ " - "
													+ row.endereco.cidade.nomeCidade;
										}
										return '';
									}
								}, {
									data : 'status.descStatus',
									visible : true
								}, {
									data : 'fase.descFase',
									visible : true
								}, {
									data : 'linkscolumn',
									orderable : false,
									searchable : false,
									render : functionLink
								},
								{
									data : 'endereco.bairro',
									visible : false
								},
								{
									data : 'endereco.tipoLogradouro.descTipoLogradouro',
									visible : false
								}, {
									data : 'endereco.cidade.nomeCidade',
									visible : false
								}]
					});
}



function loadDataTablePosInspecao(urlDestino, functionLink) {
	var table = $('table#sample')
			.DataTable(
					{
						language : {
							'url' : '/static/js/Portuguese.json'
						},
						ajax : {
							'url' : urlDestino,
							'contentType' : 'application/json',
							'type' : 'POST',
							'data' : function(d) {
								return JSON.stringify(d);
							}
						},
						serverSide : true,						
						

						columns : [
								{
									data : 'idInspecao'
								},
								{
									data : 'dtAgendada',
									render : function(data, type, row) {
										if (row.dtAgendada)
											return new Date(row.dtAgendada)
													.toLocaleString();
										else
											return 'Não Confirmado';
									}
								},
								{
									data : 'dtSolicitacaoInspecao',
									render : function(data, type, row) {
										return new Date(
												row.dtSolicitacaoInspecao)
												.toLocaleString();
									}
								},
								{
									data : 'dtLimite',
									render : function(data, type, row) {
										return new Date(row.dtLimite)
												.toLocaleString();
									}
								},

								{
									data : 'cliente.descCliente',

								},

								{
									data : 'endereco.logradouro',

									render : function(data, type, row) {
										if (row.endereco) {
											return row.endereco.tipoLogradouro.descTipoLogradouro
													+ " "
													+ row.endereco.logradouro
													+ " "
													+ row.endereco.numero
													+ " - "
													+ row.endereco.cidade.nomeCidade;
										}
										return '';
									}
								}, {
									data : 'status.descStatus',
									visible : true
								}, {
									data : 'fase.descFase',
									visible : true
								}, {
									data : 'funcionarioVistoriador.nomeFuncionario',
									render : function(data, type, row) {
										if (row.funcionarioVistoriador)
											return row.funcionarioVistoriador.nomeFuncionario;
										else
											return 'Não Preenchido';
									},
									visible : true
								},{
									data : 'dtVistoria',
									render : function(data, type, row) {
										if (row.dtVistoria)
											return new Date(row.dtVistoria)
										.toLocaleString();
										else
											return 'Não Preenchido';
									},
									visible : true
								},
								{
									data : 'linkscolumn',
									orderable : false,
									searchable : false,
									render : functionLink
										
									
								},
								{
									data : 'endereco.bairro',
									visible : false
								},
								{
									data : 'endereco.tipoLogradouro.descTipoLogradouro',
									visible : false
								}, {
									data : 'endereco.cidade.nomeCidade',
									visible : false
								}]
					});
}


function inspecionarLink(data, type, row) {
	return '<a href="#" onclick = "executaAjaxGet(\'/vistoria/inspecaoInicioFimCustos/'
			+ row.idInspecao
			+ '\',\'detalheInspecao\',\'\',\'\')"><span class="glyphicon glyphicon-calendar"> </span></a>';
}

function agendamentoLink(data, type, row) {
	return '<a href="#" onclick = "executaAjaxGet(\'/agendamento/'
			+ row.idInspecao
			+ '\',\'detalheInspecao\',\'\',\'\')"><span class="glyphicon glyphicon-calendar"> </span></a>';
}

function relatorioLink(data, type, row) {
	return '<a href="#" onclick = "executaAjaxGet(\'/relatorio/'
			+ row.idInspecao
			+ '\',\'detalheInspecao\',\'\',\'\')"><span class="glyphicon glyphicon-calendar"> </span></a>';
}


function revisaoLink(data, type, row) {
	return '<a href="#" onclick = "executaAjaxGet(\'/revisao/'
			+ row.idInspecao
			+ '\',\'detalheInspecao\',\'\',\'\')"><span class="glyphicon glyphicon-calendar"> </span></a>';
}


function insercaoSistemaLink(data, type, row) {
	return '<a href="#" onclick = "executaAjaxGet(\'/insercaoSistema/'
			+ row.idInspecao
			+ '\',\'detalheInspecao\',\'\',\'\')"><span class="glyphicon glyphicon-calendar"> </span></a>';
}


function aprovacaoSistemaLink(data, type, row) {
	return '<a href="#" onclick = "executaAjaxGet(\'/aprovacaoSistema/'
			+ row.idInspecao
			+ '\',\'detalheInspecao\',\'\',\'\')"><span class="glyphicon glyphicon-calendar"> </span></a>';
}

