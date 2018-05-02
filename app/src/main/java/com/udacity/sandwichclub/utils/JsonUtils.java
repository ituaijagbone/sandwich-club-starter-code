package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JsonUtils {

    public static Optional<Sandwich> parseSandwichJson(String json) {
        if (json == null) {
            return Optional.empty();
        }

        try {
            JSONObject sandwichAsObject = new JSONObject(json);
            JSONObject nameAsJSONObject = sandwichAsObject.getJSONObject("name");
            String mainName = nameAsJSONObject.getString("mainName");
            JSONArray alsoKnownAsAsJSONArray = nameAsJSONObject.getJSONArray("alsoKnownAs");
            List<String> alsoKnownAs = new ArrayList<>();
            for (int index = 0; index < alsoKnownAsAsJSONArray.length(); index++) {
                String knownAs = alsoKnownAsAsJSONArray.getString(index);
                alsoKnownAs.add(knownAs);
            }
            String placeOfOrigin = sandwichAsObject.getString("placeOfOrigin");
            String description = sandwichAsObject.getString("description");
            String image = sandwichAsObject.getString("image");
            JSONArray ingredientsAsJSONArray = sandwichAsObject.getJSONArray("ingredients");
            List<String> ingredients = new ArrayList<>();
            for (int index = 0; index < ingredientsAsJSONArray.length(); index++) {
                String ingredient = ingredientsAsJSONArray.getString(index);
                ingredients.add(ingredient);
            }
            Sandwich sandwich = new Sandwich(mainName, alsoKnownAs, placeOfOrigin, description, image, ingredients);
            return Optional.of(sandwich);
        } catch (JSONException e) {
            return Optional.empty();
        }
    }
}
