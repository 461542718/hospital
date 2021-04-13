// ui-search 定义
$.fn.UiSearch = function(){
    var ui = $(this);

    $('.ui-search-selected',ui).on('click',function(){
        $('.ui-search-select-list').show();
        //return false是为了防止事件冒泡
        return false;
    });

    $('.ui-search-select-list a',ui).on('click',function(){

        $('.ui-search-selected').text( $(this).text() );
        $('.ui-search-select-list').hide();
        //return false是为了防止事件冒泡，防止触发下面body中点击事件
        return false;
    });

    $('body').on('click',function(){
        $('.ui-search-select-list').hide();
    })

}
$.fn.UiCotentList= function(){
    var ui = $(this);
    var navs = $('.link',this);
    var contentLists = $('.content-list');

    $('.link',ui).on('click',function(){
        var navIndex = $(this).index();
        contentLists.removeClass('focus').eq(navIndex).addClass('focus');
        navs.removeClass('selected').eq(navIndex).addClass('selected');
    })
}
var j=0;
$.fn.slideSchedule = function(dir){
    var ui = $(this);
    // console.log(ui);

    var width = $(".main").width();
    var content = $(".schedule_box ");
    $(this).click(function(){
        if(dir=="left"){
            j--;
            if(j<0){
                j=0;
                return;
            }

        }else if(dir=="right"){
            j++;
            if(j>6){
                j=6;
                return;
            }
        }
        var distance = -width*j;
        console.log("j"+j);
        console.log(distance+"px");
        $(content).css("left",distance+"px");


    })

}
$.fn.loadtable = function(){
    var weeks = new Array('星期日','星期一','星期二','星期三','星期四','星期五','星期六');

    for(var i=0;i<49;i++){
        var currDate = new Date();
        // getDay() 方法可返回一周（0~6）的某一天的数字。
        // 星期天为 0, 星期一为 1, 以此类推。
        currDate = new Date(currDate.getTime() + 24*60*60*1000*i);
        var currWeekDay = currDate.getDay();
        //获取当前日(1-31)
        var currDay = currDate.getDate()
        var currYear = currDate.getFullYear();
        //getMonth()，一月份: 0
        var currMonth = currDate.getMonth()+1;
        var week = weeks[currWeekDay];
        var str = week+'<br/>'+currYear+'-'+currMonth+'-'+currDay;
        // console.log(str);

        var html = new Array();
        html.push('<div class="schedule_box_item"><div class="date">'+str+'</div>');
        html.push('<div class="status"></div>');
        html.push('<div class="status status_full">约满</div>');
        html.push('<div class="status"></div></div>');
        $('.schedule_box').append(html.join(''));
    }
}
$.fn.back = function(){
    $(this).on('click',function(){
        $('.nav').removeClass('hidden');
        $('.schedule').addClass('hidden');
        $('.content-lists .wrap')[1].classList.add("hidden");
        // var test = $('.content-lists .wrap')[0].classList;
        // console.log(test);
        $('.content-lists .wrap')[0].classList.remove("hidden");
    })
}
$.fn.jump = function(){
    $(this).on('click',function(){
        console.log("click "+this);
        $('.nav').addClass('hidden');
        $('.schedule').removeClass('hidden');

        $('.content-lists .wrap')[0].classList.add("hidden");
        // var test = $('.content-lists .wrap')[0].classList;
        // console.log(test);
        $('.content-lists .wrap')[1].classList.remove("hidden");
    })
}

//test 测试数据
$.fn.loadData = function(){
    for(var i=0;i<4;i++){
        //一共四科
        for(var j=0;j<3;j++){
            //一共3行
            var eles = new Array();
            eles.push('<tr>');
            if(j==0){
                eles.push('<td rowspan="3" class="classify">门诊</td>');
            }
            for(var k=0;k<4;k++){
                eles.push('<td><a href="#" class="classify-item">口腔门诊</a></td>');
            }
            eles.push('</tr>')
            // console.log(eles.join(''));
            $(this).append(eles.join(''));
        }
    }
}

//test 测试数据



//页面初始化，事件绑定
$(document).ready(function(){
    $('.ui-search').UiSearch();
    $('.content .nav').UiCotentList();
    $('.leftBtn .wrap_btn').slideSchedule("left");
    $('.rightBtn .wrap_btn').slideSchedule("right");

    $('.schedule_box').loadtable();
    $('.back').back();



    //test
    $('.content-table table').loadData();


    $('.classify-item').jump();


})
