/**
 * 弹出层方法
 */
var popup = function() {
	
	var show=function(url,data,width,height,title){
		$.ajax({
			type : "post",
			url : encodeURI(encodeURI(cxt + url)),
			data : data,
			dataType : "HTML",
			contentType: "application/x-www-form-urlencoded; charset=UTF-8",
			async: false,
			cache: false,
			success:function(response){
				result = response;
				layer.open({
				  type: 1, //page层
				  area: [width+'px', height+'px'],
				  title: title,
				  shade: 0.6, //遮罩透明度
				  moveType: 1, //拖拽风格，0是默认，1是传统拖动
				  shift: 0, //0-6的动画形式，-1不开启
				  scrollbar: false,
				  content: result
				});
			}
		});
	}
	
	return {
		show : show	
	};
}();
