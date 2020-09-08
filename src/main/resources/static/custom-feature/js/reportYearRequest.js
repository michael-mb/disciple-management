$(".drop").change(function () {
    var end = this.value;
    var pageURL = $(location).attr("href");
    pageURL = pageURL.split('?')[0];
    window.location = pageURL+"?year="+end;
});