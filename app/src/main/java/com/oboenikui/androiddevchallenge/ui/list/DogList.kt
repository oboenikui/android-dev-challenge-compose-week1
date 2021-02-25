package com.oboenikui.androiddevchallenge.ui.list

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.navigate
import androidx.navigation.compose.rememberNavController
import com.oboenikui.androiddevchallenge.DogRepository
import com.oboenikui.androiddevchallenge.R
import com.oboenikui.androiddevchallenge.Screens
import com.oboenikui.androiddevchallenge.model.Dog
import com.oboenikui.androiddevchallenge.model.Sex
import com.oboenikui.androiddevchallenge.ui.theme.MyTheme
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class DogListViewModel : ViewModel() {
    private val _dogs = MutableStateFlow<List<Dog>>(emptyList())
    val dogs: StateFlow<List<Dog>>
        get() = _dogs

    private val repository = DogRepository()

    init {
        repository.observeDogs()
            .onEach { _dogs.emit(it) }
            .launchIn(viewModelScope)
    }
}


@Composable
fun DogListScreen(navController: NavController, viewModel: DogListViewModel = viewModel()) {
    val dogs by viewModel.dogs.collectAsState()
    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Puppy adoption") })
        }
    ) {
        DogList(navController, dogs = dogs)
    }
}

@Composable
fun DogList(navController: NavController, dogs: List<Dog>) {

    Column(Modifier.verticalScroll(rememberScrollState())) {
        repeat(dogs.size) { index ->
            Box(
                Modifier
                    .padding(8.dp)
            ) {
                DogItem(navController, dog = dogs[index])
            }
        }
    }
}

@Composable
fun DogItem(navController: NavController, dog: Dog) {
    Card(
        Modifier
            .fillMaxWidth()
            .clickable { navController.navigate(Screens.Detail.routeOf(dog.id)) }
    ) {
        Row {
            Image(
                painter = painterResource(id = dog.image),
                contentDescription = "${dog.breed} ${dog.sex} ${dog.ageByMonth} months old",
                modifier = Modifier
                    .size(88.dp),
                contentScale = ContentScale.Crop,
            )
            Box(
                contentAlignment = Alignment.CenterStart
            ) {
                Column(Modifier.padding(8.dp)) {
                    Text(text = "Dog breed: ${dog.breed}")
                    Text(text = "Sex: ${dog.sex}")
                    Text(text = dog.ageByMonth?.let { "Age: $it months old" }
                        ?: run { "Age: Unknown" })
                }
            }
        }
    }
}

@Preview("Light Theme", widthDp = 360, heightDp = 640)
@Composable
fun LightPreview() {
    MyTheme {
        DogList(
            rememberNavController(),
            listOf(
                Dog(
                    id = 1,
                    ageByMonth = 2,
                    breed = "mixed",
                    image = R.drawable.lhasa_mixed_puppy,
                    sex = Sex.Male,
                ),
            ),
        )
    }
}