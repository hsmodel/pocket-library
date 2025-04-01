package com.hsmodel.rightnow.components

import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.hsmodel.rightnow.models.Group

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GroupDropDownMenuBox(onClick: (String) -> Unit, allGroups: List<Group>, modifier: Modifier = Modifier) {
    val groupList = allGroups.toList()
    var selectedText by remember { mutableStateOf(groupList[0].name) }
    var isExpanded by remember { mutableStateOf(false) }

    ExposedDropdownMenuBox(
        modifier = modifier,
        expanded = isExpanded,
        onExpandedChange = { isExpanded = !isExpanded }
    ) {
        TextField(
            modifier = Modifier.menuAnchor(),
            onValueChange = { },
            readOnly = true,
            value = selectedText,
            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(isExpanded) }
        )
        ExposedDropdownMenu(
            expanded = isExpanded,
            onDismissRequest = { isExpanded = false }
        ) {
            groupList.forEach{ text ->
                DropdownMenuItem(
                    text = {Text(text = text.name)},
                    onClick = {
                        selectedText = text.name
                        isExpanded = false
                        onClick(selectedText)
                    },
                    contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding
                )
            }
        }
    }
}