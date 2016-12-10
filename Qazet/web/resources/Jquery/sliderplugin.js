(function($) {

    $.fn.ms = function(options) {


        var targetObject = $(this);
        var settings = {
            'color': false
        }

        if (options) {
            $.extend(settings, options);
        }


        var fms = targetObject.find('.featured_mini_slider')
        targetObject.wrapInner("<div class='mini_slider'></div>");
        targetObject.wrapInner("<div class='featured_mini_slider'></div>");
        var fms = targetObject.find('.featured_mini_slider')
        var ms = targetObject.find('.mini_slider')
        fms.after("<div class='mini_slider_div_PhotoNumber'></div>");
        var msdp = targetObject.find('.mini_slider_div_PhotoNumber')
        msdp.after("<div class='thumbinal'></div>");
        targetObject.find('.thumbinal').after("<div class='thumbinalHover'></div>");
        targetObject.find('.thumbinalHover').after("<div class='divForSize'></div>");
        ms.css("height", fms.css("height"));

        var sw = parseInt(fms.css("width"))
        ms.css("width", sw * targetObject.find('.mini_slider div').size());

        var color = targetObject.find('.thumbinal').css("background-color");
        var colorClick = targetObject.find('.thumbinalHover').css("background-color");
        msdp.append("<img style='float:left' class='mini_slider_leftbutton'  src='resources/designtools/icons/mini_slider_left.png' />")


        for (i = 0; i < ms.find('img').size(); i++) {

            msdp.append("<div><span>" + i + "</span><img style='float:left' class='mini_slider_button'  src='resources/designtools/icons/mini-slider-button.png' /></div>")

        }
        
        var pa=parseInt(msdp.css('width'))-(msdp.find('div').size()+2)*(parseInt(msdp.find('div').css('width'))+parseInt(msdp.find('div').css('margin-left'))+parseInt(msdp.find('div').css('margin-right')))
        var wg=parseInt(msdp.css('width'))-pa/2
        
        msdp.css('padding-left',pa/2+"px")
        msdp.css('width',wg+"px")
        msdp.find("div").css("background-color", color);
        msdp.find("div").eq(0).css("background-color",  colorClick);
        
        var leftButton = targetObject.find('.mini_slider_leftbutton');
        msdp.find("div").eq(msdp.find('div').size()-1).after("<img class='mini_slider_rightbutton'  style='float:left' src='resources/designtools/icons/mini_slider_right.png' />")
        var rightButton = targetObject.find('.mini_slider_rightbutton');
        var sr=parseInt(msdp.find("div").eq(0).css("width"))*2
        leftButton.css("width", sr/2+"px");
        rightButton.css("width", sr/2+"px");
        leftButton.css("height", sr+"px");
        rightButton.css("height", sr+"px");
        
        var path=ms.eq(0).find("img").attr("src").split("esources")
        var path2=leftButton.attr("src").split("esources")
        var path3=rightButton.attr("src").split("esources")
        var path4=$(".mini_slider_button").eq(0).attr("src").split("esources")
        leftButton.attr("src",path[0]+"esources"+path2[1])
        rightButton.attr("src",path[0]+"esources"+path3[1])
        $(".mini_slider_button").attr("src",path[0]+"esources"+path4[1])
        $(".mini_slider_button").height(msdp.find("div").height())
        $(".mini_slider_button").width(msdp.find("div").width())
        var i = 0;
        
        var allwidth = 0;
        var mini_slider_div_width = fms.width();
        var wi = parseInt(fms.css("width"))
        var san = (wi / 2) * 5

        function animation(px) {
            ms.stop(false, false).animate({
                left: -px
            }, san);
        }

        msdp

        msdp.find('div').click(function() {
            
            
             i = $(this).index()-1;
            position = i * mini_slider_div_width;
            animation(position);

           
//               if(index==0){
//                
//                i=nsdn.find('div').size() - 1
//            }
//            else{
//                
//                i--
//            }
//            
//             if (index == (nsdn.find('div').size() - 1)) {
//                i = 0;
//            }
//            else{
//                
//                i++
//            }
             
            msdp.find('div').css("background-color", color);
            $(this).css("background-color", colorClick);
//         $('#mini_slider_div_PhotoNumber div').css({ boxShadow: '0px 0px 2px #fff' });
//         $(this).css({ boxShadow: '0px 0px 2px white' })

        
            return false;

        });

         rightButton.click(function() {
              if (i == (msdp.find('div').size() - 1)) {
                i = 0;
            }
            else{
                
                i++
            }
            
              allwidth = i * mini_slider_div_width;
            
            
            
            

            msdp.find('div').css("background-color", color);
            msdp.find('div').eq(i).css("background-color", colorClick);
//        $('#mini_slider_div_PhotoNumber div').css({ boxShadow: '0px 0px 2px #fff' });
//        $('#mini_slider_div_PhotoNumber div').eq(i).css({ boxShadow: '0px 0px 2px white' })
            animation(allwidth);
            
           
             
         })

         leftButton.click(function() {
              if(i==0){
                
                i=msdp.find('div').size() - 1
            }
            else{
                
                i--
            }
           
            allwidth = i * mini_slider_div_width;
            
            
            msdp.find('div').css("background-color", color);
            msdp.find('div').eq(i).css("background-color", colorClick);
          

//        $('#mini_slider_div_PhotoNumber div').css({ boxShadow: '0px 0px 2px #fff' });
//        $('#mini_slider_div_PhotoNumber div').eq(i).css({ boxShadow: '0px 0px 2px white' })
            animation(allwidth);
            
             
         })

        var timer = setInterval(function() {
          
            if (i == (msdp.find('div').size() - 1)) {
                i = 0;
            }
            else{
                
                i++
            }
            
            allwidth = i * mini_slider_div_width;
            
            
            

            msdp.find('div').css("background-color", color);
            msdp.find('div').eq(i).css("background-color", colorClick);
//        $('#mini_slider_div_PhotoNumber div').css({ boxShadow: '0px 0px 2px #fff' });
//        $('#mini_slider_div_PhotoNumber div').eq(i).css({ boxShadow: '0px 0px 2px white' })
            animation(allwidth);
            
          
        }, 8000);





    }

})(jQuery);




