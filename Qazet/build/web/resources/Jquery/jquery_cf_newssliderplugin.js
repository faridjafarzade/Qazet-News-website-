(function($) {

    $.fn.cf_newsslider = function(options) {


        var targetObject = $(this);
        var settings = {
            'color': false
        }

        if (options) {
            $.extend(settings, options);
        }


        targetObject.wrapInner("<div class='news_slider'></div>");
        targetObject.wrapInner("<div class='featured_news_slider'></div>");
        var fns = targetObject.find('.featured_news_slider')
        var ns = targetObject.find('.news_slider')

        fns.before("<div class='news_slider_div_Header'></div>");
        var nsdh = targetObject.find('.news_slider_div_Header');
        nsdh.wrapInner("<div class='news_slider_div_HeaderText'></div>");
        var nsdht = targetObject.find('.news_slider_div_HeaderText');
        nsdht.after("<div class='news_slider_div_Number'></div>");
        var nsdn = targetObject.find('.news_slider_div_Number')
        ns.before("<div class='news_slider_news'></div>")
        var nsn = targetObject.find('.news_slider_news')
        nsn.before("<img class='news_slider_leftbutton'  src='resources/designtools/icons/news_slider_left_button.png' />")
        var leftButton = targetObject.find('.news_slider_leftbutton');
        nsn.before("<img class='news_slider_rightbutton'  src='resources/designtools/icons/news_slider_right_button.png' />")
        var rightButton = targetObject.find('.news_slider_rightbutton');
        var nsto = targetObject.find('.news_slider_topic')
        var nst = targetObject.find('.news_slider_text')
        var nsi = targetObject.find('.news_slider_images')
        nsdn.after("<div class='thumbinal'></div>");
        targetObject.find('.thumbinal').after("<div class='thumbinalClick'></div>");
        var color = targetObject.find('.thumbinal').css("background-color");
        var colorClick = targetObject.find('.thumbinalClick').css("background-color");
        var sw = parseInt(fns.css("width"))
        ns.css("width", sw * targetObject.find('.news_slider div').size());
        var path=nsi.eq(0).find("img").attr("src").split("esources")
        var path2=leftButton.attr("src").split("esources")
        var path3=rightButton.attr("src").split("esources")
        leftButton.attr("src",path[0]+"esources"+path2[1])
        rightButton.attr("src",path[0]+"esources"+path3[1])
        nsn.html(nst.eq(0).html());
        nsdht.html("<b>" + nsto.eq(0).html() + "</b>")

        for (var i = 0; i < nsi.size(); i++) {
            nsdn.append("<div><span>" + i + "</span><img style='float:left' class='slider_button'  src='resources/designtools/icons/news-slider-button.png' /></div>")
            ns.append("<div>" + nsi.eq(i).html() + "</div>")
        }
        nsn.css("top", 325 - parseInt(nsn.css("height")))

        nsdn.find("div").css("background-color",color)
        nsdn.find("div").eq(0).css("background-color",colorClick)
        var path4=$(".slider_button").eq(0).attr("src").split("esources")
        $(".slider_button").attr("src",path[0]+"esources"+path4[1])
        $(".slider_button").height(nsdn.find("div").height())
        $(".slider_button").width(nsdn.find("div").width())
      
        var i = 0;
        
        var allwidth = 0;
        var mini_slider_div_width = fns.width();
        var wi = parseInt(fns.css("width"))
        var san = (wi / 2) * 5

        function animation(px) {
            ns.stop(false, false).animate({
                left: -px
            }, san);
        }

        nsdn

        nsdn.find('div').click(function() {
            
            
             i = $(this).index();
            position = i * mini_slider_div_width;
            animation(position);

            nsn.html(nst.eq(i).html());
            nsdht.html("<b>" + nsto.eq(i).html() + "</b>")
            nsn.css("top", 325 - parseInt(nsn.css("height")))
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
             
            nsdn.find('div').css("background-color", color);
            $(this).css("background-color", colorClick);
//         $('#mini_slider_div_PhotoNumber div').css({ boxShadow: '0px 0px 2px #fff' });
//         $(this).css({ boxShadow: '0px 0px 2px white' })

        
            return false;

        });

         rightButton.click(function() {
              if (i == (nsdn.find('div').size() - 1)) {
                i = 0;
            }
            else{
                
                i++
            }
            
              allwidth = i * mini_slider_div_width;
            nsn.html(nst.eq(i).html());
            nsdht.html("<b>" + nsto.eq(i).html() + "</b>")
            nsn.css("top", 325 - parseInt(nsn.css("height")))
            
            
            

            nsdn.find('div').css("background-color", color);
            nsdn.find('div').eq(i).css("background-color", colorClick);
//        $('#mini_slider_div_PhotoNumber div').css({ boxShadow: '0px 0px 2px #fff' });
//        $('#mini_slider_div_PhotoNumber div').eq(i).css({ boxShadow: '0px 0px 2px white' })
            animation(allwidth);
            
           
             
         })

         leftButton.click(function() {
              if(i==0){
                
                i=nsdn.find('div').size() - 1
            }
            else{
                
                i--
            }
           
            allwidth = i * mini_slider_div_width;
            nsn.html(nst.eq(i).html());
            nsdht.html("<b>" + nsto.eq(i).html() + "</b>")
            nsn.css("top", 325 - parseInt(nsn.css("height")))
            
            nsdn.find('div').css("background-color", color);
            nsdn.find('div').eq(i).css("background-color", colorClick);
          

//        $('#mini_slider_div_PhotoNumber div').css({ boxShadow: '0px 0px 2px #fff' });
//        $('#mini_slider_div_PhotoNumber div').eq(i).css({ boxShadow: '0px 0px 2px white' })
            animation(allwidth);
            
             
         })

        var timer = setInterval(function() {
          
            if (i == (nsdn.find('div').size() - 1)) {
                i = 0;
            }
            else{
                
                i++
            }
            
            allwidth = i * mini_slider_div_width;
            nsn.html(nst.eq(i).html());
            nsdht.html("<b>" + nsto.eq(i).html() + "</b>")
            nsn.css("top", 325 - parseInt(nsn.css("height")))
            
            

            nsdn.find('div').css("background-color", color);
            nsdn.find('div').eq(i).css("background-color", colorClick);
//        $('#mini_slider_div_PhotoNumber div').css({ boxShadow: '0px 0px 2px #fff' });
//        $('#mini_slider_div_PhotoNumber div').eq(i).css({ boxShadow: '0px 0px 2px white' })
            animation(allwidth);
            
          
        }, 14000);






    }

})(jQuery);




