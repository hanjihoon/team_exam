<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:set var="root" value="${pageContext.request.contextPath}" />
<c:set var="ver" value="?version=1.0.0" />

<%
	String sessionId = (String)session.getId();
%>

<c:set var="isLogin" value="${(empty sessionScope.user)?false:true}" />
<c:set var="Log" value="${(empty sessionScope.user)?'login':'logout'}" />
<c:set var="acceptSize" value="${sessionScope.acceptSize}" />

<script src="/js/jquery-3.3.1.js"></script>
<script src="/js/components/visibility.js"></script>
<script src="/js/components/sidebar.js"></script>
<script src="/js/components/transition.js"></script>
<script src="/js/components/form.js"></script>
<script src="/js/components/dropdown.js"></script>
<script src="/js/components/dimmer.js"></script>
<script src="/js/components/modal.js"></script>
<script src="/js/components/accordion.js"></script>
<script src="/js/components/shape.js"></script>
<script src="/js/components/checkbox.js"></script>
<script src="/js/components/popup.js"></script>
<script src="/js/common.js"></script>
<script src="/js/map/d3.v3.min.js"></script>
<script src="/js/map/topojson.v0.min.js"></script>

<link rel="stylesheet" type="text/css" href="/css/components/reset.css">
<link rel="stylesheet" type="text/css" href="/css/components/site.css">
<link rel="stylesheet" type="text/css"
	href="/css/components/container.css">
<link rel="stylesheet" type="text/css" href="/css/components/grid.css">
<link rel="stylesheet" type="text/css" href="/css/components/header.css">
<link rel="stylesheet" type="text/css" href="/css/components/image.css">
<link rel="stylesheet" type="text/css" href="/css/components/menu.css">
<link rel="stylesheet" type="text/css" href="/css/components/divider.css">
<link rel="stylesheet" type="text/css"
	href="/css/components/dropdown.css">
<link rel="stylesheet" type="text/css"
	href="/css/components/segment.css">
<link rel="stylesheet" type="text/css" href="/css/components/form.css">
<link rel="stylesheet" type="text/css" href="/css/components/list.css">
<link rel="stylesheet" type="text/css" href="/css/components/icon.css">
<link rel="stylesheet" type="text/css"
	href="/css/components/sidebar.css">
<link rel="stylesheet" type="text/css"
	href="/css/components/transition.css">
<link rel="stylesheet" type="text/css" href="/css/components/input.css">
<link rel="stylesheet" type="text/css" href="/css/components/button.css">
<link rel="stylesheet" type="text/css"
	href="/css/components/message.css">
<link rel="stylesheet" type="text/css" href="/css/components/dimmer.css">
<link rel="stylesheet" type="text/css" href="/css/components/modal.css">
<link rel="stylesheet" type="text/css" href="/css/components/table.css">

<link rel="stylesheet" type="text/css" href="/css/components/accordion.css">
<link rel="stylesheet" type="text/css" href="/css/components/shape.css">
<link rel="stylesheet" type="text/css" href="/css/components/checkbox.css">
<link rel="stylesheet" type="text/css" href="/css/components/loader.css">
<link rel="stylesheet" type="text/css" href="/css/components/card.css">
<link rel="stylesheet" type="text/css" href="/css/components/label.css">
<link rel="stylesheet" type="text/css" href="/css/components/popup.css">
<%@ include file="/WEB-INF/views/common/header.jsp"%>
