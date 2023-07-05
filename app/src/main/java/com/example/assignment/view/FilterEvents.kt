package com.example.assignment.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Switch
import androidx.compose.material.SwitchDefaults
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.assignment.R
import com.example.assignment.Screen
import com.example.assignment.viewmodel.EventViewModel


@Composable
fun FilterEvents(navController: NavController, vm: EventViewModel) {

    /*remember {
        vm.getPreferenceList()

    }*/

    Scaffold(
        backgroundColor = Color(0XFFF1F6FA),
        topBar = {
            TopAppBar(
                title = {
                    Box(modifier = Modifier.fillMaxWidth()) {
                        Icon(painter = painterResource(id = R.drawable.outline_arrow_back_ios_24),
                            "arrowBack",
                            modifier = Modifier
                                .clickable {
                                    navController.navigate(Screen.EventScreen.route)
                                }
                                .align(Alignment.TopStart),
                            tint = Color.White)

                        Text(
                            "Filter Events",
                            color = Color.White,
                            modifier = Modifier.align(Alignment.Center)
                        )

                    }
                },
                backgroundColor = Color(0xFF1977C2),
            )
        }, content = { padding ->

            LazyColumn(modifier = Modifier.padding(16.dp)) {
                vm.filteredPreferenceData.forEach { (key, values) ->

                    item {
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = key.capitalize(),
                            fontWeight = FontWeight.Bold,
                            fontSize = 14.sp
                        )
                        Box(modifier = Modifier.height(10.dp)) {

                        }
                        if (values.size == 1) {
                            CommonRow(
                                title = values[0].name.capitalize(),
                                vm,
                                vm.convertIntToBoolean(values[0].status),
                                values[0].key,top=10,bottom=10
                            )
                            Divider(color = Color(0XFFF1F6FA))
                        } else {
                            values.forEachIndexed { index, data ->
                                if (index == 0 || index == values.lastIndex) {
                                    if (index == 0) {
                                        CommonRow(
                                            title = data.name.capitalize(),
                                            vm,
                                            vm.convertIntToBoolean(data.status),
                                            data.key,
                                            top = 10, bottom = 0
                                        )
                                        Divider(color = Color(0XFFF1F6FA))
                                    } else {
                                        CommonRow(
                                            title = data.name.capitalize(),
                                            vm,
                                            vm.convertIntToBoolean(data.status),
                                            data.key,
                                            top=0, bottom = 10
                                        )
                                        Divider(color = Color(0XFFF1F6FA))
                                    }

                                } else {
                                    CommonRow(
                                        title = data.name.capitalize(),
                                        vm,
                                        vm.convertIntToBoolean(data.status),
                                        data.key,top=0,bottom=0
                                    )
                                    Divider(color = Color(0XFFF1F6FA))
                                }
                            }
                        }

                    }
                }
            }
        })
}


@Composable
fun CommonRow(title: String, viewModel: EventViewModel, status: Boolean, key: String, top:Int,bottom:Int) {
    Row(
        modifier = Modifier.background(Color.White, shape = RoundedCornerShape(10.dp))
    ) {
        Row(
            modifier = Modifier.background(
                color = Color.White,
                shape = RoundedCornerShape(10.dp)
            )
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White, shape = RoundedCornerShape(topStart = top.dp, topEnd = top.dp, bottomStart = bottom.dp, bottomEnd = bottom.dp))
                    .padding(start = 16.dp, top = 5.dp, end = 12.dp, bottom = 5.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = title, fontSize = 12.sp, fontWeight = FontWeight.Bold)
                if (title == "Distance") {
                    DistanceSwitch()
                } else {
                    CommonSwitch(title, viewModel, status, key)
                }
            }
        }
    }
}


@Composable
fun DistanceSwitch() {
    val count = remember { mutableStateOf(5) }
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp, Alignment.Start),
        modifier = Modifier
            .width(112.dp)
            .height(45.dp)
            .background(Color.White)
    ) {
        Image(painter = painterResource(id = R.drawable.img),
            contentDescription = "",
            modifier = Modifier
                .clickable {
                    if (count.value > 5) {
                        count.value--
                    }

                }
                .height(15.dp)
                .width(15.dp))
        Text(
            text = "${count.value}mi",
            fontSize = 14.sp,
            modifier = Modifier.padding(horizontal = 10.dp)
        )
        Image(painter = painterResource(id = R.drawable.plus),
            contentDescription = "",
            modifier = Modifier
                .clickable {

                    if (count.value < 25) {
                        count.value++
                    }
                }
                .height(15.dp)
                .width(15.dp))
    }
}

@Composable
fun CommonSwitch(title: String, viewModel: EventViewModel, status: Boolean, key: String) {
    var mCheckedState = remember { mutableStateOf(status) }
    Switch(
        checked = mCheckedState.value, onCheckedChange = {
            mCheckedState.value = !mCheckedState.value
            viewModel.findAndUpdateFilterList(
                key, title, mCheckedState.value
            )
        }, colors = SwitchDefaults.colors(
            checkedThumbColor = Color.White, checkedTrackColor = Color(0XFF1977C2)
        ), modifier = Modifier.padding(0.dp)
    )
}




