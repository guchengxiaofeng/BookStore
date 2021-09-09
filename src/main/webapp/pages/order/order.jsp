<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>我的订单</title>
    <%@include file="/pages/common/head.jsp" %>
    <style type="text-css">
        h1 {
            text-align: center;
            margin-top: 200px;
        }
    </style>
</head>

<body>
<div id="header">
    <img class="logo_img" alt="" src="../..atic/img/logo.gif">
    <span class="wel_word">我的订单</span>
    <%@include file="/pages/common/login_sucess_menu.jsp" %>
</div>
<div id="main">
    <table id="dataList">
        <tr>
            <td>日期</td>
            <td>金额</td>
            <td>状态</td>
            <td>详情</td>
        </tr>
    </table>
</div>
<script>
    window.onload = () => {
        const dataList = document.getElementById("dataList");
        let data = [];
        const xhr = new XMLHttpRequest();
        xhr.open('Get', "http://47.100.10.244:8080/BookStore/manager/orderServlet?action=showMyOrders");
        xhr.send();
        xhr.onreadystatechange = function () {
            if (xhr.readyState === 4) {
                if (xhr.status >= 200 && xhr.status < 300) {
                    data = JSON.parse(xhr.response);
                    data.forEach(item => {
                        const element = document.createElement("tr");
                        const date = document.createElement("td");
                        const price = document.createElement("td");
                        const isFinish = document.createElement("td");
                        const orderId=document.createElement("td");
                        date.innerHTML = item.create_Time;
                        price.innerHTML = item.price;
                        isFinish.innerHTML = item.status === 0 ? "未发送" : "已发送";
                        const a=document.createElement("a");
                        a.setAttribute("href","http://47.100.10.244:8080/BookStore/pages/order/orderDetail.jsp?orderId="+item.order_Id);
                        console.log(item)
                        a.innerHTML="查看详情";
                        orderId.appendChild(a);
                        element.appendChild(date);
                        element.appendChild(price);
                        element.appendChild(isFinish);
                        element.appendChild(orderId);
                        dataList.appendChild(element);
                    })

                } else {
                    console.log(xhr.error);
                }
            }
        }
    }

</script>
<%@include file="/pages/common/footer.jsp" %>
</body>
</html>