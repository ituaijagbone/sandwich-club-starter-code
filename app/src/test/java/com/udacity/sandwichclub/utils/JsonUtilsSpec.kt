package com.udacity.sandwichclub.utils

import io.kotlintest.shouldBe
import org.apache.commons.io.IOUtils
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on

object JsonUtilsSpec: Spek({
    describe("JsonUtils") {
        on("parseSandwichJson(String)") {
            it("returns Optional of empty if argument is null") {
                val sandwich = JsonUtils.parseSandwichJson(null);
                sandwich.isPresent shouldBe false
            }

            it("returns Optional of empty if it can't parse JSON") {
                val sandwich = JsonUtils.parseSandwichJson("{}");
                sandwich.isPresent shouldBe false
            }

            it("returns Sandwich for valid json argument") {
                val sandwich = JsonUtils.parseSandwichJson(getFixture())
                sandwich.isPresent shouldBe true
                sandwich.get().mainName shouldBe "Ham and cheese sandwich"
                sandwich.get().placeOfOrigin shouldBe ""
                sandwich.get().description shouldBe "A ham and cheese sandwich is a common type of sandwich. It is made by putting cheese and sliced ham between two slices of bread. The bread is sometimes buttered and/or toasted. Vegetables like lettuce, tomato, onion or pickle slices can also be included. Various kinds of mustard and mayonnaise are also common."
                sandwich.get().image shouldBe "https://upload.wikimedia.org/wikipedia/commons/thumb/5/50/Grilled_ham_and_cheese_014.JPG/800px-Grilled_ham_and_cheese_014.JPG"
                sandwich.get().ingredients shouldBe listOf("Sliced bread", "Cheese", "Ham")
                sandwich.get().alsoKnownAs.isEmpty() shouldBe true
            }
        }
    }
})

private fun getFixture(): String {
    val inputStream = JsonUtilsSpec.javaClass.classLoader.getResourceAsStream("Sandwich.json")
    return IOUtils.toString(inputStream)
}