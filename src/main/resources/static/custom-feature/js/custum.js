$(document).ready(function() {

    if($(window).width() >= 768 && $(window).width() <= 1169 ){
        $(".logo-text").html("<br><br>");
    } else if($(window).width() <= 768 ){
        $(".logo-text").html("");
    }
    else{
        $(".logo-text").html("<b>DISCIPLE ONLINE</b>");
    }
});

$(window).resize(function() {

    if($(window).width() >= 768 && $(window).width() <= 1169 ){
        $(".logo-text").html("<br><br>");
    } else if($(window).width() <= 768 ){
        $(".logo-text").html("");
    }
    else{
        $(".logo-text").html("<b>DISCIPLE ONLINE</b>");
    }
});