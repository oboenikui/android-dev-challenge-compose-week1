package com.oboenikui.androiddevchallenge.ui.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
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
import com.oboenikui.androiddevchallenge.DogRepository
import com.oboenikui.androiddevchallenge.R
import com.oboenikui.androiddevchallenge.model.Dog
import com.oboenikui.androiddevchallenge.model.Sex
import com.oboenikui.androiddevchallenge.ui.theme.MyTheme
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


class DogDetailViewModel : ViewModel() {

    private val repository = DogRepository()

    private val _dog = MutableStateFlow<Dog?>(null)
    val dog: StateFlow<Dog?>
        get() = _dog

    fun init(dogId: Int) {
        viewModelScope.launch {
            _dog.emit(repository.findDog(dogId))
        }
    }
}


@Composable
fun DogDetailScreen(
    navController: NavController,
    dogId: Int,
    viewModel: DogDetailViewModel = viewModel()
) {
    val dog by viewModel.dog.collectAsState()
    viewModel.init(dogId)
    Scaffold(
        topBar = {
            TopAppBar(
                title = {},
                navigationIcon = {
                    IconButton(
                        { navController.navigateUp() }
                    ) {
                        Icon(
                            painterResource(id = R.drawable.ic_baseline_arrow_back_24),
                            contentDescription = null,
                        )
                    }
                })
        }
    ) {
        DogDetail(dog = dog)
    }
}

@Composable
fun DogDetail(dog: Dog?) {
    if (dog == null) {
        Text("Dog not found")
    } else {
        Column(Modifier.verticalScroll(rememberScrollState())) {
            Image(
                painter = painterResource(id = dog.image),
                contentDescription = "${dog.breed} ${dog.sex} ${dog.ageByMonth} months old",
                modifier = Modifier.fillMaxWidth(),
                contentScale = ContentScale.FillWidth,
            )
            Box(
                contentAlignment = Alignment.CenterStart
            ) {
                Column(Modifier.padding(8.dp)) {
                    Text(text = "Dog breed: ${dog.breed}")
                    Text(text = "Sex: ${dog.sex}")
                    Text(text = dog.ageByMonth?.let { "Age: $it months old" }
                        ?: run { "Age: Unknown" })

                    Text(text = dog.detail?.let { "Detail: $it" }
                        ?: run { "Detail: Not provided" })
                }
            }
        }
    }
}


@Preview("Light Theme", widthDp = 360, heightDp = 640)
@Composable
fun LightPreview() {
    MyTheme {
        DogDetail(
            Dog(
                id = 1,
                ageByMonth = 2,
                breed = "mixed",
                image = R.drawable.lhasa_mixed_puppy,
                sex = Sex.Male,
            ),
        )
    }
}