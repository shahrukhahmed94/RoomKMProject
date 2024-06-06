import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import database.PeopleDao
import database.Person
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

import roomkmproject.composeapp.generated.resources.Res
import roomkmproject.composeapp.generated.resources.compose_multiplatform

@Composable
@Preview
fun App(peopleDao: PeopleDao) {


    val people by peopleDao.getAllPeople().collectAsState(initial = emptyList())
    val scope = rememberCoroutineScope()

    LaunchedEffect(true) {

        val peopleList = listOf(
           Person(name = "Shahrukh"),
            Person(name = "Shahid"),
            Person(name = "Shahid")
        )

        peopleList.forEach {
            peopleDao.upsert(it)
        }


    }

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp)
    ){

        items(people) {person ->

            Text(
                text = person.name,
                modifier = Modifier.fillMaxWidth()
                    .clickable {
                        scope.launch {
                            peopleDao.delete(person)
                        }
                    }


            )

        }

    } //LazyColumn





}