package com.hsmodel.rightnow.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.hsmodel.rightnow.R

data class DropDownItem(
    val imageVector: ImageVector,
    val text: String
)

@Composable
fun MoreMenu() {
    val dropDownItems: List<DropDownItem> = listOf(
        DropDownItem(Icons.Filled.AddCircle, stringResource(R.string.add_member)),
        DropDownItem(Icons.Filled.Edit, stringResource(R.string.edit))
    )

    var isContextMenuVisible by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        IconButton(onClick = { isContextMenuVisible = !isContextMenuVisible }) {
            Icon(Icons.Default.MoreVert, contentDescription = stringResource(R.string.more))
        }

        DropdownMenu(
            expanded = isContextMenuVisible,
            onDismissRequest = { isContextMenuVisible = false }
        ) {
            dropDownItems.forEach { item ->
                DropdownMenuItem(
                    text = {
                        Text(text = item.text)
                    },
                    leadingIcon = { Icon(item.imageVector, contentDescription = null) },
                    onClick = {
                        // TODO
                    }
                )
            }
        }
    }
}