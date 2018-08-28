function go_top(t) {
    var $body = (window.opera) ? (document.compatMode == "CSS1Compat" ? $('html') : $('body')) : $('html,body');
    t ? t : t = 0;
    $body.animate({
        scrollTop: t
    }, 400);
}

function get_query(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
    var r = window.location.search.substr(1).match(reg);
    if (r != null) return unescape(r[2]);
    return null;
}

function add_z(i){
    return i<10?"0"+i:i;
}

function getthisurl(){
    return window.location.href;
}
function unselect(em) {
    if (typeof(em.onselectstart) != "undefined") {       
        // IE下禁止元素被选取       
        em.onselectstart = new Function("return false");       
    } else {
        // firefox下禁止元素被选取的变通办法       
        em.onmousedown = new Function("return false");       
        em.onmouseup = new Function("return true");       
    }
}
(function ($){
            $.dialog=function(o,c){
                var option={
                    title : "",
                    content : "",
                    btn : {yesBtn:true,noBtn:true,closeBtn:true},
                    yesBtn : {content:"确定"},
                    noBtn : {content:"返回"},
                    closeBtn :{content:"×"},
                    yesFn : false,
                    noFn : false,
                    closeFn : false
                }
                function setO(a,b){for(i in a){(typeof b[i])=="object"?setO(a[i],b[i]):b[i]=a[i];}}
                o?setO(o,option):o;
                var x="",y="",z="";
                option.btn.yesBtn?y='<div class="yesBtn btn">'+option.yesBtn.content+'</div>':y;
                option.btn.noBtn?x='<div class="noBtn btn">'+option.noBtn.content+'</div>':x;
                option.btn.closeBtn?z='<div class="closeBtn">'+option.closeBtn.content+'</div>':z;
                var d='<div id="dialog"><div class="con"><div class="title"><div class="title_con">'+option.title+'</div>'+z+'</div><div class="p">'+option.content+'</div><div class="btn_con">'+y+x+'</div></div></div></div>';
                $("#dialog").remove();
                $("body").append(d);
                var $d=$("#dialog");
                (typeof option.yesFn == "function")?$d.find(".yesBtn").one("click",option.yesFn):option.yesFn;
                (typeof option.noFn == "function")?$d.find(".noBtn").one("click",option.noFn):option.noFn;
                (typeof option.closeFn == "function")?$d.find(".closeBtn").one("click",option.closeFn):option.closeFn;
                $d.find(".closeBtn").on("click",function(){$d.remove()});
                $d.find(".btn").on("click",function(){$d.remove()});
                $d.show();
            }
        })(jQuery)
        
        /*
        $("button").click(function(){
            $.dialog({
                content:"测试",
                title:"Title",
                yesFn:function(){alert("确定按钮callback");},
                noFn:function(){window.location.href="http://www.worldonline2.com"}}
                    );
        });*/

$(document).ready(function(){
	$(".location").length?$(".location").on("click","a",location_menu):0;
	$(document).on("click",".onback",function(){window.history.back();})
	$(document).on("focus",".pagination a",function(){$(this).blur();})
	
});
function location_menu(){
	var menuD=$("#left",parent.document).contents();
}


function menuTogget(){
	$("#Menu").addClass("mini");
}

/*******************channel****************/
	function showChannelParam(id){
		$("#channelParam").modal('show');
	}
	function hideChannelParam(id){
		$("#channelParam").modal('hide');
	}
	function showCallbackUrl(id){
		$("#callbackUrl").modal('show');
	}
	function hideCallbackUrl(id){
		$("#callbackUrl").modal('hide');
	}
	function showcCustomInfo(id){
		$("#customInfo").modal('show');
	}
	function hideCustomInfo(id){
		$("#callbackUrl").modal('hide');
	}
	function showSelectParam(id){
		$("#selectParam").modal('show');
	}
	function hideSelectParam(id){
		$("#selectParam").modal('hide');
	}
	function showThirdStat(id){
		$("#thirdStat").modal('show');
	}
	function hideThirdStat(id){
		$("#thirdStat").modal('hide');
	}
	function showChannelInfo(id){
		$("#channelInfo").modal('show');
	}
	function hideChannelInfo(id){
		$("#channelInfo").modal('hide');
	}
/*******************channel****************/
/********************prototype********************/
Array.prototype.searchByKey=function(k,v){
	var o = this;
	for(var i=0;i<o.length;i++){
		if(o[i][k]==v)
			return o[i];
	}
	return ;
}
Array.prototype.searchByValue=function(v){
	var o = this;
	for(var i=0;i<o.length;i++){
		if(o[i]==v)
			return true;
	}
	return false;
}
/********************prototype********************/