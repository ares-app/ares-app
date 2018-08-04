<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%String path = request.getContextPath();%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    
    <title>user login</title>
	<script type="text/javascript" src="<%=path%>/js/jquery-3.0.0.min.js"></script>
		
	<script type="text/javascript">
		var app='<%=path%>';
		var url='';
		var param='';
		function login(){
			$('#fm').submit();	
		}
		//mobile normal login => username,password
		function mobile_nor_login_admin(){
			var url=app + "/j_spring_security_check?mobile=true";
			$.post(url, { j_username: "admin", j_password: "admin"},function(r){
				$("#t_out").text(r.jwt1)
				//alert(r.jwt1);
				//alert(r.jwt2);
			},"json");
		}
		//mobile normal login => username,password
		function mobile_nor_login_oapi(){
			var url=app + "/j_spring_security_check?mobile=true";
			$.post(url, { j_username: "oapi", j_password: "oapi"},function(r){
				$("#t_out").text(r.jwt1+"\n"+r.jwt2)
				//alert(r.jwt1);
				//alert(r.jwt2);
			},"json");
		}
		//mobile auto login => jwt
		function mobile_jwt_login_admin(){
			var url=app + "/malogin.json";
			$.post(url, { jwt: "eyJhbGciOiJBMTI4S1ciLCJlbmMiOiJBMTI4Q0JDLUhTMjU2In0.cZqkywPI-DI3GFM94Zg--RDQlYnUG-FSpStIMH4nEYFHi5_69gr1YA._FtlLsFd8bMqHk56ls6G0w.slnApx-uX9ICfJbx3AAXv2vqLHlUW0675lX3LzeoI-Lih_AwEMUTBttOyR3eGgE-a_HVaknUgohf_sYPAeSjWu3_5j_3IqqdFB_OzATWYNP7lOHmMW_aZ7RP71V8VHg7zYjFmjp73n9V5EUmNdeqsneVau-uTImBTexQUJt9dBzVkBbylbXWSdwbCJFXqunr.Ky-Vv91G7vw1wbYc3n9p1A"},function(r){
				alert(r.msg);
			},"json");
		}
		//mobile auto login => jwt
		function mobile_jwt_login_oapi(){
			var url=app + "/malogin.json";
			$.post(url, { jwt: "eyJhbGciOiJBMTI4S1ciLCJlbmMiOiJBMTI4Q0JDLUhTMjU2In0.QxzC8uczWKRGzkCY5AGq6WUDO6HuYaJL7v8gjtDjcwTztHdYQCar8g.D5QWpidkkmYRX9tSrSxjTg.D-3Xt7qxUGIoCMnFktQhqWzuH-e3o1abnNWoj2zCowOkbAswj-5LlYH57t3sfYt44fu5iHnzl73h7iDBIEi8Kjc4FTWqIGASTU2HBTvIn4mTMS3QMPgmRC5IAzFi2eTJZbMQutuuD5_fx0DokZ_fZfO80GN63tiKy69W94dmgdSQE2k_qQim53I_HllfHWal.FiEnc9znYKtSNTN1XqOV-Q"},function(r){
				alert(r.msg);
			},"json");
		}
		//need auth oapi
		function demo_auth(){
			var url=app + "/demo/getdemoauth.json";
			$.post(url, { demoid: "oapi"},function(r){
				alert(r.msg);
			},"json");
		}
		//need auth admin
		function demo_authx(){
			var url=app + "/demo/getdemoauthx.json";
			$.post(url, { demoid: "admin"},function(r){
				alert(r.msg);
			},"json");
		}
		//needn't auth
		function demo(){
			var url=app + "/demo/getdemo.json";
			$.post(url, { demoid: "any-user"},function(r){
				alert(r.msg);
			},"json");
		}
	</script>
  </head>
  
<body>
   
    <div>
    <form id="fm" method="post" action="<%=path%>/j_spring_security_check">
    	<table width="60%" align = "center">
			<tr>
				<td align="center">
					<label style="font-weight:bold; font-size:1.1em;">用户名：</label>
					<input name="j_username" style="width:160px"  />
				</td>
			</tr>
			<tr>
				<td align="center">
					<label style="font-weight:bold; font-size:1.1em;">密码：</label>
					<input type="password" style="width:160px" name="j_password" />
				</td>
			</tr>
			<tr align="center">
				<td >
					<a href="#" onclick="login()" >login</a>
					<br/><br/>
					<a href="#" onclick="mobile_nor_login_admin()" >mobile_login_admin</a>
					<a href="#" onclick="mobile_nor_login_oapi()" >mobile_login_oapi</a>
					<br/><br/>
					<a href="#" onclick="mobile_jwt_login_admin()" >jwt_login_admin</a>
					<a href="#" onclick="mobile_jwt_login_oapi()" >jwt_login_oapi</a>
					<br/><br/>
					<a href="#" onclick="demo_auth()" >demo_auth-oapi</a>
					<a href="#" onclick="demo_authx()" >demo_auth-admin</a>
					<a href="#" onclick="demo()" >demo-anyuser</a>
					<a href="<%=path%>/demopage.json">demopage</a>
				</td>
			</tr>
			<tr align="center">
				<td><textarea rows="15" cols="100" id="t_out"></textarea></td>
			</tr>
		</table>
	</form>
    </div>
  </body>
</html>
