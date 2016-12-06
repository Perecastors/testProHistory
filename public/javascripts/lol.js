/**
 * Created by Formation on 26/10/2016.
 */

$(document).ready(function() {

$( "button.toggleClassement" ).click(function() {
    $( ".toggleClassement" ).toggle();
});

$( "button.toggleYourChampionPool" ).click(function() {
    $( "div.toggleYourChampionPool" ).show();
    $( "div.toggleAdverseChampionPool" ).hide();
});

$( "button.toggleAdverseChampionPool" ).click(function() {
    $( "div.toggleAdverseChampionPool" ).show();
    $( "div.toggleYourChampionPool" ).hide();
});

});

function changementAffichage(){

    if($("#ligne1").text()=="Top"){
        $("#ligne1").html("<strong>Jungle</strong>");
        $("#ligne2").html("<strong>Top</strong>");
        var ordre ="ordre1=2&ordre2=1&ordre3=3&ordre4=4&ordre5=5";
    }
    else if($("#ligne1").text()=="Jungle"){
        $("#ligne1").html("<strong>Top</strong>");
        $("#ligne2").html("<strong>Jungle</strong>");
        var ordre ="ordre1=1&ordre2=2&ordre3=3&ordre4=4&ordre5=5";
    }
    $.ajax({
        url : "/afficherOrdre",
        dataType : 'json',
        data : ordre,
        success : function(data,statut,sucess){
            var top="";
            var jungle="";
            var mid="";
            var adc="";
            var support="";
            $.each(data,function(i,item){
                if(i<40){
                    top=top.concat('<div class="ligne"><img class="ligne" src="http://ddragon.leagueoflegends.com/cdn/6.20.1/img/champion/'+item.championTop.nom+'.png" title='+item.championTop.nom+' "border="4"></div>')
                    jungle=jungle.concat('<div class="ligne"><img class="ligne" src="http://ddragon.leagueoflegends.com/cdn/6.20.1/img/champion/'+item.championJungle.nom+'.png" title='+item.championJungle.nom+' border="4"></div>')
                    mid=mid.concat('<div class="ligne"><img class="ligne" src="http://ddragon.leagueoflegends.com/cdn/6.20.1/img/champion/'+item.championMid.nom+'.png" title='+item.championMid.nom+' border="4"></div>')
                    adc=adc.concat('<div class="ligne"><img class="ligne" src="http://ddragon.leagueoflegends.com/cdn/6.20.1/img/champion/'+item.championAdc.nom+'.png" title='+item.championAdc.nom+' border="4"></div>')
                    support=support.concat('<div class="ligne"><img class="ligne" src="http://ddragon.leagueoflegends.com/cdn/6.20.1/img/champion/'+item.championSupport.nom+'.png" title='+item.championSupport.nom+' border="4"></div>')
                }
            });
            $("#col1").html(top);
            $("#col2").html(jungle);
            $("#col3").html(mid);
            $("#col4").html(adc);
            $("#col5").html(support);
            $("#col1a").html(top);
            $("#col2a").html(jungle);
            $("#col3a").html(mid);
            $("#col4a").html(adc);
            $("#col5a").html(support);
        },

        error : function(resultat, statut, erreur){
            alert(statut);
        }
    });

};

//$(function() {
//
//    $("select").click(function(){
//        $( "select option:selected").each(function(){
//            if($(this).attr("value")=="Aatrox"){
//                $("img").hide();
//                $( "img#1" ).show();}
//            if($(this).attr("value")=="Ahri"){
//                $("img").hide();
//                $( "img#2" ).show();}
//        });
//    }).change();
//
//    $("img.cachee").hide();
//});

