$( ".testBoutonJquery" ).click(function() {
    var texte = $('.testTexteJquery').val();
    $("#resultText").html(texte);
});

$( ".testBoutonJquery2" ).click(function() {
    var texte = $('#resultText').val();
    $("#resultText2").html("test"+texte);
});