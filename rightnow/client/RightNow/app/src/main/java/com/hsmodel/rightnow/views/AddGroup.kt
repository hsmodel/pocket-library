package com.hsmodel.rightnow.views

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
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
import com.hsmodel.rightnow.models.Group
import com.hsmodel.rightnow.models.Member
import com.hsmodel.rightnow.viewmodels.GroupViewModel

@Composable
fun AddGroup(groupViewModel: GroupViewModel,
             onClickListener: () -> Unit) {
    AddGroupLayout(groupViewModel, onClickListener)
}

@SuppressLint("UnrememberedMutableState")
@Composable
fun AddGroupLayout(groupViewModel: GroupViewModel,
                   onClickListener: () -> Unit
) {
    var newGroupName by remember { mutableStateOf("") }
    val allGroup = groupViewModel.groups
    val clickedMemberList = rememberSaveable { mutableStateOf(listOf<Member>()) }
    val onClickMember = { member: Member ->
        clickedMemberList.value = clickedMemberList.value.plusElement(member)
    }

    Column(modifier = Modifier.fillMaxSize()) {
        TextField(
            value = newGroupName,
            onValueChange = { newGroupName = it },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email
            ),
            singleLine = true,
            label = { Text(text = stringResource(R.string.placeholder_new_group)) },
            modifier = Modifier.padding(10.dp).align(Alignment.CenterHorizontally),
            textStyle = TextStyle(fontWeight = FontWeight.Bold, fontSize = 30.sp),
        )

        Row (
            Modifier.fillMaxWidth().height(50.dp).padding(5.dp)
        ) {
            clickedMemberList.value.forEach { member ->
                Text(text = member.userName, modifier = Modifier.padding(5.dp))
            }
        }

        Button(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            onClick = {
                if (isAvailableGroupName(allGroup.value, newGroupName)) {
                    groupViewModel.addGroup(newGroupName, clickedMemberList.value)
                    onClickListener()
                }
        }) {
            Text(text = stringResource(R.string.ok))
        }

        HorizontalDivider(Modifier.fillMaxWidth())

        groupViewModel.getAllMembers().forEach { member ->
            MemberListLayout(member = member, onClick = onClickMember)
        }
    }
}

@Composable
fun MemberListLayout(member: Member, onClick: (Member) -> Unit) {
    Text(text = member.userName, modifier = Modifier.fillMaxWidth().padding(start = 15.dp, 3.dp).clickable {
        onClick(member)
    })
}

fun isAvailableGroupName(allGroup: List<Group>, newGroupName: String): Boolean {
    val hasSameGroupName: Group? = allGroup.find { group -> group.name == newGroupName }
    return hasSameGroupName == null
}