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
    if (slideIndex > slideView.length) { slideIndex = 1 }
    for (i = 0; i < point.length; i++) {
        point[i].className = point[i].className.replace(" active", "");
    }
    slideView[slideIndex - 1].style.display = "block";
    point[slideIndex - 1].className += " active";
    setTimeout(showSlides, 4000); // 切换时间为 2 秒
}

// 推荐轮播
// var swiper1 = new Swiper('#swiper1');
// var swiper2 = new Swiper('#swiper2');
// var swiper3 = new Swiper('#swiper3');
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

//跳转到文章内容
var artName = document.getElementsByClassName("artName")[0];
artName.onclick = function () {
    window.location.href = 'articlePage.html';
}



// 分页器
var total = 0;//总页数
var currentPage = 1;
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
}

// 切换页面
function changePage(page) {
    let pageArr = [], light;
    if (page > total || page < 1) {
        return false;
    }
    page = parseInt(page);
    // if( page < 8){
        pageArr = [page-2,page-1,page,page+1,page+2];
        light = page-1;
    // }
    // else if(page > total - 5) {
    //     pageArr = [1, '...', total - 6, total - 5, total - 4, total - 3, total - 2, total - 1, total];

    //     light = 8 - (total - page);
    // } else if (page < 6) {
    //     pageArr = [1, 2, 3, 4, 5, 6, 7, '...', total];

    //     light = page - 1;
    // } else {
    //     pageArr = [1, '...', page - 2, page - 1, page, page + 1, page + 2, '...', total];

    //     light = 4;
    // }

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

// $('#list').on('click','li',function () {
//     switch($(this).text()){
//         case '...':{
//             break;
//         }
//         case '<':{
//             setCurrentPage(currentPage-1);
//             break;
//         }
//         case '>':{
//             setCurrentPage(currentPage+1);
//             break;
//         }
//         default:{
//             setCurrentPage($(this).text());
//             break;
//         }
//     }
// })

$('#list').on('click', 'li', function () {
    // console.log($(this)[0].firstElementChild.className);
    console.log($(this));
    if ($(this)[0].firstElementChild != null) {
        switch ($(this)[0].firstElementChild.className) {
            case 'layui-icon layui-icon-left': {
                setCurrentPage(currentPage - 1);
                break;
            }
            case 'layui-icon layui-icon-right': {
                setCurrentPage(currentPage + 1);
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
                break;
            }
        }

    }
});

function getData(){
    
}

init(8, 3);