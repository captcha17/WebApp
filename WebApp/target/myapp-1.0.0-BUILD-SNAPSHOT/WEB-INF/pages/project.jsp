<%@page contentType="text/html;charset=UTF-8" language="java"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html>
<head>

<c:import url="header.jsp" charEncoding="UTF-8"></c:import>
<script src="<c:url value="/resources/js/jquery-1.7.1.js"/>"></script>
<script src="<c:url value="/resources/js/basic.js"/>"></script>
</head>
<body>

	<div id="header"></div>

	<div id="sidebar">
		<form class="form-horizontal" action="<c:url value="/user/addUser"/>"
			id="newUser">
			<div class="control-group">
				<input type="text" name="login" id="login" placeholder="login">
			</div>
			<div class="control-group">
				<input type="password" name="password" id="Password"
					placeholder="Password">
			</div>
			<div class="control-group">
				<button type="submit" class="btn">Add new developer</button>
			</div>
		</form>
		<p id="hidetext" style="display: none; color: green;">SUCCESS!!!!</p>
		<br>
		<br> <select multiple="multiple">
			<c:forEach items="${listUsers}" var="log">
				<option>${log.login}</option>
			</c:forEach>
		</select>
	</div>

	<div id="content">

		<div style="margin: 0px auto; width: 1000px;">
			<h2>
				<c:out value="${projectUnique.nameProject}" />
			</h2>
			<br> <img
				src="<c:url value="/user/image?index=${projectUnique.id}"/>"
				height="220" width="300">
			<c:out value="${projectUnique.description}" />
			<br>
			<br>

			<div>
				<span>Tasks:</span><a href="#" onclick="$('.addTask').show();"
					class="btn btn-mini">Add</a><br>
				<br>
				<form
					action="<c:url value="/user/addTask?index=${projectUnique.id}"/>"
					method="post" class="addTask" style="display: none;">
					<label class="control-label" for="task">Target of task</label>
					<div class="controls">
						<textarea name="task" id="task" rows="3"></textarea>
					</div>
					<button type="submit" class="btn btn-mini">Submit</button>
				</form>
				<c:forEach items="${listTask}" var="taskProj">
					<p style="width: 400px">
						<c:out value="${taskProj.nameTask}" />
					</p>
				</c:forEach>
			</div>

			<!--  	<p><h4>Оставить свой комментарий : </h4></p>
			<form  action="<c:url value="/navigation/comment"/>" id="newComment">
				<textarea name="comment" id="comment" rows="6" cols="40" style="width:50%"></textarea><br>
				<button type="submit" class="btn">Отправить</button>
			</form>
			<p id="hideLogin" style="display: none;">${login}</p>
			<p id="getcomment"/><br>
			<hr/>
			<p class="text-info"><h5>Комментарии:</h5> </p>
			</div>	
		
			<c:forEach items="${listComments}" var="com" begin="0" end="9" step="1">					
			<div style="margin:0px auto;width:500px;">
				<c:out value="${com.login}" /><br>
				<c:out value="${com.comment}" /><br><br>					
			</div>
			</c:forEach>
	-->
		</div>
	</div>

	<div id="footer"></div>

</body>
</html>