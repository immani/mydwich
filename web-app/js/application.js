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

function displayproductdialog(url){
    $.ajax({
        url: url,
        cache: false,
        success: function(html){
            $("#proddialog").html(html).dialog({
                autoOpen: true,
                modal: true,
                title: 'Product',
                height: 500,
                width: 600,
                buttons: [{
                        text: "Ok",
                        click: function() { $(this).dialog("close"); }
                    }]
            });
        }
    })
}

function refreshbasket(){
    $.ajax({
        url: apppath + "user/renderbasketajax",
        cache: false,
        success: function(html){
            $("#nav").html(html)
        }
    })
}

function loadcatalog(restaurantid, options){
    options = options || {};
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

function checksearchquery(val){
    if(val.length>3){
        return true
    }
    else{
        return false;
    }
}

/*
 Displays a map for a specific company / restaurant / Delivery Address
 */
function displaymap(lat, lng, title, contentString, options){
    options = options || {};
    options.mapid = options.mapid || 'map_canvas'
    options.icon = options.icon || '/images/company.png';
    var latlng = new google.maps.LatLng(lat, lng);
    var myOptions = {
        zoom: 14,
        center: latlng,
        mapTypeId: google.maps.MapTypeId.ROADMAP
    };
    var map = new google.maps.Map(document.getElementById(options.mapid), myOptions);

    var infowindow = new google.maps.InfoWindow({
        content: contentString
    });

    var marker = new google.maps.Marker({
        position: latlng,
        map: map,
        title:title,
        icon: apppath + options.icon,
        animation: google.maps.Animation.DROP
    });
    infowindow.open(map,marker);

    google.maps.event.addListener(marker, 'click', function() {
        infowindow.open(map,marker);
    })
    return map
}

function displaydeliveryaddressonmap(lat, lng, options){
    options = options || {};
    options.map = options.map || undefined;
    options.mapid = options.mapid || 'map_canvas'
    options.zoom = options.zoom || 10
    $.getJSON(apppath + 'deliveryaddress/listasjson', function(data) {
        var latlng = new google.maps.LatLng(lat, lng );
        if (options.map == undefined){
            var myOptions = {
                zoom: 10,
                center: latlng,
                mapTypeId: google.maps.MapTypeId.ROADMAP
            };
            var map = new google.maps.Map(document.getElementById(options.mapid), myOptions);
        }
        $.each(data, function(i) {
            var contentString = '<h1 class="firstHeading">' + data[i].name + '</h1><p>' + data[i].address +', ' + data[i].zip + '</p>' + data[i].city + ', ' + data[i].country;
            var infowindow = new google.maps.InfoWindow({
                content: contentString
            });

            var latlng = new google.maps.LatLng(data[i].lat , data[i].lng );

            var marker = new google.maps.Marker({
                position: latlng,
                map: options.map,
                title: data[i].name,
                icon: apppath + '/images/deliveryaddress.png'
            });

            google.maps.event.addListener(marker, 'click', function() {
                infowindow.open(options.map,marker);
            })
        });
    })
    return map;
}

function displayrestaurantsonmap(lat, lng, options){
    options = options || {};
    options.mapid = options.mapid || 'map_canvas'
    options.zoom = options.zoom || 10
    $.getJSON(apppath + 'restaurant/listasjson', function(data) {
        var latlng = new google.maps.LatLng(lat, lng );
        var myOptions = {
            zoom: options.zoom,
            center: latlng,
            mapTypeId: google.maps.MapTypeId.ROADMAP
        };
        var map = new google.maps.Map(document.getElementById(options.mapid), myOptions);

        $.each(data, function(i) {
            var contentString = '<div><h1 class="firstHeading"><a href="' + apppath + 'showrestaurant/' + data[i].url +'">' + data[i].name + '</a></h1><p>' + data[i].address +', ' + data[i].zip + '</p>' + data[i].city + ', ' + data[i].country + '</div>'

            var infowindow = new google.maps.InfoWindow({
                content: contentString
            });

            var latlng = new google.maps.LatLng(data[i].lat , data[i].lng );

            var marker = new google.maps.Marker({
                position: latlng,
                map: map,
                title: data[i].name,
                icon: apppath + '/images/restaurant.png'
            });

            google.maps.event.addListener(marker, 'click', function() {
                infowindow.open(map,marker);
            })
        });
    })
}

function displayrestaurantsnear(lat, lng, options){
    options = options || {};
    options.map = options.map || undefined;
    options.mapid = options.mapid || 'map_canvas'
    options.zoom = options.zoom || 10
    if (options.map == undefined){
        var latlng = new google.maps.LatLng(lat, lng )
        var myOptions = {
            zoom: 14,
            center: latlng,
            mapTypeId: google.maps.MapTypeId.ROADMAP
        };
        var map = new google.maps.Map(document.getElementById(options.mapid), myOptions);
    }
    $.getJSON(apppath + 'company/retrieverestaurantswithinrange', function(data) {
        $.each(data, function(i) {
            var contentString = '<h1 class="firstHeading"><a href="' + apppath + 'showrestaurant/' + data[i].url +'">' + data[i].name + '</a></h1><p>' + data[i].address +', ' + data[i].zip + '</p>' + data[i].city + ', ' + data[i].country;
            var infowindow = new google.maps.InfoWindow({
                content: contentString
            });

            var latlng = new google.maps.LatLng(data[i].lat , data[i].lng );

            var marker = new google.maps.Marker({
                position: latlng,
                map: options.map,
                title: data[i].name,
                icon: apppath + '/images/restaurant.png'
            });

            google.maps.event.addListener(marker, 'click', function() {
                infowindow.open(options.map,marker);
            })
        });
    })
    return map;
}

function displaydeliveryaddressnear(lat, lng, options){
    options = options || {};
    options.map = options.map || undefined;
    options.mapid = options.mapid || 'map_canvas'
    options.zoom = options.zoom || 10
    if (options.map == undefined){
        var latlng = new google.maps.LatLng(lat, lng )
        var myOptions = {
            zoom: 14,
            center: latlng,
            mapTypeId: google.maps.MapTypeId.ROADMAP
        };
        var map = new google.maps.Map(document.getElementById(options.mapid), myOptions);
    }
    $.getJSON(apppath + 'restaurant/retrievedeliveryaddresswithinrange?json=true', function(data) {
        $.each(data, function(i) {
            var contentString = '<h1 class="firstHeading">' + data[i].name + '</h1><p>' + data[i].address +', ' + data[i].zip + '</p>' + data[i].city + ', ' + data[i].country;
            var infowindow = new google.maps.InfoWindow({
                content: contentString
            });

            var latlng = new google.maps.LatLng(data[i].lat , data[i].lng );

            var marker = new google.maps.Marker({
                position: latlng,
                map: options.map,
                title: data[i].name,
                icon: apppath + '/images/deliveryaddress.png'
            });

            google.maps.event.addListener(marker, 'click', function() {
                infowindow.open(options.map,marker);
            })
        });
    })
    return map;
}

$(document).ready(function() {
    $("#spinner").bind("ajaxSend", function() {
        $(this).fadeIn();
    }).bind("ajaxComplete", function() {
            $(this).fadeOut();
        }
    )})