(function(win,doc){
	'use strict';
	
	var $btnCadastro = doc.querySelector('[data-js="btn-cadastro"]');
	
	$btnCadastro.addEventListener('click', limparCampos, false);
	
	function limparCampos(){
		doc.getElementById('cadastro').reset();
	}
	
	
	
})(window,document);
