<%@page contentType="text/html;charset=UTF-8" language="java"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>
<head>
<c:import url="header.jsp" charEncoding="UTF-8"></c:import>
<script src="<c:url value="/resources/js/jquery-1.7.1.js"/>"></script>
<script src="<c:url value="/resources/js/basic.js"/>"></script>
</head>
<body>

	<div id="header" style="width: 100%; text-align: center;">
		<h2 class="text-warning">Projects</h2>
		<a href="#myModal" data-toggle="modal" class="btn">Add Project</a>
	</div>

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
		<br>
		<form action="<c:url value="/user/deleteUser"/>">
			<select name="developer" multiple="multiple">
				<c:forEach items="${listUsers}" var="log">
					<option value="${log.login}">${log.login}</option>
				</c:forEach>
			</select>
			<button type="submit" class="btn">Delete</button>
		</form>
	</div>

	<div id="content">

		<div>
			<ul>
				<c:forEach items="${listProject}" var="proj">
					<li>
						<div style="margin: 0px auto; width: 100%; position: relative;">
							<a href="<c:url value="/user/delete?index=${proj.id}"/>"
								class="show" style="display: none;">&nbsp;</a><a
								href="<c:url value="/user/project?index=${proj.id}"/>"><h2
									class="text-success">
									<c:out value="${proj.nameProject}" />
								</h2> </a> <a href="#"><img
								src="<c:url value="/user/image?index=${proj.id}"/>" height="170"
								width="220"></a>
							<c:out value="${proj.description}" />

						</div>
					</li>
				</c:forEach>
			</ul>
		</div>


		<div class="modal hide fade" id="myModal" tabindex="-1" role="dialog">
			<c:url value="/user/addProject" var="url" />
			<form:form action="${url}" method="post" commandName="project"
				enctype="multipart/form-data" modelAttribute="uploadform">

				<div class="modal-header">
					<button class="close" type="button" data-dismiss="modal">X</button>
					<h3 id="myModalLabel">Add new project</h3>
				</div>
				<div class="modal-body">

					<h4>Project name</h4>
					<input type="text" id="nameProject" name="nameProject"
						style="width: 100%" />
					<h4>image</h4>
					<input type="file" required="" id="image" name="files[0]">
					<h4>Information</h4>
					<textarea name="description" id="description" rows="7"
						style="width: 100%"></textarea>

				</div>
				<div class="modal-footer">
					<input type="submit" class="btn btn-primary" value="Save">
					<button class="btn" data-dismiss="modal">Close</button>
				</div>
			</form:form>
		</div>

	</div>



</body>
</html>