package com.hsmodel.rightnow.dummy

import com.hsmodel.rightnow.models.Member

class DummyData {
    val groups: MutableMap<String, MutableList<Member>> = mutableMapOf(
        "Group A" to mutableListOf(Member("aaa"), Member("bbb")),
        "Group B" to mutableListOf(Member("ccc"), Member("ddd")),
        "Group C" to mutableListOf(Member("eee"), Member("fff"))/*,
        "Group D" to mutableListOf(Member("ggg"), Member("hhh")),
        "Group E" to mutableListOf(Member("iii"), Member("jjj")),
        "Group F" to mutableListOf(Member("kkk"), Member("lll")),
        "Group G" to mutableListOf(Member("mmm"), Member("nnn")),
        "Group H" to mutableListOf(Member("ooo"), Member("ppp")),
        "Group NO" to mutableListOf()*/
    )
    
    val members: MutableList<Member> = mutableListOf(
        Member("aaa"), Member("bbb"),
        Member("ccc"), Member("ddd"),
        Member("eee"), Member("fff"),
        Member("ggg"), Member("hhh"),
        Member("iii"), Member("jjj"),
        Member("kkk"), Member("lll"),
        Member("mmm"), Member("nnn"),
        Member("ooo"), Member("ppp")
    )

    val options: Map<String, String> = mapOf(
        "Notification" to "Turn off notifications",
        "Notification" to "Only near",
    )


}