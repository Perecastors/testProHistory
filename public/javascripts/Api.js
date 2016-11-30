var json_flat = '{"Titles":[{"title":"Interstellar"},{"title":"truc"}],"Year":"2014","Rated":"PG-13","Released":"07 Nov 2014","Runtime":"169 min","Genre":"Adventure, Drama, Sci-Fi","Director":"Christopher Nolan","Writer":"Jonathan Nolan, Christopher Nolan","Actors":"Ellen Burstyn, Matthew McConaughey, Mackenzie Foy, John Lithgow","Plot":"A team of explorers travel through a wormhole in space in an attempt to ensure humanity\'s survival.","Language":"English","Country":"USA, UK","Awards":"Won 1 Oscar. Another 39 wins & 134 nominations.","Poster":"https://images-na.ssl-images-amazon.com/images/M/MV5BMjIxNTU4MzY4MF5BMl5BanBnXkFtZTgwMzM4ODI3MjE@._V1_SX300.jpg","Metascore":"74","imdbRating":"8.6","imdbVotes":"961,788","imdbID":"tt0816692","Type":"movie","Response":"True"}';
var json_object = JSON.parse(json_flat);

function affichage(){
    $(".affiche").html('<img src='+json_object.Poster+' alt="affiche"/>');
    $("#titre").html(json_object.Titles[0].title+" : ");
    $("#note").html(json_object.imdbRating);
    $("#realisateur").html("Réalisateur : "+json_object.Director);
    var date = new Date(json_object.Released)
    var month = ["Janvier", "Fevrier", "Mars", "Avril", "Mai", "Juin",
    "Juillet", "Août", "Septembre", "Octobre", "Novembre", "Decembre"][date.getMonth()];
    var dateSortie = date.getDay()+" "+month+" "+date.getFullYear();
    $("#dateSortie").html("Date de sortie : "+dateSortie);
    $("#duree").html("Durée : "+json_object.Runtime);
    $("#description").html("Synopsis : "+json_object.Plot);
    var tableauActeurs = json_object.Actors.split(",");
    var listeActeurs ="";
    $.each(tableauActeurs,function(i,item){
       listeActeurs=listeActeurs.concat(item+",");
    });

    $("#acteurs").html(listeActeurs.slice(0,-1));

}

/*$(function(){
   $("#demoJson").text("Résumé : "+json_object.Plot).append("<br/>");

   $.each(json_object.Titles,function(i,item){
   $("#demoJson").append(i+"-"+item.title+"<br/>");
   });
})*/
