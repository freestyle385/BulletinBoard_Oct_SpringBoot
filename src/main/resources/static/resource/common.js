$('select[data-value]').each(function(el){
	const $el = $(el);
	
	const defaultValue = $el.attr('data-value').trim();
	
	if(defaultValue.length > 0){
		$el.val(defaultValue);
	}
});