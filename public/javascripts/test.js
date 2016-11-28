$( ".testBoutonJquery" ).click(function() {
    var texte = $('.testTexteJquery').val();
    $(".testDroite").text("texte : "+texte);
});