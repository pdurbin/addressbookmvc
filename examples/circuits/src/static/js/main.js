$(document).ready(function() {
    "use strict";

    $("#menu").metisMenu();

    $(window).bind("load resize", function() {
        var width = (this.window.innerWidth > 0) ? this.window.innerWidth : this.screen.width;

        if (width < 768) {
            $("div.sidebar-collapse").addClass("collapse")
        } else {
            $("div.sidebar-collapse").removeClass("collapse")
        }
    });
});
