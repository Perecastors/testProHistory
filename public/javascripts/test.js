function traduitHtml(){
        var texte = $('.testTexteJquery').val();
        $("#resultText").html(texte);
};

function afficheHtml(){
        var texte = $('#resultText').html();
        $("#resultText2").val(texte);
};

function afficheTt(){
        var texte = $('#valSelect option:selected').text();
        $("#resultText3").text(texte);
        var texte = $('#valSelect').val();
        $("#resultText4").text(texte);
};

function afficheTutu(){
        $('#valSelect').val('u');
};

function afficheDouble(){
        var choix = $("input[name='tt']:checked").val();
        $("input[name='tt2'][value="+choix+"]").prop("checked",true);
};

function clickAuto(){
    $(".boutonAuto" ).trigger( "click" );
};

function compteFils(){
    var fils = $( ".testDroite" ).find("*").length;
    $( "span.result" ).text( "There are " + fils + " fils.";
};