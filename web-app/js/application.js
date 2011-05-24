function info(msg){
    $('#info').html(msg).show('slide', 'slow').delay(2000).hide('slide', 'slow')
    // $( "#effect" ).show( selectedEffect, options, 500, callback );
}

function displayproductbuydialog(url){
    $.ajax({
                url: url,
                cache: false,
                success: function(html){
                    $("#proddialog").html(html).dialog({
                                autoOpen: true,
                                modal: true,
                                title: 'Select Product Options',
                                height: 800,
                                width: 600
                            });
                }
            })
}

function refreshbasket(){
    $.ajax({
                url: apppath + "basket/renderbasketajax",
                cache: false,
                success: function(html){
                    $("#nav").html(html)
                }
            })
}

function closeproductbuydialog(response){
    if(response == "productadded"){
        $("#proddialog").dialog( "close" );
        info("The product was added succesfully to your basket")
        refreshbasket()
    }
}
function displaymap(lat, lng, title, contentString){
    var latlng = new google.maps.LatLng(lat, lng);
    var myOptions = {
        zoom: 14,
        center: latlng,
        mapTypeId: google.maps.MapTypeId.ROADMAP
    };
    var map = new google.maps.Map(document.getElementById("map_canvas"), myOptions);

    var infowindow = new google.maps.InfoWindow({
                content: contentString
            });

    var marker = new google.maps.Marker({
                position: latlng,
                map: map,
                title:title,
                icon: apppath + '/images/beachflag.png',
                animation: google.maps.Animation.DROP
            });

    google.maps.event.addListener(marker, 'click', function() {
        infowindow.open(map,marker);
    })
    return map
}
function displayrestaurantsonmap(){
    $.getJSON(apppath + 'restaurant/listasjson', function(data) {
        var latlng = new google.maps.LatLng(50.82440, 4.38560 );
        var myOptions = {
            zoom: 10,
            center: latlng,
            mapTypeId: google.maps.MapTypeId.ROADMAP
        };
        var map = new google.maps.Map(document.getElementById("map_canvas"), myOptions);

        $.each(data, function(i) {
            var contentString = '<div><h1 class="firstHeading">' + data[i].name + '</h1><p>' + data[i].address +', ' + data[i].zip + '</p>' + data[i].city + ', ' + data[i].country + '</div>'

            var infowindow = new google.maps.InfoWindow({
                        content: contentString
                    });

            var latlng = new google.maps.LatLng(data[i].lat , data[i].lng );

            var marker = new google.maps.Marker({
                        position: latlng,
                        map: map,
                        title: data[i].name
                    });

            google.maps.event.addListener(marker, 'click', function() {
                infowindow.open(map,marker);
            })
        });

    })
}

function displayrestaurantsnearcompany(map){
    if (map == undefined){
        var map = new google.maps.Map(document.getElementById("map_canvas"));
    }
    $.getJSON(apppath + 'company/retrieverestaurantswithinrange', function(data) {
        $.each(data, function(i) {
            var contentString = '<div><h1 class="firstHeading">' + data[i].name + '</h1><p>' + data[i].address +', ' + data[i].zip + '</p>' + data[i].city + ', ' + data[i].country + '</div>'

            var infowindow = new google.maps.InfoWindow({
                        content: contentString
                    });

            var latlng = new google.maps.LatLng(data[i].lat , data[i].lng );

            var marker = new google.maps.Marker({
                        position: latlng,
                        map: map,
                        title: data[i].name
                    });

            google.maps.event.addListener(marker, 'click', function() {
                infowindow.open(map,marker);
            })
        });
    })
}

