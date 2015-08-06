package com.jpmc.locator;

     import java.util.HashMap;

     import org.json.JSONException;

     import org.json.JSONObject;

public class JSONParserPlaceDetails {

    /** Receives a JSONObject and returns a list */
    public HashMap<String,String> parse(JSONObject jObject){

        JSONObject jPlaceDetails = null;
        try {
            /** Retrieves all the elements in the 'places' array */
            jPlaceDetails = jObject.getJSONObject("result");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        /** Invoking getPlaces with the array of json object
         * where each json object represent a place
         */
        return getPlaceDetails(jPlaceDetails);
    }

    /** Parsing the Place Details Object */
    private HashMap<String, String> getPlaceDetails(JSONObject jPlaceDetails){

        HashMap<String, String> hPlaceDetails = new HashMap<String, String>();

        String name = "-NA-";
        String icon = "-NA-";
        String vicinity="-NA-";
        String latitude="";
        String longitude="";
        String formatted_address="-NA-";


        try {

            // Extracting Place name, if available
            if(!jPlaceDetails.isNull("name")){
                name = jPlaceDetails.getString("name");
            }

            // Extracting Icon, if available
            if(!jPlaceDetails.isNull("icon")){
                icon = jPlaceDetails.getString("icon");
            }

            // Extracting Place Vicinity, if available
            if(!jPlaceDetails.isNull("vicinity")){
                vicinity = jPlaceDetails.getString("vicinity");
            }

            // Extracting Place formatted_address, if available
            if(!jPlaceDetails.isNull("formatted_address")){
                formatted_address = jPlaceDetails.getString("formatted_address");
            }


            latitude = jPlaceDetails.getJSONObject("geometry").getJSONObject("location").getString("lat");
            longitude = jPlaceDetails.getJSONObject("geometry").getJSONObject("location").getString("lng");

            hPlaceDetails.put("name", name);
            hPlaceDetails.put("icon", icon);
            hPlaceDetails.put("vicinity", vicinity);
            hPlaceDetails.put("lat", latitude);
            hPlaceDetails.put("lng", longitude);
            hPlaceDetails.put("formatted_address", formatted_address);


        } catch (JSONException e) {
            e.printStackTrace();
        }
        return hPlaceDetails;
    }
}