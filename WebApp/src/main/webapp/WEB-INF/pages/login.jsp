<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<c:import url="header.jsp" charEncoding="UTF-8"></c:import>
<link rel="stylesheet"
	href="<c:url value="/resources/css/style-login.css"/>" />
<title>login page</title>
</head>
<body>

	<div id="header"></div>

	<div id="content">
		<div id="login">
			<form action="<c:url value="/user/list"/>" method="post"
				class="form-horizontal">
				<div class="control-group">
					<label class="control-label" for="log">Login</label>
					<div class="controls">
						<input type="text" name="login" placeholder="login" id="log"
							required pattern="[A-Za-z0-9]{1,30}">
						<!--  required pattern="^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\.[a-zA-Z0-9-]+)*$" -->
					</div>
				</div>
				<div class="control-group">
					<label class="control-label" for="password">Password</label>
					<div class="controls">
						<input type="password" name="password" placeholder="Password"
							id="password" required pattern="[A-Za-z0-9]{1,30}">
					</div>
				</div>
				<div class="control-group">
					<div class="controls">
						<button type="submit" class="btn">Sign in</button>
						<!-- <a href="<c:url value="/user/registr"/>" class="btn btn-primary" style="width: 120px">Registration</a> -->
					</div>
				</div>

			</form>
		</div>
	</div>


</body>
</html>