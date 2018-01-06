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
	<script src="js/jquery.min.js"></script>

	<script src="js/bootstrap.min.js"></script>

	<script src="js/scripts.js"></script>

</head>

<body>

<nav class="navbar navbar-fixed-top navbar-inverse" role="navigation">
	<div class="navbar-header">
		<button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"> <span class="sr-only">Toggle navigation</span><span class="icon-bar"></span><span class="icon-bar"></span><span class="icon-bar"></span></button> <a class="navbar-brand" href="#">集群监控</a>
	</div>

	<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
		<ul class="nav navbar-nav">


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
	<div class="container">
		<div class="row clearfix">
			<table class="table table-bordered table-hover">

				<tbody>

				<tr>
					<td style="vertical-align:middle">主机编号:</td>
					<td style="vertical-align: middle">${client.clientId}</td>
					<td style="vertical-align: middle"></td>
				</tr>
				<tr>
					<td style="vertical-align:middle">主机名:</td>
					<td onDblClick="show2(this,'clientName','${client.clientName}');">
						<input type="hidden" id="clientName" name="clientName" value="${client.clientName}">${client.clientName}</td>
					<td style="vertical-align: middle"></td>
				</tr>
				<tr>
					<td style="vertical-align: middle">IP地址:</td>
					<td onDblClick="show2(this,'clientIp','${client.clientIp}');">
						<input type="hidden" id="clientIp" name="clientIp" value="${client.clientIp}">${client.clientIp}</td>
					<td style="vertical-align: middle"></td>
				</tr>
				<tr>
					<td style="vertical-align: middle">在线状态:</td>
					<td style="vertical-align: middle">${client.status ==1 ? '在线':'掉线'}</td>
					<td style="vertical-align: middle"></td>
				</tr>
				<tr>
					<td style="vertical-align: middle">系统类型:</td>
					<td style="vertical-align: middle">${client.clientSystem}</td>
					<td style="vertical-align: middle"></td>
				</tr>
				<tr>
					<td style="vertical-align: middle">内存大小:</td>
					<td style="vertical-align: middle">${client.clientRam}G</td>
					<td style="vertical-align: middle"></td>
				</tr>
				<tr>
					<td style="vertical-align: middle">硬盘大小:</td>
					<td style="vertical-align: middle">${client.clientStorage}G</td>
					<td style="vertical-align: middle"></td>
				</tr>

				<tr><td colspan="2"><input style="margin-left: 100px;margin-right: 80px;" type="button" id="submit" value="保存"/><input type="button" id="reBack" value="返回"/></td></tr>

				</tbody>
			</table>
		</div>
	</div>

</div>


<script>
    function show2(obj,names,valu){
        //  var obj = document.getElementById("")
        obj.innerHTML = "<input type='text' id='"+names+"' name='"+names+"' value='"+ valu +"'>";

    }
    function show(obj,names,valu){

        obj.innerHTML = "<input type='text' name='"+names+"' value='"+ valu +"'>";

    }

</script>
<script>
    $(function(){
        $("#submit").click(function(){
            var str = document.getElementsByName(clientIp).value;
            if(str == "${client.clientIp}"){
                alert("error");
            }else{
                alert("ok");
            }
          /*  $.post("updateClientInfo",{
                clientId:"${client.clientId}",
                clientName:"$('#clientName').val()",
                clientIp:"$('#clientIp').val()"
            });*/

            $.ajax({
                type : "post",
                url : "updateClientInfo",
                data :  { clientId:"${client.clientId}",
                    clientName:$('#clientName').val(),
                    clientIp:$('#clientIp').val()},
				success:function(result){

                    location.reload();
                }

            });
        } );

        $("#reBack").click(function(){
            history.go(-1);
        });
    });
</script>



</body>
</html>