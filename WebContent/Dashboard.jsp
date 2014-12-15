
<%@page import="com.properties.MessageAndPropertiesDisplay"%>
<%@page import="java.util.Iterator"%>
<%@page import="com.bean.UserSerachedNews"%>
<%@page import="com.bean.Document"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Set"%>
<%@page import="java.util.HashSet"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<html>
<head>
<meta charset="utf-8">
<title><%=MessageAndPropertiesDisplay.websitetitle%></title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">
<link href="css/bootstrap.min.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="js/bootstrap.min.js"></script>
</head>
<%
	String email = (String) session.getAttribute("email");
	if (email == null) {
		String redirectURL = "login";
		response.sendRedirect(redirectURL);
	}
	int i = 0;
	Set<UserSerachedNews> userNewsList = (Set<UserSerachedNews>) session
			.getAttribute("userNewsList");
%>
<body class="" draggable="true">
	<div class="navbar navbar-default navbar-static-top navbar-inverse">
		<div class="container">
			<div class="navbar-header">
				<a type="button" class="navbar-toggle" data-toggle="collapse"
					data-target=".navbar-collapse"></a> <span class="sr-only">Toggle
					navigation</span> <span class="icon-bar"></span> <span class="icon-bar"></span>
				<span class="icon-bar"></span>
			</div>
			<div class="collapse navbar-collapse navbar-ex1-collapse">
				<ul class="nav navbar-nav navbar-right" style="">
					<li draggable="true"><a href="logout">Logout</a></li>
				</ul>
			</div>
		</div>
	</div>
	<div class="container">
		<div class="row">
			<div class="col-md-3">
				<div class="row" draggable="true">
					<div class="col-md-12">
						<h3>${name}...</h3>
					</div>
				</div>
				<div class="row">
					<div class="col-md-12">
						<h4><%=MessageAndPropertiesDisplay.tagline%></h4>
					</div>
				</div>
				<%
					if (userNewsList != null) {
						int k = 0;
				%>
				<div class="row">
					<div class="col-md-12">
						<h4>Search tags</h4>
						<hr draggable="true">
					</div>
				</div>

				<div class="row">

					<div class="col-md-12">
						<%
							Iterator<UserSerachedNews> itr = userNewsList.iterator();
								while (itr.hasNext()) {
									UserSerachedNews userNews = itr.next();
									i++;

									if (i > MessageAndPropertiesDisplay.recCount)
										break;
						%>

						<a href="search?searchWord=<%=userNews.getWord()%>"><%=userNews.getWord()%>
							<span class="badge"><%=userNews.getCount()%></span></a>

						<%
							}
						%>
					</div>
				</div>
				<%
					}
				%>
				<c:if test="${suggustedResult!=null}">
					<%
						List<Document> suggustedResult = (List<Document>) session
									.getAttribute("suggustedResult");
					%>

					<div class="row">
						<div class="col-md-12">
							<h4>Suggested News</h4>
							<hr draggable="true">
						</div>
					</div>
					<div class="row">
						<div class="col-md-12">
							<nav>
								<ul class="pager">
									<!-- if number size of list is eq to number of news to be displayed in a page button next will not be displayed -->
									<c:if test="${btnValue1!=''}">
										<li><a href="snews_p">${btnValue1}</a></li>
									</c:if>

								</ul>
							</nav>
							<%
								if (session.getAttribute("sugStartIndex") != null
											&& session.getAttribute("sugEndIndex") != null) {
										for (int j = (Integer) session
												.getAttribute("sugStartIndex"); j < (Integer) session
												.getAttribute("sugEndIndex"); j++) {
											if (j < suggustedResult.size()) {
												Document document = suggustedResult.get(j);
												if (document != null) {
							%>
							<c:set var="doc1" value="<%=document%>"></c:set>
							<h4><%=document.getTitle()%></h4>
							<p>${fn:substring(doc1.bodyFullText, 0, 300)}</p>
							<a href="read_s?id=<%=document.getId()%>">Read</a>

							<%
								}
											}
										}
									}
							%>
						</div>
					</div>
					<div class="row">
						<div class="col-md-12" a>
							<nav>
								<ul class="pager">
									<!-- if number size of list is eq to number of news to be displayed in a page button next will not be displayed -->
									<c:if test="${btnValue!=''}">
										<li><a href="snews_n">${btnValue}</a></li>
									</c:if>

								</ul>
							</nav>
						</div>
					</div>
				</c:if>
			</div>
			<div class="col-md-9">
				<div class="row">
					<div class="col-md-12">
						<form:form action="search" method="post" commandName="searchForm">
							<div class="row">
								<div class="col-md-12">
									<form:input type="text" path="searchWord"
										class="form-control hasclear" placeholder="Search..." />
								</div>
							</div>
							<div class="row">
								<div class="col-md-12">
									<br>
									<button class="btn btn-primary btn-sm" type="submit">Search</button>

								</div>
							</div>
						</form:form>
					</div>
				</div>
				<c:if test="${result!=null}">


					<%
						List<Document> result = (List<Document>) session
									.getAttribute("result");
					%>
					<div class="row">
						<div class="col-md-7">
							<div class="row">
								<div class="col-md-12">
									<h4>${word}</h4>
									<hr draggable="true">
								</div>
							</div>


							<div class="row">
								<div class="col-md-12">
									<c:if test="${fn:length(result) gt 0}">
										<nav>
											<ul class="pager">
												<c:if test="${btnValueNews1!=''}">
													<li><a href="newsresult_p">${btnValueNews1}</a></li>
												</c:if>

											</ul>
										</nav>
									</c:if>
									<%
										if (session.getAttribute("newsStartIndex") != null
													&& session.getAttribute("newsEndIndex") != null) {
												for (int l = (Integer) session
														.getAttribute("newsStartIndex"); l < (Integer) session
														.getAttribute("newsEndIndex"); l++) {
													if (l < result.size()) {
														Document document = result.get(l);
														if (document != null) {
									%>
									<c:set var="doc2" value="<%=document%>"></c:set>
									<h4><%=document.getTitle()%></h4>
									<p>${fn:substring(doc2.bodyFullText, 0, 300)}</p>
									<a href="read_n?id=<%=document.getId()%>">Read</a>

									<%
										}
													}
												}
											}
									%>
								</div>
								<c:if test="${fn:length(result) gt 0}">
									<nav>
										<ul class="pager">
											<c:if test="${btnValueNews!=''}">
												<li><a href="newsresult_n">${btnValueNews}</a></li>
											</c:if>

										</ul>
									</nav>
								</c:if>
							</div>

						</div>


						<div class="col-md-4" draggable="true">



							<c:if test="${relList!=null}">

								<%
									List<Document> relList = (List<Document>) session
													.getAttribute("relList");
								%>
								<div class="row">
									<div class="col-md-12">
										<h4>Related News</h4>
										<hr draggable="true">
									</div>
								</div>

								<div class="row">
									<div class="col-md-12">
										<nav>
											<ul class="pager">
												<c:if test="${btnValueRelNews1!=''}">
													<li><a href="relnews_p">${btnValueRelNews1}</a></li>
												</c:if>

											</ul>
										</nav>

										<%
											if (session.getAttribute("rnewsStartIndex") != null
															&& session.getAttribute("rnewsEndIndex") != null) {
														for (int l = (Integer) session
																.getAttribute("rnewsStartIndex"); l < (Integer) session
																.getAttribute("rnewsEndIndex"); l++) {
															if (l < relList.size()) {
																Document document = relList.get(l);
																if (document != null) {
										%>
										<c:set var="doc3" value="<%=document%>"></c:set>
										<h4><%=document.getTitle()%></h4>
										<p>${fn:substring(doc3.bodyFullText, 0, 300)}</p>
										<a href="read_n?id=<%=document.getId()%>">Read</a>

										<%
											}
															}
														}
													}
										%>
									</div>
								</div>



								<div class="row">
									<div class="col-md-12">
										<nav>
											<ul class="pager">
												<c:if test="${btnValueRelNews!=''}">
													<li><a href="relnews_n">${btnValueRelNews}</a></li>
												</c:if>

											</ul>
										</nav>
									</div>
								</div>

							</c:if>

						</div>
					</div>
				</c:if>



			</div>
		</div>
	</div>
</body>

</html>