$(document).ready(function(){
    var page="home"
    var imagenumber=0;
    var nav_width=0
     
    $(".home_page1 img").mouseover(function(){
        $(this).siblings().css("left", "0px")
        $(this).animate({
            left:"-100px"
        },800);
                    
    }); 
                   
    $(".home_page1 img").mouseout(function(){
        $(this).siblings().css("left", "0px")
        $(this).animate({
            left:"0px"
        },500);
    });
  
         
    
    $("#div_albomlist2 ul li div").click(function(){
        
        var idAr=$(this).attr("id").split("-")
      
        var id=idAr[1]
     
        $(" #div_portfolio1, #div_portfolio2").animate({
            width:'0%'
        },1300,function(){ 
            $("#div_portfolio1, #div_portfolio2").css("display","none"); 
            giveAlbom(id);
        })//animate end
        
    })
    $("#nav2 ul li div").eq(1).click(function(){
        if(page!="portfolio"){
            exithome() 
        
            $("body").animate({
                display:"block"
            },1300,function(){
                giveAlbom(1)
                page="portfolio"
            })
        
        }
    })
    $("#nav2 ul li div").eq(2).click(function(){
        if(page!="info"){
            exithome() 
            $("body").animate({
                display:"block"
            },1300,function(){
            
                giveInfo()
                page="info" 
             
            })
        }
    })
        
    $("#nav2 ul li div").eq(3).click(function(){
        if(page!="contacts"){
            exithome() 
            $("body").animate({
                display:"block"
            },1300,function(){
            
                giveContact()
                page="contacts" 
             
            })
        }
    })
    
    
    
    function exithome(){
       
       
    
        
        
        if(page=="home"){
        
        
            $("#nav2,#nav1").animate({
                top:"5px",
                fontSize:"16",
                padding:"1px"
            },1000);
            $("#cencorship1").css("display","block")
            $("#cencorship1").animate({
                opacity:0.5
            },1300);
            $("#nav2 ul li div,#nav1 ul li div").animate({
                fontSize:"16px"
            },500);
        }
        else{
         
            $(" #div_portfolio1, #div_portfolio2").animate({
                width:'0%'
            },1300,function(){ 
                $("#div_portfolio1, #div_portfolio2").css("display","none"); 
            })//animate end
        
            $(" #div_albomlist1, #div_albomlist2").animate({
                width:'0%'
            },1300,function(){ 
                $("#div_albomlist1, #div_albomlist2").css("display","none"); 
            })//animate end
         
            $("#div_info").animate({
                width:'0%'
            },1300,function(){ 
                $("#div_info").css("display","none"); 
            })//animate end
         
            $("#div_contact").animate({
                width:'0%'
            },1300,function(){ 
                $("#div_contact").css("display","none"); 
            })//animate end
         
          
         
        }
       
    }
    
    function giveAlbom(id){
       
        
        $.ajax({
            url:"AjaxApplications/AlbomJSon.php",
            type:"post",
            data:{
                "albom_id":id
            },
            dataType:"json",
            error:function(xhr,xeta){
                alert("xta var"+xeta+" "+xhr.statusText)     
            //$.each(data, function(sira,netice){})
            },
            success:function(data){
                var borderNumber=0
                $.each(data, function(sira,netice){
                    $("#div_images").empty()
                    borderNumber=netice.images.length
              
                    for(var i=0;i<netice.images.length;i++){
                        $("#div_images").append("<div class='div_image'><img  class='image' id='img-"+i+"' src='"+netice.images[i].adress+"'/></div>");
                    }
                })
         
           $(".image").eq(borderNumber).load(function(){
              
                    $("#div_images").animate({
                        width:'750px'
                    },1300,function(){
                        $("#div_images").vO2_scrollbar({
                            navKeyScroll:true, 
                            mouseScroll:true
                        });
                        imageEffects()
                       
                    })
                })  
                                
                    
                var albomlist_width=0
                $("#div_albomlist1, #div_albomlist2").css("display","block")
                for(var i=0;i<$("#div_albomlist2 ul li").length;i++){
                    albomlist_width=albomlist_width+$("#div_albomlist2 ul li").eq(i).width()+15
           
                }
        
                var albomlist_width_px=albomlist_width+'px';
      
          
               
                $("#div_albomlist1, #div_albomlist2").css("display","block")
                $(" #div_albomlist1, #div_albomlist2").animate({
                    width:albomlist_width_px
                },1300,function()

                {
                        $("#div_portfolio1, #div_portfolio2").css("display","block")
                        $(" #div_portfolio1, #div_portfolio2").animate({
                            width:'80%'
                        },1300);
             
                    })
                imageEffects()
               
                function imageEffects(){  
                    $(".image").unbind("mouseover")
                    $(".image").bind("mouseover",function(){
                               
                        var idAr=$(this).attr("id").split("-")
      
                        imagenumber=idAr[1]
                        $("#mouse_over_img").css("left", $(this).offset().left-10+"px") 
                        $("#mouse_over_img").css("top", $(this).offset().top-10+"px") 
                        $("#mouse_over_img").css("display", "block") 
              
             
                        if($(this).height()>$(this).width()){
                            $("#mouse_over_img").css("width", "140px")
                            $("#mouse_over_img").css("height", ($(this).height()*140/$(this).width())+"px")
                        //              alert( $("nav ul li span").css("margin-left")+" "+ $("nav ul li span").css("margin-right")
                        //               +" "+ $("nav").css("width")+" h b")
                        }
                        else{
                            $("#mouse_over_img").css("height", "140px") 
                            $("#mouse_over_img").css("width", ($(this).width()*140/$(this).height())+"px")
                        //                alert( $("nav ul li span").css("margin-left")+" "+ $("nav ul li span").css("margin-right")
                        //               +" "+ $("nav").css("width")+" w b")
                        }
              
                        var image = $(this).attr("src")
                        $("#mouse_click_img").attr("src", image)
                        $("#usingleftimage").attr("src",image)
                        $("#usingrightimage").attr("src",image)
                        $("#mouse_over_img").attr("src",image)
             
                    //image mausover end   
                    })
                       
                    $("#mouse_over_img").unbind("mouseout")
                    $("#mouse_over_img").bind("mouseout",function(){
                        $("#mouse_over_img").css("display", "none")
                    })       
                    
                    $("#mouse_over_img").unbind("click")
                   
                    $("#mouse_over_img") .bind("click",function(){
              
                        $("#nav1,#nav2,#div_albomlist1, #div_albomlist2,#div_portfolio1, #div_portfolio2").css("display", "none")  
             
                        var image=$(".image").eq(imagenumber).attr("src");
         
                        $("#mouse_click_div").css("display", "block")      
           
                        $("#mouse_click_div").animate({
                            opacity:0.8
                        },1300,function(){
                          
                            
                            $("#mouse_click_table,#mouse_click_img").css("display", "block") 
                            tolittle("#mouse_click_img")
                            center("#mouse_click_table");
                           
                          
                           
                            opa()
                            ui()
                           
                          
     
                        //#mouse_click_div animate function end
              
                        })   
         
                    //image mause click end
                    })
                }
                $("#leftarrow").unbind("click")
                $("#leftarrow").bind("click",function(){
 
                    if((imagenumber)>0){
                        imagenumber=parseInt(imagenumber)-1;
                    }
       
                    var im=$(".image").eq(imagenumber).attr("src");
                    $("#mouse_click_img").attr("src", im)
      
                    tolittle("#mouse_click_img")
                    center("#mouse_click_table");
      
                    opa()
                    ui()
    
                //left arrow click function end

                }) 
                $("#rightarrow").unbind("click")
                $("#rightarrow") .bind("click",function(){
             
                    if((imagenumber)<borderNumber-1){
                        imagenumber=parseInt(imagenumber)+1;
                    }
                    opa()
     
       
                    var  im2=$(".image").eq(imagenumber).attr("src");
                    $("#mouse_click_img").attr("src", im2)
     
                    tolittle("#mouse_click_img")
                    center("#mouse_click_table");
    
   
                    ui()
 
                // right arrow click function end
                })
         
                function opa(){
    
                    $("#rightarrow").css("display","none");
                    $("#leftarrow").css("display","none");

                    if(imagenumber<borderNumber-1){
                        $("#rightarrow").css("display","block");
                    }

                    if((imagenumber)>0){
                        $("#leftarrow").css("display","block");
                    }

                   
                // opa end
                } 

                function center(id){
    
    
                    var   thiswidth=$(id).width(); 
                    var   thisheight=$(id).height(); 
                    var wiheight= $("body").height(); 
                    var wiwidth= $("body").width();
                    var imagetop=(wiheight-parseInt(thisheight))/2;
                    var imageleft=(wiwidth-parseInt(thiswidth))/2;
      
                    if(wiwidth>thiswidth){
                        $("#mouse_click_table").css("left",parseInt(imageleft)+"px");
                    }
                    else {
                        $("#mouse_click_table").css("left","10px");
                    }
                    if(wiheight>thisheight){
                        $("#mouse_click_table").css("top",parseInt(imagetop)+"px");
                    }
                    else {
                        $("#mouse_click_table").css("top","10px");
                    }
      
                //center end
                }
                function tolittle(id){
                    
                    var wiheight= $("body").height(); 
                    var wiwidth= $("body").width(); 
                    var maxwidth=parseInt(wiwidth)-30
                    var maxheight=parseInt(wiheight)-30
                    $(id).css("max-width",maxwidth+"px");
                    $(id).css("max-height",maxheight+"px");
    
                    //tolittle end
                }


                function ui(){
   
                    if((imagenumber)>0) var  usingleftimage=$(".image").eq(parseInt(imagenumber)-1).attr("src")
                    if((imagenumber)<borderNumber-1) var  usingrightimage=$(".image").eq(parseInt(imagenumber)+1).attr("src")
                    $("#usingleftimage").attr("src", usingleftimage)
                    $("#usingrightimage").attr("src", usingrightimage)
   
    
                //ui end
                }
          
               
                $("#mouse_click_div").click(function(){
                    $("#mouse_click_img,#mouse_click_div,#rightarrow,#leftarrow").css("display", "none") 
                    $(" #nav1,#nav2,#nav1,#nav2,#div_albomlist1, #div_albomlist2,#div_portfolio1, #div_portfolio2").css("display", "block") 
          
     
                //mause click div end
                })       
            //ajax success end
            }
        }) 
      
    //givealbom end  
    }
 

 
    function giveInfo(){
   
        $.ajax({
            url : "AjaxApplications/About.php",
            type : "POST",
            success: function(data){
                $("#div_about").html(data);
         
                $("#div_info").css("display","block")   
                $("#div_info").animate({
                    width:'80%'
                },1300,function(){ });
         
                $("#div_about").vO2_scrollbar({
                    navKeyScroll:true, 
                    mouseScroll:true
                });
         },
            error: function(){
                alert(2)
            }
        });
  
    //exit giveinfo
    }
 
    function giveContact(){
        
       $("#div_contact").css("display","block")   
        $("#div_contact").animate({
            width:'90%'
        },1300,function(){});
//        giveContacts("#social_media")  
//        giveContacts("#telephone_number")  
//        giveContacts("#Websayt")  
//        giveContacts("#Email")
//        giveContacts("#Adress")
   //exit givecontact
}
 
    $("#div_SM_button, #div_contacts_button").bind("click",function(){
       
        var idAr=$(this).attr("id").split("_")
      
        var id="#"+idAr[0]+"_"+idAr[1]
        openDiv(id)
    })
   
    $("#send").click(function(){
     
     
     
        var info="Topic="+$("#Topic").val()+
        "&&Name="+$("#Name").val()+
        "&&Surname="+$("#Surname").val()+
        "&&Email="+$("#Email").val()+
        "&&Mesage="+$("#Mesage").val()
          
        $.post("AjaxApplications/insertMesage.php",info,function(data){})
        $("#clean").click()
   
    })
	
    function openDiv(divId){
            
        if   ($(divId).css("display")=="block"){
            $(divId).css("display","none")
        }
        else {
            $(divId).css("display","block")
                 
             
        }
            
    }
     
    function giveContacts(divId){
          
      
        var keyAr=$(divId).attr("id").split("_")
        if(keyAr.length>1){
            var key=keyAr[0]
            for(var i=1;i<keyAr.length;i++){
                key=key+" "+keyAr[i]
            }
        }
        else var key=$(divId).attr("id")
          
      
        var info="Key="+key;
      
        $.ajax({
            url:"AjaxApplications/Contacts.php",
            type:"post",
            data:{
                "Key":key
            },
            dataType:"json",
            error:function(xhr,xeta){
                alert("xta var"+xeta+" "+xhr.statusText)     
            //$.each(data, function(sira,netice){})
            },
            success:function(data){
                $(divId).find("table").empty()
                if(key=="social media"){
                    for(var i=0;i<data.length;i++){
                        $(divId).find("table").append("<tr><td><a href='"+data[i].Value+"'>"+data[i].Type+"</span></td></tr>"); 
                    }    
                           
                }
                else if(key=="Email"||key=="Websayt"){
                      
                    for(var i=0;i<data.length;i++){
                        $(divId+" table").append("<tr><td><a href='"+data[i].Value+"'>"+data[i].Value+"</a></td></tr>"); 
                      
                       
                    }
                }
                   
                else {
                    for(var i=0;i<data.length;i++){
                        $(divId).find("table").append("<tr><td><span>"+data[i].Value+"</span></td><td></tr>"); 
                     
                    }
                        
                
                
                }
                
            //ajax and success end
            }
        })
        
    
   
   
     
    }
})

