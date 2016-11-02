        //reset dropdown value
        $(".resetVal").click(function(event) {
        var idToReset = $(this).prev().attr("id");
        if(idToReset == undefined)
        {
            idToReset = $(this).next().attr("id");
        }
        $('#'+idToReset).val("");
        });

        //toggle the good tab after search
        if($(location).attr('pathname') == '/application/searchwithfilter'){
            $('#liAdvancedSearch').children().trigger('click');
        } else{
            $('#liSimpleSearch').children().trigger('click');
        }

        $(window).on('resize',function(){
           resizeTab();
       }).trigger('resize');

       $('#popup').click(function (event) {
           event.preventDefault();
           window.open($(this).attr("href"), "popupWindow", "width=540,height=510,scrollbars=yes,toolbar=no");
       });

       function resizeTab(){
            var sizeScreen = $(window).height();
            var headerSize = $('#header').height()+50;
            $('#tabSize').css('height',(sizeScreen-headerSize)+'px');
        }

