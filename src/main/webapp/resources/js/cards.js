/**
 * 
 */

// init Isotope
var $card_container = $('.card_container').isotope({
  // options
  itemSelector: '.business_card',
  layoutMode: 'fitRows',
  transitionDuration: 0
});

// store filter for each group
//var filters = {};
var buttonSelected = '*';

// bind filter on select change
$('.filters-select').on( 'change', function() {
  // get filter value from option value
  var filterValue = this.value;
  $card_container.isotope({ filter: buttonSelected + filterValue });
});

// filter items on button click
$('.filter_nav').on( 'click', 'button', function() {
  var filterValue = $(this).attr('data-filter');
  buttonSelected = filterValue;
  var filterDropdown = $('#selectDropdown option:selected').val();
  //alert('filterValue: ' + filterValue + filterDropdown);
  $card_container.isotope({ filter: filterValue + filterDropdown });
});

// change is-checked class on buttons
$('.button-group').each( function( i, buttonGroup ) {
  var $buttonGroup = $( buttonGroup );
  $buttonGroup.on( 'click', 'button', function() {
    $buttonGroup.find('.is-checked').removeClass('is-checked');
    $( this ).addClass('is-checked');
  });
});

$(document).on('click', '.flex-item label, .flex-item', function() {
	$('[type="radio"]').prop('checked', false);
	
	$('[type="radio"]').closest('li').css('background-color', 'white');
	
	$(this).closest('li').find('[type="radio"]').prop('checked', true);
	
	$(this).closest('li').css('background-color', 'beige');
})

$( document ).ready(function() {
    var lastSearchBy = $('#searchByHidden').val();

	$('[type="radio"]').each(function() {
		if (lastSearchBy === $(this).val()) {
			$(this).prop('checked', true);
			$(this).closest('li').css('background-color', 'beige');
		}
		else {
			$(this).prop('checked', false);
			$(this).closest('li').css('background-color', 'white');
		}
	});
});

