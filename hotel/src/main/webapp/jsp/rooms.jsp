<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="C" uri="http://java.sun.com/jsp/jstl/xml" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Nasze pokoje</title>
</head>

<body>
<jsp:include page="classic.jsp"/>
<script type="text/javascript">

    $(document).ready(function () {
        $('li.active').removeClass('active');
    });
</script>
<c:if test="${!empty listOfRooms}">
    <h2>Lista wolnych pokoi do wynajęcia</h2>
    <form class="centeredButton"
          action="<c:url value="/reservation/reserveRoom"/>?startTime=${startTime}&finishTime=${finishTime}"
          method="POST">
        <h4>od ${startTime} do ${finishTime}</h4>
        <table align="center" id="roomTable" class="sortable" cellspacing="0">
            <tr>
                <th>Piętro</th>
                <th>Max. osobowy</th>
                <th>Cena za noc na osobę</th>
                <th>Kierunek umiejscowienia okna</th>
                <th>Rezerwuj</th>


            </tr>
            <c:forEach items="${listOfRooms}" var="room">
                <tr>
                    <c:if test="${room.floor == 0}">
                        <td>parter</td>
                    </c:if>
                    <c:if test="${room.floor != 0}">
                        <td>${room.floor}</td>
                    </c:if>
                    <td>${room.maxNumberOfGuests}</td>
                    <td>${room.price}</td>
                    <td>${room.windowsOrientation}</td>
                    <td>
                        <input type="checkbox" name="rooms" value="${room.id}">
                    </td>
                </tr>
            </c:forEach>
        </table>
        <div class="centered">
            <button class="btn btn-lg btn-primary btn-block" name="button" type="submit">Zarezerwuj</button>
        </div>
    </form>
</c:if>
<c:if test="${empty listOfRooms}">
    <h2>Niestety nie mamy wolnych pokoi w danych terminie</h2>
</c:if>
</body>
</html>
