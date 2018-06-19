(function ($) {
    Detail=function(){
        var flag = '${detail}';
        if (flag) {
            $("form").disable();
        }
    },
    $.fn.disable = function () {
        return $(this).find("*").each(function () {
            $(this).attr("disabled", "disabled");
        });
    }
})(jQuery);