package com.hsmodel.rightnow.viewmodels

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModel
import com.hsmodel.rightnow.dummy.DummyData
import com.hsmodel.rightnow.models.Group
import com.hsmodel.rightnow.models.Member
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

// TODO It helps to sync data between client and server
@SuppressLint("MutableCollectionMutableState")
class GroupViewModel : ViewModel() {
    private val _groups = MutableStateFlow(listOf<Group>())
    val groups: StateFlow<List<Group>> = _groups

    init {
        _groups.value = DummyData().groups.map { Group(it.key, it.value) }
    }

    fun getGroups(): List<Group> {
        return groups.value
    }

    fun addGroup(newGroup: String, members: List<Member>) {
        _groups.value += Group(newGroup, members.toMutableList())
    }

    fun getMembers(groupName: String): List<Member> {
        val found = groups.value.find { group -> group.name == groupName }
        return found?.members?.toList() ?: listOf()
    }

    fun getAllMembers(): List<Member> {
        val member: MutableList<Member> = mutableListOf()
        groups.value.forEach { group ->
            member += group.members
        }

        return member
    }

    fun addMember(groupName: String, member: Member) {
        val found = _groups.value.find { group -> group.name == groupName }
        if (found != null) {
            found.members += member
        } else {
            _groups.value += Group(groupName, mutableListOf(member))
        }
    }

    fun selectGroup(toBeGroupName: String, member: Member) {
        // look through same group
        val found = _groups.value.find { group -> group.name == toBeGroupName }
        if (found != null) {
            found.members += member
        } else {
            _groups.value += Group(toBeGroupName, mutableListOf(member))
        }

        // if the member exists in a group, delete it
        _groups.value.forEach { group ->
            if (group.name != toBeGroupName) {
                val foundMember = group.members.find { existedMember -> existedMember.userName == member.userName }
                if (foundMember != null) {
                    group.members.remove(foundMember)
                }
            }
        }
    }
}