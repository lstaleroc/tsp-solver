/**
 * Created by LuisSebastian on 5/22/17.
 */
function initMap() {
    var directionsService = new google.maps.DirectionsService;
    var directionsDisplay = new google.maps.DirectionsRenderer;
    var map = new google.maps.Map(document.getElementById('map'), {
        zoom: 15,
        center: {lat: 4.640081, lng:  -74.060613}
    });
    directionsDisplay.setMap(map);
    var marker = new google.maps.Marker({
        position: new google.maps.LatLng(4.640081,-74.060613)
    });
    marker.setMap(map);
    document.getElementById('submit').addEventListener('click', function() {
        calculateAndDisplayRoute(directionsService, directionsDisplay);
    });
}

function calculateAndDisplayRoute(directionsService, directionsDisplay) {
    var waypts = [];
    var checkboxArray = document.getElementById('waypoints');
    for (var i = 0; i < checkboxArray.length; i++) {
        if (checkboxArray.options[i].selected) {
            waypts.push({
                location: checkboxArray[i].value,
                stopover: true
            });
        }
    }

    directionsService.route({
        origin: document.getElementById('start').value,
        destination: document.getElementById('end').value,
        waypoints: waypts,
        optimizeWaypoints: true,
        travelMode: 'DRIVING'
    }, function(response, status) {
        if (status === 'OK') {
            directionsDisplay.setDirections(response);
            var route = response.routes[0];
            var summaryPanel = document.getElementById('directions-panel');
            summaryPanel.innerHTML = '';
            // For each route, display summary information.
            var totDist = 0;
            var totDur = 0;
            for (var i = 0; i < route.legs.length; i++) {
                var routeSegment = i + 1;
                summaryPanel.innerHTML += '<b>Ruta # ' + routeSegment +
                    '</b><br>';
                summaryPanel.innerHTML += route.legs[i].start_address + ' <b> ---> </b> ';
                summaryPanel.innerHTML += route.legs[i].end_address + '<br>';
                summaryPanel.innerHTML += '<b>Distancia: </b> '+ route.legs[i].distance.text + '<br>';
                totDist += route.legs[i].distance.value;
                summaryPanel.innerHTML += '<b>Tiempo aproximado: </b> '+ route.legs[i].duration.text + '<br><br>';
                totDur += route.legs[i].duration.value;
            }
            $("#tot-route-distance").empty();
            $("#tot-route-duration").empty();
            $("#tot-route-distance").append("<b>Distancia total ruta: </b> "+ (totDist/1000).toFixed(2).toString() + " kms");
            $("#tot-route-duration").append("<b>Duración total ruta: </b> " + (totDur/60).toFixed(2).toString() + " mins");
        } else {
            window.alert('Directions request failed due to ' + status);
        }
    });
}
