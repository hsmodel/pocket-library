package com.hsmodel.rightnow.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hsmodel.rightnow.R
import com.hsmodel.rightnow.components.GroupDropDownMenuBox
import com.hsmodel.rightnow.models.Member
import com.hsmodel.rightnow.viewmodels.GroupViewModel

@Composable
fun AddMember(groupViewModel: GroupViewModel,
              addMemberListener: (String) -> Unit) {
    AddMemberLayout(groupViewModel, addMemberListener)
}

@Composable
fun AddMemberLayout(groupViewModel: GroupViewModel,
                    addMemberListener: (String) -> Unit) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        var userName by remember { mutableStateOf("") }
        var hasMember by remember { mutableStateOf(false) }

        TextField(
            value = userName,
            onValueChange = { userName = it },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email
            ),
            singleLine = true,
            label = { Text(stringResource(R.string.placeholder_new_member)) },
            modifier = Modifier.padding(10.dp),
            textStyle = TextStyle(fontWeight = FontWeight.Bold, fontSize = 30.sp),
        )

        Spacer(Modifier.size(5.dp))

        if (hasMember) {
            Text(text = stringResource(R.string.msg_id_exists))
        }

        Button(onClick = {
            hasMember = hasAlreadyMember(groupViewModel.getAllMembers(), userName)
            if (!hasMember) {
                groupViewModel.addMember("", Member(userName))
                addMemberListener(userName)
            }
        }) {
            Text(text = stringResource(R.string.ok))
        }
    }
}

@Composable
fun SelectGroup(groupViewMember: GroupViewModel, memberName: String, selectGroupListener: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val defaultValue = groupViewMember.getGroups()[0]
        var clickedGroup by remember { mutableStateOf(defaultValue.name) }
        val onClick = { selectedGroupName: String ->
            clickedGroup = selectedGroupName
        }
        Text(text = stringResource(R.string.select_group))
        GroupDropDownMenuBox(onClick, groupViewMember.getGroups())

        Row() {
            Button(onClick = { selectGroupListener() }) {
                Text(text = stringResource(R.string.skip))
            }

            Spacer(Modifier.size(10.dp))

            Button(onClick = {
                groupViewMember.selectGroup(clickedGroup, Member(userName = memberName))
                selectGroupListener()
            }) {
                Text(text = stringResource(R.string.ok))
            }
        }
    }
}

fun hasAlreadyMember(allMembers: List<Member>, userId: String): Boolean {
    var hasMember = false

    run breaker@{
        allMembers.forEach { member ->
            if (member.userName == userId) {
                hasMember = true
                return@breaker
            }
        }
    }

    return hasMember
}