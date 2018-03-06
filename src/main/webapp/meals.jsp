<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://sargue.net/jsptags/time" prefix="javatime" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Meals</title>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<h2>Meals</h2>


<table border="2">
    <tr>
        <th>Дата</th>
        <th>Описание</th>
        <th>Количество калорий</th>
        <th>Переел ли ты в этот день</th>
    </tr>
    <c:forEach var="meal" items="${meal_list}">

        <tr bgcolor = ${meal.exceed == true ? "#FF5252" : "#64FFDA"}>

        <%--<c:if test="${meal.exceed == true}">
            <tr bgcolor = red>
        </c:if>

        <c:if test="${meal.exceed == false}">
            <tr bgcolor = green>
        </c:if>--%>

        <%--<td> ${meal.dateTime} </td>--%>
        <javatime:parseLocalDateTime value="${meal.dateTime}" pattern="yyyy-MM-dd'T'HH:mm" var="parsedDate"/>
        <td><javatime:format value="${parsedDate}" style="MS"/> </td>
        <td> ${meal.description} </td>
        <td> ${meal.calories} </td>
        <td> ${meal.exceed} </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>