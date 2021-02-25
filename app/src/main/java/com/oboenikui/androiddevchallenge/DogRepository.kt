package com.oboenikui.androiddevchallenge

import com.oboenikui.androiddevchallenge.model.Dog
import com.oboenikui.androiddevchallenge.model.Sex
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class DogRepository {

    fun observeDogs(): Flow<List<Dog>> = MutableStateFlow(dogs)

    fun findDog(id: Int): Dog? {
        return dogs.find { it.id == id }
    }

    companion object {
        private val dogs = listOf(
            Dog(
                id = 1,
                ageByMonth = 2,
                breed = "Mixed",
                image = R.drawable.lhasa_mixed_puppy,
                sex = Sex.Male,
                detail = "Cute dog",
            ),
            Dog(
                id = 2,
                ageByMonth = 3,
                breed = "Bulldog",
                image = R.drawable.lhasa_mixed_puppy,
                sex = Sex.Female,
                detail = "This dog has two brothers",
            ),
            Dog(
                id = 3,
                ageByMonth = 1,
                breed = "Terrier",
                image = R.drawable.lhasa_mixed_puppy,
                sex = Sex.Male,
            ),
            Dog(
                id = 4,
                ageByMonth = null,
                breed = "Akita",
                image = R.drawable.lhasa_mixed_puppy,
                sex = Sex.Male
            ),
            Dog(
                id = 5,
                ageByMonth = 2,
                breed = "Hound",
                image = R.drawable.lhasa_mixed_puppy,
                sex = Sex.Male
            ),
            Dog(
                id = 6,
                ageByMonth = 4,
                breed = "Mixed",
                image = R.drawable.lhasa_mixed_puppy,
                sex = Sex.Male
            ),
            Dog(
                id = 7,
                ageByMonth = 2,
                breed = "Mixed",
                image = R.drawable.lhasa_mixed_puppy,
                sex = Sex.Male
            ),
            Dog(
                id = 8,
                ageByMonth = 5,
                breed = "Foxhound",
                image = R.drawable.lhasa_mixed_puppy,
                sex = Sex.Female
            ),
            Dog(
                id = 9,
                ageByMonth = 8,
                breed = "Mixed",
                image = R.drawable.lhasa_mixed_puppy,
                sex = Sex.Male
            ),
        )
    }
}