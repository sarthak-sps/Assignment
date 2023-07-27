package com.example.assignment.view


import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.assignment.R
import com.example.assignment.Screen
import com.example.assignment.model.Result
import com.example.assignment.viewmodel.EventViewModel
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter
import java.util.*

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Events(navController: NavController, vm: EventViewModel) {
    /* LaunchedEffect(key1 = Unit, block = {


     })*/

    remember {
        vm.getEventsList()
        vm.getPreferenceList()
    }

    Scaffold(
        backgroundColor = Color(0XFFF1F6FA),
        topBar = {
            TopAppBar(
                title = {
                    Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxWidth()) {
                        Text(
                            " Events",
                            color = Color.White,
                            textAlign = TextAlign.Center
                        )
                    }
                },
                backgroundColor = Color(0xFF1977C2),
                actions = {
                    Image(
                        painter = painterResource(id = R.drawable.filter_img),
                        contentDescription = "filter",
                        modifier = Modifier
                            .height(90.dp)
                            .clickable {
                                navController.navigate(Screen.FilterScreen.route)

                            }
                            .padding(end = 5.dp),
                    )
                },
                elevation = 0.dp,

                )
        },
        content = { padding ->
            LazyColumn(modifier = Modifier.padding(16.dp), content = {
                vm.eventsData.value.data?.let { it ->
                    items(it.result) {
                        EventListItem(eventsList = it)
                        Spacer(modifier = Modifier.height(8.dp))
                    }
                }
            })
        }, modifier = Modifier.background(Color(0XFFF1F6FA))
    )
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun EventListItem(eventsList: Result) {
    /*code for date formatting*/
    var dateString = eventsList.startDate
    var endDate = eventsList.endDate
    var odt = OffsetDateTime.parse(dateString);
    var dtf = DateTimeFormatter.ofPattern("MMM dd-", Locale.ENGLISH)
    var finalStartDate = dtf.format(odt)
    var endOdt = OffsetDateTime.parse(endDate)
    var formatEndDate = DateTimeFormatter.ofPattern("MMM dd, YYYY", Locale.ENGLISH)
    var finalEndDate = formatEndDate.format(endOdt)

    var mediaBaseURL = "https://ballerapp.s3.us-east-2.amazonaws.com/"



    Row(
        modifier = Modifier
            .background(Color.White, shape = RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp))
            .fillMaxWidth()
            .padding(12.dp)
    ) {
        /*Image box*/
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(8.dp))
                .size(64.dp)
        ) {

            AsyncImage(
                model = mediaBaseURL + eventsList.logo,
                contentDescription = "",
                contentScale = ContentScale.Crop
            )
        }
        Spacer(modifier = Modifier.width(10.dp))
        Column(
            verticalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.fillMaxSize()
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = eventsList.name,
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp,

                    )
                Box(
                    modifier = Modifier
//                        .width(if (eventsList.eventType == "League") 80.dp else 100.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(
                            if (eventsList.eventType == "League") Color(0XFFFB933C) else Color(
                                0XFF32CC71
                            )
                        )
                        .padding(horizontal = 8.dp, vertical = 4.dp),
                    contentAlignment = Alignment.Center


                ) {
                    Text(text = eventsList.eventType, color = Color.White, fontSize = 10.sp)
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = eventsList.address,
                    fontSize = 11.sp,
                    maxLines = 1,
                    fontWeight = FontWeight.Bold
                )
            }
            Spacer(modifier = Modifier.height(6.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Row {
                    Text(text = finalStartDate, fontSize = 11.sp, color = Color(0XFF8A9BA8))
                    Text(text = finalEndDate, fontSize = 11.sp, color = Color(0XFF8A9BA8))
                }

                Text(
                    text = "$" + eventsList.standardPrice.toString(),
                    fontSize = 12.sp,
                    textAlign = TextAlign.End,
                    color = Color.LightGray
                )

            }

        }

    }
}

