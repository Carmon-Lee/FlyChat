function Person(name,height){
	this.name=name;
	this.height=height;
	
}
Person.prototype.hobby=function(){
	return 'swimming';
}
/*var boy=new Person('keith',180);
var girl=new Person('linda',165);
console.log(boy.hobby===girl.hobby);
console.log(girl);
console.log(girl.hobby());*/

var fn=new Function("arg1","arg2","return arg1*arg2;");
console.log(fn(2,3));

function fn2(arg1,arg2,callback){
	console.log(arg1*arg2);
	callback();
}

fn2(10,20,function(){
	console.log('function callback');
});


function fn3(url, callback){
 var httpRequest;　　　　//创建XHR
 httpRequest = window.XMLHttpRequest ? new XMLHttpRequest() :　　　//针对IE进行功能性检测
　　　　window.ActiveXObject ? new ActiveXObject("Microsoft.XMLHTTP") : undefined;
  
 httpRequest.onreadystatechange = function(){
  if(httpRequest.readystate === 4 && httpRequest.status === 200){　　//状态判断
   callback(httpRequest.responseXML); 
  }
 };
 httpRequest.open("GET", url);
 httpRequest.send();
}
 
fn3("mybatis-config.xml", function(){　　　　//调用函数
 console.log(this); 　　//此语句后输出
 console.log('callback xml read');
});
 
console.log("this will run before the above callback.");　
