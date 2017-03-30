/**
 * 
 */
$.ajax({
	type : "GET",
	url : "/inspecao",
	beforeSend : function() {
	},
	success : function(response) {
		var x = document.getElementById("page-wrapper");
		x.innerHTML = response;
	},
	complete : function() {
		$('.multipleSelect').multiselect({
			maxHeight : 200,
			dropUp : true,
			enableCaseInsensitiveFiltering : true,
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
		loadDataTableNovaInspecao('/dtinspecao/dtinspecao')
	},
	error : function(xhr) {
		alert("Um erro ocorreu: " + xhr.status + " - " + xhr.statusText);
	}
});

function loadDataTableNovaInspecao(urlDestino) {
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
								},
								{
									data : 'status.descStatus',
									visible : true
								},
								{
									data : 'fase.descFase',
									visible : true
								},
								{
									data : 'funcionarioVistoriador.nomeFuncionario',
									render : function(data, type, row) {
										if (row.funcionarioVistoriador)
											return row.funcionarioVistoriador.nomeFuncionario;
										else
											return 'Não Preenchido';
									},
									visible : true
								},
								{
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
									render : function(data, type, row) {
										return '<a href="#" onclick = "executaAjaxGetNoReload(\'/inspecao/remover/'
												+ row.idInspecao
												+ '\',\'page-wrapper\',\'\',\'\',function(){$.getScript(\'/static/js/novaInspecao.js\')})"><span class="glyphicon glyphicon-remove"> </span></a>'
												+ '<span>  </span><a href="#" onclick = "executaAjaxGet(\'/inspecao/editar/'
												+ row.idInspecao
												+ '\',\'form-wrapper\',\'\',\'\')"><span class="glyphicon glyphicon-pencil"> </span></a>';
									}
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
								} ],
						"order" : [ [ 0, "desc" ] ]
					});
}
