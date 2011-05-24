package com.immani.mydwich

import grails.converters.JSON

class GeocoderService {
    static transactional = false
    def messageSource

    def geocode(String address, String zip, String city, String country) throws Exception {
        def base = "http://maps.googleapis.com/maps/api/geocode/json?address="
        def qs = []
        qs <<  URLEncoder.encode(address + ", " + zip + " "+ city + ", " + country)
        qs << "sensor=false"
        def url = new URL(base + qs.join("&"))
        def connection = url.openConnection()
        def jsonresp
        def result = [:]
        if(connection.responseCode == 200){
            jsonresp = JSON.parse(connection.content.text)
            String status = jsonresp.status
            result.status = status

            switch (status) {
                case "OK":
                    /*
                    def test = jsonresp.results.size()
                    def test2 = jsonresp.results
                    def test3 = jsonresp.results[0].partial_match
                    */
                    if (jsonresp.results.size()>1){
                        throw new Exception(messageSource.getMessage('geo.address.multiple.match', null, null))
                    }
                    else if (jsonresp.results[0].partial_match){
                        //TODO: Check what we do, too many problems
                        //throw new Exception(messageSource.getMessage('geo.address.partialmatch', null, null))
                    }
                    result.lat = jsonresp.results.geometry.location.lat[0].toFloat()
                    result.lng = jsonresp.results.geometry.location.lng[0].toFloat()
                    break
                case "ZERO_RESULTS": //indicates that the geocode was successful but returned no results. This may occur if the geocode was passed a non-existent address or a latlng in a remote location.
                    //result.message = messageSource.getMessage('geo.address.not.found')
                    throw new Exception(messageSource.getMessage('geo.address.not.found', null, null))
                    break
                case "OVER_QUERY_LIMIT": //indicates that you are over your quota.
                    result.message = "indicates that you are over your quota"
                    break
                case "REQUEST_DENIED": //indicates that your request was denied, generally because of lack of a sensor parameter.
                    result.message = "indicates that your request was denied, generally because of lack of a sensor parameter"
                    break
                case "INVALID_REQUEST": // generally indicates that the query (address or latlng) is missing.
                    result.message = "nerally indicates that the query (address or latlng) is missing."
                    break
            }
        }
        else{
            result.lat = ""
            result.lng = ""
            log.error("GeocoderService.geocodeAirport FAILED")
            log.error(url)
            log.error(connection.responseCode)
            log.error(connection.responseMessage)
        }
        return result
    }
    def geocodeXML(String address) {
        // http://ws.geonames.org/search?name_equals=den&fcode=airp&style=full
        def base = "http://ws.geonames.org/search?"
     //   def base = "http://maps.googleapis.com/maps/api/geocode/jason?address="
        def qs = []
        qs << "name_equals=" + URLEncoder.encode(address)
      //  qs << "fcode=airp"
        qs << "style=full"
        def url = new URL(base + qs.join("&"))
        def connection = url.openConnection()

        def result = [:]
        if(connection.responseCode == 200){
            def xml = connection.content.text
            def geonames = new XmlSlurper().parseText(xml)
            result.name = geonames.geoname.name as String
            result.lat = geonames.geoname.lat as String
            result.lng = geonames.geoname.lng as String
            result.state = geonames.geoname.adminCode1 as String
            result.country = geonames.geoname.countryCode as String
        }
        else{
            log.error("GeocoderService.geocodeAirport FAILED")
            log.error(url)
            log.error(connection.responseCode)
            log.error(connection.responseMessage)
        }
        return result
    }
}
