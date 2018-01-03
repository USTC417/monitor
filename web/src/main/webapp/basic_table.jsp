<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
  
<head>
    <meta charset="utf-8">
    
<meta http-equiv="X-UA-Compatible" content="IE=edge">
    
<meta name="viewport" content="width=device-width, initial-scale=1">

    
<title>Bootstrap 3, from LayoutIt!</title>

    
<meta name="description" content="Source code generated using layoutit.com">
    
<meta name="author" content="LayoutIt!">

    
<link href="css/bootstrap.min.css" rel="stylesheet">
    
<link href="css/style.css" rel="stylesheet">
<script type="text/javascript" src="./js/jquery.min.js"/>
 </head>
 
 <body>


 <script type="text/javascript">
	 var html="";
     window.onload=function() {
         var ws;
         ws = new WebSocket("ws://localhost:9003");
         ws.onopen = function () {

         };
         ws.onmessage = function (e) {
             var msg = JSON.parse(e.data);
             var client_id = msg.client_id;
             var status = msg.status;
             location.reload();
             if (status == 1){
                 alert("客户机"+client_id+"连接了");
				 html ="<tr>\n" +
                    "\t\t\t\t\t\t\t<td style=\"vertical-align: middle\">\n" +
                    client_id +
                    "\t\t\t\t\t\t\t</td>\n" +
                    "\t\t\t\t\t\t\t<td style=\"vertical-align: middle\">\n" +
                    "\t\t\t\t\t\t\t\t<a href=\"clientInfo?clientId="+clientId+">"+client_id+"</a>\n" +
                    "\t\t\t\t\t\t\t</td>\n" +
                    "\t\t\t\t\t\t\t<td style=\"vertical-align: middle\">\n" +
                    "\t\t\t\t\t\t\t\t${ '在线'}\n" +
                    "\t\t\t\t\t\t\t</td>\n" +
                    "\t\t\t\t\t\t\t<td>\n" +
                    "\t\t\t\t\t\t\t\t<div class=\"btn-group\">\n" +
                    "\t\t\t\t\t\t\t\t\t<button class=\"btn btn-default\">操作</button> <button data-toggle=\"dropdown\" class=\"btn btn-default dropdown-toggle\"><span class=\"caret\"></span></button>\n" +
                    "\t\t\t\t\t\t\t\t\t<ul class=\"dropdown-menu\">\n" +
                    "\t\t\t\t\t\t\t\t\t\t<li >\n" +
                    "\t\t\t\t\t\t\t\t\t\t\t<a href=\"clientInfo?clientId="+client_id+">详情</a>\n" +
                    "\t\t\t\t\t\t\t\t\t\t</li>\n" +
                    "\t\t\t\t\t\t\t\t\t\t<li>\n" +
                    "\t\t\t\t\t\t\t\t\t\t\t<a href=\"clientRestart?clientId="+client_id+">重启</a>\n" +
                    "\t\t\t\t\t\t\t\t\t\t</li>\n" +
                    "\t\t\t\t\t\t\t\t\t\t<li class=\"divider\">\n" +
                    "\t\t\t\t\t\t\t\t\t\t</li>\n" +
                    "\t\t\t\t\t\t\t\t\t\t<li>\n" +
                    "\t\t\t\t\t\t\t\t\t\t\t<a href=\"clientClose?clientId="+client_id+">关机</a>\n" +
                    "\t\t\t\t\t\t\t\t\t\t</li>\n" +
                    "\t\t\t\t\t\t\t\t\t</ul>\n" +
                    "\t\t\t\t\t\t\t\t</div>\n" +
                    "\t\t\t\t\t\t\t</td>\n" +
                    "\t\t\t\t\t\t</tr>";
                    html += $("table tbody").html();

                 $("table tbody").innerHTML = html;
                 location.reload();
             }else if (status == 0){
                 alert("客户机"+client_id+"断线了");
			 }


         };
         ws.onclose = function () {
             print("close")
             ws = null;
         };
     };
//	 $(function(){
 //        if(html!="")
             location.reload();
//	 });
 </script>


<nav class="navbar navbar-fixed-top navbar-inverse " role="navigation">
				<div class="navbar-header">
                    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"> <span class="sr-only">Toggle navigation</span><span class="icon-bar"></span><span class="icon-bar"></span><span class="icon-bar"></span></button> <a class="navbar-brand" href="#">集群监控</a>
                </div>
                
                <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                    <ul class="nav navbar-nav">
                        <li class="active">
                            <a href="#">集群列表</a>
                        </li>
                        
                    </ul>
                    
                    <ul class="nav navbar-nav navbar-right">
                        
                        <li class="dropdown">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown">用户名<strong class="caret"></strong></a>
                            <ul class="dropdown-menu">
                                <li>
                                    <a href="#">个人中心</a>
                                </li>
                                <li>
                                    <a href="password.html">账号设置</a>
                                </li>
                                
                                <li class="divider">
                                </li>
                                <li>
                                    <a href="login.html">退出</a>
                                </li>
                            </ul>
                        </li>
                    </ul>
                    <form class="navbar-form navbar-right" role="search">
                        <div class="form-group">
                            <input type="text" class="form-control" />
                        </div> <button type="submit" class="btn btn-default">Submit</button>
                    </form>
                </div>
                
</nav>
 <style>
	 #top{
		 padding-top: 70px;
	 }
 </style>
<div class="container" id="top">
	<div class="row clearfix">
		<div class="col-md-12 column">
			
		</div>
	</div>
	<div class="row clearfix">
		<div class="col-md-12 column">
			<table class="table table-bordered table-hover">
				<thead>
					<tr>
						<th>
							编号
						</th>
						<th>
							IP/MAC
						</th>
						<th>
							状态
						</th>
						<th>
							操作
						</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${clients}" var="client">
						<tr>
							<td style="vertical-align: middle">
								${client.clientId}
							</td>
							<td style="vertical-align: middle">
								<a href="clientInfo?clientId=${client.clientId}">${client.clientName}</a>
							</td>
							<td style="vertical-align: middle">
								${client.status ==1 ? '在线':'掉线'}
							</td>
							<td>
								<div class="btn-group">
									<button class="btn btn-default">操作</button> <button data-toggle="dropdown" class="btn btn-default dropdown-toggle"><span class="caret"></span></button>
									<ul class="dropdown-menu">
										<li >
											<a href="clientInfo?clientId=${client.clientId}">详情</a>
										</li>
										<li>
											<a href="clientRestart?clientId=${client.clientId}">重启</a>
										</li>
										<li class="divider">
										</li>
										<li>
											<a href="clientClose?clientId=${client.clientId}">关机</a>
										</li>
									</ul>
								</div>
							</td>
						</tr>
					</c:forEach>

				</tbody>
			</table>
			<ul class="pagination">
				<li>
					 <a href="#">Prev</a>
				</li>
				<li>
					 <a href="#">1</a>
				</li>
				<li>
					 <a href="#">2</a>
				</li>
				<li>
					 <a href="#">3</a>
				</li>
				<li>
					 <a href="#">4</a>
				</li>
				<li>
					 <a href="#">5</a>
				</li>
				<li>
					 <a href="#">Next</a>
				</li>
			</ul>
			
		</div>
	</div>
</div>






<script src="js/jquery.min.js"></script>
    
<script src="js/bootstrap.min.js"></script>
    
<script src="js/scripts.js"></script>

 <script src="js/basic_table.js"></script>

 </body>

</html>
