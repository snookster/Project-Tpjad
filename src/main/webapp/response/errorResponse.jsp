<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page contentType="application/json; charset=UTF-8" %>
<% int statusCode = Integer.parseInt(request.getAttribute("errorCode").toString());%>
<% response.setStatus(statusCode); %>
{"error": "<s:property value="#request.errorMsg"/>"}