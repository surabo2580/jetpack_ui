package com.ivy.betmgmui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ivy.betmgmui.ui.theme.BetmgmUiTheme
import kotlin.math.exp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Surface(color = Color.Black, modifier =Modifier.wrapContentHeight()) {
//            BetmgmUiTheme {
//                // A surface container using the 'background' color from the theme
//                Surface(
//                    modifier = Modifier.fillMaxSize(),
//                    color = MaterialTheme.colors.background
//                ) {
                BetmgmEvent()
                //CountryCard()
            }
               // }
            //}
        }
    }
}
data class Item(val icon:Int,val country:String,val tournamentName:String,val number:Int)
data class CountryItem(val icon:Int,val country: String,val numberoftournament:Int,val countrytournament:List<Item>)

@Composable
fun BetmgmEvent() {
    val expandableforevents= remember {
        mutableStateOf(false)
    }
    val expandableforcountries= remember {
        mutableStateOf(false)
    }

    val items= remember {
        mutableStateListOf(Item(R.drawable.ic_baseline_star_border_24,"Europe","EFL Cup",5))}
        for(i in 1..4){
            items.add(Item(R.drawable.ic_baseline_star_border_24,"Europe","EFL Cup",5))
        }
    val countryItem= remember {
        mutableStateListOf(CountryItem(R.drawable.europeflag,"Europe"
            ,34,items))
    }

        countryItem.add(CountryItem(R.drawable.uk,"England",25,items))
    countryItem.add(CountryItem(R.drawable.india,"India",20,items))
    countryItem.add(CountryItem(R.drawable.italy,"Italy",35,items))
    countryItem.add(CountryItem(R.drawable.russia,"Russia",15,items))

    Column(
        modifier = Modifier.fillMaxHeight()
    ) {


        CardTitle(expandableforevents, "Top Events", items = items)
        Spacer(modifier = Modifier.height(40.dp))

        CardTitle(expandableforcountries, "All Countries", countryItem = countryItem)
    }


}
@Composable
fun CardTitle(expandable:MutableState<Boolean>,title:String,items:SnapshotStateList<Item>?=null,countryItem: SnapshotStateList<CountryItem>?=null){
    Card(
        elevation = 10.dp,
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .wrapContentHeight()

            .padding(5.dp),
        shape = RoundedCornerShape(10.dp)
    ) {
        Column(

            verticalArrangement = Arrangement.SpaceAround
        ) {
            Row(  modifier = Modifier
                .fillMaxWidth()
                .height(40.dp)
                .background(Color.LightGray)
                .padding(start = 5.dp, top = 5.dp)
            ) {
                Text(
                    text = title,
                    fontWeight = FontWeight(1000),
                    modifier = Modifier
//                    .fillMaxWidth()
//                    .height(40.dp)
//                    .background(Color.LightGray)
                        .padding(start = 5.dp, top = 5.dp)

                )
                if (expandable.value) {
                    Image(painter = painterResource(id = R.drawable.ic_baseline_keyboard_arrow_up_24),
                        contentDescription = "",

                        modifier = Modifier
                            .padding(start = 270.dp)
                            .clickable {
                                expandable.value = !expandable.value
                            }

                    )
                }
                if(!expandable.value){
                    Image(painter = painterResource(id = R.drawable.ic_baseline_keyboard_arrow_down_24),
                        contentDescription = "",

                        modifier = Modifier
                            .padding(start = 270.dp)
                            .clickable {
                                expandable.value = !expandable.value
                            }

                    )

                }
            }
            if(expandable.value && items!=null) {
                LazyColumn {
                    items(items) { item ->
                        CardItem(item)

                    }
                }
            }
            if(expandable.value && countryItem!=null) {
                LazyColumn {
                    items(countryItem) { item ->
                        CountryCard(item)

                    }
                }
            }
        }

    }

}
@Composable
fun CardItem( item:Item){

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(55.dp)
                .border(width = 0.5.dp, color = Color.LightGray, shape = RectangleShape)
                .padding(start = 5.dp, top = 5.dp)
        ) {
            Image(
                modifier = Modifier.padding(top = 10.dp),
                painter = painterResource(id = item.icon),
                contentDescription = ""
            )
            Column(modifier = Modifier.padding(start = 5.dp)) {

                Text(text = item.tournamentName, fontWeight = FontWeight.ExtraBold)
                Text(text = item.country, fontWeight = FontWeight.Medium, color = Color.LightGray)
            }
            Text(
                text = item.number.toString(),
                fontWeight = FontWeight.Medium,
                color = Color.LightGray,
                modifier = Modifier.padding(start = 270.dp, top = 10.dp)
            )
//            Image(
//                modifier = Modifier.padding(top = 10.dp),
//                painter = painterResource(id = item.icon),
//                contentDescription = ""
//            )
        }


}
@Composable
fun CountryCard(countryItem: CountryItem ){
    val expandableforcountries= remember {
        mutableStateOf(false)
    }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(55.dp)
                .border(width = 0.5.dp, color = Color.LightGray, shape = RectangleShape)
                .padding(start = 5.dp, top = 5.dp)
        ) {
            Image(painter = painterResource(id = countryItem.icon),
                contentDescription = "",
                modifier = Modifier.padding(top = 10.dp).size(20.dp).clip(shape= RoundedCornerShape(50)),
                contentScale = ContentScale.Crop



            )
//        Row(  modifier = Modifier
//            .fillMaxWidth()
//            //.height(40.dp)
//            //.background(Color.LightGray)
//            .padding(start = 5.dp, top = 5.dp)
//        ){

//            Text(text = countryItem.tournamentName, fontWeight = FontWeight.ExtraBold)
            Text(text = countryItem.country, modifier = Modifier.padding(10.dp), fontWeight = FontWeight.Medium, color = Color.Black)
            if(expandableforcountries.value){
            Image(painter = painterResource(id = R.drawable.ic_baseline_keyboard_arrow_up_24),
                contentDescription = "",

                modifier = Modifier
                    .padding(start = 270.dp)
                    .clickable {
                        expandableforcountries.value = !expandableforcountries.value
                    }
                //.background(color = Color.Green)

            )}
            if(!expandableforcountries.value){
                Image(painter = painterResource(id = R.drawable.ic_baseline_keyboard_arrow_down_24),
                    contentDescription = "",

                    modifier = Modifier
                        .padding(start = 270.dp)
                        .clickable {
                            expandableforcountries.value = !expandableforcountries.value
                        }
                    //.background(color = Color.Green)

                )

            }


//            Text(
//                text = countryItem.numberoftournament.toString(),
//                fontWeight = FontWeight.Medium,
//                color = Color.LightGray,
//                modifier = Modifier.padding(start = 270.dp, top = 10.dp)
//            )



        }
//        Spacer(modifier = Modifier.height(55.dp))
        Column() {
            if (expandableforcountries.value) {
                Column {
                    countryItem.countrytournament.forEach { item ->
                        CountryCardItem(item)

                    }
                }
            }
        }








}
@Composable
fun CountryCardItem(item:Item){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(55.dp)
            //.border(width = 0.5.dp, color = Color.LightGray, shape = RectangleShape)
            .padding(start = 5.dp, top = 5.dp)
    ) {
        Image(
            modifier = Modifier.padding(top = 10.dp),
            painter = painterResource(id = item.icon),
            contentDescription = ""
        )
//        Row(  modifier = Modifier
//            .fillMaxWidth()
//            //.height(40.dp)
//            //.background(Color.LightGray)
//            .padding(start = 5.dp, top = 5.dp)
//        ){

//            Text(text = countryItem.tournamentName, fontWeight = FontWeight.ExtraBold)
        Text(text = item.tournamentName, fontWeight = FontWeight.Medium, modifier = Modifier.padding(10.dp), color = Color.Black)




            Text(
                text = item.number.toString(),
                fontWeight = FontWeight.Medium,
                color = Color.DarkGray,
                modifier = Modifier.padding(start = 270.dp, top = 10.dp)
            )


    }


}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    BetmgmUiTheme {
        BetmgmEvent()
    }
}