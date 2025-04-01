package com.hsmodel.rightnow.views

import android.annotation.SuppressLint
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.hsmodel.rightnow.models.Group
import com.hsmodel.rightnow.viewmodels.GroupViewModel

@SuppressLint("UnrememberedMutableState", "StateFlowValueCalledInComposition")
@Composable
fun Group(groupViewModel: GroupViewModel) {
    val groups by groupViewModel.groups.collectAsState()
    var groupClicked by remember { mutableStateOf("") }
    val onGroupChanged = { text: String ->
        groupClicked = text
    }

    Column () {
        LazyRow (
            modifier = Modifier.fillMaxWidth().height(80.dp).padding(PaddingValues(10.dp))
        ) {
            items(groups) { group ->
                GroupLayout(group, onGroupChanged)
            }
        }

        Spacer(Modifier.size(10.dp))
        HorizontalDivider(Modifier.fillMaxWidth())
        Spacer(Modifier.size(10.dp))

        Member(groupViewModel, groupClicked)
    }
}

@Composable
fun GroupLayout(group: Group, clickedGroup: (String) -> Unit) {
    Column(
        modifier = Modifier.width(100.dp).height(60.dp)
            .padding(5.dp)
            .border(1.dp, Color.Black, RoundedCornerShape(1.dp))
            .clickable { clickedGroup(group.name) }
    ) {
        Text(
            text = group.name,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        Text(
            text = group.members.size.toString(),
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
    }
}