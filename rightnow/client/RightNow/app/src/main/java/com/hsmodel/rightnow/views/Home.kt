package com.hsmodel.rightnow.views

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.core.content.ContextCompat.getString
import com.hsmodel.rightnow.R
import com.hsmodel.rightnow.components.GroupDropDownMenuBox
import com.hsmodel.rightnow.viewmodels.GroupViewModel

@Composable
fun Home(groupViewModel: GroupViewModel) {
    HomeLayout(groupViewModel)
}

@Composable
fun HomeLayout(groupViewModel: GroupViewModel) {
    ConstraintLayout(
        modifier = Modifier.fillMaxSize()
    ) {
        val (locationPanel, targetGroup, playButton) = createRefs()

        LocationPanel(Modifier.constrainAs(locationPanel) {
            centerHorizontallyTo(parent)
            bottom.linkTo(targetGroup.top, margin = 5.dp)
        })

        // TODO need to get the default group from user preference
        var clickedGroup by remember { mutableStateOf("") }
        val onClick = { selectedGroupName: String ->
            clickedGroup = selectedGroupName
        }

        GroupDropDownMenuBox(onClick, groupViewModel.getGroups(), Modifier.constrainAs(targetGroup) {
            centerHorizontallyTo(parent)
            bottom.linkTo(playButton.top, margin = 5.dp)
        })

        PlayIcon(Modifier.constrainAs(playButton) {
            centerHorizontallyTo(parent)
            centerVerticallyTo(parent)
        })

    }
}

@Composable
fun LocationPanel(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
    ) {
        Icon(
            imageVector = Icons.Filled.Refresh,
            contentDescription = stringResource(R.string.refresh)
        )

        Spacer(Modifier.size(5.dp))

        Text(
            text = "Address"
        )
    }
}

@Composable
fun PlayIcon(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    Icon (
        imageVector = Icons.Filled.PlayArrow,
        contentDescription = stringResource(R.string.msg_play_button),
        modifier = Modifier
            .size(150.dp)
            .clickable {
                Toast.makeText(context, getString(context, R.string.done), Toast.LENGTH_SHORT).show()
            }.then(modifier)
    )
}