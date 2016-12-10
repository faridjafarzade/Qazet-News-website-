$(document).ready( function() {		
  var i = 0;	
  var tumgenislik = 0;
  var mini_slider_div_width = $('.mini_slider div').width();
  
  function animation(px){	
	  $('.mini_slider').stop(false, false).animate({	
	  left: -px	
	}, 300);	
  }

  $('.mini_slider_div_PhotoNumber div').click(function(){	
	var index = $(this).index();	
	pozisyon = index * mini_slider_div_width;	
	animation(pozisyon);	
	if(index == ($('.mini_slider_div_PhotoNumber div').length-1)){	
	  i = 0;	
	}else{		
	i = index + 1;
	}
         $('.mini_slider_div_PhotoNumber div').css("background-color","white");      
         $(this).css("background-color","#4e5759");
//         $('#mini_slider_div_PhotoNumber div').css({ boxShadow: '0px 0px 2px #fff' });
//         $(this).css({ boxShadow: '0px 0px 2px white' })

  return false;	

  });
  
  var zamanlayici = setInterval(function() {
	tumgenislik = i * mini_slider_div_width;
	if(i== ($('.mini_slider_div_PhotoNumber div').length-1)){	
	  i = -1;	
	}
        $('.mini_slider_div_PhotoNumber div').css("background-color","white");
        $('.mini_slider_div_PhotoNumber div').eq(i).css("background-color","#4e5759");
//        $('#mini_slider_div_PhotoNumber div').css({ boxShadow: '0px 0px 2px #fff' });
//        $('#mini_slider_div_PhotoNumber div').eq(i).css({ boxShadow: '0px 0px 2px white' })
        animation(tumgenislik);	
	i++;	
  }, 8000);		

});