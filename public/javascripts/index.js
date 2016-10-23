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
