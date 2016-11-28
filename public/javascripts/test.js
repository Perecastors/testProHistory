$( ".testBoutonJquery" ).click(function() {
    var texte = $('.testTexteJquery').val();
    $(".testDroite").append(texte);
});