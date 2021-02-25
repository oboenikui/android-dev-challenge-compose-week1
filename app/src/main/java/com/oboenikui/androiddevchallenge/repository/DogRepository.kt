/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.oboenikui.androiddevchallenge.repository

import com.oboenikui.androiddevchallenge.R
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
