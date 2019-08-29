// 页面加载时拼接文章div
$(document).ready(function () {
    $.ajax({
        async: false,
        url: "http://localhost:8888/selectArticle",
        type: "GET",
        data: {pageNum: 1, pageSize: 3},
        error: function (error) {
            console.log(error);
        },
        success: function (data) {
            // $('.articles').empty();
            // $('.content').empty();
            var articles = $('.article');
            $.each(data, function (i, obj) {
                <!--这里可以迭代出每一篇文章的url-->
                var center = $(
                    '<div class="blogDetail">' +
                    '<div class="blogTop">' +
                    '<img src="../static/assets/images/view1.png">' +
                    '</div>' +
                    '<div class="blogBottom">' +
                    '<div class="BB-T">' +
                    '<p class="articleright">' + obj['articleCategories'] + '</p>' +
                    '<i class="layui-icon layui-icon-camera-fill">' +
                    '</i>' +
                    '</div>' +
                    '<div class="BB-M">' +
                    '<p class="artName">' + obj['articleTitle'] + '</p>' +
                    '<p class="digest">' + obj['articleTabloid'] + '</p>' +
                    '</div>' +
                    '<div class="BB-B">' +
                    '<div class="witeEnd">' +
                    '<p class="digest">' + obj['articleAuthor'] + '</p>' +
                    '<i class="layui-icon layui-icon-tabs">' + obj['articleDate'] + '</i>' +
                    '</div>' +
                    '<div class="icon">' +
                    '<i class="layui-icon layui-icon-dialogue">' + obj['articleLeaveMessage'] + '</i>' +
                    '<i class="layui-icon layui-icon-star">' + obj['articleStar'] + '</i>' +
                    '</div>' + '</div>' + '</div>' + '</div>'
                    )
                ;
                articles.append(center);
                $('.blogDetail').eq(i).attr("onclick", "articleClick(" + obj['articleId'] + ");")
            });
        }
    });
});

// 点击跳转到对应页面
function articleClick(articleId) {
    $.ajax({
        async: false,
        url: "http://localhost:8888/Article/" + articleId,
        success: function () {
            window.location.href = "/Article/" + articleId;
        }
    })
}

// 首页搜索框
function articleSearch(SearchMsg) {
    $.ajax({
        // async: false,
        url: "http://localhost:8888/Article/selectLikeArticle",
        data: {text: SearchMsg},
    })
}
