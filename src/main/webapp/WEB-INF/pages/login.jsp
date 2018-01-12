<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@page session="true"%>
<html>
<head>
	<title>Login Page</title>

</head>
<body onload='document.loginForm.username.focus();'>

<style>
	body {
		background-color: gainsboro;
	}

	h3 {
		font-family: 'HelveticaNeueCyr Light';
	}

	TABLE {
		/*margin-top: 100px;*/
		/*margin-top: 50px;*/
		width: 50%;
		font-size: 1em;
		font-family: 'HelveticaNeueCyr Light';
		letter-spacing: 1px;
		border-radius: 10px;
		border-spacing: 0;
		text-align: center;
		box-shadow: 0 0 10px rgba(0,0,0,0.5);
	}
	TD, TH {
		padding: 3px;
		border: 0px solid black;
	}
	TH {
		background: #08dd00;
		color: white;
		text-shadow: 0 1px 1px #2D2020;
		padding: 10px 20px;
	}
	/*th, td {*/
		/*border-style: solid;*/
		/*border-width: 0 1px 1px 0;*/
		/*border-color: gainsboro;*/
	/*}*/

	td {
		padding: 10px 20px;
		background: #F8E391;
	}

	th:first-child {
		border-top-left-radius: 10px;
	}

	th:last-child {
		border-top-right-radius: 10px;
		border-right: none;
	}

	tr:last-child td:first-child {
		border-radius: 0 0 0 10px;
	}

	tr:last-child td:last-child {
		border-radius: 0 0 10px 0;
	}

	tr td:last-child {
		border-right: none;
	}

	.button {
		background-color: #08dd00;
		border: none;
		color: white;
		padding: 10px 25px;
		text-align: center;
		text-decoration: none;
		display: inline-block;
		font-size: 1em;
		border-radius: 10px 10px 10px 10px;
	}

	#linkHeader {
		/*margin-top: 100px;*/
		width: 100%;
		height: 50px;
		background-color: #07b500;
		box-shadow: 0 0 10px rgba(0,0,0,0.5);
	}

	#menu {
		margin: 0 auto;
	}

	#menu>li {
		color: #ffffff;
		font-size: 1.5em;
		margin-right: 50px;
		font-family: 'Bebas Neue';
		list-style-type: none;
		letter-spacing: 1px;
		margin-top: 10px;
	}

	ul {
		text-align: center;
	}

	li {
		display: inline-block;
	}

	#container {
		/*margin-top: 100px;*/
		margin-top: 50px;
		width: 80%;
		padding: 30px;
		background-color: #ffffd2;
		box-shadow: 0 0 10px rgba(0,0,0,0.5);
	}
	a {
		text-decoration: none;
	}

	a:visited {
		color: white;
	}
</style>

<center>
	<div id="linkHeader">
		<ul id="menu">
			<sec:authorize access="hasAuthority('user')">
				<li><a href="/shop-app/user/product">Products</a></li>
			</sec:authorize>
			<sec:authorize access="hasAuthority('user')">
				<li><a href="/shop-app/user/orders">Orders</a></li>
			</sec:authorize>
			<sec:authorize access="hasAuthority('user')">
				<li><a href="/shop-app/user/cart">Cart</a></li>
			</sec:authorize>
			<sec:authorize access="hasAuthority('user')">
				<li><a href="javascript:formSubmit()">Logout</a></li>
			</sec:authorize>
			<sec:authorize access="!isAuthenticated()">
				<li><a href="/shop-app/">Products</a></li>
			</sec:authorize>
			<sec:authorize access="!isAuthenticated()">
				<li><a href="/shop-app/register/">Register</a></li>
			</sec:authorize>
			<sec:authorize access="!isAuthenticated()">
				<li><a href="/shop-app/login">Log in</a></li>
			</sec:authorize>
			<sec:authorize access="hasAuthority('admin')">
				<li><a href="/shop-app/admin/product">Products</a></li>
			</sec:authorize>
			<sec:authorize access="hasAuthority('admin')">
				<li><a href="/shop-app/admin/order">Orders</a></li>
			</sec:authorize>
			<sec:authorize access="hasAuthority('admin')">
				<li><a href="/shop-app/admin/category">Categories</a></li>
			</sec:authorize>
			<sec:authorize access="hasAuthority('admin')">
				<li><a href="javascript:formSubmit()">Logout</a></li>
			</sec:authorize>

		</ul>
	</div>


<div id="container">

	<h3>Login with Username and Password</h3>

	<form name='loginForm'
		  action="<c:url value='/login' />" method='POST'>

		<table>
			<tr>
				<td>User:</td>
				<td><input type='text' name='username'></td>
			</tr>
			<tr>
				<td>Password:</td>
				<td><input type='password' name='password' /></td>
			</tr>
			<tr>
				<td colspan='2'><input class="button" name="submit" type="submit"
									   value="submit" /></td>
			</tr>
		</table>

		<input type="hidden" name="${_csrf.parameterName}"
			   value="${_csrf.token}" />

	</form>
</div>
</center>
</body>
</html>