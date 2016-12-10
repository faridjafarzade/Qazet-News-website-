$(document).ready(function() {



var ustunde = 0;
var sss=(parseInt($(window).height())-parseInt($("#table_header").css("height"))-parseInt($("#div_footer").css("height"))-parseInt($("#div_footer2").css("height"))-60)+"px"

$("#content").css("height",sss)
    $(".div_menu_news").css("left", (parseInt($("body").css("width")) / 2 - 500) + "px");
    //$(".nav").css("margin-left", (parseInt($("body").css("width")) / 2 - 500) + "px");
    //$(".nav").css("margin-right", (parseInt($("body").css("width")) / 2 - parseInt($("#nav").css("width"))) + "px");

    $(".td_results").eq(20).css("display","none");
    $(".td_results").eq(21).css("display","none");
    
    if($(".td_results").size()<22){
      $(".pb").eq(1).css("display","none");  
    }
    
    if($("#span_pb").html()<10){
       $(".pb").eq(0).css("display","none"); 
    }
    
    if ($.trim($(".td_data").eq(2).html()) == '') {
        $(".td_header").eq(2).css("display", "none");
        $(".td_data").eq(2).css("display", "none");
    }

    if ($.trim($(".td_data").eq(0).html()) == '') {
        $(".td_header").eq(0).css("display", "none");
        $(".td_data").eq(0).css("display", "none");
    }

    if ($.trim($("#mini_slider").html()) == '') {

        $(".td_header").eq(1).css("display", "none");
        $(".td_data").eq(1).css("display", "none");
    }
    else {
        $('#mini_slider').ms({
        })
    }
    for (var i = 0; i < $(".table_menu_news").size(); i++) {
        if ($(".table_menu_news").eq(i).find(".td_menu_news").size() <= 0) {
            $(".table_menu_news").eq(i).find("tr").eq(0).find("td").eq(0).css("display", "none")
        }
        if ($(".table_menu_news").eq(i).find(".td_menu_news2").size() <= 0) {
            $(".table_menu_news").eq(i).find("tr").eq(0).find("td").eq(1).css("display", "none")
        }
        if ($(".table_menu_news").eq(i).find(".td_menu_news2").size() <= 0
                && $(".table_menu_news").eq(i).find(".td_menu_news").size() <= 0
                && $(".table_menu_news").eq(i).find(".td_menu_topics").size() <= 0) {
            $("#div_menu_news-" + i).css("width", "0px")
            $("#div_menu_news-" + i).css("height", "0px")
            $("#div_menu_news-" + i).css("opacity", "0")
        }
    }


    for (var i = 0; i < $(".div_topVideos").size(); i++) {
        //alert($("#img_topVideo-1").size())
        var pos = $("#img_topVideo-" + i).offset().top

        $("#img_topVideoIcon-" + i).css("top", pos + 90 + "px")


    }


    for (var i = 0; i < $(".div_topVideos").size(); i++) {
        //alert($("#img_topVideo-1").size())
        var pos = $("#img_topVideo-" + i).offset().top

        $("#img_topVideoIcon-" + i).css("top", pos + 90 + "px")


    }

    $("#ec").click(function() {

        $("#table_WebsiteTopNews").css("display", "block");
        $("#table_WebsiteNewestNews").css("display", "none");
        $("#ec").css("font-size", "18px");
        $("#tnn").css("font-size", "14px");
        $("#tnn").css("color", "#AFAFAF");
        $("#ec").css("color", "#4e5759");
        $("#tnn").css("margin-left", "10px");
        $("#ec").css("margin-left", "8px");

    })

    $("#tnn").click(function() {

        $("#table_WebsiteNewestNews").css("display", "block");
        $("#table_WebsiteTopNews").css("display", "none");
        $("#tnn").css("font-size", "18px");
        $("#ec").css("font-size", "14px");
        $("#tnn").css("color", "#4e5759");
        $("#ec").css("color", "#AFAFAF");
        $("#ec").css("margin-left", "10px");
        $("#tnn").css("margin-left", "8px");

    })
    $("#li_showMoreTopics").click(function() {
        //alert($("#td_moreTopics").css("display"))
        
        if ($("#td_moreTopics").css("display") == "block") {
            $("#td_moreTopics").css("display", "none")
            $("#li_showMoreTopics").css("color", "#4e5759")
            $("#li_showMoreTopics").css("background-color", "transparent")
        }
        else {
            $("#td_moreTopics").css("display", "block")
            $("#li_showMoreTopics").css("color", "#cccccc")
            $("#li_showMoreTopics").css("background-color", "#021A1A")
        }
        
    })


    $("#nav .li_f").hover(function() {
      
        $(".div_menu_news").css("display", "none")
        $(".div_menu_news").eq($(this).find("span").html()).css("display", "block")
    })

    $(".div_menu_news").hover(function() {
    ustunde=1
        $(".li_f").css("background-color", "transparent")
        $("#li_f-" + $(this).find("span").html()).css("background-color", "#021A1A")
     
    })
    
    $(".div_menu_news").mouseleave(function() {
        if(ustunde>0){
        $(".div_menu_news").css("display", "none")
        $(".li_f").css("background-color", "transparent")
        ustunde=0;
    }

    })


    $("#div_banner,#div_border").hover(function() {
        $(".div_menu_news").css("display", "none")
        $(".li_f").css("background-color", "transparent")
    })

    $("#div_context").hover(function() {
        $(".div_menu_news").css("display", "none")
        $(".li_f").css("background-color", "transparent")
    })

    $("#td_moreTopics").hover(function() {
        $(".div_menu_news").css("display", "none")
        $(".li_f").css("background-color", "transparent")
    })



    $(".inputSearch").focus(function() {
        if ($(this).val() == 'Search...')
            $(this).val("")
         $("#search table").css("margin-left","700px")
        
    })

    $(".inputSearch").focusout(function() {
        var a = $.trim($(this).val())
        if (a == '' || a == ' ')
            $(this).val("Search...")
         $("#search table").css("margin-left","800px")
    })

    $(".table_nn").hover(function() {
        $(".td_nn").css("display", "none")
        $(this).find(".td_nn").css("display", "block")

        for (var i = 0; i < $(".div_topVideos").size(); i++) {

            var pos = $("#img_topVideoIcon-" + i).offset().top

            $("#img_topVideoIcon-" + i).css("top", pos + 320 + "px")


        }

    }, function() {
        $(".td_nn").css("display", "none")
        for (var i = 0; i < $(".div_topVideos").size(); i++) {

            var pos = $("#img_topVideoIcon-" + i).offset().top

            $("#img_topVideoIcon-" + i).css("top", pos - 320 + "px")


        }

    })


})



