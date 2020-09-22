$(document).ready(function() {
    setupScreenWidth();
    setupUsersSearchFuntion();
    scrollTopFunction();
});

$(window).resize(function() {
    setupScreenWidth();
});

function setupScreenWidth(){
    if($(window).width() >= 768 && $(window).width() <= 1169 ){
        $(".logo-text").html("<br><br>");
    } else if($(window).width() <= 768 ){
        $(".logo-text").html("");
    }
    else{
        $(".logo-text").html("<b>DISCIPLE ONLINE</b>");
    }
}

function setupUsersSearchFuntion(){
    let input = $('#searchUsers');
    input.on('input', function (){
        let inputText = input.val();
        if(inputText.isEmpty())
            inputText = "empty"

        let url = 'users/search/'+inputText;
        $.ajax({
                type	: 'GET',
                cache	: false,
                url		: url
            },
            setTimeout(function(){ $('#users-box').load(url+' #users-box')
            })
        );
    })
}

function scrollTopFunction(){
    $(window).scroll(function () {
        if ($(this).scrollTop() > 100) {
            $('.scroll-top').fadeIn();
        } else {
            $('.scroll-top').fadeOut();
        }
    });

    $('.scroll-top').click(function () {
        $("html, body").animate({
            scrollTop: 0
        }, 100);
        return false;
    });
}
String.prototype.isEmpty = function() {
    return (this.length === 0 || !this.trim());
};