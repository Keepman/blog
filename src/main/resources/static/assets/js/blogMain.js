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

// 分页器
layui.use(['laypage', 'layer'], function () {
    var laypage = layui.laypage
        , layer = layui.layer;

    //不显示首页尾页
    laypage.render({
        elem: 'demo4'
        , count: 100
        , first: false
        , last: false,
        theme: '#FF5722'
    });
});

//跳转到文章内容
var artName = document.getElementsByClassName("artName")[0];
artName.onclick = function () {
    window.location.href = 'articlePage.html';
}