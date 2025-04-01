package com.hsmodel.rightnow.views

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.hsmodel.rightnow.R
import com.hsmodel.rightnow.models.Member
import com.hsmodel.rightnow.viewmodels.GroupViewModel

@Composable
fun Member(groupViewModel: GroupViewModel, groupClicked: String) {
    if (groupClicked.isNotEmpty()) {
        LazyColumn(
            modifier = Modifier.fillMaxWidth().padding(start = 15.dp)
        ) {
            val members = groupViewModel.getMembers(groupClicked)
            if (members.isNotEmpty()) {
                items(members) { member ->
                    MemberLayout(member)
                }
            } else {
                item { Text(text = stringResource(R.string.empty)) }
            }
        }
    }
}

@Composable
fun MemberLayout(member: Member) {
    Text(text = member.userName, modifier = Modifier.fillMaxWidth().height(30.dp))
}