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
    var fils = $( ".testDroite" ).children();
    $( "span.result" ).text( "Il y a " + fils.length + " fils dans la div 'testDroite'");
    $( ".result2" ).text("");
    fils.each(function() {
        var nom = $(this).prop("tagName");
        $( ".result2" ).append("<li>"+nom+"</li>");
    });
};

jQuery('#datetimepicker').datetimepicker({
 i18n:{
  de:{
   months:[
    'Januar','Februar','März','April',
    'Mai','Juni','Juli','August',
    'September','Oktober','November','Dezember',
   ],
   dayOfWeek:[
    "So.", "Mo", "Di", "Mi",
    "Do", "Fr", "Sa.",
   ]
  }
 },
 timepicker:false,
 formatDate:'d.m.Y'
});