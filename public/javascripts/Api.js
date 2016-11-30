function affichage(data){
    console.log(data);
    var json_object = data;
    if(json_object.Title == undefined){
        alert(json_object.Error);
        return ;
    }
    $(".affiche").html('<img src='+json_object.Poster+' alt="affiche"/>');
    $("#titre").html(json_object.Title);
    $("#note").html(" "+json_object.imdbRating);
    $("#realisateur").html("Réalisateur : "+json_object.Director);
    var date = new Date(json_object.Released)
    var month = ["Janvier", "Fevrier", "Mars", "Avril", "Mai", "Juin",
    "Juillet", "Août", "Septembre", "Octobre", "Novembre", "Decembre"][date.getMonth()];
    var dateSortie = date.getDay()+" "+month+" "+date.getFullYear();
    $("#dateSortie").html("Date de sortie : "+dateSortie);
    $("#langue").html(json_object.Language);
    $("#duree").html("Durée : "+json_object.Runtime);
    $("#description").html(json_object.Plot);
    var tableauActeurs = json_object.Actors.split(",");
    var listeActeurs ="";
    $.each(tableauActeurs,function(i,item){
       listeActeurs=listeActeurs.concat(item+",");
    });
    $("#acteurs").html(listeActeurs.slice(0,-1));
}

function importJson(){

    var titreFilm = $('#titreDuFilm').val();

    $.ajax({
       url : "http://www.omdbapi.com/?",
       dataType : 'json',
       data : 't='+titreFilm+'&plot=full'+'&type=movie',
       success : function(data, statut,sucess){
            affichage(data)
       },

       error : function(resultat, statut, erreur){
            alert(statut);
            console.log(erreur);
       }
    });
}

function importListeJson(){

     var titreFilm = $('#tags').val();
     var listeFilm =[];
     $.ajax({
       url : "http://www.omdbapi.com/?",
       dataType : 'json',
       async:false,
       data : 's='+titreFilm,
       success : function(data, statut,sucess){
            $.each(data.Search,function(i,item){
                listeFilm.push(item.Title);
            });
            console.log(listeFilm);
            return listeFilm;
       },
       error : function(resultat, statut, erreur){
            alert(statut);
       }
    });
}

$( function() {
    $( "#tags" ).autocomplete({
        minLength: 2,
        source: function(){return importListeJson()}
    });
  } );

function toto(){
    return [ "The Matrix", "The Matrix Reloaded", "The Matrix Revolutions", "The Matrix Revisited", "Enter the Matrix", "The Matrix: Path of Neo", "CR: Enter the Matrix", "Armitage III: Dual Matrix", "Sex and the Matrix", "Buhera mátrix" ];
}



/*$(function(){
   $("#demoJson").text("Résumé : "+json_object.Plot).append("<br/>");

   $.each(json_object.Titles,function(i,item){
   $("#demoJson").append(i+"-"+item.title+"<br/>");
   });
})*/
