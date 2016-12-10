$(document).ready(function() {


    $('#news_slider_1').cf_newsslider({
    })
    for (var i = 0; i < $("#table_topics .table_topics_tr").size(); i = i + 2) {

        $("#table_topics .table_topics_tr").eq(i).append($("#table_topics .table_topics_tr").eq(i + 1).html());
        $("#table_topics .table_topics_tr").eq(i + 1).html(" ")
        $("#table_topics .table_topics_tr").eq(i + 1).css("display", "none")
    }

    for (var i = 0; i < $(".table_topic").size(); i = i + 2) {

        for (var j = 0; j <= 6; j++) {
            var max = 0
            var w1 = parseInt($(".table_topic").eq(i).find("td").eq(j).css("height"))
            var w2 = parseInt($(".table_topic").eq(i + 1).find("td").eq(j).css("height"))
            if (w1 > w2) {
                $(".table_topic").eq(i + 1).find("td").eq(j).css("height", w1 + "px")

            }
            if (w1 < w2) {
                $(".table_topic").eq(i).find("td").eq(j).css("height", w2 + "px")
            }


        }


    }
})



