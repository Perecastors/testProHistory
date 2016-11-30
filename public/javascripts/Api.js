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

    $.ajax({
       url : "http://www.omdbapi.com/?",
       dataType : 'json',
       data : 's='+titreFilm+'&plot=full'+'&type=movie',
       success : function(data, statut,sucess){
            console.log("data"+data);
            var listeFilm =[];
            $.each(data.Search,function(i,item){
                listeFilm.push(item.Title);
            });
            console.log(listeFilm);
            return listeFilm;
       },

       error : function(resultat, statut, erreur){
            alert(statut);
            return [];
       }

    });

//    var liste ='{"Search":[{"Title":"The Matrix","Year":"1999","imdbID":"tt0133093","Type":"movie","Poster":"https://images-na.ssl-images-amazon.com/images/M/MV5BMDMyMmQ5YzgtYWMxOC00OTU0LWIwZjEtZWUwYTY5MjVkZjhhXkEyXkFqcGdeQXVyNDYyMDk5MTU@._V1_SX300.jpg"},{"Title":"The Matrix Reloaded","Year":"2003","imdbID":"tt0234215","Type":"movie","Poster":"https://images-na.ssl-images-amazon.com/images/M/MV5BMjA0NDM5MDY2OF5BMl5BanBnXkFtZTcwNzg5OTEzMw@@._V1_SX300.jpg"},{"Title":"The Matrix Revolutions","Year":"2003","imdbID":"tt0242653","Type":"movie","Poster":"https://images-na.ssl-images-amazon.com/images/M/MV5BMTkyNjc4NTQzOV5BMl5BanBnXkFtZTcwNDYzMTQyMQ@@._V1_SX300.jpg"},{"Title":"The Matrix Revisited","Year":"2001","imdbID":"tt0295432","Type":"movie","Poster":"https://images-na.ssl-images-amazon.com/images/M/MV5BMTIzMTA4NDI4NF5BMl5BanBnXkFtZTYwNjg5Nzg4._V1_SX300.jpg"},{"Title":"Enter the Matrix","Year":"2003","imdbID":"tt0277828","Type":"game","Poster":"http://ia.media-imdb.com/images/M/MV5BMjA4NTYwNjk0M15BMl5BanBnXkFtZTgwODk3MDMwMTE@._V1_SX300.jpg"},{"Title":"The Matrix: Path of Neo","Year":"2005","imdbID":"tt0451118","Type":"game","Poster":"http://ia.media-imdb.com/images/M/MV5BYWM2ZWU5MDUtYTU2ZS00ZDFmLWIyNGEtNWZkMjRmZjI2YzMzXkEyXkFqcGdeQXVyMTA1OTEwNjE@._V1_SX300.jpg"},{"Title":"CR: Enter the Matrix","Year":"2009","imdbID":"tt1675286","Type":"game","Poster":"http://ia.media-imdb.com/images/M/MV5BMTExMzY3NTQ1NjleQTJeQWpwZ15BbWU3MDAyMjk2NzM@._V1_SX300.jpg"},{"Title":"Armitage III: Dual Matrix","Year":"2002","imdbID":"tt0303678","Type":"movie","Poster":"https://images-na.ssl-images-amazon.com/images/M/MV5BOTUwOTY3Mjg1MF5BMl5BanBnXkFtZTcwODI2MTAyMQ@@._V1_SX300.jpg"},{"Title":"Sex and the Matrix","Year":"2000","imdbID":"tt0274085","Type":"movie","Poster":"N/A"},{"Title":"Buhera mátrix","Year":"2007","imdbID":"tt0970173","Type":"movie","Poster":"N/A"}],"totalResults":"87","Response":"True"}';
//    var liste_object = JSON.parse(liste);

}

function test(){
    return ["ab","aab","bc"];
}

$( function() {
    $( "#tags" ).autocomplete({
        minLength: 3,
        source: importListeJson()
    });
  } );





/*$(function(){
   $("#demoJson").text("Résumé : "+json_object.Plot).append("<br/>");

   $.each(json_object.Titles,function(i,item){
   $("#demoJson").append(i+"-"+item.title+"<br/>");
   });
})*/
