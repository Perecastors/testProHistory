$( ".testBoutonJquery" ).click(function() {
    var texte = $('.testTexteJquery').val();
    $("#resultText").text(texte);
});