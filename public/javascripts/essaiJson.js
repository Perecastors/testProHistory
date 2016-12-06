function importChampionsJson(){
    var choix = $("input[name='ligne']:checked");
    var truc="";
    $("input[name='ligne']").each(function() {
        if(this.checked){
            truc=truc.concat(this.value+"=true&");
        }
        else{
            truc=truc.concat(this.value+"=false&");
        }
    });
    $.ajax({
        url : "/getChampionsJson",
        dataType : 'json',
        data : truc,
        success : function(data,statut,sucess){
            afficherData(data);
        },

        error : function(resultat, statut, erreur){
            alert(statut);
        }
    });
};

function afficherData(data){
    var top ="";
    $.each(data[0],function(i,item){
        top=top.concat(data[0][i]+" ");
    });
    $("#top").text(top);
    var jungle ="";
    $.each(data[1],function(i,item){
        jungle=jungle.concat(data[1][i]+" ");
    });
    $("#jungle").text(jungle);
    var mid ="";
    $.each(data[2],function(i,item){
        mid=mid.concat(data[2][i]+" ");
    });
    $("#mid").text(mid);
    var adc ="";
    $.each(data[3],function(i,item){
        adc=adc.concat(data[3][i]+" ");
    });
    $("#adc").text(adc);
    var support ="";
    $.each(data[4],function(i,item){
        support=support.concat(data[4][i]+" ");
    });
    $("#support").text(support);
};