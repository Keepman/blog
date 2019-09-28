// 幻灯片轮播
var slideIndex = 0;
showSlides();

// showSlides(slideIndex);
function plusSlides(n) {
    showSlides(slideIndex += n);
}

function currentSlide(n) {
    showSlides(slideIndex = n);
}

function showSlides(n) {
    var i;
    var slideView = document.getElementsByClassName("slideView");
    var point = document.getElementsByClassName("point");
    // if (n > slideView.length) {slideIndex = 1} 
    // if (n < 1) {slideIndex = slideView.length}
    for (i = 0; i < slideView.length; i++) {
        slideView[i].style.display = "none";
    }
    slideIndex++;
    if (slideIndex > slideView.length) {
        slideIndex = 1
    }
    for (i = 0; i < point.length; i++) {
        point[i].className = point[i].className.replace(" active", "");
    }
    slideView[slideIndex - 1].style.display = "block";
    point[slideIndex - 1].className += " active";
    setTimeout(showSlides, 4000); // 切换时间为 2 秒
}

// 推荐轮播

var mySwiper = new Swiper('.swiper-container', {
    slidesPerView: 3,
    spaceBetween: 20,
    //spaceBetween : '10%',按container的百分比
})

// 获取弹窗
var diaS = document.getElementById('diaS');
// 打开弹窗的按钮对象
var searchBlog = document.getElementById("searchBlog");
// 获取 <span> 元素，用于关闭弹窗
var span = document.querySelector('.close');
searchBlog.onclick = function () {
    diaS.style.display = "block";
}
// 点击 <span> (x), 关闭弹窗
span.onclick = function () {
    diaS.style.display = "none";
}
// 在用户点击其他地方时，关闭弹窗
window.onclick = function (event) {
    if (event.target == diaS) {
        diaS.style.display = "none";
    }
}

// 分页器
var total = 0;//总页数
var currentPage = 1;//当前页
var isInit = false;

// 初始化方法
function init(page, num) {
    if (!isInit) {
        isInit = true;
        total = page;
        currentPage = num;

        changePage(currentPage);
    }
}

function setCurrentPage(page) {
    // if(page>total||page<1){
    //     return;
    // }
    currentPage = parseInt(page);
    console.log(currentPage);
    getData(currentPage);
}

// 切换页面
function changePage(page) {
    var pageArr = [], light;
    if (page > total || page < 1) {
        return false;
    }
    page = parseInt(page);

    pageArr = [page - 2, page - 1, page, page + 1, page + 2];
    light = page - 1;


    console.log(pageArr);
    renderPage(pageArr, light);
    return true;
}

function renderPage(pageArr, light) {
    for (let i = 0; i < pageArr.length; i++) {
        $(".list-page").eq(i).text(pageArr[i]);
        if (pageArr[i] === '...') {
            $(".list-page").eq(i).css('border', 'none');
            $(".list-page").eq(i).css('backgroundColor', '#f1f1f1');
        } else if (i === 1 || i === pageArr.length - 2) {
            $(".list-page").eq(i).css('border', '1px solid #ccc');
        }
    }

    $(".list-page").eq(light).css('color', '#fff').css('backgroundColor', '#2962ff');
}

$('#list').on('click', 'li', function () {
    console.log($(this));
    if ($(this)[0].firstElementChild != null) {
        switch ($(this)[0].firstElementChild.className) {
            case 'layui-icon layui-icon-left': {
                setCurrentPage(currentPage - 1);
                // $(this)[currentPage - 1].addClass('clearfix');
                break;
            }
            case 'layui-icon layui-icon-right': {
                setCurrentPage(currentPage + 1);
                // $(this)[currentPage + 1].addClass('clearfix');
                break;
            }
        }
    } else {
        switch ($(this).text()) {
            case '...': {
                break;
            }
            default: {
                setCurrentPage($(this).text());
                // $(this).addClass('clearfix');
                break;
            }
        }

    }
});

function getData(currentPage){
    $.ajax({
        // async: false,
        url: "http://localhost:8888/selectArticle",
        type: "GET",
        data: {pageNum: currentPage, pageSize: 3},
        success: function (data) {
            $('.article').empty();
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
    })
}

// 小周写的
$(document).ready(function () {
    $.ajax({
        async: false,
        url: "http://localhost:8888/Article/selectCountArticle",
        type: "GET",
        data: {},
        error: function (error) {
            console.log(error);
        },
        success: function (data) {
            console.log(data);
            let list = $('.fenye');
            list.empty();
            let pageTotal = 0;
            if (data % 3 === 0) {
                pageTotal = data / 3;
            } else {
                pageTotal = Math.ceil(data / 3);
            }
            let left = $('<li class="roll">' +
                '<i class="layui-icon layui-icon-left"></i>' +
                '</li>');
            list.append(left);
            for (let i = 0; i < pageTotal; i++) {
                let li = $('<li class="list-page">' + (i + 1) + '</li>');
                list.append(li);
            }
            let right = $('<li class="roll">' +
                '<i class="layui-icon layui-icon-right"></i>' +
                '</li>');
            list.append(right);
        }
    })
});

