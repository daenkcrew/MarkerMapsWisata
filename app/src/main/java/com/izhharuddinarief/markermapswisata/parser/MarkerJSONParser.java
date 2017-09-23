package com.izhharuddinarief.markermapswisata.parser;

/**
 * Created by IzhharuddinArief on 24/09/2017.
 */


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class MarkerJSONParser {

    private String lat = "";
    private String lng = "";


    /* Receives a JSONObject and returns a list */
    public List<HashMap<String, String>> parse(JSONObject jObject) {

        JSONArray jMarkers = null;
        try {
            /* Retrieves all the elements in the 'markers' array */
            jMarkers = jObject.getJSONArray("wisata");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        /* Invoking getMarkers with the array of json object
         * where each json object represent a marker
         */
        return getMarkers(jMarkers);
    }

    private List<HashMap<String, String>> getMarkers(JSONArray jMarkers) {
        int markersCount = jMarkers.length();
        List<HashMap<String, String>> markersList = new ArrayList<>();
        HashMap<String, String> marker;

        /* Taking each marker, parses and adds to list object */
        for (int i = 0; i < markersCount; i++) {
            try {
                /* Call getMarker with marker JSON object to parse the marker */
                // markersList.add(getMarker((JSONObject) jMarkers.get(i)));
                marker = getMarker((JSONObject)jMarkers.get(i));
                markersList.add(marker);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        return markersList;
    }

    /* Parsing the Marker JSON object */
    private HashMap<String, String> getMarker(JSONObject jMarker) {

        HashMap<String, String> marker = new HashMap<>();
        try {

            String nama = jMarker.getString("nama");

            // Extracting latitude, if available
            if (!jMarker.isNull("lat")) {
                lat = jMarker.getString("lat");
            }

            // Extracting latitude, if available
            if (!jMarker.isNull("lng")) {
                lng = jMarker.getString("lng");
            }

            marker.put("nama", nama);
            marker.put("lat", this.lat);
            marker.put("lng", this.lng);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return marker;
    }
}