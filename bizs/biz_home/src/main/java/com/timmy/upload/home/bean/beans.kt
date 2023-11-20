package com.timmy.upload.home.bean

data class AtricleList(
    val curPage: Int,
    val datas: List<Article>,
    val offset: Int,
    val over: Boolean,
    val pageCount: Int,
    val size: Int,
    val total: Int,
)

/**
 *      {
"adminAdd": false,
"apkLink": "",
"audit": 1,
"author": "",
"canEdit": false,
"chapterId": 502,
"chapterName": "自助",
"collect": false,
"courseId": 13,
"desc": "",
"descMd": "",
"envelopePic": "",
"fresh": true,
"host": "",
"id": 27471,
"isAdminAdd": false,
"link": "https://juejin.cn/post/7296705389855768627",
"niceDate": "33分钟前",
"niceShareDate": "33分钟前",
"origin": "",
"prefix": "",
"projectLink": "",
"publishTime": 1698924831000,
"realSuperChapterId": 493,
"selfVisible": 0,
"shareDate": 1698924831000,
"shareUser": "equationl",
"superChapterId": 494,
"superChapterName": "广场Tab",
"tags": [],
"title": "为 Compose MultiPlatform 添加 C/C++ 支持（3）：实战 Desktop、Android、iOS 调用同一个 C/C++ 代码",
"type": 0,
"userId": 87590,
"visible": 1,
"zan": 0
},
 */
data class Article(
    val adminAdd: Boolean,
    val apkLink: String,
    val audit: Int,

//    "adminAdd" : false,
//    "apkLink": "",
//"audit": 1,
//"author": "",
//"canEdit": false,
//"chapterId": 502,
//"chapterName": "自助",
//"collect": false,
//"courseId": 13,
//"desc": "",
//"descMd": "",
//"envelopePic": "",
//"fresh": true,
//"host": "",
//"id": 27471,
//"isAdminAdd": false,
//"link": "https://juejin.cn/post/7296705389855768627",
//"niceDate": "33分钟前",
//"niceShareDate": "33分钟前",
//"origin": "",
//"prefix": "",
//"projectLink": "",
//"publishTime": 1698924831000,
//"realSuperChapterId": 493,
//"selfVisible": 0,
//"shareDate": 1698924831000,
//"shareUser": "equationl",
//"superChapterId": 494,
//"superChapterName": "广场Tab",
//"tags": [],
//"title": "为 Compose MultiPlatform 添加 C/C++ 支持（3）：实战 Desktop、Android、iOS 调用同一个 C/C++ 代码",
//"type": 0,
//"userId": 87590,
//"visible": 1,
//"zan": 0
)